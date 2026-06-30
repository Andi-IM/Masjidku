package org.masjidku.domain.mapper;

import org.masjidku.domain.entity.UserSessionEntity;
import org.masjidku.model.session.UserSession;

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
