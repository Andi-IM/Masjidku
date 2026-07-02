package org.masjidku.accounting.client.model.zakat;

import org.masjidku.accounting.client.model.BasePengeluaranModel;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ZakatKeluar extends BasePengeluaranModel {
    public ZakatKeluar() {
        super();
    }

    public ZakatKeluar(String tujuan, BigDecimal jumlah, LocalDate tanggal, String operator) {
        super(null, tujuan, "", jumlah, tanggal, operator);
    }

    public ZakatKeluar(String id, String tujuan, BigDecimal jumlah, LocalDate tanggal, String operator) {
        super(id, tujuan, "", jumlah, tanggal, operator);
    }

    public String nama() {
        return getTujuan();
    }

    public String getNama() {
        return getTujuan();
    }
}
