package org.masjidku.accounting.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "penerima_zakat")
public class ZakatKeluarEntity extends BasePengeluaranEntity {
}
