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

package org.masjidku.model.tahunanggaran;

import org.masjidku.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaDao {
    private final String TA_TABLE = "tahunanggaran";
    Connection con;
    PreparedStatement ps;

    //constructor
    public TaDao() {
    }

    public static void main(String[] args) throws SQLException {
        TaDao dao = new TaDao();
        if (dao.getConnection()) {
            System.out.println(dao.getYear());
        }
    }

    public boolean getConnection() {
        DatabaseConnection connection = new DatabaseConnection();
        if (connection.getConnection() != null) {
            con = connection.getConnection();
            return true;
        }
        return false;
    }

    public void create(String tahun, String status) throws SQLException {
        String query = "INSERT INTO " + TA_TABLE + "(tahun, status) VALUES(?, ?)";
        ps = con.prepareStatement(query);
        ps.setString(1, tahun);
        ps.setString(2, status);
        ps.executeUpdate();
    }

    public String getYear() throws SQLException {
        String query = "SELECT * FROM " + TA_TABLE + " WHERE status='Aktif'";
        ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String resultYear = rs.getString(1);
            return resultYear.substring(0, 4);
        }
        return null;
    }

}
