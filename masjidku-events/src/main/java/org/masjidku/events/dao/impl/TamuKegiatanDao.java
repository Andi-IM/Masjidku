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
package org.masjidku.events.dao.impl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.masjidku.events.client.model.TamuKegiatan;
import org.masjidku.events.dao.base.Dao;
public class TamuKegiatanDao extends Dao<TamuKegiatan> implements org.masjidku.events.client.service.TamuKegiatanService {
    private static final String QUERY_1 = "SELECT " +
                "id_undangan, " +
                "id_tamu, " +
                "id_kegiatan, " +
                "g.tamuNama, " +
                "g.tamuAlamat, " +
                "g.tamuNotelp, " +
                "k.kegiatanNama, " +
                "keterangan " +
                "FROM tamukegiatan " +
                "INNER JOIN tamu g " +
                "ON tamukegiatan.id_tamu = g.tamuID " +
                "INNER JOIN kegiatan k " +
                "ON tamukegiatan.id_kegiatan = k.kegiatanID " +
                "WHERE id_undangan=?";
    private static final String QUERY_2 = "SELECT " +
                "id_undangan, " +
                "id_tamu, " +
                "id_kegiatan, " +
                "g.tamuNama, " +
                "g.tamuAlamat, " +
                "g.tamuNotelp, " +
                "k.kegiatanNama, " +
                "keterangan " +
                "FROM tamukegiatan " +
                "INNER JOIN tamu g " +
                "ON tamukegiatan.id_tamu = g.tamuID " +
                "INNER JOIN kegiatan k " +
                "ON tamukegiatan.id_kegiatan = k.kegiatanID";
    private static final String QUERY_3 = "INSERT INTO tamukegiatan(id_kegiatan, id_tamu, keterangan, operator) VALUES(?,?,?,?)";
    private static final String QUERY_4 = "UPDATE tamukegiatan SET keterangan=? WHERE id_tamu=? and id_kegiatan=? and id_undangan=?";
    private static final String QUERY_5 = "DELETE FROM tamukegiatan WHERE id_undangan=?";
    private static final String QUERY_6 = "SELECT id_undangan FROM tamukegiatan WHERE id_undangan=?";

    public TamuKegiatanDao() {
        getConnection();
    }
    private final String ACTIVITY = "kegiatan";
    private final String GUEST = "tamu";
    
    public TamuKegiatan get(String id) throws SQLException {
        ps = con.prepareStatement(QUERY_1);
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
    public ObservableList<TamuKegiatan> getAll() throws SQLException {
        ObservableList<TamuKegiatan> items = FXCollections.observableArrayList();
        ps = con.prepareStatement(QUERY_2);
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
    public void save(TamuKegiatan tamuKegiatan) {
    }
    public void save(String idKegiatan, String idTamu, String keterangan, String opeartor) throws SQLException {
        executeUpdateQuery(QUERY_3, idKegiatan, idTamu, keterangan, opeartor);
    }
    public void update(String[] params) throws SQLException {
        executeUpdateQuery(QUERY_4, params[0], params[1], params[2], params[3]);
    }
    public void delete(String id) throws SQLException { executeDelete(QUERY_5, id); }
    public boolean isUndanganExist(String id) throws SQLException {
        ps = con.prepareStatement(QUERY_6);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }
}
