package org.masjidku.accounting.client.model.operasional;

import org.masjidku.accounting.client.model.BasePengeluaranModel;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Operasional extends BasePengeluaranModel {
    public Operasional() {
        super();
    }

    public Operasional(String tujuan, String keterangan, BigDecimal jumlah, LocalDate tanggal, String operator) {
        super(tujuan, keterangan, jumlah, tanggal, operator);
    }

    public Operasional(String id, String tujuan, String keterangan, BigDecimal jumlah, LocalDate tanggal, String operator) {
        super(id, tujuan, keterangan, jumlah, tanggal, operator);
    }

    public String nama() {
        return getTujuan();
    }

    public String getNama() {
        return getTujuan();
    }
}
