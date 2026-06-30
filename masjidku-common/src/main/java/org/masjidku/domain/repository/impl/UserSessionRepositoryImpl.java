package org.masjidku.domain.repository.impl;

import org.hibernate.Session;
import org.masjidku.domain.entity.UserSessionEntity;
import org.masjidku.domain.mapper.UserSessionMapper;
import org.masjidku.domain.repository.UserSessionRepository;
import org.masjidku.domain.repository.base.HibernateUtil;
import org.masjidku.model.session.UserSession;

import java.util.List;
import java.util.stream.Collectors;

public class UserSessionRepositoryImpl implements UserSessionRepository {

    @Override
    public void logUserSession(String userid, String timestamp) {
        HibernateUtil.executeInTransaction(() -> {
            UserSessionEntity entity = new UserSessionEntity();
            entity.setUserid(userid);
            entity.setTimestamp(timestamp);
            HibernateUtil.getSessionFactory().getCurrentSession().persist(entity);
        });
    }

    @Override
    public void updateUserSession(String sessionId, String duration) {
        HibernateUtil.executeInTransaction(() -> {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            UserSessionEntity entity = session.get(UserSessionEntity.class, Integer.parseInt(sessionId));
            if (entity != null) {
                entity.setDuration(duration);
                session.merge(entity);
            }
        });
    }

    @Override
    public UserSession getSessionData(String userId) {
        return HibernateUtil.executeInTransaction(() -> {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            UserSessionEntity entity = session.createQuery(
                            "FROM UserSessionEntity u WHERE u.userid = :userId ORDER BY u.sessionId DESC", UserSessionEntity.class)
                    .setParameter("userId", userId)
                    .setMaxResults(1)
                    .uniqueResult();
            return UserSessionMapper.toDomain(entity);
        });
    }

    @Override
    public List<UserSession> getAllSessions() {
        return HibernateUtil.executeInTransaction(() -> {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            List<UserSessionEntity> entities = session.createQuery("FROM UserSessionEntity", UserSessionEntity.class).list();
            return entities.stream().map(UserSessionMapper::toDomain).collect(Collectors.toList());
        });
    }

    @Override
    public List<UserSession> getAllSessions(String userid) {
        return HibernateUtil.executeInTransaction(() -> {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            List<UserSessionEntity> entities = session.createQuery("FROM UserSessionEntity u WHERE u.userid = :userId", UserSessionEntity.class)
                    .setParameter("userId", userid)
                    .list();
            return entities.stream().map(UserSessionMapper::toDomain).collect(Collectors.toList());
        });
    }

    @Override
    public void truncateData() {
        HibernateUtil.executeInTransaction(() -> {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.createMutationQuery("DELETE FROM UserSessionEntity").executeUpdate();
        });
    }
}
