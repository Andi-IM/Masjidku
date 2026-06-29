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

package org.masjidku.accounting.dao.tpa;
import org.intellij.lang.annotations.Language;
import org.masjidku.accounting.client.model.tpa.*;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.masjidku.accounting.dao.base.Dao;

import java.sql.SQLException;

public class TpaMasukDao extends Dao<TpaMasuk> {
    private static final String QUERY_1 = "SELECT * FROM infak_tpa WHERE id=?";
    private static final String QUERY_2 = "SELECT * FROM infak_tpa";
    @Language("SQL")
    private static final String QUERY_3 = "INSERT INTO infak_tpa(id, donatur, jumlah, tanggal, operator) VALUES (?,?,?,?,?)";
    @Language("SQL")
    private static final String QUERY_4 = "UPDATE infak_tpa SET donatur=?, jumlah=?, tanggal=?, operator=? WHERE id=?";
    private static final String QUERY_5 = "DELETE FROM infak_tpa WHERE id=?";
    @Language("SQL")
    private static final String QUERY_6 = "SELECT * FROM infak_tpa ORDER BY ID DESC LIMIT 1";
    private static final String QUERY_7 = "SELECT IFNULL(SUM(jumlah), 0) FROM infak_tpa";
    @Language("SQL")
    private static final String QUERY_8 = "SELECT id FROM infak_tpa WHERE id=?";


    

    @Override
    public TpaMasuk get(String id) throws SQLException {
        ps = con.prepareStatement(QUERY_1);
        ps.setString(1, id);
        rs = ps.executeQuery();

        TpaMasuk model = null;
        if (rs.next()){
            model = new TpaMasuk(
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
    public ObservableList<TpaMasuk> getAll() throws SQLException {
        ObservableList<TpaMasuk> item = FXCollections.observableArrayList();

        ps = con.prepareStatement(QUERY_2);
        rs = ps.executeQuery();

        TpaMasuk operasional;
        while (rs.next()){
            operasional = new TpaMasuk(
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
    public void save(TpaMasuk tpaMasuk) throws SQLException {
        executeUpdateQuery(QUERY_3, tpaMasuk.getId(), tpaMasuk.getDonatur(), tpaMasuk.getJumlah(), tpaMasuk.getTanggal(), tpaMasuk.getOperator());
    }

    @Override
    public void update(String[] params) throws SQLException {
        executeUpdateQuery(QUERY_4, params[1], params[2], params[3], params[4], params[0]);
    }

    @Override
    public void delete(String id) throws SQLException { executeDelete(QUERY_5, id); }

    public TpaMasuk getLastRecord() throws SQLException {
        TpaMasuk res = executeGetLastRecord(QUERY_6, rs1 -> new TpaMasuk(rs1.getString(1), rs1.getString(2), rs1.getString(3), rs1.getString(4), rs1.getString(5)));
        return res != null ? res : new TpaMasuk();
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


