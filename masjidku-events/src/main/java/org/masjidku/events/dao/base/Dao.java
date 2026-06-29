package org.masjidku.events.dao.base;

import org.intellij.lang.annotations.Language;
import org.masjidku.util.db.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class Dao<T> {

    protected Dao() {
        getConnection();
    }
    protected Connection con;
    protected java.sql.PreparedStatement ps;
    protected java.sql.ResultSet rs;
        
    public void getConnection() {
        DatabaseConnection connection = new DatabaseConnection();
        if (connection.getConnection() != null) {
            con = connection.getConnection();
        }
    }

    @SuppressWarnings("SqlSourceToSinkFlow")
    protected void executeDelete(@Language("SQL") String query, String id) throws SQLException {
        try (java.sql.PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
    }

    protected boolean executeCheckExists(@org.intellij.lang.annotations.Language("SQL") String query, String id) throws SQLException {
        try (java.sql.PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, id);
            try (java.sql.ResultSet resultSet = stmt.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    protected String executeGetTotal(@org.intellij.lang.annotations.Language("SQL") String query) throws SQLException {
        try (java.sql.PreparedStatement stmt = con.prepareStatement(query);
             java.sql.ResultSet resultSet = stmt.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
            return null;
        }
    }

    protected void executeUpdateQuery(@org.intellij.lang.annotations.Language("SQL") String query, String... params) throws SQLException {
        try (java.sql.PreparedStatement stmt = con.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setString(i + 1, params[i]);
            }
            stmt.executeUpdate();
        }
    }

    protected interface RowMapper<T> {
        T map(java.sql.ResultSet rs) throws SQLException;
    }

    protected <R> R executeGet(@org.intellij.lang.annotations.Language("SQL") String query, String id, RowMapper<R> mapper) throws SQLException {
        try (java.sql.PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, id);
            try (java.sql.ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return mapper.map(resultSet);
                }
                return null;
            }
        }
    }

    protected <R> javafx.collections.ObservableList<R> executeGetAll(@org.intellij.lang.annotations.Language("SQL") String query, RowMapper<R> mapper) throws SQLException {
        javafx.collections.ObservableList<R> items = javafx.collections.FXCollections.observableArrayList();
        try (java.sql.PreparedStatement stmt = con.prepareStatement(query);
             java.sql.ResultSet resultSet = stmt.executeQuery()) {
            while (resultSet.next()) {
                items.add(mapper.map(resultSet));
            }
            return items;
        }
    }

    protected javafx.collections.ObservableList<String> executeGetAllNames(@org.intellij.lang.annotations.Language("SQL") String query) throws SQLException {
        javafx.collections.ObservableList<String> list = javafx.collections.FXCollections.observableArrayList();
        try (java.sql.PreparedStatement stmt = con.prepareStatement(query);
             java.sql.ResultSet resultSet = stmt.executeQuery()) {
            while (resultSet.next()) {
                list.add(resultSet.getString(2));
            }
            return list;
        }
    }

    protected String executeGetIdByName(@org.intellij.lang.annotations.Language("SQL") String query, String name) throws SQLException {
        try (java.sql.PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, name);
            try (java.sql.ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString(1);
                }
                return "";
            }
        }
    }
}








