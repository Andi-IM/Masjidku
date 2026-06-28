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

package org.masjidku.accounting.dao.pembangunan;
import org.masjidku.accounting.client.model.pembangunan.*;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.masjidku.accounting.dao.base.Dao;

import java.sql.SQLException;

public class PembangunanDao extends Dao<Pembangunan> {
    private static final String QUERY_1 = "SELECT * FROM pembangunan_keluar WHERE id=?";
    private static final String QUERY_2 = "SELECT * FROM pembangunan_keluar";
    private static final String QUERY_3 = "INSERT INTO pembangunan_keluar(id, nama, keterangan, jumlah, tanggal, operator) VALUES (?,?,?,?,?,?)";
    private static final String QUERY_4 = "UPDATE pembangunan_keluar SET nama=?, keterangan=?, jumlah=?, tanggal=?, operator=? WHERE id=?";
    private static final String QUERY_5 = "DELETE FROM pembangunan_keluar WHERE id=?";
    private static final String QUERY_6 = "SELECT * FROM pembangunan_keluar ORDER BY ID DESC LIMIT 1";
    private static final String QUERY_7 = "SELECT IFNULL(SUM(jumlah),0) FROM pembangunan_keluar";
    private static final String QUERY_8 = "SELECT id FROM pembangunan_keluar WHERE id=?";


    

    @Override
    public Pembangunan get(String id) throws SQLException {
        ps = con.prepareStatement(QUERY_1);
        ps.setString(1, id);
        rs = ps.executeQuery();

        Pembangunan model = null;
        if (rs.next()){
            model = new Pembangunan(
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
    public ObservableList<Pembangunan> getAll() throws SQLException {
        ObservableList<Pembangunan> item = FXCollections.observableArrayList();

        ps = con.prepareStatement(QUERY_2);
        rs = ps.executeQuery();

        Pembangunan pembangunan;
        while (rs.next()){
            pembangunan = new Pembangunan(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
            );
            item.add(pembangunan);
        }
        return item;
    }

    @Override
    public void save(Pembangunan pembangunan) throws SQLException {
        ps = con.prepareStatement(QUERY_3);

        ps.setString(1, pembangunan.getId());
        ps.setString(2, pembangunan.getTujuan());
        ps.setString(2, pembangunan.getKeterangan());
        ps.setString(3, pembangunan.getJumlah());
        ps.setString(4, pembangunan.getTanggal());
        ps.setString(5, pembangunan.getOperator());
        ps.executeUpdate();
    }

    @Override
    public void update(String[] params) throws SQLException {
        ps = con.prepareStatement(QUERY_4);
        ps.setString(1, params[1]);
        ps.setString(2, params[2]);
        ps.setString(3, params[3]);
        ps.setString(4, params[4]);
        ps.setString(5, params[5]);
        ps.setString(5, params[0]);
        ps.executeUpdate();
    }

    @Override
    public void delete(String id) throws SQLException {
        ps = con.prepareStatement(QUERY_5);
        ps.setString(1, id);
        ps.executeUpdate();
    }

    public Pembangunan getLastRecord() throws SQLException {
        ps = con.prepareStatement(QUERY_6);
        rs = ps.executeQuery();

        Pembangunan model = new Pembangunan();
        if (rs.next()){
            model = new Pembangunan(
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

    public String getTotalIncome() throws SQLException {
        ps = con.prepareStatement(QUERY_7);
        rs = ps.executeQuery();

        if (rs.next()){
            return rs.getString(1);
        }
        return null;
    }

    public boolean isDataExist(String id) throws SQLException {
        ps = con.prepareStatement(QUERY_8);
        ps.setString(1, id);
        rs = ps.executeQuery();

        return rs.next();
    }
}
