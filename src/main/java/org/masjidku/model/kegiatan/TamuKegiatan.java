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

package org.masjidku.model.kegiatan;

public class TamuKegiatan implements TamuAndKegiatan {
    String id_kegiatan;
    String id_tamu;
    String keterangan;

    public TamuKegiatan(String id_kegiatan, String id_tamu, String keterangan) {
        this.id_kegiatan = id_kegiatan;
        this.id_tamu = id_tamu;
        this.keterangan = keterangan;
    }

    @Override
    public String getIdTamu() {
        return id_tamu;
    }

    @Override
    public String getIdKegiatan() {
        return id_kegiatan;
    }
}
