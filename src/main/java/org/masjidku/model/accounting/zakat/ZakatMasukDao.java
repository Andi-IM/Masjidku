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
import org.masjidku.model.accounting.anakyatim.DonasiAYatim;

import java.sql.SQLException;

public class ZakatMasukDao extends Dao<ZakatMasuk> {

    private final String TABLE = "pemberi_zakat";

    @Override
    public ZakatMasuk get(String id) throws SQLException {
        return null;
    }

    @Override
    public ObservableList<ZakatMasuk> getAll() throws SQLException {
        ObservableList<ZakatMasuk> zakatkeluar = FXCollections.observableArrayList();

        query = "SELECT id, nama, jumlah, tanggal, operator from pemberi_zakat";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        ZakatMasuk zakatmasuk;
        while (rs.next()) {
            zakatmasuk = new ZakatMasuk();
            zakatmasuk.setId(rs.getString(1));
            zakatmasuk.setTujuan(rs.getString(2));
            zakatmasuk.setJumlah(rs.getDouble(3));
            zakatmasuk.setTanggal(rs.getString(4));
            zakatmasuk.setOperator(rs.getString(5));
        }
        return zakatkeluar;
    }

    @Override
    public void save(ZakatMasuk zakatMasuk) throws SQLException {
        query = "INSERT INTO " + TABLE + "(id, nama, jumlah, tanggal, operator) VALUES(?,?,?,?,?)";
        ps = con.prepareStatement(query);
        ps.setString(1, zakatMasuk.getId());
        ps.setString(2, zakatMasuk.getTujuan());
        ps.setDouble(3, zakatMasuk.getJumlah());
        ps.setString(4, zakatMasuk.getTanggal());
        ps.setString(5, zakatMasuk.getOperator());
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
