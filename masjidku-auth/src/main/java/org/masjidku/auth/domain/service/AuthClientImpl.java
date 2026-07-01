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

package org.masjidku.auth.domain.service;

import org.hibernate.SessionFactory;
import org.masjidku.auth.client.AuthClient;
import org.masjidku.auth.client.dto.UpdateUserCredentialsDto;
import org.masjidku.auth.client.dto.UpdateUserProfileDto;
import org.masjidku.auth.client.dto.UpdateUserStatusDto;
import org.masjidku.auth.client.model.User;
import org.masjidku.auth.client.model.UserProfile;
import org.masjidku.auth.client.model.UserSession;
import org.masjidku.auth.domain.repository.UserProfileRepository;
import org.masjidku.auth.domain.repository.UserRepository;
import org.masjidku.auth.domain.repository.UserSessionRepository;
import org.masjidku.auth.domain.repository.impl.UserProfileRepositoryImpl;
import org.masjidku.auth.domain.repository.impl.UserRepositoryImpl;
import org.masjidku.auth.domain.repository.impl.UserSessionRepositoryImpl;
import org.masjidku.common.HibernateContext;
import org.masjidku.common.TransactionHelper;

import javax.inject.Inject;
import java.util.List;

public class AuthClientImpl implements AuthClient {
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final UserSessionRepository userSessionRepository;

    @Inject
    public AuthClientImpl(UserRepository userRepository,
                          UserProfileRepository userProfileRepository,
                          UserSessionRepository userSessionRepository) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
        this.userSessionRepository = userSessionRepository;
    }

    public AuthClientImpl(SessionFactory sessionFactory, TransactionHelper transactionHelper) {
        this.userRepository = new UserRepositoryImpl(sessionFactory, transactionHelper);
        this.userProfileRepository = new UserProfileRepositoryImpl(sessionFactory, transactionHelper);
        this.userSessionRepository = new UserSessionRepositoryImpl(sessionFactory, transactionHelper);
    }

    public AuthClientImpl() {
        this(HibernateContext.getSessionFactory(), HibernateContext.getTransactionHelper());
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAll();
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(UpdateUserStatusDto dto) {
        userRepository.update(dto);
    }

    @Override
    public void updateUser(UpdateUserCredentialsDto dto) {
        userRepository.update(dto);
    }

    @Override
    public void deleteUser(String userid) {
        userRepository.delete(userid);
    }

    @Override
    public boolean isUserReset(String userid) {
        return userRepository.isReset(userid);
    }

    @Override
    public void resetUser(String userId) {
        userRepository.reset(userId);
    }

    @Override
    public User getUser(String userid) {
        return userRepository.get(userid);
    }

    @Override
    public boolean isUserExist(String userid) {
        return userRepository.isUserExist(userid);
    }

    @Override
    public boolean isUserExist(String userid, String password) {
        return userRepository.isUserExist(userid, password);
    }

    @Override
    public void updateUserProfile(UpdateUserProfileDto dto) {
        userProfileRepository.update(dto);
    }

    @Override
    public UserProfile getFullUserData(String userid) {
        return userProfileRepository.getFullUserData(userid);
    }

    @Override
    public void logUserSession(String userid, String timestamp) {
        userSessionRepository.logUserSession(userid, timestamp);
    }

    @Override
    public void updateUserSession(String sessionId, String duration) {
        userSessionRepository.updateUserSession(sessionId, duration);
    }

    @Override
    public UserSession getSessionData(String userId) {
        return userSessionRepository.getSessionData(userId);
    }

    @Override
    public List<UserSession> getAllSessions() {
        return userSessionRepository.getAllSessions();
    }

    @Override
    public void truncateSessionData() {
        userSessionRepository.truncateData();
    }
}
