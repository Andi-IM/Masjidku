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

package org.masjidku.model.accounting.anakyatim;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.masjidku.model.Dao;
import org.masjidku.model.DaoFactory;
import org.masjidku.model.user.User;

import java.sql.SQLException;

public class DonasiAYatimDao extends Dao<DonasiAYatim> {

    private final String TABLE = "infak_anakyatim";

    @Override
    public DonasiAYatim get(String id) throws SQLException {
        return null;
    }

    @Override
    public ObservableList<DonasiAYatim> getAll() throws SQLException {
        ObservableList<DonasiAYatim> anakyatim = FXCollections.observableArrayList();

        query = "SELECT id, donatur, jumlah, tanggal, operator from infak_anakyatim";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        DonasiAYatim donasiayatim;
        while (rs.next()) {
            donasiayatim = new DonasiAYatim();
            donasiayatim.setId(rs.getString(1));
            donasiayatim.setDonatur(rs.getString(2));
            donasiayatim.setJumlah(rs.getDouble(3));
            donasiayatim.setTanggal(rs.getString(4));
            donasiayatim.setOperator(rs.getString(5));

        }

        return anakyatim;
    }

    @Override
    public void save(DonasiAYatim donasiAYatim) throws SQLException {
        query = "INSERT INTO " + TABLE + "(id, donatur, jumlah, tanggal, operator) VALUES(?,?,?,?,?)";
        ps = con.prepareStatement(query);
        ps.setString(1, donasiAYatim.getId());
        ps.setString(2, donasiAYatim.getDonatur());
        ps.setDouble(3, donasiAYatim.getJumlah());
        ps.setString(4, donasiAYatim.getTanggal());
        ps.setString(5, donasiAYatim.getOperator());
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

