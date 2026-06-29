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

import org.masjidku.model.Dao;

import java.sql.SQLException;

public abstract class UserDaoFactory<T> extends Dao<T> {
    public abstract boolean isReset(String userid) throws SQLException;
    public abstract void reset(String userId) throws SQLException;
    public abstract boolean isUserExist(String userid) throws SQLException;
    public abstract boolean isUserExist(String userid, String password) throws SQLException;
    public abstract void update(String userid, String username, String password) throws  SQLException;
}
