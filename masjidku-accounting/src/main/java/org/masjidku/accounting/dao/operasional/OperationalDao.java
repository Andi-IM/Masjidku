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

package org.masjidku.accounting.dao.operasional;
import org.intellij.lang.annotations.Language;
import org.masjidku.accounting.client.model.operasional.*;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.masjidku.accounting.dao.base.Dao;

import java.sql.SQLException;

public class OperationalDao extends Dao<Operasional> {
    private static final String QUERY_1 = "SELECT * FROM operasional_keluar WHERE id=?";
    private static final String QUERY_2 = "SELECT * FROM operasional_keluar";
    @Language("SQL")
    private static final String QUERY_3 = "INSERT INTO operasional_keluar(id, nama, keterangan, jumlah, tanggal, operator) VALUES (?,?,?,?,?,?)";
    @Language("SQL")
    private static final String QUERY_4 = "UPDATE operasional_keluar SET nama=?, keterangan=?, jumlah=?, tanggal=?, operator=? WHERE id=?";
    private static final String QUERY_5 = "DELETE FROM operasional_keluar WHERE id=?";
    @Language("SQL")
    private static final String QUERY_6 = "SELECT id FROM operasional_keluar WHERE id=?";
    @Language("SQL")
    private static final String QUERY_7 = "SELECT * FROM operasional_keluar ORDER BY ID DESC LIMIT 1";
    @Language("SQL")
    private static final String QUERY_8 = "SELECT IFNULL(SUM(jumlah),0) FROM operasional_keluar";


    

    @Override
    public Operasional get(String id) throws SQLException {
        ps = con.prepareStatement(QUERY_1);
        ps.setString(1, id);
        rs = ps.executeQuery();

        Operasional model = null;
        if (rs.next()){
            model = new Operasional(
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
    public ObservableList<Operasional> getAll() throws SQLException {
        ObservableList<Operasional> item = FXCollections.observableArrayList();

        ps = con.prepareStatement(QUERY_2);
        rs = ps.executeQuery();

        Operasional operasional;
        while (rs.next()){
            operasional = new Operasional(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
            );
            item.add(operasional);
        }
        return item;
    }

    @Override
    public void save(Operasional operasional) throws SQLException {
        executeUpdateQuery(QUERY_3, operasional.getId(), operasional.getTujuan(), operasional.getKeterangan(), operasional.getJumlah(), operasional.getTanggal(), operasional.getOperator());
    }

    @Override
    public void update(String[] params) throws SQLException {
        executeUpdateQuery(QUERY_4, params[1], params[2], params[3], params[4], params[5], params[0]);
    }

    @Override
    public void delete(String id) throws SQLException { executeDelete(QUERY_5, id); }

    public boolean isDataExist(String id) throws SQLException { return executeCheckExists(QUERY_6, id); }

    public Operasional getLastRecord() throws SQLException {
        return executeGetLastRecord(QUERY_7, rs1 -> new Operasional(rs1.getString(1), rs1.getString(2), rs1.getString(3), rs1.getString(4), rs1.getString(5), rs1.getString(6)));
    }

    public String getTotalIncome() throws SQLException { return executeGetTotal(QUERY_8); }
}

