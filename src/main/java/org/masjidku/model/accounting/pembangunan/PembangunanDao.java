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

package org.masjidku.model.accounting.pembangunan;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.masjidku.model.Dao;

import java.sql.SQLException;

public class PembangunanDao extends Dao<Pembangunan> {

    private final String TABLE = "pembangunan_keluar";

    @Override
    public Pembangunan get(String id) throws SQLException {
        query = "SELECT * FROM "+TABLE+" WHERE id=?";
        ps = con.prepareStatement(query);
        ps.setString(1, id);
        rs = ps.executeQuery();

        Pembangunan model = null;
        if (rs.next()){
            model = new Pembangunan(
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

    @Override
    public ObservableList<Pembangunan> getAll() throws SQLException {
        ObservableList<Pembangunan> item = FXCollections.observableArrayList();

        query = "SELECT * FROM "+TABLE;
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        Pembangunan pembangunan;
        while (rs.next()){
            pembangunan = new Pembangunan(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
            );
            item.add(pembangunan);
        }
        return item;
    }

    @Override
    public void save(Pembangunan pembangunan) throws SQLException {
        query = "INSERT INTO "+TABLE+"(id, nama, keterangan, jumlah, tanggal, operator) VALUES (?,?,?,?,?,?)";

        ps = con.prepareStatement(query);
        ps.setString(1, pembangunan.getId());
        ps.setString(2, pembangunan.getTujuan());
        ps.setString(2, pembangunan.getKeterangan());
        ps.setString(3, pembangunan.getJumlah());
        ps.setString(4, pembangunan.getTanggal());
        ps.setString(5, pembangunan.getOperator());
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

    public Pembangunan getLastRecord() throws SQLException {
        query = "SELECT * FROM "+TABLE+" ORDER BY ID DESC LIMIT 1";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        Pembangunan model = new Pembangunan();
        if (rs.next()){
            model = new Pembangunan(
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

    public String getTotalIncome() throws SQLException {
        query = "SELECT IFNULL(SUM(jumlah),0) FROM "+TABLE;
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        if (rs.next()){
            return rs.getString(1);
        }
        return null;
    }

    public boolean isDataExist(String id) throws SQLException {
        query = "SELECT id FROM "+TABLE+" WHERE id=?";
        ps = con.prepareStatement(query);
        ps.setString(1, id);
        rs = ps.executeQuery();

        return rs.next();
    }
}
