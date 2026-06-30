package org.masjidku.domain.mapper;

import org.masjidku.domain.entity.UserSessionEntity;
import org.masjidku.model.session.UserSession;

public class UserSessionMapper {

    private UserSessionMapper() {}

    public static UserSession toDomain(UserSessionEntity entity) {
        if (entity == null) {
            return null;
        }
        UserSession domain = new UserSession();
        domain.setSession_id(entity.getSessionId() != null ? String.valueOf(entity.getSessionId()) : null);
        domain.setUserid(entity.getUserid());
        domain.setTimestamp(entity.getTimestamp());
        domain.setDuration(entity.getDuration());
        return domain;
    }

    public static UserSessionEntity toEntity(UserSession domain) {
        if (domain == null) {
            return null;
        }
        UserSessionEntity entity = new UserSessionEntity();
        if (domain.getSession_id() != null && !domain.getSession_id().isEmpty()) {
            entity.setSessionId(Integer.parseInt(domain.getSession_id()));
        }
        entity.setUserid(domain.getUserid());
        entity.setTimestamp(domain.getTimestamp());
        entity.setDuration(domain.getDuration());
        return entity;
    }
}
