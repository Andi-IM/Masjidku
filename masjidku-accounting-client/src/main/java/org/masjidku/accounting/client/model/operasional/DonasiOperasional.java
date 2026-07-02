package org.masjidku.accounting.client.model.operasional;

import org.masjidku.accounting.client.model.BaseDonasiModel;
import java.math.BigDecimal;
import java.time.LocalDate;

public class DonasiOperasional extends BaseDonasiModel {
    public DonasiOperasional() {
        super();
    }

    public DonasiOperasional(String donatur, BigDecimal jumlah, LocalDate tanggal, String operator) {
        super(donatur, jumlah, tanggal, operator);
    }

    public DonasiOperasional(String id, String donatur, BigDecimal jumlah, LocalDate tanggal, String operator) {
        super(id, donatur, jumlah, tanggal, operator);
    }
}
