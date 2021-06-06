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

import java.sql.ResultSet;
import java.sql.SQLException;

public class TamuKegiatanDao extends DaoFactory<TamuKegiatan> {
    private final String TABLE = "tamukegiatan";

    @Override
    public TamuKegiatan get(String id) throws SQLException {
        String query = "SELECT * FROM "+TABLE+" WHERE id_undangan=?";

        ps = con.prepareStatement(query);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();

        TamuKegiatan model = null;
        if(rs.next()){
            model = new TamuKegiatan(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)
            );
        }
        return model;
    }

    @Override
    public ObservableList<TamuKegiatan> getAll() throws SQLException {
        ObservableList<TamuKegiatan> items = FXCollections.observableArrayList();
        String query = "SELECT * FROM "+TABLE;
        ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        TamuKegiatan tg;
        while (rs.next()){
            tg = new TamuKegiatan(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)
            );
            items.add(tg);
        }
        return items;
    }

    @Override
    public void save(TamuKegiatan tamuKegiatan) throws SQLException {
        String query = "INSERT INTO "+TABLE+"(id_undangan, id_kegiatan, id_tamu, keterangan) VALUES(?,?,?,?)";

        ps = con.prepareStatement(query);
        ps.setString(1, tamuKegiatan.getId_undangan());
        ps.setString(2, tamuKegiatan.getIdKegiatan());
        ps.setString(3, tamuKegiatan.getIdTamu());
        ps.setString(4, tamuKegiatan.getKeterangan());
        ps.executeUpdate();
    }

    @Override
    public void update(String[] params) throws SQLException {
        String query = "UPDATE "+TABLE+" SET keterangan=? WHERE id_undangan=? and id_tamu=? and id_kegiatan=?";

        ps = con.prepareStatement(query);
        ps.setString(1, params[0]);
        ps.executeUpdate();
    }

    @Override
    public void delete(String id) throws SQLException {
        String query = "DELETE FROM "+TABLE+" WHERE id_undangan=?";
        ps = con.prepareStatement(query);
        ps.setString(1, id);
        ps.executeUpdate();
    }

    public boolean isUndanganExist(String id) throws SQLException {
        String query = "SELECT id_undangan FROM "+TABLE+" WHERE id_undangan=?";
        ps = con.prepareStatement(query);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();

        return rs.next();
    }
}
