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

    private final String TABLE = "infak_anakyatim";

    @Override
    public AnakYatim get(String id) throws SQLException {
        return null;
    }

    @Override
    public ObservableList<AnakYatim> getAll() throws SQLException {
        ObservableList<AnakYatim> donatur = FXCollections.observableArrayList();
        return null;
    }

    @Override
    public void save(AnakYatim anakYatim) throws SQLException {

    }

    @Override
    public void update(String[] params) throws SQLException {

    }

    @Override
    public void delete(String id) throws SQLException {

    }
}
