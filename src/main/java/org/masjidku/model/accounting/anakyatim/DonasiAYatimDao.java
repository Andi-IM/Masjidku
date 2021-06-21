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

package org.masjidku.model.accounting.anakyatim;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.masjidku.model.Dao;

import java.sql.SQLException;

public class DonasiAYatimDao extends Dao<DonasiAYatim> {

    private final String TABLE = "infak_anakyatim";

    @Override
    protected DonasiAYatim get(String id) throws SQLException {
        query = "SELECT * FROM "+TABLE+" WHERE id=?";
        ps = con.prepareStatement(query);
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
    protected ObservableList<DonasiAYatim> getAll() throws SQLException {
        ObservableList<DonasiAYatim> donatur = FXCollections.observableArrayList();

        query = "SELECT * FROM "+TABLE;
        ps = con.prepareStatement(query);
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
    protected void save(DonasiAYatim donasiAYatim) throws SQLException {
        query = "INSERT INTO "+TABLE+"(id, donatur, jumlah, tanggal, operator) VALUES (?,?,?,?,?)";

        ps = con.prepareStatement(query);
        ps.setString(1, donasiAYatim.getId());
        ps.setString(2, donasiAYatim.getDonatur());
        ps.setString(3, donasiAYatim.getJumlah());
        ps.setString(4, donasiAYatim.getTanggal());
        ps.setString(5, donasiAYatim.getOperator());
        ps.executeUpdate();
    }

    @Override
    protected void update(String[] params) throws SQLException {
        query = "UPDATE "+TABLE+" SET donatur=?, jumlah=?, tanggal=?, operator=? WHERE id=?";
        ps = con.prepareStatement(query);
        ps.setString(1, params[1]);
        ps.setString(2, params[2]);
        ps.setString(3, params[3]);
        ps.setString(4, params[4]);
        ps.setString(5, params[0]);
        ps.executeUpdate();
    }

    @Override
    protected void delete(String id) throws SQLException {
        query = "DELETE FROM "+TABLE+" WHERE id=?";
        ps = con.prepareStatement(query);
        ps.setString(1, id);
        ps.executeUpdate();
    }

    public DonasiAYatim getLastRecord() throws SQLException {
        query = "SELECT * FROM "+TABLE+" ORDER BY ID DESC LIMIT 1";
        ps = con.prepareStatement(query);
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

    public String getTotalIncome() throws SQLException {
        query = "SELECT IFNULL(0, SUM(jumlah)) FROM "+TABLE;
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        if (rs.next()){
            return rs.getString(1);
        }
        return null;
    }

    public boolean isDonaturExist(String id) throws SQLException {
        query = "SELECT id FROM "+TABLE+" WHERE id=?";
        ps = con.prepareStatement(query);
        ps.setString(1, id);
        rs = ps.executeQuery();

        return rs.next();
    }
}
