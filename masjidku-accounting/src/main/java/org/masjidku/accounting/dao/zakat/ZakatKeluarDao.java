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

package org.masjidku.accounting.dao.zakat;
import org.masjidku.accounting.client.model.zakat.*;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.masjidku.accounting.dao.base.Dao;

import java.sql.SQLException;

public class ZakatKeluarDao extends Dao<ZakatKeluar> {
    private static final String QUERY_1 = "SELECT * FROM penerima_zakat WHERE id=?";
    private static final String QUERY_2 = "SELECT * FROM penerima_zakat";
    private static final String QUERY_3 = "INSERT INTO penerima_zakat(id, nama, jumlah, tanggal, operator) VALUES (?,?,?,?,?)";
    private static final String QUERY_4 = "UPDATE penerima_zakat SET nama=?, jumlah=?, tanggal=?, operator=? WHERE id=?";
    private static final String QUERY_5 = "DELETE FROM penerima_zakat WHERE id=?";
    private static final String QUERY_6 = "SELECT * FROM penerima_zakat ORDER BY ID DESC LIMIT 1";
    private static final String QUERY_7 = "SELECT IFNULL(SUM(jumlah),0) FROM penerima_zakat";
    private static final String QUERY_8 = "SELECT id FROM penerima_zakat WHERE id=?";


    

    @Override
    public ZakatKeluar get(String id) throws SQLException {
        ps = con.prepareStatement(QUERY_1);
        ps.setString(1, id);
        rs = ps.executeQuery();

        ZakatKeluar model = null;
        if (rs.next()){
            model = new ZakatKeluar(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
            );
        }
        return model;
    }

    @Override
    public ObservableList<ZakatKeluar> getAll() throws SQLException {
        ObservableList<ZakatKeluar> item = FXCollections.observableArrayList();

        ps = con.prepareStatement(QUERY_2);
        rs = ps.executeQuery();

        ZakatKeluar operasional;
        while (rs.next()){
            operasional = new ZakatKeluar(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
            );
            item.add(operasional);
        }
        return item;
    }

    @Override
    public void save(ZakatKeluar zakatKeluar) throws SQLException {
        executeUpdateQuery(QUERY_3, zakatKeluar.getId(), zakatKeluar.getTujuan(), zakatKeluar.getKeterangan(), zakatKeluar.getJumlah(), zakatKeluar.getTanggal(), zakatKeluar.getOperator());
    }

    @Override
    public void update(String[] params) throws SQLException {
        executeUpdateQuery(QUERY_4, params[1], params[2], params[3], params[4], params[0]);
    }

    @Override
    public void delete(String id) throws SQLException { executeDelete(QUERY_5, id); }

    public ZakatKeluar getLastRecord() throws SQLException {
        ps = con.prepareStatement(QUERY_6);
        rs = ps.executeQuery();

        ZakatKeluar model = new ZakatKeluar();
        if (rs.next()){
            model = new ZakatKeluar(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
            );
        }
        return model;
    }

    public String gettotalOutcome() throws SQLException {
        ps = con.prepareStatement(QUERY_7);
        rs = ps.executeQuery();

        if (rs.next()){
            return rs.getString(1);
        }
        return "0";
    }

    public boolean isDataExist(String id) throws SQLException { return executeCheckExists(QUERY_8, id); }
}
