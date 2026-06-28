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
import org.masjidku.events.dao.base.Dao;
import java.sql.SQLException;
import org.masjidku.events.client.service.KegiatanService;
import org.masjidku.events.client.model.Kegiatan;
public class KegiatanDao extends Dao<Kegiatan> implements KegiatanService {
    private static final String QUERY_1 = "SELECT * FROM kegiatan WHERE kegiatanID=?";
    private static final String QUERY_2 = "SELECT * FROM kegiatan";
    private static final String QUERY_3 = "INSERT INTO kegiatan"+ "(kegiatanNama, kegiatanWaktu, kegiatanTanggal, kegiatanTempat, operator) VALUES(?,?,?,?,?)";
    private static final String QUERY_4 = "UPDATE kegiatan SET kegiatanNama=?, kegiatanWaktu=?, kegiatanTanggal=?, kegiatanTempat=?, operator=? WHERE kegiatanID=?";
    private static final String QUERY_5 = "DELETE FROM kegiatan WHERE kegiatanID=?";
    private static final String QUERY_6 = "SELECT kegiatanID FROM kegiatan WHERE kegiatanID=?";
    private static final String QUERY_7 = "SELECT * FROM kegiatan";
    private static final String QUERY_8 = "SELECT kegiatanID FROM kegiatan WHERE kegiatanNama=?";
    private static final String QUERY_9 = "SELECT * FROM kegiatan ORDER BY kegiatanID DESC LIMIT 1";
    private static final String QUERY_10 = "SELECT IFNULL(COUNT(kegiatanID),0) FROM kegiatan";

    public KegiatanDao() {
        getConnection();
    }
    
    @Override
    public Kegiatan get(String id) throws SQLException {
        ps = con.prepareStatement(QUERY_1);
        ps.setString(1, id);
        rs = ps.executeQuery();
        Kegiatan model = null;
        if(rs.next()){
            model = new Kegiatan(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
            );
        }
        return model;
    }
    @Override
    public ObservableList<Kegiatan> getAll() throws SQLException {
        ObservableList<Kegiatan> items = FXCollections.observableArrayList();
        ps = con.prepareStatement(QUERY_2);
        rs = ps.executeQuery();
        Kegiatan kegiatan;
        while(rs.next()){
            kegiatan = new Kegiatan(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
            );
            items.add(kegiatan);
        }
        return items;
    }
    @Override
    public void save(Kegiatan kegiatan) throws SQLException {
        executeUpdateQuery(QUERY_3, kegiatan.getNama(), kegiatan.getWaktu(), kegiatan.getTanggal(), kegiatan.getTempat(), kegiatan.getOperator());
    }
    @Override
    public void update(String[] params) throws SQLException {
        executeUpdateQuery(QUERY_4, params[0], params[1], params[2], params[3], params[4]);
    }
    @Override
    public void delete(String id) throws SQLException { executeDelete(QUERY_5, id); }
    public boolean isKegiatanExist(String id) throws SQLException { return executeCheckExists(QUERY_6, id); }
    public ObservableList<String> getAllKegiatanName() throws SQLException {
        ObservableList<String> items = FXCollections.observableArrayList();
        ps = con.prepareStatement(QUERY_7);
        rs = ps.executeQuery();
        String name;
        while(rs.next()){
            name = rs.getString(2);
            items.add(name);
        }
        return items;
    }
    public String getIdByName(String name) throws SQLException {
        ps = con.prepareStatement(QUERY_8);
        ps.setString(1, name);
        rs = ps.executeQuery();
        if(rs.next()){
            return rs.getString(1);
        }
        return "";
    }
    public Kegiatan getLastRecord() throws SQLException {
        ps = con.prepareStatement(QUERY_9);
        rs = ps.executeQuery();
        Kegiatan model = new Kegiatan();
        if (rs.next()){
            model = new Kegiatan();
            model.setIdKegiatan(rs.getString(1));
            model.setNama(rs.getString(2));
            model.setWaktu(rs.getString(3));
            model.setTanggal(rs.getString(4));
            model.setTanggal(rs.getString(5));
            model.setOperator(rs.getString(6));
        }
        return model;
    }
    public String getTotalKegiatan() throws SQLException { return executeGetTotal(QUERY_10); }
}
