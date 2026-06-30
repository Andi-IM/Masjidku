package org.masjidku.model.session.dao;

import com.google.common.base.Stopwatch;
import org.masjidku.domain.repository.UserSessionRepository;
import org.masjidku.domain.repository.impl.UserSessionRepositoryImpl;
import org.masjidku.model.session.UserSession;
import org.masjidku.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public enum SessionManager {
    INSTANCE;

    private User currentUser;
    private final UserSessionRepository sessionRepository = new UserSessionRepositoryImpl();
    private UserSession userSession;
    private Stopwatch stopwatch;

    private static final Logger log = LoggerFactory.getLogger(SessionManager.class);

    public static SessionManager getInstance() {
        return INSTANCE;
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
            sessionRepository.updateUserSession(userSession.getSession_id(), duration);
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
