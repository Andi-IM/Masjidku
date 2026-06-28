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

import org.masjidku.accounting.dao.base.DaoFactory;

import java.sql.SQLException;

public class DaoFunctions extends DaoFactory {

    public String getInfakYatimBalance() throws SQLException {
        String query = "SELECT getInfakYatimBalance()";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        if (rs.next()){
            return rs.getString(1);
        }

        return null;
    }

    public String getOperationalBalance() throws SQLException{
        String query = "SELECT getOperationalBalance()";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        if (rs.next()){
            return rs.getString(1);
        }

        return null;
    }

    public String getPembangunanBalance() throws SQLException {
        String query = "SELECT getPembangunanBalance()";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        if (rs.next()){
            return rs.getString(1);
        }

        return null;
    }

    public String getTpaBalance() throws SQLException {
        String query = "SELECT getTpaBalance()";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        if (rs.next()){
            return rs.getString(1);
        }
        return null;
    }

    public String getZakatBalance() throws SQLException {
        String query = "SELECT getZakatBalance()";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();

        if (rs.next()){
            return rs.getString(1);
        }
        return null;
    }
}
