package org.masjidku.model.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Stopwatch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.masjidku.model.DaoFactory;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class Session extends DaoFactory implements SessionDao{
    private static final Logger log = LoggerFactory.getLogger(Session.class);
    final Stopwatch stopwatch = Stopwatch.createUnstarted();
    
    private static final String INSERT_QUERY = "INSERT INTO sessions (userid, timestamp) VALUES(?,?)";
    private static final String UPDATE_QUERY = "UPDATE sessions SET duration=? WHERE session_id=?";
    private static final String SELECT_LATEST_QUERY = "SELECT * FROM sessions WHERE userid=? ORDER BY session_id DESC LIMIT 1";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM sessions";
    private static final String SELECT_BY_USER_QUERY = "SELECT * FROM sessions WHERE userid=?";
    private static final String TRUNCATE_QUERY = "DELETE FROM sessions";

    private final ObservableList<UserSession> userSessions = FXCollections.observableArrayList();

    public Session(){
        stopwatch.start();
        System.out.println("login on: "+getTimeStamp());
    }

    public void logout(){
        stopwatch.stop();
        System.out.println(getUserDuration());
    }

    private String getUserDuration(){
        long actualSecond = stopwatch.elapsed(TimeUnit.SECONDS);

        long hour = actualSecond / 3600;
        long minutes = actualSecond % 3600 / 60;
        long second = actualSecond % 60;

        if (hour > 0 ) {
            return hour +" jam "+ minutes+" menit"+second+" detik.";
        }
        if (minutes > 0){
            return minutes+" menit "+second+" detik.";
        }
        return second+" detik.";
    }

    private String getTimeStamp(){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss");
        return myDateObj.format(myFormatObj);
    }

    @Override
    public void logUserSession(String userid) {
        try {
            ps = con.prepareStatement(INSERT_QUERY);
            ps.setString(1, userid);
            ps.setString(2, getTimeStamp());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            log.error("An error occurred", e);
        }
    }

    @Override
    public void updateUserSession(String sessionId) {
        try {
            ps = con.prepareStatement(UPDATE_QUERY);
            ps.setString(1, getUserDuration());
            ps.setString(2, sessionId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            log.error("An error occurred", e);
        }
    }

    @Override
    public UserSession getSessionData(String userId){
        UserSession model = null;
        try {
            ps = con.prepareStatement(SELECT_LATEST_QUERY);
            ps.setString(1, userId);
            rs = ps.executeQuery();

            while (rs.next()){
                model = new UserSession();
                model.setSession_id(rs.getString(1));
                model.setUserid(rs.getString(2));
                model.setTimestamp(rs.getString(3));
                model.setDuration(rs.getString(4));
            }
            rs.close();
            ps.close();

        } catch (SQLException throwables) {
            log.error("An error occurred", throwables);
        }
        return model;
    }

    @Override
    public ObservableList<UserSession> getAllSessions(){
        try {
            ps = con.prepareStatement(SELECT_ALL_QUERY);
            generateList();
            ps.close();
        } catch (SQLException e) {
            log.error("An error occurred", e);
        }
        return userSessions;
    }

    @Override
    public ObservableList<UserSession> getAllSessions(String userid){
        try {
            ps = con.prepareStatement(SELECT_BY_USER_QUERY);
            ps.setString(1, userid);
            generateList();
            ps.close();
        } catch (SQLException e) {
            log.error("An error occurred", e);
        }
        return userSessions;
    }

    @SuppressWarnings("SqlWithoutWhere")
    public void truncateData() {
        try {
            ps = con.prepareStatement(TRUNCATE_QUERY);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            log.error("An error occurred", e);
        }
    }
    private void generateList() throws SQLException {
        rs = ps.executeQuery();
        UserSession sessions;
        while (rs.next()){
            sessions = new UserSession();
            sessions.setSession_id(rs.getString(1));
            sessions.setUserid(rs.getString(2));
            sessions.setTimestamp(rs.getString(3));
            sessions.setDuration(rs.getString(4));
            userSessions.add(sessions);
        }
        rs.close();
    }
}
