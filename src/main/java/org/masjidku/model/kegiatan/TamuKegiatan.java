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
    private String id_undangan;
    private String id_kegiatan;
    private String id_tamu;
    private String keterangan;

    public TamuKegiatan(String id_undangan, String id_kegiatan, String id_tamu, String keterangan) {
        setId_undangan(id_undangan);
        setIdKegiatan(id_kegiatan);
        setIdTamu(id_tamu);
        setKeterangan(keterangan);
    }

    public String getId_undangan() {
        return id_undangan;
    }

    public void setId_undangan(String id_undangan) {
        this.id_undangan = id_undangan;
    }

    @Override
    public String getIdTamu() {
        return id_tamu;
    }

    @Override
    public void setIdTamu(String idTamu) { this.id_tamu = idTamu; }

    @Override
    public String getIdKegiatan() {
        return id_kegiatan;
    }

    @Override
    public void setIdKegiatan(String idKegiatan) { this.id_kegiatan = idKegiatan; }

    @Override
    public String getKeterangan() { return keterangan; }

    @Override
    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }
}
