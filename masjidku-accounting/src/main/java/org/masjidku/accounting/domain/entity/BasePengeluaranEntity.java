package org.masjidku.accounting.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
public abstract class BasePengeluaranEntity extends BaseTransactionEntity {
    @Column(name = "tujuan")
    private String tujuan;
    
    @Column(name = "keterangan")
    private String keterangan;

    public String getTujuan() { return tujuan; }
    public void setTujuan(String tujuan) { this.tujuan = tujuan; }

    public String getKeterangan() { return keterangan; }
    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasePengeluaranEntity that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(tujuan, that.tujuan) &&
               Objects.equals(keterangan, that.keterangan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tujuan, keterangan);
    }
}
