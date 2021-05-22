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

import org.masjidku.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class UserProfileDao {
    Connection con;
    PreparedStatement ps;
    private final String SQL_PROFILE_TABLE = "profil_user";
    private final String SQL_USER_TABLE = "user";

    // Constructor
    public UserProfileDao(){}

    public boolean getConnection(){
        DatabaseConnection connection = new DatabaseConnection();
        if (connection.getConnection() != null){
            con = connection.getConnection();
            return true;
        }
        return false;
    }

    /**
     * Only Admin can create User
     *
     * @throws SQLException as Error Handling
     */
    public void create(String username) throws SQLException {
        String SQL_INSERT_ADDITIONAL_INFO = "INSERT INTO " + SQL_PROFILE_TABLE + "(userid, notelp, alamat) VALUES(?,?,?)";
        ps = con.prepareStatement(SQL_INSERT_ADDITIONAL_INFO);
        ps.setString(1, username);
        ps.setString(2, null);
        ps.setString(3, null);
        ps.executeUpdate();
    }

    public void update(String userid, String notelp, String alamat) throws SQLException{
        String SQL_UPDATE_ADDITIONAL_INFO = "UPDATE " + SQL_PROFILE_TABLE + " SET notelp=?, alamat=? WHERE userid=?";
        ps = con.prepareStatement(SQL_UPDATE_ADDITIONAL_INFO);
        ps.setString(1, userid);
        ps.setString(2, notelp);
        ps.setString(3, alamat);
        ps.executeUpdate();
    }

    public User getFullUserData() throws SQLException{
        UserProfile model = new UserProfile();
        String SQL_GET_ADDITIONAL_INFO = "SELECT "+SQL_USER_TABLE+".userid, password, username, jabatan, notelp, alamat " +
                "FROM "+SQL_USER_TABLE+" INNER JOIN "+SQL_PROFILE_TABLE+" pu on "+SQL_USER_TABLE+".userid = pu.userid";
        ps = con.prepareStatement(SQL_GET_ADDITIONAL_INFO);

        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()){
            model.setUserId(resultSet.getString(1));
            model.setPassword(resultSet.getString(2));
            model.setUsername(resultSet.getString(3));
            model.setNotelp(resultSet.getString(4));
            model.setAlamat(resultSet.getString(5));
        }
        return model;
    }
}
