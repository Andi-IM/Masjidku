package org.masjidku.domain.mapper;

import org.masjidku.domain.entity.UserEntity;
import org.masjidku.model.user.User;

public class UserMapper {

    private UserMapper() {
    }

    public static User toDomain(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        User domain = new User();
        domain.setUserId(entity.getUserId());
        domain.setPassword(entity.getPassword());
        domain.setUsername(entity.getUsername());
        domain.setJabatan(entity.getJabatan());
        domain.setStatus(entity.getStatus());
        domain.setCreated_at(entity.getCreatedAt());
        domain.setUpdated_at(entity.getUpdatedAt());
        return domain;
    }

    public static UserEntity toEntity(User domain) {
        if (domain == null) {
            return null;
        }
        UserEntity entity = new UserEntity();
        entity.setUserId(domain.getUserId());
        entity.setPassword(domain.getPassword());
        entity.setUsername(domain.getUsername());
        if (domain.getJabatan() != null) {
            entity.setJabatan(domain.getJabatan().toString());
        }
        entity.setStatus(domain.getStatus());
        entity.setCreatedAt(domain.getCreated_at());
        entity.setUpdatedAt(domain.getUpdated_at());
        return entity;
    }
}
