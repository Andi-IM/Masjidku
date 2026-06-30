package org.masjidku.domain.mapper;

import org.masjidku.domain.entity.UserProfileEntity;
import org.masjidku.model.user.UserProfile;

public class UserProfileMapper {

    private UserProfileMapper(){}

    public static UserProfile toDomain(UserProfileEntity entity) {
        if (entity == null) {
            return null;
        }
        return new UserProfile(
            entity.getUser() != null ? UserMapper.toDomain(entity.getUser()) : null,
            entity.getAlamat(),
            entity.getNotelp()
        );
    }

    public static UserProfileEntity toEntity(UserProfile domain) {
        if (domain == null) {
            return null;
        }
        UserProfileEntity entity = new UserProfileEntity();
        if (domain.user() != null) {
            entity.setUserId(domain.user().id());
        }
        entity.setAlamat(domain.alamat());
        entity.setNotelp(domain.notelp());
        return entity;
    }
}
