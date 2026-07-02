package org.masjidku.accounting.client.model.pembangunan;

import org.masjidku.accounting.client.model.BasePengeluaranModel;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Pembangunan extends BasePengeluaranModel {
    public Pembangunan() {
        super();
    }

    public Pembangunan(String tujuan, String keterangan, BigDecimal jumlah, LocalDate tanggal, String operator) {
        super(tujuan, keterangan, jumlah, tanggal, operator);
    }

    public Pembangunan(String id, String tujuan, String keterangan, BigDecimal jumlah, LocalDate tanggal, String operator) {
        super(id, tujuan, keterangan, jumlah, tanggal, operator);
    }

    public String nama() {
        return getTujuan();
    }

    public String getNama() {
        return getTujuan();
    }
}
