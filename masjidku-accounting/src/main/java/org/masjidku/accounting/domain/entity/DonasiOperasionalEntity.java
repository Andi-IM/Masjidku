package org.masjidku.accounting.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "infak_operasional")
public class DonasiOperasionalEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "donatur")
    private String donatur;
    @Column(name = "jumlah")
    private String jumlah;
    @Column(name = "tanggal")
    private String tanggal;
    @Column(name = "operator")
    private String operator;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDonatur() {
        return donatur;
    }

    public void setDonatur(String donatur) {
        this.donatur = donatur;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}

