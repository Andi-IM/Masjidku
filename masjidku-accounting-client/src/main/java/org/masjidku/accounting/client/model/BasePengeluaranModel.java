package org.masjidku.accounting.client.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public abstract class BasePengeluaranModel extends BaseTransactionModel {
    private final String tujuan;
    private final String keterangan;

    protected BasePengeluaranModel() {
        this(null, "", "", BigDecimal.ZERO, null, "");
    }

    protected BasePengeluaranModel(String tujuan, String keterangan, BigDecimal jumlah, LocalDate tanggal, String operator) {
        this(null, tujuan, keterangan, jumlah, tanggal, operator);
    }

    protected BasePengeluaranModel(String id, String tujuan, String keterangan, BigDecimal jumlah, LocalDate tanggal, String operator) {
        super(id, jumlah, tanggal, operator);
        this.tujuan = tujuan;
        this.keterangan = keterangan;
    }

    public String getTujuan() { return tujuan; }
    public String getKeterangan() { return keterangan; }

    public String tujuan() { return tujuan; }
    public String keterangan() { return keterangan; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasePengeluaranModel that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(tujuan, that.tujuan) &&
               Objects.equals(keterangan, that.keterangan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tujuan, keterangan);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{"
                + "id='" + getId() + '\''
                + ", tujuan='" + tujuan + '\''
                + ", keterangan='" + keterangan + '\''
                + ", jumlah=" + getJumlah()
                + ", tanggal=" + getTanggal()
                + ", operator='" + getOperator() + '\''
                + '}';
    }
}
