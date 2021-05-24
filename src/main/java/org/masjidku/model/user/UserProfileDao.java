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

import javafx.collections.ObservableList;
import org.masjidku.model.Dao;
import org.masjidku.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class UserProfileDao implements Dao<UserProfile> {
    Connection con;
    PreparedStatement ps;
    private final String SQL_PROFILE_TABLE = "profil_user";
    private final String SQL_USER_TABLE = "user";

    // Constructor
    public UserProfileDao() {
    }

    public boolean getConnection() {
        DatabaseConnection connection = new DatabaseConnection();
        if (connection.getConnection() != null) {
            con = connection.getConnection();
            return true;
        }
        return false;
    }

    @Override
    public UserProfile get(String id) throws SQLException {
        return null;
    }

    @Override
    public ObservableList<UserProfile> getAll() throws SQLException {
        return null;
    }

    /**
     * Only Admin can create User
     *
     * @throws SQLException as Error Handling
     */
    @Override
    public void save(UserProfile userProfile) throws SQLException {
        String insertQuery = "INSERT INTO " + SQL_PROFILE_TABLE + "(userid, notelp, alamat) VALUES(?,?,?)";
        ps = con.prepareStatement(insertQuery);
        ps.setString(1, userProfile.getUserId());
        ps.setString(2, userProfile.getAlamat());
        ps.setString(3, userProfile.getNotelp());
        ps.executeUpdate();
    }

    @Override
    public void update(String[] params) throws SQLException {
        String updateQuery = "UPDATE " + SQL_PROFILE_TABLE + " SET notelp=?, alamat=? WHERE userid=?";
        ps = con.prepareStatement(updateQuery);
        ps.setString(1, params[0]);
        ps.setString(2, params[1]);
        ps.setString(3, params[2]);
        ps.executeUpdate();
    }

    @Override
    public void delete(String id) throws SQLException {
    }

    public User getFullUserData() throws SQLException {
        UserProfile model = new UserProfile();
        String SQL_GET_ADDITIONAL_INFO = "SELECT " + SQL_USER_TABLE + ".userid, password, username, jabatan, notelp, alamat " +
                "FROM " + SQL_USER_TABLE + " INNER JOIN " + SQL_PROFILE_TABLE + " pu on " + SQL_USER_TABLE + ".userid = pu.userid";
        ps = con.prepareStatement(SQL_GET_ADDITIONAL_INFO);

        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            model.setUserId(resultSet.getString(1));
            model.setPassword(resultSet.getString(2));
            model.setUsername(resultSet.getString(3));
            model.setNotelp(resultSet.getString(4));
            model.setAlamat(resultSet.getString(5));
        }
        return model;
    }
}
