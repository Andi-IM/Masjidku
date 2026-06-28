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

public class ZakatMasukDao extends Dao<ZakatMasuk> {
    private static final String QUERY_1 = "SELECT * FROM pemberi_zakat WHERE id=?";
    private static final String QUERY_2 = "SELECT * FROM pemberi_zakat";
    private static final String QUERY_3 = "INSERT INTO pemberi_zakat(id, nama, jumlah, tanggal, operator) VALUES (?,?,?,?,?)";
    private static final String QUERY_4 = "UPDATE pemberi_zakat SET nama=?, jumlah=?, tanggal=?, operator=? WHERE id=?";
    private static final String QUERY_5 = "DELETE FROM pemberi_zakat WHERE id=?";
    private static final String QUERY_6 = "SELECT * FROM pemberi_zakat ORDER BY ID DESC LIMIT 1";
    private static final String QUERY_7 = "SELECT IFNULL(SUM(jumlah),0) FROM pemberi_zakat";
    private static final String QUERY_8 = "SELECT id FROM pemberi_zakat WHERE id=?";


    

    @Override
    public ZakatMasuk get(String id) throws SQLException {
        ps = con.prepareStatement(QUERY_1);
        ps.setString(1, id);
        rs = ps.executeQuery();

        ZakatMasuk model = null;
        if (rs.next()) {
            model = new ZakatMasuk(
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
    public ObservableList<ZakatMasuk> getAll() throws SQLException {
        ObservableList<ZakatMasuk> donatur = FXCollections.observableArrayList();

        ps = con.prepareStatement(QUERY_2);
        rs = ps.executeQuery();

        ZakatMasuk donasi;
        while (rs.next()) {
            donasi = new ZakatMasuk(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
            );
            donatur.add(donasi);
        }
        return donatur;
    }

    @Override
    public void save(ZakatMasuk zakatMasuk) throws SQLException {
        executeUpdateQuery(QUERY_3, zakatMasuk.getId(), zakatMasuk.getDonatur(), zakatMasuk.getJumlah(), zakatMasuk.getTanggal(), zakatMasuk.getOperator());
    }

    @Override
    public void update(String[] params) throws SQLException {
        executeUpdateQuery(QUERY_4, params[1], params[2], params[3], params[4], params[0]);
    }

    @Override
    public void delete(String id) throws SQLException { executeDelete(QUERY_5, id); }

    public ZakatMasuk getLastRecord() throws SQLException {
        ZakatMasuk res = executeGetLastRecord(QUERY_6, rs1 -> new ZakatMasuk(rs1.getString(1), rs1.getString(2), rs1.getString(3), rs1.getString(4), rs1.getString(5)));
        return res != null ? res : new ZakatMasuk();
    }

    public String getTotalIncome() throws SQLException {
        ps = con.prepareStatement(QUERY_7);
        rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getString(1);
        }
        return "0";
    }

    public boolean isDonaturExist(String id) throws SQLException { return executeCheckExists(QUERY_8, id); }
}


