package org.masjidku.accounting.client.model.tpa;

import org.masjidku.accounting.client.model.BasePengeluaranModel;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TpaKeluar extends BasePengeluaranModel {
    public TpaKeluar() {
        super();
    }

    public TpaKeluar(String tujuan, String keterangan, BigDecimal jumlah, LocalDate tanggal, String operator) {
        super(tujuan, keterangan, jumlah, tanggal, operator);
    }

    public TpaKeluar(String id, String tujuan, String keterangan, BigDecimal jumlah, LocalDate tanggal, String operator) {
        super(id, tujuan, keterangan, jumlah, tanggal, operator);
    }

    public String nama() {
        return getTujuan();
    }

    public String getNama() {
        return getTujuan();
    }
}
