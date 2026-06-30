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

package org.masjidku.util;

import com.google.common.base.Stopwatch;
import org.masjidku.domain.repository.UserSessionRepository;
import org.masjidku.model.session.UserSession;
import org.masjidku.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Singleton
public class SessionManager {
    private User currentUser;
    private final UserSessionRepository sessionRepository;
    private UserSession userSession;
    private Stopwatch stopwatch;

    private static final Logger log = LoggerFactory.getLogger(SessionManager.class);

    @Inject
    public SessionManager(UserSessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void login(User user) {
        this.currentUser = user;
        this.stopwatch = Stopwatch.createStarted();
        log.info("login on: {}", getTimeStamp());

        this.sessionRepository.logUserSession(user.getUserId(), getTimeStamp());
        this.userSession = sessionRepository.getSessionData(user.getUserId());
    }

    public void logout() {
        if (stopwatch != null && stopwatch.isRunning()) {
            stopwatch.stop();
        }

        if (userSession != null) {
            String duration = getUserDuration();
            log.info("session duration: {}", duration);
            sessionRepository.updateUserSession(userSession.session_id(), duration);
        }

        this.currentUser = null;
        this.userSession = null;
        this.stopwatch = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    private String getUserDuration() {
        if (stopwatch == null) {
            return "0 detik.";
        }

        long actualSecond = stopwatch.elapsed(TimeUnit.SECONDS);

        long hour = actualSecond / 3600;
        long minutes = (actualSecond % 3600) / 60;
        long second = actualSecond % 60;

        if (hour > 0) {
            return String.format("%d jam %d menit %d detik.", hour, minutes, second);
        }
        if (minutes > 0) {
            return String.format("%d menit %d detik.", minutes, second);
        }
        return String.format("%d detik.", second);
    }

    private String getTimeStamp() {
        LocalDateTime myDateObj = LocalDateTime.now(ZoneId.systemDefault());
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss");
        return myDateObj.format(myFormatObj);
    }
}
