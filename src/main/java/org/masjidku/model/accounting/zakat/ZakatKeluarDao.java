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

package org.masjidku.model.accounting.zakat;

import javafx.collections.ObservableList;
import org.masjidku.model.Dao;
import org.masjidku.model.DaoFactory;

import java.sql.SQLException;

public class ZakatKeluarDao extends Dao<ZakatKeluar> {

    private final String TABLE = "penerima_zakat";

    @Override
    protected ZakatKeluar get(String id) throws SQLException {
        return null;
    }

    @Override
    protected ObservableList<ZakatKeluar> getAll() throws SQLException {
        return null;
    }

    @Override
    protected void save(ZakatKeluar zakatKeluar) throws SQLException {

    }

    @Override
    protected void update(String[] params) throws SQLException {

    }

    @Override
    protected void delete(String id) throws SQLException {

    }
}
