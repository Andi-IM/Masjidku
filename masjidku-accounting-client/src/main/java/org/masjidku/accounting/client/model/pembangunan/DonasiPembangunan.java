package org.masjidku.accounting.client.model.pembangunan;

import org.masjidku.accounting.client.model.BaseDonasiModel;
import java.math.BigDecimal;
import java.time.LocalDate;

public class DonasiPembangunan extends BaseDonasiModel {
    public DonasiPembangunan() {
        super();
    }

    public DonasiPembangunan(String donatur, BigDecimal jumlah, LocalDate tanggal, String operator) {
        super(donatur, jumlah, tanggal, operator);
    }

    public DonasiPembangunan(String id, String donatur, BigDecimal jumlah, LocalDate tanggal, String operator) {
        super(id, donatur, jumlah, tanggal, operator);
    }
}
