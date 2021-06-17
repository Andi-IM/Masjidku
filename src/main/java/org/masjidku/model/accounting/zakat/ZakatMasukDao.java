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
import org.masjidku.model.accounting.operasional.DonasiOperasional;

import java.sql.SQLException;

public class ZakatMasukDao extends Dao<ZakatMasuk> {

    private final String TABLE = "pemberi_zakat";

    @Override
    protected ZakatMasuk get(String id) throws SQLException {
        query = "SELECT * FROM "+TABLE+" WHERE id=?";
        ps = con.prepareStatement(query);
        ps.setString(1, id);
        rs = ps.executeQuery();

        ZakatMasuk model = null;
        if (rs.next()){
            model = new ZakatMasuk(
                    rs.getString(1),
                    rs.getString(2),
                    Double.parseDouble(rs.getString(3)),
                    rs.getString(4),
                    rs.getString(5)
            );
        }
        return model;
    }

    @Override
    protected ObservableList<ZakatMasuk> getAll() throws SQLException {
        ObservableList<ZakatMasuk> donatur = FXCollections.observableArrayList();

        query = "SELECT * FROM "+TABLE;
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        ZakatMasuk donasi;
        while (rs.next()){
            donasi = new ZakatMasuk(
                    rs.getString(1),
                    rs.getString(2),
                    Double.parseDouble(rs.getString(3)),
                    rs.getString(4),
                    rs.getString(5)
            );
            donatur.add(donasi);
        }
        return donatur;
    }

    @Override
    protected void save(ZakatMasuk zakatMasuk) throws SQLException {
        query = "INSERT INTO "+TABLE+"(id, nama, jumlah, tanggal, operator) VALUES (?,?,?,?,?)";

        ps = con.prepareStatement(query);
        ps.setString(1, zakatMasuk.getId());
        ps.setString(2, zakatMasuk.getDonatur());
        ps.setString(3, String.valueOf(zakatMasuk.getJumlah()));
        ps.setString(4, zakatMasuk.getTanggal());
        ps.setString(5, zakatMasuk.getOperator());
        ps.executeUpdate();
    }

    @Override
    protected void update(String[] params) throws SQLException {
        query = "UPDATE "+TABLE+" SET nama=?, jumlah=?, tanggal=?, operator=? WHERE id=?";
        ps = con.prepareStatement(query);
        ps.setString(1, params[1]);
        ps.setString(2, params[2]);
        ps.setString(3, params[3]);
        ps.setString(4, params[4]);
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

    public boolean isDonaturExist(String id) throws SQLException {
        query = "SELECT id FROM "+TABLE+" WHERE id=?";
        ps = con.prepareStatement(query);
        ps.setString(1, id);
        rs = ps.executeQuery();

        return rs.next();
    }
}
