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

package org.masjidku.model.kegiatan;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TamuKegiatanDao extends TamuKegiatanDaoFactory<TamuKegiatan> {
    private final String ACTIVITY = "kegiatan";
    private final String GUEST = "tamu";
    private final String TABLE = "tamukegiatan";

    @Override
    public TamuKegiatan get(String id) throws SQLException {
        query = "SELECT " +
                "id_undangan, " +
                "id_tamu, " +
                "id_kegiatan, " +
                "g.tamuNama, " +
                "g.tamuAlamat, " +
                "g.tamuNotelp, " +
                "k.kegiatanNama, " +
                "keterangan " +
                "FROM " + TABLE +
                " INNER JOIN " + GUEST + " g " +
                " ON tamukegiatan.id_tamu = g.tamuID " +
                "INNER JOIN " + ACTIVITY + " k " +
                "ON tamukegiatan.id_kegiatan = k.kegiatanID " +
                "WHERE id_undangan=?";

        ps = con.prepareStatement(query);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();

        TamuKegiatan model = null;
        if (rs.next()) {
            model = new TamuKegiatan();
            model.setIdUndangan(rs.getString(1));
            model.setIdTamu(rs.getString(2));
            model.setIdKegiatan(rs.getString(3));
            model.setNama(rs.getString(4));
            model.setAlamat(rs.getString(5));
            model.setKegiatan(rs.getString(6));
            model.setNotelp(rs.getString(7));
            model.setKeterangan(rs.getString(8));
        }
        return model;
    }

    @Override
    public ObservableList<TamuKegiatan> getAll() throws SQLException {
        ObservableList<TamuKegiatan> items = FXCollections.observableArrayList();
        query = "SELECT " +
                "id_undangan, " +
                "id_tamu, " +
                "id_kegiatan, " +
                "g.tamuNama, " +
                "g.tamuAlamat, " +
                "g.tamuNotelp, " +
                "k.kegiatanNama, " +
                "keterangan " +
                "FROM " + TABLE +
                " INNER JOIN " + GUEST + " g " +
                " ON tamukegiatan.id_tamu = g.tamuID " +
                "INNER JOIN " + ACTIVITY + " k " +
                "ON tamukegiatan.id_kegiatan = k.kegiatanID";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        TamuKegiatan model;
        while (rs.next()) {
            model = new TamuKegiatan();
            model.setIdUndangan(rs.getString(1));
            model.setIdTamu(rs.getString(2));
            model.setIdKegiatan(rs.getString(3));
            model.setNama(rs.getString(4));
            model.setAlamat(rs.getString(5));
            model.setKegiatan(rs.getString(6));
            model.setNotelp(rs.getString(7));
            model.setKeterangan(rs.getString(8));
            items.add(model);
        }
        return items;
    }

    @Override
    public void save(TamuKegiatan tamuKegiatan) throws SQLException {
    }

    @Override
    public void save(String idKegiatan, String idTamu, String keterangan, String opeartor) throws SQLException {
        String query = "INSERT INTO " + TABLE + "(id_kegiatan, id_tamu, keterangan, operator) VALUES(?,?,?,?)";

        ps = con.prepareStatement(query);
        ps.setString(1, idKegiatan);
        ps.setString(2, idTamu);
        ps.setString(3, keterangan);
        ps.setString(4, opeartor);
        ps.executeUpdate();
    }

    @Override
    public void update(String[] params) throws SQLException {
        String query = "UPDATE " + TABLE + " SET keterangan=? WHERE id_tamu=? and id_kegiatan=? and id_undangan=?";

        ps = con.prepareStatement(query);
        ps.setString(1, params[0]);
        ps.setString(2, params[1]);
        ps.setString(3, params[2]);
        ps.setString(4, params[3]);
        ps.executeUpdate();
    }

    @Override
    public void delete(String id) throws SQLException {
        String query = "DELETE FROM " + TABLE + " WHERE id_undangan=?";
        ps = con.prepareStatement(query);
        ps.setString(1, id);
        ps.executeUpdate();
    }

    @Override
    public boolean isUndanganExist(String id) throws SQLException {
        String query = "SELECT id_undangan FROM " + TABLE + " WHERE id_undangan=?";
        ps = con.prepareStatement(query);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }
}
