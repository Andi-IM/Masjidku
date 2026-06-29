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

package org.masjidku.accounting.dao.base;

import javafx.collections.ObservableList;
import org.intellij.lang.annotations.Language;

import java.sql.SQLException;

public abstract class Dao<T> extends AccountingDaoFactory {

    public abstract T get(String id) throws SQLException;
    public abstract ObservableList<T> getAll() throws SQLException;
    public abstract void save(T t) throws SQLException;
    public abstract void update(String[] params) throws SQLException;
    public abstract void delete(String id) throws SQLException;

    protected void executeDelete(@Language("SQL") String query, String id) throws SQLException {
        try (java.sql.PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, id);
            ps.executeUpdate();
        }
    }

    protected boolean executeCheckExists(@Language("SQL") String query, String id) throws SQLException {
        try (java.sql.PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, id);
            try (java.sql.ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    protected String executeGetTotal(@Language("SQL") String query) throws SQLException {
        try (java.sql.PreparedStatement ps = con.prepareStatement(query);
             java.sql.ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getString(1);
            }
            return null;
        }
    }

    protected void executeUpdateQuery(@Language("SQL") String query, String... params) throws SQLException {
        try (java.sql.PreparedStatement ps = con.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                ps.setString(i + 1, params[i]);
            }
            ps.executeUpdate();
        }
    }

    protected interface RowMapper<T> {
        T map(java.sql.ResultSet rs) throws SQLException;
    }
    protected <R> R executeGetLastRecord(@Language("SQL") String query, RowMapper<R> mapper) throws java.sql.SQLException {
        try (java.sql.PreparedStatement ps = con.prepareStatement(query);
             java.sql.ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return mapper.map(rs);
            }
            return null;
        }
    }
}
