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

package org.masjidku.model.kegiatan;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.masjidku.model.DaoFactory;

import java.sql.SQLException;

public class KegiatanDao extends DaoFactory<Kegiatan> {

    private final String TABLE = "kegiatan";

    @Override
    public Kegiatan get(String id) throws SQLException {
        query = "SELECT * FROM "+TABLE+" WHERE kegiatanID=?";

        ps = con.prepareStatement(query);
        ps.setString(1, id);
        rs = ps.executeQuery();

        Kegiatan model = null;
        if(rs.next()){
            model = new Kegiatan(
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
    public ObservableList<Kegiatan> getAll() throws SQLException {
        ObservableList<Kegiatan> items = FXCollections.observableArrayList();

        query = "SELECT * FROM "+TABLE;
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        Kegiatan kegiatan;
        while(rs.next()){
            kegiatan = new Kegiatan(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
            );
            items.add(kegiatan);
        }
        return items;
    }

    @Override
    public void save(Kegiatan kegiatan) throws SQLException {
        query = "INSERT INTO "+TABLE+ "(kegiatanID, kegiatanNama, kegiatanWaktu, kegiatanTanggal, kegiatanTempat, operator) VALUES(?,?,?,?,?,?)";

        ps = con.prepareStatement(query);
        ps.setString(1, kegiatan.getIdKegiatan());
        ps.setString(2, kegiatan.getNama());
        ps.setString(3, kegiatan.getWaktu());
        ps.setString(4, kegiatan.getTanggal());
        ps.setString(5, kegiatan.getTempat());
        ps.setString(6, kegiatan.getOperator());
        ps.executeUpdate();
    }

    @Override
    public void update(String[] params) throws SQLException {
        query = "UPDATE "+TABLE+" SET kegiatanNama=?, kegiatanWaktu=?, kegiatanTanggal=?, kegiatanTempat=?, operator=? WHERE kegiatanID=?";

        ps = con.prepareStatement(query);
        ps.setString(1, params[0]);
        ps.setString(2, params[1]);
        ps.setString(3, params[2]);
        ps.setString(4, params[3]);
        ps.setString(5, params[4]);
        ps.executeUpdate();
    }

    @Override
    public void delete(String id) throws SQLException {
        query = "DELETE FROM "+TABLE+" WHERE kegiatanID=?";
        ps = con.prepareStatement(query);
        ps.setString(1, id);
        ps.executeUpdate();
    }

    public boolean isKegiatanExist(String id) throws SQLException {
        query = "SELECT kegiatanID FROM "+TABLE+" WHERE kegiatanID=?";
        ps = con.prepareStatement(query);
        ps.setString(1, id);
        rs = ps.executeQuery();

        return rs.next();
    }
}
