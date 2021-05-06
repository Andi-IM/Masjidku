package org.masjidku.model;

import com.google.common.hash.Hashing;
import org.masjidku.util.DatabaseConnection;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserDao {
    Connection con;
    private final String SQL_USER_TABLE = "user";
    private final String SQL_INSERT_USER = "INSERT INTO " + SQL_USER_TABLE + "(username, password, status) " +
            "VALUES(?,?,?)";
    private final String SQL_UPDATE_USER = "";
    private final String SQL_RESET_USER = "";
    private final String SQL_DELETE_USER = "";

    PreparedStatement ps;
    public UserDao(){
        DatabaseConnection connection = new DatabaseConnection();
        con = connection.getConnection();
    }

    public void create() throws SQLException {
        ps = con.prepareStatement(SQL_INSERT_USER);
        ps.setString(1, "root");

        @SuppressWarnings("UnstableApiUsage")
        String hex = Hashing
                .sha256()
                .hashString("Root", StandardCharsets.UTF_8)
                .toString();
        ps.setString(2, hex);
        ps.setString(3, "Aktif");
        ps.executeUpdate();
    }
}
