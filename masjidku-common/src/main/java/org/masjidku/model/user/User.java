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

package org.masjidku.model.user;

import static org.masjidku.model.user.User.Jabatan.*;

@SuppressWarnings("unused")
public class User {
    private String userId;
    private String username;
    private String password;
    private String jabatan;
    private String status;
    private String createdAt;
    private String updatedAt;

    /**
     * Constructor
     */
    public User() {
        this(null, null, "none", null, null, null);
    }

    /**
     * Filled Constructor
     *
     * @param userId    a user id
     * @param username  a username
     * @param jabatan   user role
     * @param status    user status
     * @param createdAt first time create
     * @param updatedAt after user update data.
     */
    public User(String userId, String username, String jabatan, String status, String createdAt, String updatedAt) {
        setUserId(userId);
        setUsername(username);
        setJabatan(jabatan);
        setStatus(status);
        setCreatedAt(createdAt);
        setUpdatedAt(updatedAt);
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
        return switch (this.jabatan) {
            case "admin" -> ADMIN;
            case "ketua" -> KETUA;
            case "sekretaris" -> SEKRETARIS;
            case "bendahara" -> BENDAHARA;
            default -> NONE;
        };
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public enum Jabatan {
        ADMIN("admin"),
        KETUA("ketua"),
        SEKRETARIS("sekretaris"),
        BENDAHARA("bendahara"),
        NONE("");

        private final String label;

        Jabatan(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return this.label;
        }
    }
}

