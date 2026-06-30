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

package org.masjidku.accounting.client.model.zakat;

public record ZakatKeluar(String id, String tujuan, String jumlah, String tanggal, String operator) {

    public ZakatKeluar() {
        this(null, null, "0", null, null);
    }

    public ZakatKeluar(String tujuan, String jumlah, String tanggal, String operator) {
        this(null, tujuan, jumlah, tanggal, operator);
    }

    public String nama() {
        return tujuan;
    }

    public String getId() { return id; }
    public String getTujuan() { return tujuan; }
    public String getJumlah() { return jumlah; }
    public String getTanggal() { return tanggal; }
    public String getOperator() { return operator; }
    public String getNama() { return tujuan; }
}
