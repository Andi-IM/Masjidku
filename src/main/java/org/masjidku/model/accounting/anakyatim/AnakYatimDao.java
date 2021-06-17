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

import com.google.common.hash.Hashing;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.masjidku.model.Dao;
import org.masjidku.model.user.User;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class AnakYatimDao extends Dao<AnakYatim> {

    private final String TABLE = "penerima_anakyatim";

    @Override
    public AnakYatim get(String id) throws SQLException {
        return null;
    }

    @Override
    public ObservableList<AnakYatim> getAll() throws SQLException {
        ObservableList<AnakYatim> donatur = FXCollections.observableArrayList();
        query = "SELECT id, nama, usia, jumlah, tanggal, operator FROM penerima_anakyatim" ;
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        AnakYatim anakyatim;
        while (rs.next()) {
            anakyatim = new AnakYatim();
            anakyatim.setId(rs.getString(1));
            anakyatim.setTujuan(rs.getString(2));
            anakyatim.setUsia(rs.getString(3));
            anakyatim.setJumlah(rs.getLong(4));
            anakyatim.setTanggal(rs.getString(5));
            anakyatim.setOperator(rs.getString(6));
        }

        return donatur;
    }

    @Override
    public void save(AnakYatim anakYatim) throws SQLException {
        query = "INSERT INTO " + TABLE + "(id, nama, usia, jumlah, tanggal, operator) VALUES(?,?,?,?,?)";
        ps = con.prepareStatement(query);
        ps.setString(1, anakYatim.getId());
        ps.setString(2, anakYatim.getNama());
        ps.setString(3, anakYatim.getUsia());
        ps.setString(4, anakYatim.getJumlah());
        ps.setString(5, anakYatim.getTanggal());
        ps.setString(6, anakYatim.getOperator());
        ps.executeUpdate();
    }

    @Override
    public void update(String[] params) throws SQLException {
        query = "UPDATE " + TABLE + " SET nama=?, usia=? ,jumlah=?, tanggal=? , operator=? WHERE id=?";
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

