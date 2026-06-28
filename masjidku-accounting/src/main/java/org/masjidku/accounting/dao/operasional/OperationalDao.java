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

public class OperationalDao extends Dao<Operasional> {
    private static final String QUERY_1 = "SELECT * FROM operasional_keluar WHERE id=?";
    private static final String QUERY_2 = "SELECT * FROM operasional_keluar";
    private static final String QUERY_3 = "INSERT INTO operasional_keluar(id, nama, keterangan, jumlah, tanggal, operator) VALUES (?,?,?,?,?,?)";
    private static final String QUERY_4 = "UPDATE operasional_keluar SET nama=?, keterangan=?, jumlah=?, tanggal=?, operator=? WHERE id=?";
    private static final String QUERY_5 = "DELETE FROM operasional_keluar WHERE id=?";
    private static final String QUERY_6 = "SELECT id FROM operasional_keluar WHERE id=?";
    private static final String QUERY_7 = "SELECT * FROM operasional_keluar ORDER BY ID DESC LIMIT 1";
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
        ps = con.prepareStatement(QUERY_3);

        ps.setString(1, operasional.getId());
        ps.setString(2, operasional.getTujuan());
        ps.setString(2, operasional.getKeterangan());
        ps.setString(3, operasional.getJumlah());
        ps.setString(4, operasional.getTanggal());
        ps.setString(5, operasional.getOperator());
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

    public boolean isDataExist(String id) throws SQLException {
        ps = con.prepareStatement(QUERY_6);
        ps.setString(1, id);
        rs = ps.executeQuery();

        return rs.next();
    }

    public Operasional getLastRecord() throws SQLException {
        ps = con.prepareStatement(QUERY_7);
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

    public String getTotalIncome() throws SQLException {
        ps = con.prepareStatement(QUERY_8);
        rs = ps.executeQuery();

        if (rs.next()){
            return rs.getString(1);
        }
        return null;
    }
}
