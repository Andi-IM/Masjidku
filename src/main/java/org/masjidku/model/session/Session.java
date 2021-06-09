/*
 * Copyright (c) 2021. Creative Commons Legal Code
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

package org.masjidku.model.session;

import com.google.common.base.Stopwatch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.masjidku.model.DaoFactory;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class Session extends DaoFactory implements SessionDao{
    // Effective Guava v15.0, this is the one way of creating a Stopwatch instance.
    final Stopwatch stopwatch = Stopwatch.createUnstarted();
    private final String TABLE = "sessions";
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
        query = "INSERT INTO " + TABLE + "(userid, timestamp) VALUES(?,?)";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, userid);
            ps.setString(2, getTimeStamp());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUserSession(String sessionId) {
        query = "UPDATE " + TABLE + " SET duration=? WHERE session_id=?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, getUserDuration());
            ps.setString(2, sessionId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserSession getSessionData(String userId){
        query = "SELECT * FROM "+TABLE+" WHERE userid=?";
        UserSession model = null;
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, userId);
            rs = ps.executeQuery();

            // latestUserData
            while (rs.next()){
                if (rs.isLast()){
                    model = new UserSession();
                    model.setSession_id(rs.getString(1));
                    model.setUserid(rs.getString(2));
                    model.setTimestamp(rs.getString(3));
                    model.setSession_long(rs.getString(4));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return model;
    }

    @Override
    public ObservableList<UserSession> getAllSessions(){
        query = "SELECT * FROM "+TABLE;
        try {
            ps = con.prepareStatement(query);
            generateList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userSessions;
    }

    @Override
    public ObservableList<UserSession> getAllSessions(String userid){
        query = "SELECT * FROM "+TABLE+" WHERE userid=?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, userid);
            generateList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userSessions;
    }

    private void generateList() throws SQLException {
        rs = ps.executeQuery();
        UserSession sessions;
        while (rs.next()){
            sessions = new UserSession();
            sessions.setSession_id(rs.getString(1));
            sessions.setUserid(rs.getString(2));
            sessions.setTimestamp(rs.getString(3));
            sessions.setSession_long(rs.getString(4));
            userSessions.add(sessions);
        }
    }
}
