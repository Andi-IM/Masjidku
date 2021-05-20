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

@SuppressWarnings("unused")
public class UserDao {
    Connection con;
    private final String SQL_USER_TABLE = "user";

    PreparedStatement ps;

    // constructor
    public UserDao() {}

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
    public void create(String userid, String username, String jabatan, String status) throws SQLException {
        String SQL_INSERT_USER = "INSERT INTO " + SQL_USER_TABLE + "(userid, password, username, jabatan, status, created_at, updated_at) VALUES(?,?,?,?,?,?,?)";
        ps = con.prepareStatement(SQL_INSERT_USER);
        ps.setString(1, userid);

        @SuppressWarnings("UnstableApiUsage")
        String hex = Hashing
                .sha256()
                .hashString("12345678", StandardCharsets.UTF_8)
                .toString();

        ps.setString(2, hex);
        ps.setString(3, username);
        ps.setString(4, jabatan);
        ps.setString(5, status);
        ps.setString(6, null);
        ps.setString(7, null);
        ps.executeUpdate();
    }

    /**
     *  Only Specified User can Modify
     *
     *  @param model an User Model
     *  @throws SQLException as Error Handling
     */
    public void update(User model) throws SQLException {
        String SQL_UPDATE_USER = "UPDATE " + SQL_USER_TABLE + " SET username=?, password=? jabatan=? WHERE userid=?";
        ps = con.prepareStatement(SQL_UPDATE_USER);
    }

    /**
     *  Only admin can reset user account.
     *
     *  @param id an user id
     *  @throws SQLException as Error Handling
     */
    public void reset(int id) throws SQLException {
        String SQL_RESET_USER = "UPDATE " + SQL_USER_TABLE + " SET password=? WHERE userid=?";
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
     * @param userid an user id
     * @param status am user status
     * @throws SQLException as Error Handling.
     */
    public void suspend(String userid, String status) throws SQLException {
        String SQL_SUSPEND_USER = "UPDATE " + SQL_USER_TABLE + " SET status=? WHERE userid=?";
        ps = con.prepareStatement(SQL_SUSPEND_USER);
        ps.setString(1, status);
        ps.setString(2, userid);
        ps.executeUpdate();
    }

    /**
     * Only Admin can see this
     * @throws SQLException as Error Handling
     */
    public List<User> getUsers() throws SQLException {
        String SQL_GET_USERS = "SELECT userid, username, jabatan, status, created_at, updated_at FROM " + SQL_USER_TABLE;
        ps = con.prepareStatement(SQL_GET_USERS);
        ResultSet resultset = ps.executeQuery();
        List<User> list = new ArrayList<>();
        User user = null;
        while (resultset.next()){
            user = new User();
            user.setUserId(resultset.getString(1));
            user.setUsername(resultset.getString(2));
            user.setJabatan(resultset.getString(3));
            user.setStatus(resultset.getString(4));
            user.setCreated_at(resultset.getString(5));
            user.setUpdated_at(resultset.getString(6));
            list.add(user);
        }
        return list;
    }

    /**
     * Only Specified User can see this
     * also this code used for Authorization
     *
     * @throws SQLException as Error Handling
     */
    public User getUserData(String userid) throws SQLException {
        String SQL_GET_USER_DATA = "SELECT * FROM " + SQL_USER_TABLE + " WHERE userid=? ";
        ps = con.prepareStatement(SQL_GET_USER_DATA);
        ps.setString(1, userid);
        User model = null;
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            model = new User();
            model.setUserId(rs.getString(1));
            model.setPassword(rs.getString(2));
            model.setUsername(rs.getString(3));
            model.setJabatan(rs.getString(4));
            model.setStatus(rs.getString(5));
            model.setAlamat(null);
            model.setNotelp(null);
            model.setCreated_at(rs.getString(6));
            model.setUpdated_at(rs.getString(7));
        }
        return model;
    }

    /**
     *  Get UserInfo for Authentication
     *
     * @param userid username
     * @param password user password
     * @return userId
     * @throws SQLException for Error Handling
     */
    public boolean getUser(String userid, String password) throws SQLException {
        String SQL_GET_USER = "SELECT userid FROM " + SQL_USER_TABLE + " WHERE userid=? and password=? LIMIT 1";
        ps = con.prepareStatement(SQL_GET_USER);
        ps.setString(1, userid);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        return rs.next();
    }

    /**
     *  Only Admin can delete user.
     *
     * @param userid user Id
     * @throws SQLException as Error Handling
     */
    public void delete(String userid) throws  SQLException {
        String SQL_DELETE_USER = "DELETE FROM " + SQL_USER_TABLE + " WHERE userid=?";
        ps = con.prepareStatement(SQL_DELETE_USER);
        ps.setString(1, userid);
        ps.executeUpdate();
    }

    public static void main(String[] args) {
        UserDao dao = new UserDao();

        if (dao.getConnection()){
            try {
                List<User> list = dao.getUsers();
                System.out.println(list.get(0).getUsername());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
