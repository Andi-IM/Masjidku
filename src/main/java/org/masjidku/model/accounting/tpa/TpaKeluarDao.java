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

package org.masjidku.model.accounting.tpa;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.masjidku.model.Dao;
import org.masjidku.model.accounting.pembangunan.Pembangunan;

import java.sql.SQLException;

public class TpaKeluarDao extends Dao<TpaKeluar> {

    private final String TABLE = "tpa_keluar";

    @Override
    public TpaKeluar get(String id) throws SQLException {
        query = "SELECT * FROM "+TABLE+" WHERE id=?";
        ps = con.prepareStatement(query);
        ps.setString(1, id);
        rs = ps.executeQuery();

        TpaKeluar model = null;
        if (rs.next()){
            model = new TpaKeluar(
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
    public ObservableList<TpaKeluar> getAll() throws SQLException {
        ObservableList<TpaKeluar> item = FXCollections.observableArrayList();

        query = "SELECT * FROM "+TABLE;
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        TpaKeluar operasional;
        while (rs.next()){
            operasional = new TpaKeluar(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
            );
            item.add(operasional);
        }
        return item;
    }

    @Override
    public void save(TpaKeluar tpaKeluar) throws SQLException {
        query = "INSERT INTO "+TABLE+"(id, nama, keterangan, jumlah, tanggal, operator) VALUES (?,?,?,?,?,?)";

        ps = con.prepareStatement(query);
        ps.setString(1, tpaKeluar.getId());
        ps.setString(2, tpaKeluar.getTujuan());
        ps.setString(2, tpaKeluar.getKeterangan());
        ps.setString(3, tpaKeluar.getJumlah());
        ps.setString(4, tpaKeluar.getTanggal());
        ps.setString(5, tpaKeluar.getOperator());
        ps.executeUpdate();
    }

    @Override
    public void update(String[] params) throws SQLException {
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
    public void delete(String id) throws SQLException {
        query = "DELETE FROM "+TABLE+" WHERE id=?";
        ps = con.prepareStatement(query);
        ps.setString(1, id);
        ps.executeUpdate();
    }

    public TpaKeluar getLastRecord() throws SQLException {
        query = "SELECT * FROM "+TABLE+" ORDER BY ID DESC LIMIT 1";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        TpaKeluar model = new TpaKeluar();
        if (rs.next()){
            model = new TpaKeluar(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
            );
        }
        return model;
    }

    public String getTotalOutcome() throws SQLException {
        query = "SELECT IFNULL(SUM(jumlah),0) FROM "+TABLE;
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        if (rs.next()){
            return rs.getString(1);
        }
        return "0";
    }

    public boolean isDataExist(String id) throws SQLException {
        query = "SELECT id FROM "+TABLE+" WHERE id=?";
        ps = con.prepareStatement(query);
        ps.setString(1, id);
        rs = ps.executeQuery();

        return rs.next();
    }
}
