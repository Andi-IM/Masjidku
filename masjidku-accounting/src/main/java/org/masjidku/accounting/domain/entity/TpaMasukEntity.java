package org.masjidku.accounting.domain.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "infak_tpa")
@AttributeOverride(name = "donatur", column = @Column(name = "nama"))
public class TpaMasukEntity extends BaseDonasiEntity {
}
