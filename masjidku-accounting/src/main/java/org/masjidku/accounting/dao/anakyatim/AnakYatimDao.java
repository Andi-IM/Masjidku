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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.intellij.lang.annotations.Language;
import org.masjidku.accounting.client.model.anakyatim.AnakYatim;
import org.masjidku.accounting.dao.base.Dao;

import java.sql.SQLException;

public class AnakYatimDao extends Dao<AnakYatim> {
    @Language("SQL")
    private static final String QUERY_1 = "SELECT * FROM penerima_anakyatim WHERE id=?";
    @Language("SQL")
    private static final String QUERY_2 = "SELECT * FROM penerima_anakyatim";
    @Language("SQL")
    private static final String QUERY_3 = "INSERT INTO penerima_anakyatim(id, nama, usia, jumlah, tanggal, operator) VALUES (?,?,?,?,?,?)";
    @Language("SQL")
    private static final String QUERY_4 = "UPDATE penerima_anakyatim SET nama=?, usia=?, jumlah=?, tanggal=?, operator=? WHERE id=?";
    @Language("SQL")
    private static final String QUERY_5 = "DELETE FROM penerima_anakyatim WHERE id=?";
    @Language("SQL")
    private static final String QUERY_6 = "SELECT * FROM penerima_anakyatim ORDER BY ID DESC LIMIT 1";
    @Language("SQL")
    private static final String QUERY_7 = "SELECT IFNULL(SUM(jumlah),0) FROM penerima_anakyatim";
    @Language("SQL")
    private static final String QUERY_8 = "SELECT id FROM penerima_anakyatim WHERE id=?";


    @Override
    public AnakYatim get(String id) throws SQLException {
        ps = con.prepareStatement(QUERY_1);
        ps.setString(1, id);
        rs = ps.executeQuery();

        AnakYatim model = null;
        if (rs.next()) {
            model = new AnakYatim(
                    rs.getString(1),
                    rs.getString(2),
                    Integer.parseInt(rs.getString(3)),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
            );
        }
        return model;
    }

    @Override
    public ObservableList<AnakYatim> getAll() throws SQLException {
        ObservableList<AnakYatim> daftar = FXCollections.observableArrayList();

        ps = con.prepareStatement(QUERY_2);
        rs = ps.executeQuery();

        AnakYatim anakYatim;
        while (rs.next()) {
            anakYatim = new AnakYatim(
                    rs.getString(1),
                    rs.getString(2),
                    Integer.parseInt(rs.getString(3)),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
            );
            daftar.add(anakYatim);
        }
        return daftar;
    }

    @Override
    public void save(AnakYatim anakYatim) throws SQLException {
        ps = con.prepareStatement(QUERY_3);

        ps.setString(1, anakYatim.getId());
        ps.setString(2, anakYatim.getTujuan());
        ps.setInt(3, anakYatim.getUsia());
        ps.setString(4, anakYatim.getJumlah());
        ps.setString(5, anakYatim.getTanggal());
        ps.setString(6, anakYatim.getOperator());
        ps.executeUpdate();
    }

    @Override
    public void update(String[] params) throws SQLException {
        executeUpdateQuery(QUERY_4, params[1], params[2], params[3], params[4], params[5], params[0]);
    }

    @Override
    public void delete(String id) throws SQLException {
        executeDelete(QUERY_5, id);
    }

    public AnakYatim getLastRecord() throws SQLException {
        ps = con.prepareStatement(QUERY_6);
        rs = ps.executeQuery();

        AnakYatim model = new AnakYatim();
        if (rs.next()) {
            model = new AnakYatim(
                    rs.getString(1),
                    rs.getString(2),
                    Integer.parseInt(rs.getString(3)),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
            );
        }
        return model;
    }

    public String getTotalOutcome() throws SQLException {
        return executeGetTotal(QUERY_7);
    }

    public boolean isAnakYatimExist(String id) throws SQLException {
        return executeCheckExists(QUERY_8, id);
    }
}
