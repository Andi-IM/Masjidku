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

package org.masjidku.auth.client;


import org.masjidku.auth.client.model.User;
import org.masjidku.auth.client.dto.UpdateUserCredentialsDto;
import org.masjidku.auth.client.dto.UpdateUserProfileDto;
import org.masjidku.auth.client.dto.UpdateUserStatusDto;
import org.masjidku.auth.client.model.UserProfile;
import org.masjidku.auth.client.model.UserSession;

import java.util.List;

public interface AuthClient {
    // UserRepository operations
    List<User> getAllUsers();
    void saveUser(User user);
    void updateUser(UpdateUserStatusDto dto);
    void updateUser(UpdateUserCredentialsDto dto);
    void deleteUser(String userid);
    boolean isUserReset(String userid);
    void resetUser(String userId);
    User getUser(String userid);
    boolean isUserExist(String userid);
    boolean isUserExist(String userid, String password);

    // UserProfileRepository operations
    void updateUserProfile(UpdateUserProfileDto dto);
    UserProfile getFullUserData(String userid);

    // UserSessionRepository operations
    void logUserSession(String userid, String timestamp);
    void updateUserSession(String sessionId, String duration);
    UserSession getSessionData(String userId);
    List<UserSession> getAllSessions();
    void truncateSessionData();
}