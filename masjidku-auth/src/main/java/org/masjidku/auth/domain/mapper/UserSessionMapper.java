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

import org.masjidku.auth.domain.entity.UserSessionEntity;
import org.masjidku.auth.client.model.UserSession;

public class UserSessionMapper {

    private UserSessionMapper() {
    }

    public static UserSession toDomain(UserSessionEntity entity) {
        if (entity == null) {
            return null;
        }
        return new UserSession(
            entity.getSessionId() != null ? String.valueOf(entity.getSessionId()) : null,
            entity.getUserid(),
            entity.getTimestamp(),
            entity.getDuration()
        );
    }
}
