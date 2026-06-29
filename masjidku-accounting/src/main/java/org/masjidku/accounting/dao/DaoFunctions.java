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

package org.masjidku.accounting.dao;

import org.intellij.lang.annotations.Language;
import org.masjidku.accounting.dao.base.AccountingDaoFactory;

import java.sql.SQLException;

public class DaoFunctions extends AccountingDaoFactory {

    public String getInfakYatimBalance() throws SQLException {
        @Language("SQL") String query = "SELECT (SELECT COALESCE(SUM(jumlah), 0) FROM infak_anakyatim) - (SELECT COALESCE(SUM(jumlah), 0) FROM penerima_anakyatim)";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getString(1);
        }

        return null;
    }

    public String getOperationalBalance() throws SQLException {
        @Language("SQL") String query = "SELECT (SELECT COALESCE(SUM(jumlah), 0) FROM infak_operasional) - (SELECT COALESCE(SUM(jumlah), 0) FROM operasional_keluar)";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getString(1);
        }

        return null;
    }

    public String getPembangunanBalance() throws SQLException {
        @Language("SQL") String query = "SELECT (SELECT COALESCE(SUM(jumlah), 0) FROM infak_pembangunan) - (SELECT COALESCE(SUM(jumlah), 0) FROM pembangunan_keluar)";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getString(1);
        }

        return null;
    }

    public String getTpaBalance() throws SQLException {
        @Language("SQL") String query = "SELECT (SELECT COALESCE(SUM(jumlah), 0) FROM infak_tpa) - (SELECT COALESCE(SUM(jumlah), 0) FROM tpa_keluar)";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    public String getZakatBalance() throws SQLException {
        @Language("SQL") String query = "SELECT (SELECT COALESCE(SUM(jumlah), 0) FROM pemberi_zakat) - (SELECT COALESCE(SUM(jumlah), 0) FROM penerima_zakat)";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }
}
