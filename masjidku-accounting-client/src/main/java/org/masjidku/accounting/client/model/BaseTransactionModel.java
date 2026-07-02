package org.masjidku.accounting.client.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public abstract class BaseTransactionModel {
    private final String id;
    private final BigDecimal jumlah;
    private final LocalDate tanggal;
    private final String operator;

    protected BaseTransactionModel(String id, BigDecimal jumlah, LocalDate tanggal, String operator) {
        this.id = id;
        this.jumlah = jumlah;
        this.tanggal = tanggal;
        this.operator = operator;
    }

    public String getId() { return id; }
    public BigDecimal getJumlah() { return jumlah; }
    public LocalDate getTanggal() { return tanggal; }
    public String getOperator() { return operator; }

    public String id() { return id; }
    public BigDecimal jumlah() { return jumlah; }
    public LocalDate tanggal() { return tanggal; }
    public String operator() { return operator; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseTransactionModel that = (BaseTransactionModel) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(jumlah, that.jumlah) &&
               Objects.equals(tanggal, that.tanggal) &&
               Objects.equals(operator, that.operator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jumlah, tanggal, operator);
    }
}
