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

package org.masjidku.events.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tamukegiatan")
public class TamuKegiatan {
    @Id
    @Column(name = "id_undangan", length = 5)
    private String idUndangan;

    @ManyToOne
    @JoinColumn(name = "id_tamu")
    private Tamu tamu;

    @ManyToOne
    @JoinColumn(name = "id_kegiatan")
    private Kegiatan kegiatan;

    @Column(name = "keterangan", length = 50)
    private String keterangan;

    public TamuKegiatan() {
        this(null, null, null, null);
    }

    public TamuKegiatan(String idUndangan, Tamu tamu, Kegiatan kegiatan, String keterangan) {
        this.idUndangan = idUndangan;
        this.tamu = tamu;
        this.kegiatan = kegiatan;
        this.keterangan = keterangan;
    }

    public String getIdUndangan() {
        return idUndangan;
    }

    public void setIdUndangan(String idUndangan) {
        this.idUndangan = idUndangan;
    }

    public Tamu getTamu() {
        return tamu;
    }

    public void setTamu(Tamu tamu) {
        this.tamu = tamu;
    }

    public Kegiatan getKegiatanModel() {
        return kegiatan;
    }

    public void setKegiatanModel(Kegiatan kegiatan) {
        this.kegiatan = kegiatan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    // Convenience getters for JavaFX PropertyValueFactory compatibility
    public String getIdTamu() {
        return tamu != null ? tamu.getIdTamu() : null;
    }

    public String getIdKegiatan() {
        return kegiatan != null ? kegiatan.getIdKegiatan() : null;
    }

    public String getNama() {
        return tamu != null ? tamu.getNama() : null;
    }

    public String getAlamat() {
        return tamu != null ? tamu.getAlamat() : null;
    }

    public String getNotelp() {
        return tamu != null ? tamu.getNotelp() : null;
    }

    public String getKegiatan() {
        return kegiatan != null ? kegiatan.getNama() : null;
    }
}
