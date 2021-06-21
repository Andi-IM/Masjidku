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

public class TamuKegiatan  {
    private String idUndangan;
    private String idKegiatan;
    private String idTamu;

    private String nama;
    private String kegiatan;
    private String alamat;
    private String notelp;
    private String keterangan;

    public TamuKegiatan() { this(null,null,null,null,null,null,null,null); }

    public TamuKegiatan(String idUndangan, String idKegiatan, String idTamu, String nama, String kegiatan, String alamat, String notelp, String keterangan) {
        setIdUndangan(idUndangan);
        setIdKegiatan(kegiatan);
        setIdTamu(idTamu);
        setNama(nama);
        setIdKegiatan(kegiatan);
        setAlamat(alamat);
        setNotelp(notelp);
        setKeterangan(keterangan);
    }

    public String getIdUndangan() {
        return idUndangan;
    }

    public void setIdUndangan(String idUndangan) {
        this.idUndangan = idUndangan;
    }

    public String getIdKegiatan() {
        return idKegiatan;
    }

    public String getIdTamu() {
        return idTamu;
    }

    public void setIdTamu(String idTamu) {
        this.idTamu = idTamu;
    }

    public void setKegiatan(String kegiatan) {
        this.kegiatan = kegiatan;
    }

    public String getNama() {
        return nama;
    }

    public String getKegiatan() {
        return kegiatan;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNotelp() {
        return notelp;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }

    private void setNotelp(String notelp) { this.notelp = notelp; }

    private void setAlamat(String alamat) { this.alamat = alamat; }

    private void setNama(String nama) { this.nama = nama; }

    private void setIdKegiatan(String kegiatan) { this.kegiatan = kegiatan; }

}
