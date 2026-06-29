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

import java.util.List;

public interface EventsClient {
    List<Kegiatan> getAllKegiatan();

    boolean isKegiatanExist(String id);

    void delete(String id);

    void save(Kegiatan kegiatan);

    void update(Kegiatan kegiatan);

    List<Tamu> getAllTamu();
}
