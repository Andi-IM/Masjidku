package org.masjidku.accounting.client.model.anakyatim;

import org.masjidku.accounting.client.model.BaseDonasiModel;
import java.math.BigDecimal;
import java.time.LocalDate;

public class DonasiAYatim extends BaseDonasiModel {
    public DonasiAYatim() {
        super();
    }

    public DonasiAYatim(String donatur, BigDecimal jumlah, LocalDate tanggal, String operator) {
        super(donatur, jumlah, tanggal, operator);
    }

    public DonasiAYatim(String id, String donatur, BigDecimal jumlah, LocalDate tanggal, String operator) {
        super(id, donatur, jumlah, tanggal, operator);
    }
}
