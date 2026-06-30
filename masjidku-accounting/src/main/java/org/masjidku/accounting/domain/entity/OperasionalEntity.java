package org.masjidku.accounting.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "operasional_keluar")
public class OperasionalEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "nama")
    private String nama;
    @Column(name = "keterangan")
    private String keterangan;
    @Column(name = "jumlah")
    private String jumlah;
    @Column(name = "tanggal")
    private String tanggal;
    @Column(name = "operator")
    private String operator;
    public OperasionalEntity() {}
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getKeterangan() { return keterangan; }
    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }
    public String getJumlah() { return jumlah; }
    public void setJumlah(String jumlah) { this.jumlah = jumlah; }
    public String getTanggal() { return tanggal; }
    public void setTanggal(String tanggal) { this.tanggal = tanggal; }
    public String getOperator() { return operator; }
    public void setOperator(String operator) { this.operator = operator; }
}

