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
import org.masjidku.events.client.model.TamuKegiatan;
import java.sql.SQLException;
public interface TamuKegiatanUseCase {
    TamuKegiatan getTamuKegiatanById(String id) throws SQLException;
    ObservableList<TamuKegiatan> getAllTamuKegiatan() throws SQLException;
    void saveTamuKegiatan(String idKegiatan, String idTamu, String keterangan, String operator) throws SQLException;
    void updateTamuKegiatan(String[] params) throws SQLException;
    void deleteTamuKegiatan(String id) throws SQLException;
    boolean isUndanganExist(String id) throws SQLException;
}
