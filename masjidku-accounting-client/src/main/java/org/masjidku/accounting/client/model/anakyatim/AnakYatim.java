package org.masjidku.accounting.client.model.anakyatim;

import org.masjidku.accounting.client.model.BasePengeluaranModel;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AnakYatim extends BasePengeluaranModel {
    private final int usia;

    public AnakYatim() {
        super();
        this.usia = 0;
    }

    public AnakYatim(String tujuan, int usia, BigDecimal jumlah, LocalDate tanggal, String operator) {
        super(null, tujuan, "", jumlah, tanggal, operator);
        this.usia = usia;
    }

    public AnakYatim(String id, String tujuan, int usia, BigDecimal jumlah, LocalDate tanggal, String operator) {
        super(id, tujuan, "", jumlah, tanggal, operator);
        this.usia = usia;
    }

    public AnakYatim(String id, String tujuan, int usia, BigDecimal jumlah, LocalDate tanggal, String keterangan, String operator) {
        super(id, tujuan, keterangan, jumlah, tanggal, operator);
        this.usia = usia;
    }

    public AnakYatim(String tujuan, int usia, BigDecimal jumlah, LocalDate tanggal, String keterangan, String operator) {
        super(null, tujuan, keterangan, jumlah, tanggal, operator);
        this.usia = usia;
    }

    public int getUsia() {
        return usia;
    }

    public int usia() {
        return usia;
    }

    public String nama() {
        return getTujuan();
    }

    public String getNama() {
        return getTujuan();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnakYatim that)) return false;
        if (!super.equals(o)) return false;
        return usia == that.usia;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), usia);
    }

    @Override
    public String toString() {
        return "AnakYatim{"
                + "id='" + getId() + '\''
                + ", tujuan='" + getTujuan() + '\''
                + ", keterangan='" + getKeterangan() + '\''
                + ", jumlah=" + getJumlah()
                + ", tanggal=" + getTanggal()
                + ", operator='" + getOperator() + '\''
                + ", usia=" + usia
                + '}';
    }
}
