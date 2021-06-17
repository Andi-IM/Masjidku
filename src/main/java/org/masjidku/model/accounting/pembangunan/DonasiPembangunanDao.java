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
import org.masjidku.model.accounting.zakat.ZakatKeluar;

import java.sql.SQLException;

public class DonasiPembangunanDao extends Dao<DonasiPembangunan> {

    private final String TABLE = "infak_pembangunan";

    @Override
    public DonasiPembangunan get(String id) throws SQLException {
        return null;
    }

    @Override
    public ObservableList<DonasiPembangunan> getAll() throws SQLException {
        ObservableList<DonasiPembangunan> pembangunan = FXCollections.observableArrayList();

        query = "SELECT id, donatur, jumlah, tanggal, operator from infak_pembangunan";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

       DonasiPembangunan donasipembangunan;
        while (rs.next()) {
            donasipembangunan = new DonasiPembangunan();
            donasipembangunan.setId(rs.getString(1));
            donasipembangunan.setDonatur(rs.getString(2));
            donasipembangunan.setJumlah(rs.getDouble(3));
            donasipembangunan.setTanggal(rs.getString(4));
            donasipembangunan.setOperator(rs.getString(5));

        }
        return pembangunan;
    }

    @Override
    public void save(DonasiPembangunan donasiPembangunan) throws SQLException {
        query = "INSERT INTO " + TABLE + "(id, donatur, jumlah, tanggal, operator) VALUES(?,?,?,?,?)";
        ps = con.prepareStatement(query);
        ps.setString(1, donasiPembangunan.getId());
        ps.setString(2, donasiPembangunan.getDonatur());
        ps.setDouble(3, donasiPembangunan.getJumlah());
        ps.setString(4, donasiPembangunan.getTanggal());
        ps.setString(5, donasiPembangunan.getOperator());
        ps.executeUpdate();
    }

    @Override
    public void update(String[] params) throws SQLException {
        query = "UPDATE " + TABLE + " SET  donatur=? ,jumlah=?, tanggal=? , operator=? WHERE id=?";
        ps = con.prepareStatement(query);
        ps.setString(1, params[0]);
        ps.setString(2, params[1]);
        ps.setString(3, params[2]);
        ps.setString(4, params[3]);
        ps.setString(5, params[4]);
        ps.setString(6, params[5]);
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
