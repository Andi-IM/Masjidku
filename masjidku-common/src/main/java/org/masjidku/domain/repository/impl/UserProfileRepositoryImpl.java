package org.masjidku.domain.repository.impl;

import org.masjidku.domain.entity.UserProfileEntity;
import org.masjidku.domain.mapper.UserProfileMapper;
import org.masjidku.domain.repository.UserProfileRepository;
import org.masjidku.model.user.UserProfile;
import javax.inject.Inject;
import org.hibernate.SessionFactory;
import org.masjidku.domain.repository.base.TransactionHelper;

public class UserProfileRepositoryImpl implements UserProfileRepository {

    private final SessionFactory sessionFactory;
    private final TransactionHelper transactionHelper;

    @Inject
    public UserProfileRepositoryImpl(SessionFactory sessionFactory, TransactionHelper transactionHelper) {
        this.sessionFactory = sessionFactory;
        this.transactionHelper = transactionHelper;
    }

    public UserProfileRepositoryImpl() {
        this(org.masjidku.domain.repository.base.HibernateContext.getSessionFactory(), org.masjidku.domain.repository.base.HibernateContext.getTransactionHelper());
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

    @Override
    public boolean getConnection() {
        return true;
    }
}
