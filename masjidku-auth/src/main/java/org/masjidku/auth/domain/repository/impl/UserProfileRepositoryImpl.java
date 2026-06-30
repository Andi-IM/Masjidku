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

import org.hibernate.SessionFactory;
import org.masjidku.auth.client.model.UserProfile;
import org.masjidku.auth.domain.entity.UserProfileEntity;
import org.masjidku.auth.domain.mapper.UserProfileMapper;
import org.masjidku.auth.domain.repository.UserProfileRepository;
import org.masjidku.common.TransactionHelper;

import javax.inject.Inject;

import static org.masjidku.common.HibernateContext.getSessionFactory;
import static org.masjidku.common.HibernateContext.getTransactionHelper;

public class UserProfileRepositoryImpl implements UserProfileRepository {

    private final SessionFactory sessionFactory;
    private final TransactionHelper transactionHelper;

    @Inject
    public UserProfileRepositoryImpl(SessionFactory sessionFactory, TransactionHelper transactionHelper) {
        this.sessionFactory = sessionFactory;
        this.transactionHelper = transactionHelper;
    }

    public UserProfileRepositoryImpl() {
        this(getSessionFactory(), getTransactionHelper());
    }


    @Override
    public void save(UserProfile userProfile) {
        transactionHelper.executeInTransaction(() -> {
            UserProfileEntity entity = UserProfileMapper.toEntity(userProfile);
            sessionFactory.getCurrentSession().persist(entity);
        });
    }

    @Override
    public void update(String[] params) {
        // params[0] = notelp, params[1] = alamat, params[2] = userid
        transactionHelper.executeInTransaction(() -> {
            org.hibernate.Session session = sessionFactory.getCurrentSession();
            UserProfileEntity entity = session.get(UserProfileEntity.class, params[2]);
            if (entity != null) {
                entity.setNotelp(params[0]);
                entity.setAlamat(params[1]);
                session.merge(entity);
            } else {
                entity = new UserProfileEntity();
                entity.setUserId(params[2]);
                entity.setNotelp(params[0]);
                entity.setAlamat(params[1]);
                session.persist(entity);
            }
        });
    }

    @Override
    public UserProfile getFullUserData(String userid) {
        return transactionHelper.executeInTransaction(() -> {
            org.hibernate.Session session = sessionFactory.getCurrentSession();
            UserProfileEntity entity = session.createQuery(
                            "SELECT p FROM UserProfileEntity p LEFT JOIN FETCH p.user WHERE p.userId = :userid", UserProfileEntity.class)
                    .setParameter("userid", userid)
                    .uniqueResult();
            return UserProfileMapper.toDomain(entity);
        });
    }
}
