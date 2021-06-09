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
import javafx.collections.ObservableList;
import org.masjidku.model.Dao;
import org.masjidku.model.DaoFactory;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class Session extends DaoFactory<Session> {
    // Effective Guava v15.0, this is the one way of creating a Stopwatch instance.
    final Stopwatch stopwatch = Stopwatch.createStarted();
    private String time_elapsed;

    public Session(){
        stopwatch.start();
    }

    public void logout(){
        stopwatch.stop();
    };

    private String getUserlongSession(){
        long hour = stopwatch.elapsed(TimeUnit.HOURS);
        long minutes = stopwatch.elapsed(TimeUnit.MINUTES);
        long second = stopwatch.elapsed(TimeUnit.SECONDS);

        return null;
    }

//        //Effective Guava v15.0, this is the one way of creating a Stopwatch instance.
//        final Stopwatch stopwatch = Stopwatch.createStarted();
//
//        //Sleep for few random milliseconds.
//        try {
//            Thread.sleep(new Random().nextInt(1000));
//        } catch (final InterruptedException interruptedException) {
//            interruptedException.printStackTrace();
//        }
//
//        stopwatch.stop(); //optional
//
//        System.out.println("Elapsed time ==> " + stopwatch);
//        System.out.println("Elapsed time in Nanoseconds ==> " + stopwatch.elapsed(TimeUnit.NANOSECONDS));
//        System.out.println("Elapsed time in Microseconds ==> " + stopwatch.elapsed(TimeUnit.MICROSECONDS));
//        System.out.println("Elapsed time in Milliseconds ==> " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
//        System.out.println("Elapsed time in Seconds ==> " + stopwatch.elapsed(TimeUnit.SECONDS));
//        //System.out.println("Elapsed time in Minutes ==> " + stopwatch.elapsed(TimeUnit.MINUTES));

}
