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

package org.masjidku.events.client;

import org.masjidku.events.client.model.Kegiatan;
import org.masjidku.events.client.model.Tamu;
import org.masjidku.events.client.model.TamuKegiatan;

import java.util.List;

public interface EventsClient {
    List<Kegiatan> getAllKegiatan();
    boolean isKegiatanExist(String id);
    void delete(Kegiatan kegiatan);
    void save(Kegiatan kegiatan);
    void update(Kegiatan kegiatan);

    List<Tamu> getAllTamu();
    boolean isTamuExist(String id);
    void save(Tamu tamu);
    void update(Tamu tamu);
    void delete(Tamu tamu);

    List<TamuKegiatan> getAllUndangan();
    boolean isUndanganExist(String id);
    void save(TamuKegiatan undangan);
    void delete(TamuKegiatan undangan);
    void update(TamuKegiatan undangan);
}
