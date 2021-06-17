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

package org.masjidku.model.accounting.zakat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.masjidku.model.Dao;
import org.masjidku.model.DaoFactory;
import org.masjidku.model.accounting.anakyatim.DonasiAYatim;

import java.sql.SQLException;

public class ZakatKeluarDao extends Dao<ZakatKeluar> {

    private final String TABLE = "penerima_zakat";

    @Override
    public ZakatKeluar get(String id) throws SQLException {
        return null;
    }

    @Override
    public ObservableList<ZakatKeluar> getAll() throws SQLException {
        ObservableList<ZakatKeluar> zakatmasuk = FXCollections.observableArrayList();

        query = "SELECT id, nama, jumlah, tanggal, operator from penerima_zakat";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        ZakatKeluar zakatkeluar;
        while (rs.next()) {
            zakatkeluar = new ZakatKeluar();
            zakatkeluar.setId(rs.getString(1));
            zakatkeluar.setTujuan(rs.getString(2));
            zakatkeluar.setJumlah(rs.getDouble(3));
            zakatkeluar.setTanggal(rs.getString(4));
            zakatkeluar.setOperator(rs.getString(5));

        }
        return zakatmasuk;
    }

    @Override
    public void save(ZakatKeluar zakatKeluar) throws SQLException {
        query = "INSERT INTO " + TABLE + "(id, nama, jumlah, tanggal, operator) VALUES(?,?,?,?,?)";
        ps = con.prepareStatement(query);
        ps.setString(1, zakatKeluar.getId());
        ps.setString(2, zakatKeluar.getTujuan());
        ps.setDouble(3, zakatKeluar.getJumlah());
        ps.setString(4, zakatKeluar.getTanggal());
        ps.setString(5, zakatKeluar.getOperator());
        ps.executeUpdate();
    }

    @Override
    public void update(String[] params) throws SQLException {
        query = "UPDATE " + TABLE + " SET  nama=? ,jumlah=?, tanggal=? , operator=? WHERE id=?";
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
