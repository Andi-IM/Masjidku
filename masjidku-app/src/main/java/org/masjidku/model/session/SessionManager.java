package org.masjidku.model.session;
import org.masjidku.model.user.User;
import java.util.logging.Logger;
public class SessionManager {
    private static SessionManager instance;
    private User currentUser;
    private Session sessionDb;
    private UserSession userSession;
    private static final Logger LOGGER = Logger.getLogger(SessionManager.class.getName());
    private SessionManager() {}
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }
    public void login(User user) {
        this.currentUser = user;
        this.sessionDb = new Session();
        this.sessionDb.getConnection();
        this.sessionDb.logUserSession(user.getUserId());
        this.userSession = sessionDb.getSessionData(user.getUserId());
    }
    public void logout() {
        if (sessionDb != null && userSession != null) {
            sessionDb.logout();
            sessionDb.updateUserSession(userSession.getSession_id());
        }
        this.currentUser = null;
        this.sessionDb = null;
        this.userSession = null;
    }
    public User getCurrentUser() {
        return currentUser;
    }
}
