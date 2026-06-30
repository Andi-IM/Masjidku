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

import com.google.common.hash.Hashing;
import org.hibernate.SessionFactory;
import org.masjidku.auth.client.model.User;
import org.masjidku.auth.domain.entity.UserEntity;
import org.masjidku.auth.domain.entity.UserProfileEntity;
import org.masjidku.auth.domain.mapper.UserMapper;
import org.masjidku.auth.domain.repository.UserRepository;
import org.masjidku.common.HibernateContext;
import org.masjidku.common.TransactionHelper;

import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final SessionFactory sessionFactory;
    private final TransactionHelper transactionHelper;
    private static final String NUM = "12345678";
    private static final String HEX = Hashing
            .sha256()
            .hashString(NUM, StandardCharsets.UTF_8)
            .toString();

    @Inject
    public UserRepositoryImpl(SessionFactory sessionFactory, TransactionHelper transactionHelper) {
        this.sessionFactory = sessionFactory;
        this.transactionHelper = transactionHelper;
    }

    public UserRepositoryImpl() {
        this(HibernateContext.getSessionFactory(), HibernateContext.getTransactionHelper());
    }


    @Override
    public List<User> getAll() {
        return transactionHelper.executeInTransaction(() -> {
            List<UserEntity> entities = sessionFactory.getCurrentSession()
                    .createQuery("FROM UserEntity", UserEntity.class)
                    .list();
            return entities.stream().map(UserMapper::toDomain).toList();
        });
    }

    @Override
    public void save(User user) {
        transactionHelper.executeInTransaction(() -> {
            UserEntity entity = UserMapper.toEntity(user);
            entity.setPassword(HEX);

            sessionFactory.getCurrentSession().persist(entity);

            // Generate Profile automatically
            UserProfileEntity profileEntity = new UserProfileEntity();
            profileEntity.setUserId(entity.getUserId());
            sessionFactory.getCurrentSession().persist(profileEntity);
            return null;
        });
    }

    @Override
    public void update(String[] params) {
        // params: jabatan, status, userid
        transactionHelper.executeInTransaction(() -> {
            org.hibernate.Session session = sessionFactory.getCurrentSession();
            UserEntity entity = session.get(UserEntity.class, params[2]);
            if (entity != null) {
                entity.setJabatan(params[0]);
                entity.setStatus(params[1]);
                session.merge(entity);
            }
        });
    }

    @Override
    public void update(String userid, String username, String password) {
        transactionHelper.executeInTransaction(() -> {
            org.hibernate.Session session = sessionFactory.getCurrentSession();
            UserEntity entity = session.get(UserEntity.class, userid);
            if (entity != null) {
                entity.setUsername(username);
                String hex = Hashing
                        .sha256()
                        .hashString(password, StandardCharsets.UTF_8)
                        .toString();
                entity.setPassword(hex);
                session.merge(entity);
            }
        });
    }

    @Override
    public void delete(String userid) {
        transactionHelper.executeInTransaction(() -> {
            org.hibernate.Session session = sessionFactory.getCurrentSession();
            UserEntity entity = session.get(UserEntity.class, userid);
            if (entity != null) {
                session.remove(entity);
            }
        });
    }

    @Override
    public boolean isReset(String userid) {
        return transactionHelper.executeInTransaction(() -> {
            org.hibernate.Session session = sessionFactory.getCurrentSession();
            UserEntity entity = session.get(UserEntity.class, userid);
            if (entity != null) {
                return HEX.equals(entity.getPassword());
            }
            return false;
        });
    }

    @Override
    public void reset(String userId) {
        transactionHelper.executeInTransaction(() -> {
            org.hibernate.Session session = sessionFactory.getCurrentSession();
            UserEntity entity = session.get(UserEntity.class, userId);
            if (entity != null) {
                entity.setPassword(HEX);
                session.merge(entity);
            }
        });
    }

    @Override
    public User get(String userid) {
        return transactionHelper.executeInTransaction(() -> {
            org.hibernate.Session session = sessionFactory.getCurrentSession();
            UserEntity entity = session.get(UserEntity.class, userid);
            return UserMapper.toDomain(entity);
        });
    }

    @Override
    public boolean isUserExist(String userid) {
        return transactionHelper.executeInTransaction(() -> {
            org.hibernate.Session session = sessionFactory.getCurrentSession();
            Long count = session.createQuery("SELECT count(u) FROM UserEntity u WHERE u.userId = :userid", Long.class)
                    .setParameter("userid", userid)
                    .uniqueResult();
            return count != null && count > 0;
        });
    }

    @Override
    public boolean isUserExist(String userid, String password) {
        return transactionHelper.executeInTransaction(() -> {
            org.hibernate.Session session = sessionFactory.getCurrentSession();
            Long count = session.createQuery("SELECT count(u) FROM UserEntity u WHERE u.userId = :userid AND u.password = :password", Long.class)
                    .setParameter("userid", userid)
                    .setParameter("password", password)
                    .uniqueResult();
            return count != null && count > 0;
        });
    }
}
