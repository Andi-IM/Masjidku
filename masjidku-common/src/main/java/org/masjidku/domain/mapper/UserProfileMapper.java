package org.masjidku.domain.mapper;

import org.masjidku.domain.entity.UserProfileEntity;
import org.masjidku.model.user.UserProfile;

public class UserProfileMapper {

    private UserProfileMapper(){}

    public static UserProfile toDomain(UserProfileEntity entity) {
        if (entity == null) {
            return null;
        }
        UserProfile domain = new UserProfile();
        domain.setAlamat(entity.getAlamat());
        domain.setNotelp(entity.getNotelp());
        if (entity.getUser() != null) {
            domain.setUser(UserMapper.toDomain(entity.getUser()));
        }
        return domain;
    }

    public static UserProfileEntity toEntity(UserProfile domain) {
        if (domain == null) {
            return null;
        }
        UserProfileEntity entity = new UserProfileEntity();
        if (domain.getUser() != null) {
            entity.setUserId(domain.getUser().getUserId());
        }
        entity.setAlamat(domain.getAlamat());
        entity.setNotelp(domain.getNotelp());
        return entity;
    }
}
