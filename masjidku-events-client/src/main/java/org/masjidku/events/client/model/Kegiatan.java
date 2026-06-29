package org.masjidku.events.client.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Kegiatan {
    private String idKegiatan;
    private String nama;
    private LocalTime waktu;
    private LocalDate tanggal;
    private String tempat;
    private String operator;

    public Kegiatan() { }

    public Kegiatan(String nama, LocalTime waktu, LocalDate tanggal, String tempat, String operator) {
        this.nama = nama;
        this.waktu = waktu;
        this.tanggal = tanggal;
        this.tempat = tempat;
        this.operator = operator;
    }

    public Kegiatan(String id, String nama, LocalTime waktu, LocalDate tanggal, String tempat, String operator) {
        this.idKegiatan = id;
        this.nama = nama;
        this.waktu = waktu;
        this.tanggal = tanggal;
        this.tempat = tempat;
        this.operator = operator;
    }

    public String getIdKegiatan() { return idKegiatan; }
    public void setIdKegiatan(String idKegiatan) { this.idKegiatan = idKegiatan; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public LocalTime getWaktu() { return waktu; }
    public void setWaktu(LocalTime waktu) { this.waktu = waktu; }

    public LocalDate getTanggal() { return tanggal; }
    public void setTanggal(LocalDate tanggal) { this.tanggal = tanggal; }

    public String getTempat() { return tempat; }
    public void setTempat(String tempat) { this.tempat = tempat; }

    public String getOperator() { return operator; }
    public void setOperator(String operator) { this.operator = operator; }
}
