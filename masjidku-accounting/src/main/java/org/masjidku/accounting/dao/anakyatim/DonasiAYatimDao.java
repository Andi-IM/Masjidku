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

package org.masjidku.accounting.dao.anakyatim;
import org.intellij.lang.annotations.Language;
import org.masjidku.accounting.client.model.anakyatim.*;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.masjidku.accounting.dao.base.Dao;

import java.sql.SQLException;

public class DonasiAYatimDao extends Dao<DonasiAYatim> {
    @Language("SQL")
    private static final String QUERY_1 = "SELECT * FROM infak_anakyatim WHERE id=?";
    @Language("SQL")
    private static final String QUERY_2 = "SELECT * FROM infak_anakyatim";
    @Language("SQL")
    private static final String QUERY_3 = "INSERT INTO infak_anakyatim(id, donatur, jumlah, tanggal, operator) VALUES (?,?,?,?,?)";
    @Language("SQL")
    private static final String QUERY_4 = "UPDATE infak_anakyatim SET donatur=?, jumlah=?, tanggal=?, operator=? WHERE id=?";
    @Language("SQL")
    private static final String QUERY_5 = "DELETE FROM infak_anakyatim WHERE id=?";
    @Language("SQL")
    private static final String QUERY_6 = "SELECT * FROM infak_anakyatim ORDER BY ID DESC LIMIT 1";
    @Language("SQL")
    private static final String QUERY_7 = "SELECT IFNULL(SUM(jumlah),0) FROM infak_anakyatim";
    @Language("SQL")
    private static final String QUERY_8 = "SELECT id FROM infak_anakyatim WHERE id=?";


    

    @Override
    public DonasiAYatim get(String id) throws SQLException {
        ps = con.prepareStatement(QUERY_1);
        ps.setString(1, id);
        rs = ps.executeQuery();

        DonasiAYatim model = null;
        if (rs.next()){
            model = new DonasiAYatim(
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
    public ObservableList<DonasiAYatim> getAll() throws SQLException {
        ObservableList<DonasiAYatim> donatur = FXCollections.observableArrayList();

        ps = con.prepareStatement(QUERY_2);
        rs = ps.executeQuery();

        DonasiAYatim anakYatim;
        while (rs.next()){
            anakYatim = new DonasiAYatim(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
            );
            donatur.add(anakYatim);
        }
        return donatur;
    }

    @Override
    public void save(DonasiAYatim donasiAYatim) throws SQLException {
        executeUpdateQuery(QUERY_3, donasiAYatim.getId(), donasiAYatim.getDonatur(), donasiAYatim.getJumlah(), donasiAYatim.getTanggal(), donasiAYatim.getOperator());
    }

    @Override
    public void update(String[] params) throws SQLException {
        executeUpdateQuery(QUERY_4, params[1], params[2], params[3], params[4], params[0]);
    }

    @Override
    public void delete(String id) throws SQLException { executeDelete(QUERY_5, id); }

    public DonasiAYatim getLastRecord() throws SQLException {
        ps = con.prepareStatement(QUERY_6);
        rs = ps.executeQuery();

        DonasiAYatim model = new DonasiAYatim();
        if (rs.next()){
            model = new DonasiAYatim(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
            );
        }
        return model;
    }

    public String getTotalIncome() throws SQLException { return executeGetTotal(QUERY_7); }

    public boolean isDonaturExist(String id) throws SQLException { return executeCheckExists(QUERY_8, id); }
}
