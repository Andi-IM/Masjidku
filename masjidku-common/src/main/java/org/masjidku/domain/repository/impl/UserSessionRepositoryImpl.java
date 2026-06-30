package org.masjidku.domain.repository.impl;

import org.hibernate.Session;
import org.masjidku.domain.entity.UserSessionEntity;
import org.masjidku.domain.mapper.UserSessionMapper;
import org.masjidku.domain.repository.UserSessionRepository;
import org.masjidku.model.session.UserSession;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.hibernate.SessionFactory;
import org.masjidku.domain.repository.base.TransactionHelper;

public class UserSessionRepositoryImpl implements UserSessionRepository {

    private final SessionFactory sessionFactory;
    private final TransactionHelper transactionHelper;

    @Inject
    public UserSessionRepositoryImpl(SessionFactory sessionFactory, TransactionHelper transactionHelper) {
        this.sessionFactory = sessionFactory;
        this.transactionHelper = transactionHelper;
    }

    public UserSessionRepositoryImpl() {
        this(org.masjidku.domain.repository.base.HibernateContext.getSessionFactory(), org.masjidku.domain.repository.base.HibernateContext.getTransactionHelper());
    }



    @Override
    public void logUserSession(String userid, String timestamp) {
        transactionHelper.executeInTransaction(() -> {
            UserSessionEntity entity = new UserSessionEntity();
            entity.setUserid(userid);
            entity.setTimestamp(timestamp);
            sessionFactory.getCurrentSession().persist(entity);
        });
    }

    @Override
    public void updateUserSession(String sessionId, String duration) {
        transactionHelper.executeInTransaction(() -> {
            Session session = sessionFactory.getCurrentSession();
            UserSessionEntity entity = session.get(UserSessionEntity.class, Integer.parseInt(sessionId));
            if (entity != null) {
                entity.setDuration(duration);
                session.merge(entity);
            }
        });
    }

    @Override
    public UserSession getSessionData(String userId) {
        return transactionHelper.executeInTransaction(() -> {
            Session session = sessionFactory.getCurrentSession();
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
        return transactionHelper.executeInTransaction(() -> {
            Session session = sessionFactory.getCurrentSession();
            List<UserSessionEntity> entities = session.createQuery("FROM UserSessionEntity", UserSessionEntity.class).list();
            return entities.stream().map(UserSessionMapper::toDomain).collect(Collectors.toList());
        });
    }

    @Override
    public List<UserSession> getAllSessions(String userid) {
        return transactionHelper.executeInTransaction(() -> {
            Session session = sessionFactory.getCurrentSession();
            List<UserSessionEntity> entities = session.createQuery("FROM UserSessionEntity u WHERE u.userid = :userId", UserSessionEntity.class)
                    .setParameter("userId", userid)
                    .list();
            return entities.stream().map(UserSessionMapper::toDomain).collect(Collectors.toList());
        });
    }

    @Override
    public void truncateData() {
        transactionHelper.executeInTransaction(() -> {
            Session session = sessionFactory.getCurrentSession();
            session.createMutationQuery("DELETE FROM UserSessionEntity").executeUpdate();
        });
    }
}
