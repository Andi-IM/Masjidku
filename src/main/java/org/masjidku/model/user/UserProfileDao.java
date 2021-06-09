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
import org.masjidku.model.DaoFactory;

import java.sql.SQLException;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class UserProfileDao extends DaoFactory {

    private final String PROFILE_TABLE = "profil_user";
    private final String USER_TABLE = "user";

    /**
     * Only Admin can create User
     *
     * @throws SQLException as Error Handling
     */
    public void save(UserProfile userProfile) throws SQLException {
        query = "INSERT INTO " + PROFILE_TABLE + "(userid, notelp, alamat) VALUES(?,?,?)";
        ps = con.prepareStatement(query);
        ps.setString(1, userProfile.getUserId());
        ps.setString(2, userProfile.getAlamat());
        ps.setString(3, userProfile.getNotelp());
        ps.executeUpdate();
    }

    public void update(String[] params) throws SQLException {
        query = "UPDATE " + PROFILE_TABLE + " SET notelp=?, alamat=? WHERE userid=?";
        ps = con.prepareStatement(query);
        ps.setString(1, params[0]);
        ps.setString(2, params[1]);
        ps.setString(3, params[2]);
        ps.executeUpdate();
    }

    public User getFullUserData(String userid) throws SQLException {
        UserProfile model = new UserProfile();
        query = "SELECT " + USER_TABLE + ".userid, password, username, jabatan, notelp, alamat " +
                "FROM " + USER_TABLE + " INNER JOIN " + PROFILE_TABLE + " pu on " + USER_TABLE + ".userid = pu.userid " +
                "WHERE "+USER_TABLE+".userid=?";
        ps = con.prepareStatement(query);
        ps.setString(1, userid);

        rs = ps.executeQuery();
        if (rs.next()) {
            model.setUserId(rs.getString(1));
            model.setPassword(rs.getString(2));
            model.setUsername(rs.getString(3));
            model.setJabatan(rs.getString(4));
            model.setNotelp(rs.getString(5));
            model.setAlamat(rs.getString(6));
        }
        return model;
    }
}
