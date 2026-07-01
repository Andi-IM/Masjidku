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

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

import jakarta.persistence.Convert;



@Entity
@Table(name = "kegiatan")
public class Kegiatan {
    @Id
    @Column(name = "kegiatanID", length = 5)
    private String idKegiatan;

    @Column(name = "kegiatanNama", length = 50)
    private String nama;

    @Convert(converter = LocalTimeStringConverter.class)
    @Column(name = "kegiatanWaktu")
    private LocalTime waktu;

    @Convert(converter = LocalDateStringConverter.class)
    @Column(name = "kegiatanTanggal")
    private LocalDate tanggal;

    @Column(name = "kegiatanTempat", length = 50)
    private String tempat;

    @Column(name = "operator", length = 50)
    private String operator;

    public Kegiatan() {
        this.waktu = LocalTime.now(ZoneId.systemDefault());
        this.tanggal = LocalDate.now(ZoneId.systemDefault());
    }

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

    public String getIdKegiatan() {
        return idKegiatan;
    }

    public void setIdKegiatan(String idKegiatan) {
        this.idKegiatan = idKegiatan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public LocalTime getWaktu() {
        return waktu;
    }

    public void setWaktu(LocalTime waktu) {
        this.waktu = waktu;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
