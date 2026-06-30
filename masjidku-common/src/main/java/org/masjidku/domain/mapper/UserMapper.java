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
        return new User(
                entity.getUserId(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getJabatan(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public static UserEntity toEntity(User domain) {
        if (domain == null) {
            return null;
        }
        UserEntity entity = new UserEntity();
        entity.setUserId(domain.id());
        entity.setPassword(domain.password());
        entity.setUsername(domain.username());
        if (domain.jabatan() != null) {
            entity.setJabatan(domain.jabatan());
        }
        entity.setStatus(domain.status());
        entity.setCreatedAt(domain.createdAt());
        entity.setUpdatedAt(domain.updatedAt());
        return entity;
    }
}
