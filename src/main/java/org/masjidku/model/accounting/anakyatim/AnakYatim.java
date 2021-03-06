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

package org.masjidku.model.accounting.anakyatim;

import org.masjidku.model.accounting.UangKeluar;

public class AnakYatim extends UangKeluar {
    private int usia;

    public AnakYatim(){ this(null, null, 0, "0", null, null); }

    public AnakYatim(String id, String tujuan, int usia, String jumlah, String tanggal, String operator) {
        super(id, tujuan, jumlah, tanggal, operator);
        setUsia(usia);
    }

    public AnakYatim(String tujuan, int usia, String jumlah, String tanggal, String operator) {
        super(tujuan, jumlah, tanggal, operator);
        setUsia(usia);
    }

    public String getNama() {
        return super.getTujuan();
    }

    public int getUsia() {
        return usia;
    }

    @Override
    public String getKeterangan() {
        return super.getKeterangan();
    }

    @Override
    public String getJumlah() {
        return super.getJumlah();
    }

    public void setUsia(int usia) {
        this.usia = usia;
    }
}
