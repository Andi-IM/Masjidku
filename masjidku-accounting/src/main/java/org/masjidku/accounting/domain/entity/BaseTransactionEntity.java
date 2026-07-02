package org.masjidku.accounting.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseTransactionEntity {
    @Id
    @Column(name = "id")
    private String id;
    
    @Column(name = "jumlah", precision = 19, scale = 2)
    private BigDecimal jumlah;
    
    @Column(name = "tanggal")
    private LocalDate tanggal;
    
    @Column(name = "operator")
    private String operator;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public BigDecimal getJumlah() { return jumlah; }
    public void setJumlah(BigDecimal jumlah) { this.jumlah = jumlah; }

    public LocalDate getTanggal() { return tanggal; }
    public void setTanggal(LocalDate tanggal) { this.tanggal = tanggal; }

    public String getOperator() { return operator; }
    public void setOperator(String operator) { this.operator = operator; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseTransactionEntity that = (BaseTransactionEntity) o;
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
