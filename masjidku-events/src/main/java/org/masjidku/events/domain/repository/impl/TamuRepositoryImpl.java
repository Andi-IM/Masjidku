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

package org.masjidku.events.domain.repository.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.intellij.lang.annotations.Language;
import org.masjidku.events.client.model.Tamu;
import org.masjidku.events.domain.repository.TamuRepository;
import org.masjidku.events.domain.repository.base.BaseRepository;

import java.sql.SQLException;

public class TamuRepositoryImpl extends BaseRepository<Tamu> implements TamuRepository {
    @Language("SQL")
    private static final String QUERY_1 = "SELECT * FROM tamu WHERE tamuID=?";
    @Language("SQL")
    private static final String QUERY_2 = "SELECT * FROM tamu";
    @Language("SQL")
    private static final String QUERY_3 = "INSERT INTO tamu(tamuID, tamuNama, tamuAlamat, tamuNotelp, operator) VALUES(?,?,?,?,?)";
    @Language("SQL")
    private static final String QUERY_4 = "UPDATE tamu SET tamuNama=?, tamuAlamat=?, tamuNotelp=?, operator=? WHERE tamuID=?";
    @Language("SQL")
    private static final String QUERY_5 = "DELETE FROM tamu WHERE tamuID=?";
    @Language("SQL")
    private static final String QUERY_6 = "SELECT tamuID FROM tamu WHERE tamuID=?";
    @Language("SQL")
    private static final String QUERY_7 = "SELECT * FROM tamu";
    @Language("SQL")
    private static final String QUERY_8 = "SELECT tamuID FROM tamu WHERE tamuNama=?";

    public TamuRepositoryImpl() {
        getConnection();
    }

    @Override
    public Tamu get(String id) {
        try {
            ps = con.prepareStatement(QUERY_1);
            ps.setString(1, id);
            rs = ps.executeQuery();
            Tamu model = null;
            if (rs.next()) {
                model = new Tamu(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );
            }
            return model;
        } catch (SQLException e) {
            throw new RuntimeException("Database error in get Tamu", e);
        }
    }

    @Override
    public ObservableList<Tamu> getAll() {
        try {
            ObservableList<Tamu> items = FXCollections.observableArrayList();
            ps = con.prepareStatement(QUERY_2);
            rs = ps.executeQuery();
            while (rs.next()) {
                items.add(new Tamu(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                ));
            }
            return items;
        } catch (SQLException e) {
            throw new RuntimeException("Database error in getAll Tamu", e);
        }
    }

    @Override
    public void save(Tamu tamu) {
        try {
            executeUpdateQuery(QUERY_3, tamu.getIdTamu(), tamu.getNama(), tamu.getAlamat(), tamu.getNotelp(), tamu.getOperator());
        } catch (SQLException e) {
            throw new RuntimeException("Database error in save Tamu", e);
        }
    }

    @Override
    public void update(String[] params) {
        try {
            executeUpdateQuery(QUERY_4, params[0], params[1], params[2], params[3], params[4]);
        } catch (SQLException e) {
            throw new RuntimeException("Database error in update Tamu", e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            executeDelete(QUERY_5, id);
        } catch (SQLException e) {
            throw new RuntimeException("Database error in delete Tamu", e);
        }
    }

    @Override
    public boolean isTamuExist(String id) {
        try {
            return executeCheckExists(QUERY_6, id);
        } catch (SQLException e) {
            throw new RuntimeException("Database error in isTamuExist", e);
        }
    }

    @Override
    public ObservableList<String> getAllTamuName() {
        try {
            return executeGetAllNames(QUERY_7);
        } catch (SQLException e) {
            throw new RuntimeException("Database error in getAllTamuName", e);
        }
    }

    @Override
    public String getIdByName(String name) {
        try {
            return executeGetIdByName(QUERY_8, name);
        } catch (SQLException e) {
            throw new RuntimeException("Database error in getIdByName", e);
        }
    }
}


