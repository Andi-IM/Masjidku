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
import org.masjidku.model.DaoFactory;
import org.masjidku.model.accounting.anakyatim.AnakYatim;

import java.sql.SQLException;

public class PembangunanDao extends Dao<Pembangunan> {

    private final String TABLE = "pembangunan_keluar";

    @Override
    public Pembangunan get(String id) throws SQLException {
        return null;
    }

    @Override
    public ObservableList<Pembangunan> getAll() throws SQLException {
        ObservableList<Pembangunan> donasipembangunan = FXCollections.observableArrayList();
        query = "SELECT id, nama, keterangan, jumlah, tanggal, operator FROM pembangunan_keluar" ;
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        Pembangunan pembangunan;
        while (rs.next()) {
            pembangunan = new Pembangunan();
            pembangunan.setId(rs.getString(1));
            pembangunan.setTujuan(rs.getString(2));
            pembangunan.setKeterangan(rs.getString(3));
            pembangunan.setJumlah(rs.getDouble(4));
            pembangunan.setTanggal(rs.getString(5));
            pembangunan.setOperator(rs.getString(6));
        }

        return donasipembangunan;
    }

    @Override
    public void save(Pembangunan pembangunan) throws SQLException {
        query = "INSERT INTO " + TABLE + "(id, nama, keterangan, jumlah, tanggal, operator) VALUES(?,?,?,?,?)";
        ps = con.prepareStatement(query);
        ps.setString(1, pembangunan.getId());
        ps.setString(2, pembangunan.getTujuan());
        ps.setString(3, pembangunan.getKeterangan());
        ps.setDouble(4, pembangunan.getJumlah());
        ps.setString(5, pembangunan.getTanggal());
        ps.setString(6, pembangunan.getOperator());
        ps.executeUpdate();
    }

    @Override
    public void update(String[] params) throws SQLException {
        query = "UPDATE " + TABLE + " SET  nama=? , keterangan =?,jumlah=?, tanggal=? , operator=? WHERE id=?";
        ps = con.prepareStatement(query);
        ps.setString(1, params[0]);
        ps.setString(2, params[1]);
        ps.setString(3, params[2]);
        ps.setString(4, params[3]);
        ps.setString(5, params[4]);
        ps.setString(6, params[5]);
        ps.setString(7, params[6]);
        ps.executeUpdate();
    }

    @Override
    public void delete(String id) throws SQLException {
        query = "DELETE FROM " + TABLE + " WHERE id=?";
        ps = con.prepareStatement(query);
        ps.setString(1, id);
        ps.executeUpdate();
    }
}
