/*
 * Copyright (c) 2021. Creative Commons Legal Code
 *
 *                            CC0 1.0 Universal
 *
 *                                CREATIVE COMMONS CORPORATION IS NOT A LAW FIRM AND DOES NOT PROVIDE
 *                                LEGAL SERVICES. DISTRIBUTION OF THIS DOCUMENT DOES NOT CREATE AN
 *                                ATTORNEY-CLIENT RELATIONSHIP. CREATIVE COMMONS PROVIDES THIS
 *                                INFORMATION ON AN "AS-IS" BASIS. CREATIVE COMMONS MAKES NO WARRANTIES
 *                                REGARDING THE USE OF THIS DOCUMENT OR THE INFORMATION OR WORKS
 *                                PROVIDED HEREUNDER, AND DISCLAIMS LIABILITY FOR DAMAGES RESULTING FROM
 *                                THE USE OF THIS DOCUMENT OR THE INFORMATION OR WORKS PROVIDED
 *                                HEREUNDER.
 */

package org.masjidku.accounting.client.model.operasional;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DonasiOperasional(String id, String donatur, BigDecimal jumlah, LocalDate tanggal, String operator) {

    public DonasiOperasional() {
        this(null, null, BigDecimal.ZERO, null, null);
    }

    public DonasiOperasional(String donatur, BigDecimal jumlah, LocalDate tanggal, String operator) {
        this(null, donatur, jumlah, tanggal, operator);
    }

    public String nama() {
        return donatur;
    }

    public String getId() { return id; }
    public String getDonatur() { return donatur; }
    public BigDecimal getJumlah() { return jumlah; }
    public LocalDate getTanggal() { return tanggal; }
    public String getOperator() { return operator; }
    public String getNama() { return donatur; }
}
