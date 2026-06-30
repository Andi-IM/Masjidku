package org.masjidku.accounting.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "tpa_keluar")
public class TpaKeluarEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "nama")
    private String tujuan;
    @Column(name = "keterangan")
    private String keterangan;
    @Column(name = "jumlah")
    private String jumlah;
    @Column(name = "tanggal")
    private String tanggal;
    @Column(name = "operator")
    private String operator;
    public TpaKeluarEntity() {}
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTujuan() { return tujuan; }
    public void setTujuan(String tujuan) { this.tujuan = tujuan; }
    public String getKeterangan() { return keterangan; }
    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }
    public String getJumlah() { return jumlah; }
    public void setJumlah(String jumlah) { this.jumlah = jumlah; }
    public String getTanggal() { return tanggal; }
    public void setTanggal(String tanggal) { this.tanggal = tanggal; }
    public String getOperator() { return operator; }
    public void setOperator(String operator) { this.operator = operator; }
}

