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

public final class UserSession {
    private String session_id;
    private String userid;
    private String timestamp;
    private String duration;

    public UserSession(){ }

    public UserSession(String session_id, String userid, String timestamp, String duration) {
        setSession_id(session_id);
        setDuration(duration);
        setTimestamp(timestamp);
        setUserid(userid);

    }

    public String getSession_id() {
        return session_id;
    }

    public String getUserid() {
        return userid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getDuration() {
        return duration;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
