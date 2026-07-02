package org.masjidku.accounting.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.math.BigDecimal;
import java.time.LocalDate;

@MappedSuperclass
public abstract class BaseDonasiEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "donatur")
    private String donatur;
    @Column(name = "jumlah", precision = 19, scale = 2)
    private BigDecimal jumlah;
    @Column(name = "tanggal")
    private LocalDate tanggal;
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

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
