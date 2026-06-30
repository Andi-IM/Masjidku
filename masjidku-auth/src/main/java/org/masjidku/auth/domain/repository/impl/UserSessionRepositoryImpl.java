/*
 * Copyright (c) 2026. Creative Commons Legal Code
 *
 *                            CC0 1.0 Universal
 *
 *                                CREATIVE COMMONS CORPORATION IS NOT A LAW FIRM AND DOES NOT PROVIDE
 *                                LEGAL SERVICES. DISTRIBUTION OF THIS DOCUMENT DOES NOT CREATE AN
 *                                ATTORNEY-CLIENT RELATIONSHIP. CREATIVE COMMONS PROVIDES THIS
 *                                INFORMATION ON AN "AS-IS" BASIS. CREATIVE COMMONS MAKES NO WARRANTIES
 *                                REGARDING THE USE OF THIS DOCUMENT OR THE INFORMATION OR WORKS
 *                                PROVIDED HEREUNDER, AND DISCLAIMS LIABILITY FOR DAMAGES RESULTING FROM
 *                                THE USE OF THIS DOCUMENT OR THE INFORMATION OR WORKS PROVIDED
 *                                HEREUNDER.
 */

package org.masjidku.auth.domain.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.masjidku.auth.client.model.UserSession;
import org.masjidku.auth.domain.entity.UserSessionEntity;
import org.masjidku.auth.domain.mapper.UserSessionMapper;
import org.masjidku.auth.domain.repository.UserSessionRepository;
import org.masjidku.common.TransactionHelper;

import javax.inject.Inject;
import java.util.List;

import static org.masjidku.common.HibernateContext.getSessionFactory;
import static org.masjidku.common.HibernateContext.getTransactionHelper;

public class UserSessionRepositoryImpl implements UserSessionRepository {

    private final SessionFactory sessionFactory;
    private final TransactionHelper transactionHelper;

    @Inject
    public UserSessionRepositoryImpl(SessionFactory sessionFactory, TransactionHelper transactionHelper) {
        this.sessionFactory = sessionFactory;
        this.transactionHelper = transactionHelper;
    }

    public UserSessionRepositoryImpl() {
        this(getSessionFactory(), getTransactionHelper());
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
                    .setParameter("id", userId)
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
            return entities.stream().map(UserSessionMapper::toDomain).toList();
        });
    }

    @Override
    public List<UserSession> getAllSessions(String userid) {
        return transactionHelper.executeInTransaction(() -> {
            Session session = sessionFactory.getCurrentSession();
            List<UserSessionEntity> entities = session.createQuery("FROM UserSessionEntity u WHERE u.userid = :userId", UserSessionEntity.class)
                    .setParameter("id", userid)
                    .list();
            return entities.stream().map(UserSessionMapper::toDomain).toList();
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
