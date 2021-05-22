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

package org.masjidku.model;

import static org.masjidku.model.Jabatan.*;

@SuppressWarnings("unused")
public class User {
    private String userId;
    private String username;
    private String password;
    private String jabatan;
    private String status;
    private String created_at;
    private String updated_at;

    /**
     * Constructor
     */
    public User() { this(null, null, "none", null, null, null); }

    /**
     * Filled Constructor
     * @param userId an user id
     * @param username an user name
     * @param jabatan user role
     * @param status user status
     * @param created_at first time create
     * @param updated_at after user update data.
     */
    public User(String userId, String username, String jabatan, String status, String created_at, String updated_at) {
        setUserId(userId);
        setUsername(username);
        setJabatan(jabatan);
        setStatus(status);
        setCreated_at(created_at);
        setUpdated_at(updated_at);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Jabatan getJabatan() {
        switch (this.jabatan) {
            case "admin":
                return admin;
            case "ketua":
                return ketua;
            case "sekretaris":
                return sekretaris;
            case "bendahara":
                return bendahara;
            default:
                return none;
        }
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan.toLowerCase();
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}