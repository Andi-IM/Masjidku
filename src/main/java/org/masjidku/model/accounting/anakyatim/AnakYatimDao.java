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

public class AnakYatimDao extends Dao<AnakYatim> {

    private final String TABLE = "penerima_anakyatim";

    @Override
    public AnakYatim get(String id) throws SQLException {
        query = "SELECT * FROM "+TABLE+" WHERE id=?";
        ps = con.prepareStatement(query);
        ps.setString(1, id);
        rs = ps.executeQuery();

        AnakYatim model = null;
        if (rs.next()){
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

        query = "SELECT * FROM "+TABLE;
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        AnakYatim anakYatim;
        while (rs.next()){
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
        query = "INSERT INTO "+TABLE+"(id, nama, usia, jumlah, tanggal, operator) VALUES (?,?,?,?,?,?)";

        ps = con.prepareStatement(query);
        ps.setString(1, anakYatim.getId());
        ps.setString(2, anakYatim.getTujuan());
        ps.setString(3, String.valueOf(anakYatim.getUsia()));
        ps.setString(4, String.valueOf(anakYatim.getJumlah()));
        ps.setString(5, anakYatim.getTanggal());
        ps.setString(6, anakYatim.getOperator());
        ps.executeUpdate();
    }

    @Override
    public void update(String[] params) throws SQLException {
        query = "UPDATE "+TABLE+" SET nama=?, usia=?, jumlah=?, tanggal=?, operator=? WHERE id=?";
        ps = con.prepareStatement(query);
        ps.setString(1, params[1]);
        ps.setString(2, params[2]);
        ps.setString(3, params[3]);
        ps.setString(4, params[4]);
        ps.setString(5, params[5]);
        ps.setString(6, params[0]);
        ps.executeUpdate();
    }

    @Override
    public void delete(String id) throws SQLException {
        query = "DELETE FROM "+TABLE+" WHERE id=?";
        ps = con.prepareStatement(query);
        ps.setString(1, id);
        ps.executeUpdate();
    }

    public AnakYatim getLastRecord() throws SQLException {
        query = "SELECT * FROM "+TABLE+" ORDER BY ID DESC LIMIT 1";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        AnakYatim model = null;
        if (rs.next()){
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
        query = "SELECT IFNULL(0, SUM(jumlah)) FROM "+TABLE;
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        if (rs.next()){
            return rs.getString(1);
        }
        return null;
    }

    public boolean isAnakYatimExist(String id) throws SQLException {
        query = "SELECT id FROM "+TABLE+" WHERE id=?";
        ps = con.prepareStatement(query);
        ps.setString(1, id);
        rs = ps.executeQuery();

        return rs.next();
    }
}
