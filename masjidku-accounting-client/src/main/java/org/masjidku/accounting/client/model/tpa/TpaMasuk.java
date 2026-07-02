package org.masjidku.accounting.client.model.tpa;

import org.masjidku.accounting.client.model.BaseDonasiModel;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TpaMasuk extends BaseDonasiModel {
    public TpaMasuk() {
        super();
    }

    public TpaMasuk(String donatur, BigDecimal jumlah, LocalDate tanggal, String operator) {
        super(donatur, jumlah, tanggal, operator);
    }

    public TpaMasuk(String id, String donatur, BigDecimal jumlah, LocalDate tanggal, String operator) {
        super(id, donatur, jumlah, tanggal, operator);
    }
}
