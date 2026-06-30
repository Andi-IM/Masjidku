package org.masjidku.domain.repository;

import org.masjidku.model.session.UserSession;

import java.util.List;

public interface UserSessionRepository {
    void logUserSession(String userid, String timestamp);
    void updateUserSession(String sessionId, String duration);
    UserSession getSessionData(String userId);
    List<UserSession> getAllSessions();
    List<UserSession> getAllSessions(String userid);
    void truncateData();
}
