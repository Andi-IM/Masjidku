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
import org.masjidku.model.DaoFactory;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class Session extends DaoFactory {
    // Effective Guava v15.0, this is the one way of creating a Stopwatch instance.
    final Stopwatch stopwatch = Stopwatch.createUnstarted();
    private String time_elapsed;
    private final String TABLE = "sessions";

    public Session(){
        stopwatch.start();
        System.out.println("login on: "+getTimeStamp());
    }

    public void logout(){
        stopwatch.stop();
        System.out.println(getUserDuration());
    };

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

    public void updateUserSession(String userid) {
        query = "UPDATE " + TABLE + " SET duration=? WHERE userid=?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, getUserDuration());
            ps.setString(2, userid);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
