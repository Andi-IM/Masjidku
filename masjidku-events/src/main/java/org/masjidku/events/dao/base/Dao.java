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

    @SuppressWarnings("SqlSourceToSinkFlow")
    protected void executeDelete(String query, String id) throws SQLException {
        ps = con.prepareStatement(query);
        ps.setString(1, id);
        ps.executeUpdate();
    }

    @SuppressWarnings("SqlSourceToSinkFlow")
    protected boolean executeCheckExists(String query, String id) throws SQLException {
        ps = con.prepareStatement(query);
        ps.setString(1, id);
        rs = ps.executeQuery();
        return rs.next();
    }

    @SuppressWarnings("SqlSourceToSinkFlow")
    protected String executeGetTotal(String query) throws SQLException {
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    @SuppressWarnings("SqlSourceToSinkFlow")
    protected void executeUpdateQuery(String query, String... params) throws SQLException {
        ps = con.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            ps.setString(i + 1, params[i]);
        }
        ps.executeUpdate();
    }

    protected interface RowMapper<T> {
        T map(java.sql.ResultSet rs) throws SQLException;
    }

    @SuppressWarnings("SqlSourceToSinkFlow")
    protected <R> R executeGet(String query, String id, RowMapper<R> mapper) throws SQLException {
        ps = con.prepareStatement(query);
        ps.setString(1, id);
        rs = ps.executeQuery();
        if (rs.next()) {
            return mapper.map(rs);
        }
        return null;
    }

    @SuppressWarnings("SqlSourceToSinkFlow")
    protected <R> javafx.collections.ObservableList<R> executeGetAll(String query, RowMapper<R> mapper) throws SQLException {
        javafx.collections.ObservableList<R> items = javafx.collections.FXCollections.observableArrayList();
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next()) {
            items.add(mapper.map(rs));
        }
        return items;
    }

    @SuppressWarnings("SqlSourceToSinkFlow")
    protected javafx.collections.ObservableList<String> executeGetAllNames(String query) throws SQLException {
        javafx.collections.ObservableList<String> list = javafx.collections.FXCollections.observableArrayList();
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next()) {
            list.add(rs.getString(2));
        }
        return list;
    }

    @SuppressWarnings("SqlSourceToSinkFlow")
    protected String executeGetIdByName(String query, String name) throws SQLException {
        ps = con.prepareStatement(query);
        ps.setString(1, name);
        rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getString(1);
        }
        return "";
    }
}






