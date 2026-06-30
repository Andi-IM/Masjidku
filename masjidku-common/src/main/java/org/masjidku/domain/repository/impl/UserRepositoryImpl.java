package org.masjidku.domain.repository.impl;

import com.google.common.hash.Hashing;
import org.masjidku.domain.entity.UserEntity;
import org.masjidku.domain.entity.UserProfileEntity;
import org.masjidku.domain.mapper.UserMapper;
import org.masjidku.domain.repository.UserRepository;
import org.masjidku.domain.repository.base.HibernateUtil;
import org.masjidku.model.user.User;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public List<User> getAll() {
        return HibernateUtil.executeInTransaction(() -> {
            List<UserEntity> entities = HibernateUtil.getSessionFactory().getCurrentSession()
                    .createQuery("FROM UserEntity", UserEntity.class)
                    .list();
            return entities.stream().map(UserMapper::toDomain).collect(Collectors.toList());
        });
    }

    @Override
    public void save(User user) {
        HibernateUtil.executeInTransaction(() -> {
            UserEntity entity = UserMapper.toEntity(user);
            
            String hex = Hashing
                    .sha256()
                    .hashString("12345678", StandardCharsets.UTF_8)
                    .toString();
            entity.setPassword(hex);
            
            HibernateUtil.getSessionFactory().getCurrentSession().persist(entity);

            // Generate Profile automatically
            UserProfileEntity profileEntity = new UserProfileEntity();
            profileEntity.setUserId(entity.getUserId());
            HibernateUtil.getSessionFactory().getCurrentSession().persist(profileEntity);
            return null;
        });
    }

    @Override
    public void update(String[] params) {
        // params: jabatan, status, userid
        HibernateUtil.executeInTransaction(() -> {
            org.hibernate.Session session = HibernateUtil.getSessionFactory().getCurrentSession();
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
        HibernateUtil.executeInTransaction(() -> {
            org.hibernate.Session session = HibernateUtil.getSessionFactory().getCurrentSession();
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
        HibernateUtil.executeInTransaction(() -> {
            org.hibernate.Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            UserEntity entity = session.get(UserEntity.class, userid);
            if (entity != null) {
                session.remove(entity);
            }
        });
    }

    @Override
    public boolean isReset(String userid) {
        return HibernateUtil.executeInTransaction(() -> {
            org.hibernate.Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            UserEntity entity = session.get(UserEntity.class, userid);
            if (entity != null) {
                String hex = Hashing
                        .sha256()
                        .hashString("12345678", StandardCharsets.UTF_8)
                        .toString();
                return hex.equals(entity.getPassword());
            }
            return false;
        });
    }

    @Override
    public void reset(String userId) {
        HibernateUtil.executeInTransaction(() -> {
            org.hibernate.Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            UserEntity entity = session.get(UserEntity.class, userId);
            if (entity != null) {
                String hex = Hashing
                        .sha256()
                        .hashString("12345678", StandardCharsets.UTF_8)
                        .toString();
                entity.setPassword(hex);
                session.merge(entity);
            }
        });
    }

    @Override
    public User get(String userid) {
        return HibernateUtil.executeInTransaction(() -> {
            org.hibernate.Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            UserEntity entity = session.get(UserEntity.class, userid);
            return UserMapper.toDomain(entity);
        });
    }

    @Override
    public boolean isUserExist(String userid) {
        return HibernateUtil.executeInTransaction(() -> {
            org.hibernate.Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Long count = session.createQuery("SELECT count(u) FROM UserEntity u WHERE u.userId = :userid", Long.class)
                    .setParameter("userid", userid)
                    .uniqueResult();
            return count != null && count > 0;
        });
    }

    @Override
    public boolean isUserExist(String userid, String password) {
        return HibernateUtil.executeInTransaction(() -> {
            org.hibernate.Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Long count = session.createQuery("SELECT count(u) FROM UserEntity u WHERE u.userId = :userid AND u.password = :password", Long.class)
                    .setParameter("userid", userid)
                    .setParameter("password", password)
                    .uniqueResult();
            return count != null && count > 0;
        });
    }

    @Override
    public boolean getConnection() {
        return true;
    }
}
