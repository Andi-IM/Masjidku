package org.masjidku.accounting.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseDonasiEntity extends BaseTransactionEntity {
    @Column(name = "donatur")
    private String donatur;

    public String getDonatur() { return donatur; }
    public void setDonatur(String donatur) { this.donatur = donatur; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseDonasiEntity that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(donatur, that.donatur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), donatur);
    }
}
