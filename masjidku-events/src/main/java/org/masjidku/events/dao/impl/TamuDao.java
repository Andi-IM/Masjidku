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

package org.masjidku.events.dao.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.masjidku.events.dao.base.Dao;

import java.sql.SQLException;

import org.masjidku.events.client.service.TamuService;
import org.masjidku.events.client.model.Tamu;
public class TamuDao extends Dao<Tamu> implements TamuService {
    private static final String QUERY_1 = "SELECT * FROM tamu WHERE tamuID=?";
    private static final String QUERY_2 = "SELECT * FROM tamu";
    private static final String QUERY_3 = "INSERT INTO tamu(tamuID, tamuNama, tamuAlamat, tamuNotelp, operator) VALUES(?,?,?,?,?)";
    private static final String QUERY_4 = "UPDATE tamu SET tamuNama=?, tamuAlamat=?, tamuNotelp=?, operator=? WHERE tamuID=?";
    private static final String QUERY_5 = "DELETE FROM tamu WHERE tamuID=?";
    private static final String QUERY_6 = "SELECT tamuID FROM tamu WHERE tamuID=?";
    private static final String QUERY_7 = "SELECT * FROM tamu";
    private static final String QUERY_8 = "SELECT tamuID FROM tamu WHERE tamuNama=?";

    public TamuDao() {
        getConnection();
    }


    

    @Override
    public Tamu get(String id) throws SQLException {
        ps = con.prepareStatement(QUERY_1);

        ps.setString(1, id);
        rs = ps.executeQuery();

        Tamu model = null;
        if (rs.next()){
            model = new Tamu(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
            );
        }
        return model;
    }

    @Override
    public ObservableList<Tamu> getAll() throws SQLException {
        ObservableList<Tamu> items = FXCollections.observableArrayList();
        ps = con.prepareStatement(QUERY_2);
        rs = ps.executeQuery();

        Tamu tamu;
        while(rs.next()){
            tamu = new Tamu(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
            );
            items.add(tamu);
        }
        return items;
    }

    @Override
    public void save(Tamu tamu) throws SQLException {
        ps = con.prepareStatement(QUERY_3);

        ps.setString(1, tamu.getIdTamu());
        ps.setString(2, tamu.getNama());
        ps.setString(3, tamu.getAlamat());
        ps.setString(4, tamu.getNotelp());
        ps.setString(5, tamu.getOperator());
        ps.executeUpdate();
    }

    @Override
    public void update(String[] params) throws SQLException {
        ps = con.prepareStatement(QUERY_4);

        ps.setString(1, params[0]);
        ps.setString(2, params[1]);
        ps.setString(3, params[2]);
        ps.setString(4, params[3]);
        ps.setString(5, params[4]);
        ps.executeUpdate();
    }

    @Override
    public void delete(String id) throws SQLException {
        ps = con.prepareStatement(QUERY_5);
        ps.setString(1, id);
        ps.executeUpdate();
    }

    public boolean isTamuExist(String id) throws SQLException {
        ps = con.prepareStatement(QUERY_6);
        ps.setString(1, id);
        rs = ps.executeQuery();

        return rs.next();
    }

    public ObservableList<String> getAllTamuName() throws SQLException {
        ObservableList<String> namaTamu = FXCollections.observableArrayList();

        ps = con.prepareStatement(QUERY_7);
        rs = ps.executeQuery();

        String name;
        while(rs.next()){
            name = rs.getString(2);
            namaTamu.add(name);
        }
        return namaTamu;
    }

    public String getIdByName(String name) throws SQLException {
        ps = con.prepareStatement(QUERY_8);

        ps.setString(1, name);
        rs = ps.executeQuery();

        if(rs.next()){
            return rs.getString(1);
        }
        return "";
    }
}
