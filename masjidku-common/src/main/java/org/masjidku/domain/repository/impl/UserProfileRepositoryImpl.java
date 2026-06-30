package org.masjidku.domain.repository.impl;

import org.masjidku.domain.entity.UserProfileEntity;
import org.masjidku.domain.mapper.UserProfileMapper;
import org.masjidku.domain.repository.UserProfileRepository;
import org.masjidku.domain.repository.base.HibernateUtil;
import org.masjidku.model.user.UserProfile;

public class UserProfileRepositoryImpl implements UserProfileRepository {

    @Override
    public void save(UserProfile userProfile) {
        HibernateUtil.executeInTransaction(() -> {
            UserProfileEntity entity = UserProfileMapper.toEntity(userProfile);
            HibernateUtil.getSessionFactory().getCurrentSession().persist(entity);
        });
    }

    @Override
    public void update(String[] params) {
        // params[0] = notelp, params[1] = alamat, params[2] = userid
        HibernateUtil.executeInTransaction(() -> {
            org.hibernate.Session session = HibernateUtil.getSessionFactory().getCurrentSession();
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
        return HibernateUtil.executeInTransaction(() -> {
            org.hibernate.Session session = HibernateUtil.getSessionFactory().getCurrentSession();
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
