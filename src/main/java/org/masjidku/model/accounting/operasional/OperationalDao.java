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

package org.masjidku.model.accounting.operasional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.masjidku.model.Dao;
import org.masjidku.model.DaoFactory;

import java.sql.SQLException;

public class OperationalDao extends Dao<Operasional> {

    private final String TABLE = "operasional_keluar";

    @Override
    protected Operasional get(String id) throws SQLException {
        query = "SELECT * FROM "+TABLE+" WHERE id=?";
        ps = con.prepareStatement(query);
        ps.setString(1, id);
        rs = ps.executeQuery();

        Operasional model = null;
        if (rs.next()){
            model = new Operasional(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    Double.parseDouble(rs.getString(4)),
                    rs.getString(5),
                    rs.getString(6)
            );
        }
        return model;
    }

    @Override
    protected ObservableList<Operasional> getAll() throws SQLException {
        ObservableList<Operasional> item = FXCollections.observableArrayList();

        query = "SELECT * FROM "+TABLE;
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        Operasional operasional;
        while (rs.next()){
            operasional = new Operasional(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    Double.parseDouble(rs.getString(4)),
                    rs.getString(5),
                    rs.getString(6)
            );
            item.add(operasional);
        }
        return item;
    }

    @Override
    protected void save(Operasional operasional) throws SQLException {
        query = "INSERT INTO "+TABLE+"(id, nama, keterangan, jumlah, tanggal, operator) VALUES (?,?,?,?,?,?)";

        ps = con.prepareStatement(query);
        ps.setString(1, operasional.getId());
        ps.setString(2, operasional.getTujuan());
        ps.setString(2, operasional.getKeterangan());
        ps.setString(3, String.valueOf(operasional.getJumlah()));
        ps.setString(4, operasional.getTanggal());
        ps.setString(5, operasional.getOperator());
        ps.executeUpdate();
    }

    @Override
    protected void update(String[] params) throws SQLException {
        query = "UPDATE "+TABLE+" SET nama=?, keterangan=?, jumlah=?, tanggal=?, operator=? WHERE id=?";
        ps = con.prepareStatement(query);
        ps.setString(1, params[1]);
        ps.setString(2, params[2]);
        ps.setString(3, params[3]);
        ps.setString(4, params[4]);
        ps.setString(5, params[5]);
        ps.setString(5, params[0]);
        ps.executeUpdate();
    }

    @Override
    protected void delete(String id) throws SQLException {
        query = "DELETE FROM "+TABLE+" WHERE id=?";
        ps = con.prepareStatement(query);
        ps.setString(1, id);
        ps.executeUpdate();
    }

    public boolean isDataExist(String id) throws SQLException {
        query = "SELECT id FROM "+TABLE+" WHERE id=?";
        ps = con.prepareStatement(query);
        ps.setString(1, id);
        rs = ps.executeQuery();

        return rs.next();
    }
}
