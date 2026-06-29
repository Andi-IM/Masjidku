package org.masjidku.events.client.model;

public class TamuKegiatan {
    private String idUndangan;
    private Tamu tamu;
    private Kegiatan kegiatan;
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

    public String getIdUndangan() { return idUndangan; }
    public void setIdUndangan(String idUndangan) { this.idUndangan = idUndangan; }

    public Tamu getTamu() { return tamu; }
    public void setTamu(Tamu tamu) { this.tamu = tamu; }

    public Kegiatan getKegiatanModel() { return kegiatan; }
    public void setKegiatanModel(Kegiatan kegiatan) { this.kegiatan = kegiatan; }

    public String getKeterangan() { return keterangan; }
    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }

    // Convenience getters for JavaFX PropertyValueFactory compatibility
    public String getIdTamu() { return tamu != null ? tamu.getIdTamu() : null; }
    public String getIdKegiatan() { return kegiatan != null ? kegiatan.getIdKegiatan() : null; }
    public String getNama() { return tamu != null ? tamu.getNama() : null; }
    public String getAlamat() { return tamu != null ? tamu.getAlamat() : null; }
    public String getNotelp() { return tamu != null ? tamu.getNotelp() : null; }
    public String getKegiatan() { return kegiatan != null ? kegiatan.getNama() : null; }
}
