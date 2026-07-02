package org.masjidku.accounting.client.model.zakat;

import org.masjidku.accounting.client.model.BaseDonasiModel;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ZakatMasuk extends BaseDonasiModel {
    public ZakatMasuk() {
        super();
    }

    public ZakatMasuk(String donatur, BigDecimal jumlah, LocalDate tanggal, String operator) {
        super(donatur, jumlah, tanggal, operator);
    }

    public ZakatMasuk(String id, String donatur, BigDecimal jumlah, LocalDate tanggal, String operator) {
        super(id, donatur, jumlah, tanggal, operator);
    }
}
