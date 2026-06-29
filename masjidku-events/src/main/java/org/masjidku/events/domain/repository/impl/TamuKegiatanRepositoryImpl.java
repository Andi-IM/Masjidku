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
package org.masjidku.events.domain.repository.impl;

import javafx.collections.ObservableList;
import org.intellij.lang.annotations.Language;
import org.masjidku.events.client.model.TamuKegiatan;
import org.masjidku.events.domain.repository.base.BaseRepository;
import org.masjidku.events.domain.repository.TamuKegiatanRepository;

import java.sql.SQLException;

public class TamuKegiatanRepositoryImpl extends BaseRepository<TamuKegiatan> implements TamuKegiatanRepository {
    @Language("SQL")
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
    @Language("SQL")
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
    @Language("SQL")
    private static final String QUERY_3 = "INSERT INTO tamukegiatan(id_kegiatan, id_tamu, keterangan, operator) VALUES(?,?,?,?)";
    @Language("SQL")
    private static final String QUERY_4 = "UPDATE tamukegiatan SET keterangan=? WHERE id_tamu=? and id_kegiatan=? and id_undangan=?";
    @Language("SQL")
    private static final String QUERY_5 = "DELETE FROM tamukegiatan WHERE id_undangan=?";
    private static final String QUERY_6 = "SELECT id_undangan FROM tamukegiatan WHERE id_undangan=?";

    public TamuKegiatanRepositoryImpl() {
        getConnection();
    }

    private final String ACTIVITY = "kegiatan";
    private final String GUEST = "tamu";

    public TamuKegiatan getTamuKegiatanById(String id) {
        try {
            return executeGet(QUERY_1, id, this::mapResultSetToModel);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<TamuKegiatan> getAllTamuKegiatan() throws SQLException {
        return executeGetAll(QUERY_2, this::mapResultSetToModel);
    }

    public void saveTamuKegiatan(String idKegiatan, String idTamu, String keterangan, String opeartor) throws SQLException {
        executeUpdateQuery(QUERY_3, idKegiatan, idTamu, keterangan, opeartor);
    }

    public void updateTamuKegiatan(String[] params) throws SQLException {
        executeUpdateQuery(QUERY_4, params[0], params[1], params[2], params[3]);
    }

    public void deleteTamuKegiatan(String id) throws SQLException {
        executeDelete(QUERY_5, id);
    }

    public boolean isUndanganExist(String id) throws SQLException {
        return executeCheckExists(QUERY_6, id);
    }

    private TamuKegiatan mapResultSetToModel(java.sql.ResultSet rs) throws SQLException {
        org.masjidku.events.client.model.Tamu tamu = new org.masjidku.events.client.model.Tamu();
        tamu.setIdTamu(rs.getString(2));
        tamu.setNama(rs.getString(4));
        tamu.setAlamat(rs.getString(5));
        tamu.setNotelp(rs.getString(6));

        org.masjidku.events.client.model.Kegiatan kegiatan = new org.masjidku.events.client.model.Kegiatan();
        kegiatan.setIdKegiatan(rs.getString(3));
        kegiatan.setNama(rs.getString(7));

        TamuKegiatan model = new TamuKegiatan();
        model.setIdUndangan(rs.getString(1));
        model.setTamu(tamu);
        model.setKegiatanModel(kegiatan);
        model.setKeterangan(rs.getString(8));
        return model;
    }









}
