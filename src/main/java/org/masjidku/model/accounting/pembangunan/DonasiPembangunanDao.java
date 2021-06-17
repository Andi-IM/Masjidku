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
import org.masjidku.model.accounting.operasional.DonasiOperasional;

import java.sql.SQLException;

public class DonasiPembangunanDao extends Dao<DonasiPembangunan> {

    private final String TABLE = "infak_pembangunan";

    @Override
    protected DonasiPembangunan get(String id) throws SQLException {
        query = "SELECT * FROM "+TABLE+" WHERE id=?";
        ps = con.prepareStatement(query);
        ps.setString(1, id);
        rs = ps.executeQuery();

        DonasiPembangunan model = null;
        if (rs.next()){
            model = new DonasiPembangunan(
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
    protected ObservableList<DonasiPembangunan> getAll() throws SQLException {
        ObservableList<DonasiPembangunan> donatur = FXCollections.observableArrayList();

        query = "SELECT * FROM "+TABLE;
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        DonasiPembangunan donasi;
        while (rs.next()){
            donasi = new DonasiPembangunan(
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
    protected void save(DonasiPembangunan donasiPembangunan) throws SQLException {
        query = "INSERT INTO "+TABLE+"(id, donatur, jumlah, tanggal, operator) VALUES (?,?,?,?,?)";

        ps = con.prepareStatement(query);
        ps.setString(1, donasiPembangunan.getId());
        ps.setString(2, donasiPembangunan.getDonatur());
        ps.setString(3, String.valueOf(donasiPembangunan.getJumlah()));
        ps.setString(4, donasiPembangunan.getTanggal());
        ps.setString(5, donasiPembangunan.getOperator());
        ps.executeUpdate();
    }

    @Override
    protected void update(String[] params) throws SQLException {
        query = "UPDATE "+TABLE+" SET donatur=?, jumlah=?, tanggal=?, operator=? WHERE id=?";
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
