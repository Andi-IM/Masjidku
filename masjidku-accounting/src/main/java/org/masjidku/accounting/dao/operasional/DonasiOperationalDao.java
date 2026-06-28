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
import org.masjidku.accounting.client.model.operasional.*;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.masjidku.accounting.dao.base.Dao;

import java.sql.SQLException;

public class DonasiOperationalDao extends Dao<DonasiOperasional> {
    private static final String QUERY_1 = "SELECT * FROM infak_operasional WHERE id=?";
    private static final String QUERY_2 = "SELECT * FROM infak_operasional";
    private static final String QUERY_3 = "INSERT INTO infak_operasional(id, donatur, jumlah, tanggal, operator) VALUES (?,?,?,?,?)";
    private static final String QUERY_4 = "UPDATE infak_operasional SET donatur=?, jumlah=?, tanggal=?, operator=? WHERE id=?";
    private static final String QUERY_5 = "DELETE FROM infak_operasional WHERE id=?";
    private static final String QUERY_6 = "SELECT id FROM infak_operasional WHERE id=?";
    private static final String QUERY_7 = "SELECT * FROM infak_operasional ORDER BY ID DESC LIMIT 1";
    private static final String QUERY_8 = "SELECT IFNULL(SUM(jumlah),0) FROM infak_operasional";


    

    @Override
    public DonasiOperasional get(String id) throws SQLException {
        ps = con.prepareStatement(QUERY_1);
        ps.setString(1, id);
        rs = ps.executeQuery();

        DonasiOperasional model = null;
        if (rs.next()){
            model = new DonasiOperasional(
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
    public ObservableList<DonasiOperasional> getAll() throws SQLException {
        ObservableList<DonasiOperasional> donatur = FXCollections.observableArrayList();

        ps = con.prepareStatement(QUERY_2);
        rs = ps.executeQuery();

        DonasiOperasional donasi;
        while (rs.next()){
            donasi = new DonasiOperasional(
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
    public void save(DonasiOperasional donasiOperasional) throws SQLException {
        executeUpdateQuery(QUERY_3, donasiOperasional.getId(), donasiOperasional.getDonatur(), donasiOperasional.getJumlah(), donasiOperasional.getTanggal(), donasiOperasional.getOperator());
    }

    @Override
    public void update(String[] params) throws SQLException {
        executeUpdateQuery(QUERY_4, params[1], params[2], params[3], params[4], params[0]);
    }

    @Override
    public void delete(String id) throws SQLException { executeDelete(QUERY_5, id); }

    public boolean isDonaturExist(String id) throws SQLException { return executeCheckExists(QUERY_6, id); }

    public DonasiOperasional getLastRecord() throws SQLException {
        ps = con.prepareStatement(QUERY_7);
        rs = ps.executeQuery();

        DonasiOperasional model = new DonasiOperasional();
        if (rs.next()){
            model = new DonasiOperasional(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
            );
        }
        return model;
    }

    public String getTotalOutcome() throws SQLException { return executeGetTotal(QUERY_8); }
}
