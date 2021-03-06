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

package org.masjidku.model.kegiatan;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.masjidku.model.Dao;

import java.sql.SQLException;

public class TamuDao extends Dao<Tamu> {

    private final String TABLE = "tamu";

    @Override
    public Tamu get(String id) throws SQLException {
        query = "SELECT * FROM "+TABLE+" WHERE tamuID=?";

        ps = con.prepareStatement(query);
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
        query = "SELECT * FROM "+TABLE;
        ps = con.prepareStatement(query);
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
        query = "INSERT INTO "+TABLE+"(tamuID, tamuNama, tamuAlamat, tamuNotelp, operator) VALUES(?,?,?,?,?)";

        ps = con.prepareStatement(query);
        ps.setString(1, tamu.getIdTamu());
        ps.setString(2, tamu.getNama());
        ps.setString(3, tamu.getAlamat());
        ps.setString(4, tamu.getNotelp());
        ps.setString(5, tamu.getOperator());
        ps.executeUpdate();
    }

    @Override
    public void update(String[] params) throws SQLException {
        query = "UPDATE "+TABLE+" SET tamuNama=?, tamuAlamat=?, tamuNotelp=?, operator=? WHERE tamuID=?";

        ps = con.prepareStatement(query);
        ps.setString(1, params[0]);
        ps.setString(2, params[1]);
        ps.setString(3, params[2]);
        ps.setString(4, params[3]);
        ps.setString(5, params[4]);
        ps.executeUpdate();
    }

    @Override
    public void delete(String id) throws SQLException {
        query = "DELETE FROM "+TABLE+" WHERE tamuID=?";
        ps = con.prepareStatement(query);
        ps.setString(1, id);
        ps.executeUpdate();
    }

    public boolean isTamuExist(String id) throws SQLException {
        query = "SELECT tamuID FROM "+TABLE+" WHERE tamuID=?";
        ps = con.prepareStatement(query);
        ps.setString(1, id);
        rs = ps.executeQuery();

        return rs.next();
    }

    public ObservableList<String> getAllTamuName() throws SQLException {
        ObservableList<String> namaTamu = FXCollections.observableArrayList();

        query = "SELECT * FROM "+TABLE;
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        String name;
        while(rs.next()){
            name = rs.getString(2);
            namaTamu.add(name);
        }
        return namaTamu;
    }

    public String getIdByName(String name) throws SQLException {
        query = "SELECT tamuID FROM "+TABLE+" WHERE tamuNama=?";

        ps = con.prepareStatement(query);
        ps.setString(1, name);
        rs = ps.executeQuery();

        if(rs.next()){
            return rs.getString(1);
        }
        return "";
    }
}
