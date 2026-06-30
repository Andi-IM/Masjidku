package org.masjidku.model.user.dao;

import com.google.common.hash.Hashing;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.masjidku.model.user.User;
import org.masjidku.model.user.UserProfile;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

@SuppressWarnings("unused")
public class UserDao extends UserDaoFactory<User> {

    private static final String SELECT_ALL_QUERY = "SELECT userid, username, jabatan, status, created_at, updated_at FROM user";
    private static final String INSERT_QUERY = "INSERT INTO user (userid, password, username, jabatan, status) VALUES(?,?,?,?,?)";
    private static final String UPDATE_STATUS_QUERY = "UPDATE user SET jabatan=?, status=? WHERE userid=?";
    private static final String UPDATE_PROFILE_QUERY = "UPDATE user SET username=?, password=? WHERE userid=?";
    private static final String DELETE_QUERY = "DELETE FROM user WHERE userid=?";
    private static final String SELECT_PASSWORD_QUERY = "SELECT password FROM user WHERE userid=?";
    private static final String RESET_PASSWORD_QUERY = "UPDATE user SET password=? WHERE userid=?";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM user WHERE userid=?";
    private static final String CHECK_EXIST_QUERY = "SELECT userid FROM user WHERE userid=?";
    private static final String CHECK_AUTH_QUERY = "SELECT userid FROM user WHERE userid=? and password=? LIMIT 1";

    @Override
    public ObservableList<User> getAll() throws SQLException {
        ObservableList<User> users = FXCollections.observableArrayList();
        ps = con.prepareStatement(SELECT_ALL_QUERY);
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
        rs.close();
        ps.close();
        return users;
    }

    @Override
    public void save(User user) throws SQLException {
        ps = con.prepareStatement(INSERT_QUERY);
        ps.setString(1, user.getUserId());

        String hex = Hashing
                .sha256()
                .hashString("12345678", StandardCharsets.UTF_8)
                .toString();

        ps.setString(2, hex);
        ps.setString(3, user.getUsername());
        ps.setString(4, user.getJabatan().toString());
        ps.setString(5, user.getStatus());
        ps.executeUpdate();
        ps.close();

        generateProfile(user);
    }

    private void generateProfile(User user) throws SQLException {
        UserProfileDao dao = new UserProfileDao();
        dao.getConnection();
        UserProfile profile = new UserProfile(user);
        dao.save(profile);
    }

    @Override
    public void update(String[] params) throws SQLException {
        ps = con.prepareStatement(UPDATE_STATUS_QUERY);
        ps.setString(1, params[0]);
        ps.setString(2, params[1]);
        ps.setString(3, params[2]);
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void update(String userid, String username, String password) throws  SQLException {
        ps = con.prepareStatement(UPDATE_PROFILE_QUERY);
        ps.setString(1, username);
        ps.setString(3, userid);

        String hex = Hashing
                .sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
        ps.setString(2, hex);
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void delete(String userid) throws SQLException {
        ps = con.prepareStatement(DELETE_QUERY);
        ps.setString(1, userid);
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public boolean isReset(String userid) throws SQLException {
        ps = con.prepareStatement(SELECT_PASSWORD_QUERY);
        ps.setString(1, userid);
        rs = ps.executeQuery();
        boolean reset = false;
        if (rs.next()) {
            String hex = Hashing
                    .sha256()
                    .hashString("12345678", StandardCharsets.UTF_8)
                    .toString();
            reset = rs.getString(1).equals(hex);
        }
        rs.close();
        ps.close();
        return reset;
    }

    @Override
    public void reset(String userId) throws SQLException {
        ps = con.prepareStatement(RESET_PASSWORD_QUERY);
        String hex = Hashing
                .sha256()
                .hashString("12345678", StandardCharsets.UTF_8)
                .toString();

        ps.setString(2, userId);
        ps.setString(1, hex);
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public User get(String userid) throws SQLException {
        ps = con.prepareStatement(SELECT_BY_ID_QUERY);
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
        rs.close();
        ps.close();
        return model;
    }

    @Override
    public boolean isUserExist(String userid) throws SQLException {
        ps = con.prepareStatement(CHECK_EXIST_QUERY);
        ps.setString(1, userid);
        rs = ps.executeQuery();
        boolean exist = rs.next();
        rs.close();
        ps.close();
        return exist;
    }

    @Override
    public boolean isUserExist(String userid, String password) throws SQLException {
        ps = con.prepareStatement(CHECK_AUTH_QUERY);
        ps.setString(1, userid);
        ps.setString(2, password);
        rs = ps.executeQuery();
        boolean exist = rs.next();
        rs.close();
        ps.close();
        return exist;
    }
}
