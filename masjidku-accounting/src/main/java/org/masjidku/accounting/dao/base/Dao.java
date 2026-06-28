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

import java.sql.SQLException;

public abstract class Dao<T> extends DaoFactory {

    public abstract T get(String id) throws SQLException;
    public abstract ObservableList<T> getAll() throws SQLException;
    public abstract void save(T t) throws SQLException;
    public abstract void update(String[] params) throws SQLException;
    public abstract void delete(String id) throws SQLException;


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
    protected <R> R executeGetLastRecord(String query, RowMapper<R> mapper) throws SQLException {
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();
        if (rs.next()) {
            return mapper.map(rs);
        }
        return null;
    }
}





