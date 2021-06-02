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

package org.masjidku.util;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unused")
public final class UserSession {
    private static UserSession instance;

    private String username;
    private Set<String> privileges;

    private UserSession(String username, Set<String> privileges){
        this.username = username;
        this.privileges = privileges;
    }

    public static UserSession getInstance(String username, Set<String> privileges){
        if (instance == null ){
            instance = new UserSession(username, privileges);
        }
        return instance;
    }

    public String getUsername(){
        return username;
    }

    public Set<String> getPrivileges(){
        return privileges;
    }

    public void clearSession(){
        username = "";
        privileges = new HashSet<>();
    }

    @Override
    public String toString(){
        return "UserSession{" +
                "username='" + username + '\'' +
                ", privileges=" + privileges +
                '}';
    }
}
