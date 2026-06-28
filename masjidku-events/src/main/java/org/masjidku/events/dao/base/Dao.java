package org.masjidku.events.dao.base;

import org.masjidku.util.db.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Dao<T> {

    public Dao() {
        getConnection();
    }
    protected Connection con;
    protected PreparedStatement ps;
    protected ResultSet rs;

    public void getConnection() {
        DatabaseConnection connection = new DatabaseConnection();
        if (connection.getConnection() != null) {
            con = connection.getConnection();
        }
    }

    // --- HELPER METHODS UNTUK DRY --- //

    protected void executeDelete(String query, String id) throws SQLException {
        ps = con.prepareStatement(query);
        ps.setString(1, id);
        ps.executeUpdate();
    }

    protected boolean executeCheckExists(String query, String id) throws SQLException {
        ps = con.prepareStatement(query);
        ps.setString(1, id);
        rs = ps.executeQuery();
        return rs.next();
    }

    protected String executeGetTotal(String query) throws SQLException {
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    protected void executeUpdateQuery(String query, String... params) throws SQLException {
        ps = con.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            ps.setString(i + 1, params[i]);
        }
        ps.executeUpdate();
    }
}
