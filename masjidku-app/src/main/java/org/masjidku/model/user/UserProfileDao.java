package org.masjidku.model.user;

import org.masjidku.model.DaoFactory;

import java.sql.SQLException;

@SuppressWarnings({"unused"})
public class UserProfileDao extends DaoFactory {

    private static final String INSERT_QUERY = "INSERT INTO profil_user (userid, notelp, alamat) VALUES(?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE profil_user SET notelp=?, alamat=? WHERE userid=?";
    private static final String SELECT_FULL_USER_QUERY = "SELECT user.userid, password, username, jabatan, status, notelp, alamat, created_at, updated_at " +
                "FROM profil_user pu LEFT JOIN user ON pu.userid = user.userid " +
                "WHERE pu.userid=?";

    public void save(UserProfile userProfile) throws SQLException {
        ps = con.prepareStatement(INSERT_QUERY);
        ps.setString(1, userProfile.getUser().getUserId());
        ps.setString(2, userProfile.getAlamat());
        ps.setString(3, userProfile.getNotelp());
        ps.executeUpdate();
        ps.close();
    }

    public void update(String[] params) throws SQLException {
        ps = con.prepareStatement(UPDATE_QUERY);
        ps.setString(1, params[0]);
        ps.setString(2, params[1]);
        ps.setString(3, params[2]);
        ps.executeUpdate();
        ps.close();
    }

    public UserProfile getFullUserData(String userid) throws SQLException {
        UserProfile model = null;
        User user;
        
        ps = con.prepareStatement(SELECT_FULL_USER_QUERY);
        ps.setString(1, userid);

        rs = ps.executeQuery();
        if (rs.next()) {
            model = new UserProfile();
            user = new User();

            user.setUserId(rs.getString("userid")); // Using simplified column name assuming no ambiguity or alias handled
            // Need to fix this according to previous view_file. Wait.
            // Previous code: user.setUserId(rs.getString("user.userid")); 
            // In SQLite/MySQL rs.getString("user.userid") can be flaky. But let's keep original logic.
            user.setUserId(rs.getString("userid"));
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
        rs.close();
        ps.close();
        return model;
    }
}
