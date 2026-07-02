package org.masjidku.accounting.client.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class BasePengeluaranModel {
    private final String id;
    private final String tujuan;
    private final String keterangan;
    private final BigDecimal jumlah;
    private final LocalDate tanggal;
    private final String operator;

    protected BasePengeluaranModel() {
        this(null, "", "", BigDecimal.ZERO, null, "");
    }

    protected BasePengeluaranModel(String tujuan, String keterangan, BigDecimal jumlah, LocalDate tanggal, String operator) {
        this(null, tujuan, keterangan, jumlah, tanggal, operator);
    }

    protected BasePengeluaranModel(String id, String tujuan, String keterangan, BigDecimal jumlah, LocalDate tanggal, String operator) {
        this.id = id;
        this.tujuan = tujuan;
        this.keterangan = keterangan;
        this.jumlah = jumlah;
        this.tanggal = tanggal;
        this.operator = operator;
    }

    public String getId() { return id; }
    public String getTujuan() { return tujuan; }
    public String getKeterangan() { return keterangan; }
    public BigDecimal getJumlah() { return jumlah; }
    public LocalDate getTanggal() { return tanggal; }
    public String getOperator() { return operator; }
    
    // Kept for record-like compatibility if needed
    public String id() { return id; }
    public String tujuan() { return tujuan; }
    public String keterangan() { return keterangan; }
    public BigDecimal jumlah() { return jumlah; }
    public LocalDate tanggal() { return tanggal; }
    public String operator() { return operator; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasePengeluaranModel that = (BasePengeluaranModel) o;
        return java.util.Objects.equals(id, that.id) &&
               java.util.Objects.equals(tujuan, that.tujuan) &&
               java.util.Objects.equals(keterangan, that.keterangan) &&
               java.util.Objects.equals(jumlah, that.jumlah) &&
               java.util.Objects.equals(tanggal, that.tanggal) &&
               java.util.Objects.equals(operator, that.operator);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, tujuan, keterangan, jumlah, tanggal, operator);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{"
                + "id='" + id + '\''
                + ", tujuan='" + tujuan + '\''
                + ", keterangan='" + keterangan + '\''
                + ", jumlah=" + jumlah
                + ", tanggal=" + tanggal
                + ", operator='" + operator + '\''
                + '}';
    }
}
