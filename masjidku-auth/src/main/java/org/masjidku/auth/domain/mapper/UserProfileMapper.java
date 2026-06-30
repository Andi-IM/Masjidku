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

package org.masjidku.auth.domain.mapper;

import org.masjidku.auth.domain.entity.UserProfileEntity;
import org.masjidku.auth.client.model.UserProfile;

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
