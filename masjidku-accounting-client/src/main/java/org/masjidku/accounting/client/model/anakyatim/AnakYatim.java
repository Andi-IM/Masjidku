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

package org.masjidku.accounting.client.model.anakyatim;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AnakYatim(String id, String tujuan, int usia, BigDecimal jumlah, LocalDate tanggal, String keterangan,
                        String operator) {

    public AnakYatim() {
        this(null, null, 0, BigDecimal.ZERO, null, "", null);
    }

    public AnakYatim(String tujuan, int usia, BigDecimal jumlah, LocalDate tanggal, String operator) {
        this(null, tujuan, usia, jumlah, tanggal, "", operator);
    }

    public AnakYatim(String id, String tujuan, int usia, BigDecimal jumlah, LocalDate tanggal, String operator) {
        this(id, tujuan, usia, jumlah, tanggal, "", operator);
    }

    public AnakYatim(String tujuan, int usia, BigDecimal jumlah, LocalDate tanggal, String keterangan, String operator) {
        this(null, tujuan, usia, jumlah, tanggal, keterangan, operator);
    }

    public String nama() {
        return tujuan;
    }

    public String getId() { return id; }
    public String getTujuan() { return tujuan; }
    public int getUsia() { return usia; }
    public BigDecimal getJumlah() { return jumlah; }
    public LocalDate getTanggal() { return tanggal; }
    public String getKeterangan() { return keterangan; }
    public String getOperator() { return operator; }
    public String getNama() { return tujuan; }
}
