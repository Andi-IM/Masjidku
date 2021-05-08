package org.masjidku.model;

import com.google.common.hash.Hashing;
import org.masjidku.util.DatabaseConnection;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    Connection con;
    private final String SQL_USER_TABLE = "user";
    private final String SQL_GET_USERS = "SELECT (username, jabatan, status, created_at, updated_at) FROM " + SQL_USER_TABLE;
    private final String SQL_GET_USER_DATA = "SELECT (username, nama, jabatan, no_telp, status, alamat) FROM "+ SQL_USER_TABLE + " WHERE id =?";
    private final String SQL_GET_USER = "SELECT (username, password, jabatan, status) FROM "+ SQL_USER_TABLE + " WHERE username =? and password=?";
    private final String SQL_INSERT_USER = "INSERT INTO " + SQL_USER_TABLE + "(username, password, status) VALUES(?,?,?)";
    private final String SQL_UPDATE_USER = "UPDATE " + SQL_USER_TABLE + " SET username=?, password=?, nama=?, jabatan=?, no_telp=?, alamat=? WHERE id=?";
    private final String SQL_RESET_USER = "UPDATE "+SQL_USER_TABLE + " SET password=? WHERE id=?";
    private final String SQL_SUSPEND_USER = "UPDATE "+SQL_USER_TABLE + " SET status=? WHERE id=?";
    private final String SQL_DELETE_USER = "DELETE FROM "+ SQL_USER_TABLE +" WHERE id=?";

    PreparedStatement ps;
    public UserDao(){
        DatabaseConnection connection = new DatabaseConnection();
        con = connection.getConnection();
    }

    /**
     * Only Admin can create User
     *
     * @param model an User Model
     * @throws SQLException as Error Handling
     */
    public void create(User model) throws SQLException {
        ps = con.prepareStatement(SQL_INSERT_USER);
        ps.setString(1, model.getUsername());

        @SuppressWarnings("UnstableApiUsage")
        String hex = Hashing
                .sha256()
                .hashString(model.getPassword(), StandardCharsets.UTF_8)
                .toString();
        ps.setString(2, hex);
        ps.setString(3, "Aktif");
        ps.executeUpdate();
    }

    /**
     *  Only Specified User can Modify
     *
     *  @param model an User Model
     *  @throws SQLException as Error Handling
     */
    public void update(User model) throws SQLException {
        ps = con.prepareStatement(SQL_UPDATE_USER);
    }

    /**
     *  Only admin can reset user account.
     *
     *  @param id an user id
     *  @throws SQLException as Error Handling
     */
    public void reset(int id) throws SQLException {
        ps = con.prepareStatement(SQL_RESET_USER);

        @SuppressWarnings("UnstableApiUsage")
        String hex = Hashing
                .sha256()
                .hashString("12345678", StandardCharsets.UTF_8)
                .toString();
        ps.setInt(2, id);
        ps.setString(1, hex);
        ps.executeUpdate();
    }

    /**
     *  Only admin can suspend user account
     *
     * @param id an user id
     * @param status am user status
     * @throws SQLException as Error Handling.
     */
    public void suspend(int id, String status) throws SQLException {
        ps = con.prepareStatement(SQL_SUSPEND_USER);
        ps.setString(1, status);
        ps.setInt(2, id);
        ps.executeUpdate();
    }

    /**
     * Only Admin can see this
     * @throws SQLException
     */
    public List<User> getUsers() throws SQLException {
        ps = con.prepareStatement(SQL_GET_USERS);
        ResultSet resultset = ps.executeQuery();
        List<User> list = new ArrayList<>();
        User user = new User();
        if (resultset.next()){

            list.add(user);
        }
        return list;
    }

    /**
     * Only Specified User can see this
     *
     * @throws SQLException
     */
    public User getUserData(int id) throws SQLException {
        ps = con.prepareStatement(SQL_GET_USER_DATA);
        ps.setInt(1, id);
        User model = new User();
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()){
            model = new User(
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7));
        }
        return model;
    }

    public User getUser(String username, String password) throws SQLException {
        User model= new User();
        ps = con.prepareStatement(SQL_GET_USER);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        if (rs.next()){
            model.setUsername(rs.getString(3));
            model.setPassword(rs.getString(4));
            model.setJabatan(rs.getString(5));
            model.setStatus(rs.getString(6));
        }
        return model;
    }

    /**
     *  Only Admin can delete user.
     *
     * @param id
     * @throws SQLException
     */
    public void delete(int id) throws  SQLException {
        ps = con.prepareStatement(SQL_DELETE_USER);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}
