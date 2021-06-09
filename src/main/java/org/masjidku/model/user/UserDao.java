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

import com.google.common.hash.Hashing;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.masjidku.model.Dao;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

@SuppressWarnings("unused")
public class UserDao extends Dao<User> {

    private final String TABLE = "user";

    /**
     * Only Admin can see this
     *
     * @throws SQLException as Error Handling
     */
    @Override
    public ObservableList<User> getAll() throws SQLException {
        ObservableList<User> users = FXCollections.observableArrayList();

        query = "SELECT userid, username, jabatan, status, created_at, updated_at FROM " + TABLE;
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        User user;
        while (rs.next()) {
            user = new User();
            user.setUserId(rs.getString(1));
            user.setUsername(rs.getString(2));
            user.setJabatan(rs.getString(3));
            user.setStatus(rs.getString(4));
            user.setCreated_at(rs.getString(5));
            user.setUpdated_at(rs.getString(6));
            users.add(user);
        }
        return users;
    }

    /**
     * Only Admin can create User
     *
     * @throws SQLException as Error Handling
     */
    @Override
    public void save(User user) throws SQLException {
        query = "INSERT INTO " + TABLE + "(userid, password, username, jabatan, status) VALUES(?,?,?,?,?)";
        ps = con.prepareStatement(query);
        ps.setString(1, user.getUserId());

        @SuppressWarnings("UnstableApiUsage")
        String hex = Hashing
                .sha256()
                .hashString("12345678", StandardCharsets.UTF_8)
                .toString();

        ps.setString(2, hex);
        ps.setString(3, user.getUsername());
        ps.setString(4, user.getJabatan().toString);
        ps.setString(5, user.getStatus());
        ps.executeUpdate();

        generateProfile(user);
    }

    /**
     * Generating user profile
     * @param user user Object
     * @throws SQLException error handling
     */
    private void generateProfile(User user) throws SQLException {
        UserProfileDao dao = new UserProfileDao();
        dao.getConnection();
        UserProfile profile = new UserProfile(user);
        dao.save(profile);
    }

    /**
     * Updating user preference by admin
     *
     * @param params the parameter
     * @throws SQLException as Error Handling.
     */
    @Override
    public void update(String[] params) throws SQLException {
        query = "UPDATE " + TABLE + " SET jabatan=?, status=? WHERE userid=?";
        ps = con.prepareStatement(query);
        ps.setString(1, params[0]);
        ps.setString(2, params[1]);
        ps.setString(3, params[2]);
        ps.executeUpdate();
    }

    /**
     * Only Admin can delete user.
     *
     * @param userid user Id
     * @throws SQLException as Error Handling
     */
    @Override
    public void delete(String userid) throws SQLException {
        query = "DELETE FROM " + TABLE + " WHERE userid=?";
        ps = con.prepareStatement(query);
        ps.setString(1, userid);
        ps.executeUpdate();
    }

    /**
     * Is user already reseted.
     *
     * @param userid an user id
     * @return user reset status
     * @throws SQLException error handling
     */
    public boolean isReset(String userid) throws SQLException {
        query = "SELECT password FROM " + TABLE + " WHERE userid=?";
        ps = con.prepareStatement(query);
        ps.setString(1, userid);
        rs = ps.executeQuery();

        if (rs.next()) {
            @SuppressWarnings("UnstableApiUsage")
            String hex = Hashing
                    .sha256()
                    .hashString("12345678", StandardCharsets.UTF_8)
                    .toString();
            return rs.getString(1).equals(hex);
        }
        return false;
    }

    /**
     * Only admin can reset user account.
     *
     * @param userId an user id
     * @throws SQLException as Error Handling
     */
    public void reset(String userId) throws SQLException {
        query = "UPDATE " + TABLE + " SET password=? WHERE userid=?";
        ps = con.prepareStatement(query);

        @SuppressWarnings("UnstableApiUsage")
        String hex = Hashing
                .sha256()
                .hashString("12345678", StandardCharsets.UTF_8)
                .toString();

        ps.setString(2, userId);
        ps.setString(1, hex);
        ps.executeUpdate();
    }

    /**
     * Only Specified User can see this
     * also this code used for Authorization
     *
     * @throws SQLException as Error Handling
     */
    @Override
    public User get(String userid) throws SQLException {
        query = "SELECT * FROM " + TABLE + " WHERE userid=? ";
        ps = con.prepareStatement(query);
        ps.setString(1, userid);
        rs = ps.executeQuery();

        User model = null;
        if (rs.next()) {
            model = new User();
            model.setUserId(rs.getString(1));
            model.setPassword(rs.getString(2));
            model.setUsername(rs.getString(3));
            model.setJabatan(rs.getString(4));
            model.setStatus(rs.getString(5));
            model.setCreated_at(rs.getString(6));
            model.setUpdated_at(rs.getString(7));
        }
        return model;
    }

    /**
     * method for update user
     * @param userid user id
     * @return status
     * @throws SQLException an Error Handling
     */
    public boolean isUserExist(String userid) throws SQLException {
        query = "SELECT userid FROM " + TABLE + " WHERE userid=? ";
        ps = con.prepareStatement(query);
        ps.setString(1, userid);
        rs = ps.executeQuery();

        return rs.next();
    }

    /**
     * Get UserInfo for Authentication
     *
     * @param userid   username
     * @param password user password
     * @return userId
     * @throws SQLException for Error Handling
     */
    public boolean isUserExist(String userid, String password) throws SQLException {
        query = "SELECT userid FROM " + TABLE + " WHERE userid=? and password=? LIMIT 1";
        ps = con.prepareStatement(query);
        ps.setString(1, userid);
        ps.setString(2, password);
        rs = ps.executeQuery();

        return rs.next();
    }
}
