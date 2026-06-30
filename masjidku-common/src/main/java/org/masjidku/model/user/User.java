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
public record User(
        String id,
        String username,
        String password,
        String jabatan,
        String status,
        String createdAt,
        String updatedAt
) {
    /**
     * Constructor
     */
    public User() {
        this(null, null, null, "none", null, null, null);
    }

    /**
     * Filled Constructor (Without Password for backward compatibility)
     */
    public User(String userId, String username, String jabatan, String status, String createdAt, String updatedAt) {
        this(userId, username, null, jabatan != null ? jabatan.toLowerCase() : null, status, createdAt, updatedAt);
    }

    // Explicit 7-arg canonical constructor to ensure jabatan is lowercase
    public User {
        if (jabatan != null) {
            jabatan = jabatan.toLowerCase();
        }
    }

    public Jabatan getJabatan() {
        if (this.jabatan == null) return NONE;
        return switch (this.jabatan) {
            case "admin" -> ADMIN;
            case "ketua" -> KETUA;
            case "sekretaris" -> SEKRETARIS;
            case "bendahara" -> BENDAHARA;
            default -> NONE;
        };
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
