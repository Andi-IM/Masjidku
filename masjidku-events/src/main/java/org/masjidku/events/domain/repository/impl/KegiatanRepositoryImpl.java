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
import org.masjidku.events.client.model.Kegiatan;

import org.masjidku.events.domain.repository.base.BaseRepository;
import org.masjidku.events.domain.repository.KegiatanRepository;

import java.sql.SQLException;

public class KegiatanRepositoryImpl extends BaseRepository<Kegiatan> implements KegiatanRepository {
    private static final String QUERY_1 = "SELECT * FROM kegiatan WHERE kegiatanID=?";
    private static final String QUERY_2 = "SELECT * FROM kegiatan";
    @Language("SQL")
    private static final String QUERY_3 = "INSERT INTO kegiatan" + "(kegiatanNama, kegiatanWaktu, kegiatanTanggal, kegiatanTempat, operator) VALUES(?,?,?,?,?)";
    @Language("SQL")
    private static final String QUERY_4 = "UPDATE kegiatan SET kegiatanNama=?, kegiatanWaktu=?, kegiatanTanggal=?, kegiatanTempat=?, operator=? WHERE kegiatanID=?";
    @Language("SQL")
    private static final String QUERY_5 = "DELETE FROM kegiatan WHERE kegiatanID=?";
    @Language("SQL")
    private static final String QUERY_6 = "SELECT kegiatanID FROM kegiatan WHERE kegiatanID=?";
    @Language("SQL")
    private static final String QUERY_7 = "SELECT * FROM kegiatan";
    @Language("SQL")
    private static final String QUERY_8 = "SELECT kegiatanID FROM kegiatan WHERE kegiatanNama=?";
    private static final String QUERY_9 = "SELECT * FROM kegiatan ORDER BY kegiatanID DESC LIMIT 1";
    @Language("SQL")
    private static final String QUERY_10 = "SELECT IFNULL(COUNT(kegiatanID),0) FROM kegiatan";

    public KegiatanRepositoryImpl() {
        getConnection();
    }

    @Override
    public Kegiatan getKegiatanById(String id) throws SQLException {
        return executeGet(QUERY_1, id, this::mapResultSetToModel);
    }

    @Override
    public ObservableList<Kegiatan> getAllKegiatan() throws SQLException {
        return executeGetAll(QUERY_2, this::mapResultSetToModel);
    }

    private Kegiatan mapResultSetToModel(java.sql.ResultSet rs) throws SQLException {
        return new Kegiatan(
                rs.getString(1),
                rs.getString(2),
                java.time.LocalTime.parse(rs.getString(3)),
                java.time.LocalDate.parse(rs.getString(4)),
                rs.getString(5),
                rs.getString(6)
        );
    }

    @Override
    public void saveKegiatan(Kegiatan kegiatan) throws SQLException {
        executeUpdateQuery(QUERY_3, kegiatan.getNama(), kegiatan.getWaktu().toString(), kegiatan.getTanggal().toString(), kegiatan.getTempat(), kegiatan.getOperator());
    }

    @Override
    public void updateKegiatan(String[] params) throws SQLException {
        executeUpdateQuery(QUERY_4, params[0], params[1], params[2], params[3], params[4]);
    }

    @Override
    public void deleteKegiatan(String id) throws SQLException {
        executeDelete(QUERY_5, id);
    }

    public boolean exists(String id) throws SQLException {
        return executeCheckExists(QUERY_6, id);
    }

    public ObservableList<String> getAllKegiatanNames() throws SQLException {
        return executeGetAllNames(QUERY_7);
    }

    public String getIdByName(String name) throws SQLException {
        return executeGetIdByName(QUERY_8, name);
    }

    public Kegiatan getLastKegiatan() throws SQLException {
        return executeGet(QUERY_9, null, rs -> {
            Kegiatan model = new Kegiatan();
            model.setIdKegiatan(rs.getString(1));
            model.setNama(rs.getString(2));
            model.setWaktu(java.time.LocalTime.parse(rs.getString(3)));
            model.setTanggal(java.time.LocalDate.parse(rs.getString(4)));
            model.setTanggal(java.time.LocalDate.parse(rs.getString(4)));
            model.setOperator(rs.getString(6));
            return model;
        });
    }

    public String getTotalKegiatanCount() throws SQLException {
        return executeGetTotal(QUERY_10);
    }
}








