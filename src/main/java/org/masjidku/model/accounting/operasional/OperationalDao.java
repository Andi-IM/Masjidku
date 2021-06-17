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

package org.masjidku.model.accounting.operasional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.masjidku.model.Dao;
import org.masjidku.model.DaoFactory;
import org.masjidku.model.accounting.pembangunan.Pembangunan;
import org.masjidku.model.accounting.zakat.ZakatMasuk;

import java.sql.SQLException;

public class OperationalDao extends Dao<Operasional> {

    private final String TABLE = "operasional_keluar";

    @Override
    public Operasional get(String id) throws SQLException {
        return null;
    }

    @Override
    public ObservableList<Operasional> getAll() throws SQLException {
            ObservableList<Operasional> donasioperasional = FXCollections.observableArrayList();

            query = "SELECT id, nama, jumlah, tanggal, operator from pemberi_zakat";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            Operasional operasional;
            while (rs.next()) {
                operasional = new Operasional();
                operasional.setId(rs.getString(1));
                operasional.setTujuan(rs.getString(2));
                operasional.setKeterangan(rs.getString(3));
                operasional.setJumlah(rs.getDouble(4));
                operasional.setTanggal(rs.getString(5));
                operasional.setOperator(rs.getString(6));
            }

        return donasioperasional;
    }

    @Override
    public void save(Operasional operasional) throws SQLException {
            query = "INSERT INTO " + TABLE + "(id, nama, keterangan, jumlah, tanggal, operator) VALUES(?,?,?,?,?)";
            ps = con.prepareStatement(query);
            ps.setString(1, operasional.getId());
            ps.setString(2, operasional.getTujuan());
            ps.setString(3, operasional.getKeterangan());
            ps.setDouble(4, operasional.getJumlah());
            ps.setString(5, operasional.getTanggal());
            ps.setString(6, operasional.getOperator());
            ps.executeUpdate();
        }

    @Override
    public void update(String[] params) throws SQLException {
            query = "UPDATE " + TABLE + " SET  nama=? , keterangan =?,jumlah=?, tanggal=? , operator=? WHERE id=?";
            ps = con.prepareStatement(query);
            ps.setString(1, params[0]);
            ps.setString(2, params[1]);
            ps.setString(3, params[2]);
            ps.setString(4, params[3]);
            ps.setString(5, params[4]);
            ps.setString(6, params[5]);
            ps.setString(7, params[6]);
            ps.executeUpdate();
        }

    @Override
    public void delete(String id) throws SQLException {
            query = "DELETE FROM " + TABLE + " WHERE id=?";
            ps = con.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        }
    }