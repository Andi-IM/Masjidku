package org.masjidku.accounting.client.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class BaseDonasiModel {
    private final String id;
    private final String donatur;
    private final BigDecimal jumlah;
    private final LocalDate tanggal;
    private final String operator;

    protected BaseDonasiModel() {
        this(null, "", BigDecimal.ZERO, null, "");
    }

    protected BaseDonasiModel(String donatur, BigDecimal jumlah, LocalDate tanggal, String operator) {
        this(null, donatur, jumlah, tanggal, operator);
    }

    protected BaseDonasiModel(String id, String donatur, BigDecimal jumlah, LocalDate tanggal, String operator) {
        this.id = id;
        this.donatur = donatur;
        this.jumlah = jumlah;
        this.tanggal = tanggal;
        this.operator = operator;
    }

    public String getId() {
        return id;
    }

    public String getDonatur() {
        return donatur;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public String getOperator() {
        return operator;
    }

    // Kept for record-like compatibility if needed
    public String id() {
        return id;
    }

    public String donatur() {
        return donatur;
    }

    public String nama() {
        return donatur;
    }

    public BigDecimal jumlah() {
        return jumlah;
    }

    public LocalDate tanggal() {
        return tanggal;
    }

    public String operator() {
        return operator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseDonasiModel that = (BaseDonasiModel) o;
        return java.util.Objects.equals(id, that.id) &&
                java.util.Objects.equals(donatur, that.donatur) &&
                java.util.Objects.equals(jumlah, that.jumlah) &&
                java.util.Objects.equals(tanggal, that.tanggal) &&
                java.util.Objects.equals(operator, that.operator);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, donatur, jumlah, tanggal, operator);
    }
}
