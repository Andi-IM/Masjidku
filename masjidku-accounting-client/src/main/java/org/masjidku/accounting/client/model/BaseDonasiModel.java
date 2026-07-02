package org.masjidku.accounting.client.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public abstract class BaseDonasiModel extends BaseTransactionModel {
    private final String donatur;

    protected BaseDonasiModel() {
        this(null, "", BigDecimal.ZERO, null, "");
    }

    protected BaseDonasiModel(String donatur, BigDecimal jumlah, LocalDate tanggal, String operator) {
        this(null, donatur, jumlah, tanggal, operator);
    }

    protected BaseDonasiModel(String id, String donatur, BigDecimal jumlah, LocalDate tanggal, String operator) {
        super(id, jumlah, tanggal, operator);
        this.donatur = donatur;
    }

    public String getDonatur() { return donatur; }
    public String donatur() { return donatur; }
    public String nama() { return donatur; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseDonasiModel that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(donatur, that.donatur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), donatur);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{"
                + "id='" + getId() + '\''
                + ", donatur='" + donatur + '\''
                + ", jumlah=" + getJumlah()
                + ", tanggal=" + getTanggal()
                + ", operator='" + getOperator() + '\''
                + '}';
    }
}
