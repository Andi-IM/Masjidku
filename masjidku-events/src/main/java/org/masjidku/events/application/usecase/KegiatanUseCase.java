/*
 * Copyright (c) 2026. Creative Commons Legal Code
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

package org.masjidku.events.application.usecase.impl;

import javafx.collections.ObservableList;
import org.masjidku.events.client.model.Kegiatan;

import java.sql.SQLException;

public interface KegiatanUseCase {
    Kegiatan getKegiatanById(String id) throws SQLException;

    ObservableList<Kegiatan> getAllKegiatan() throws SQLException;

    void saveKegiatan(Kegiatan kegiatan) throws SQLException;

    void updateKegiatan(String[] params) throws SQLException;

    void deleteKegiatan(String id) throws SQLException;

    boolean exists(String id) throws SQLException;

    ObservableList<String> getAllKegiatanNames() throws SQLException;

    String getIdByName(String name) throws SQLException;

    Kegiatan getLastKegiatan() throws SQLException;

    String getTotalKegiatanCount() throws SQLException;
}
