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
        ps.setString(1, userProfile.getUser().getUserId());
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

    public UserProfile getFullUserData(String userid) throws SQLException {
        UserProfile model = null;
        User user;
        query = "SELECT user.userid, password, username, jabatan, status, notelp, alamat, created_at, updated_at " +
                "FROM profil_user pu LEFT JOIN user ON pu.userid = user.userid " +
                "WHERE pu.userid=?";
        ps = con.prepareStatement(query);
        ps.setString(1, userid);

        rs = ps.executeQuery();
        if (rs.next()) {
            model = new UserProfile();
            user = new User();

            user.setUserId(rs.getString("user.userid"));
            user.setPassword(rs.getString("password"));
            user.setUsername(rs.getString("username"));
            user.setJabatan(rs.getString("jabatan"));
            user.setStatus(rs.getString("status"));
            user.setCreated_at(rs.getString("created_at"));
            user.setUpdated_at(rs.getString("updated_at"));

            model.setUser(user);
            model.setNotelp(rs.getString("notelp"));
            model.setAlamat(rs.getString("alamat"));
        }
        return model;
    }
}
