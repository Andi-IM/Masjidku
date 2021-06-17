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

import org.masjidku.model.accounting.DataDonasi;
import org.masjidku.model.accounting.UangKeluar;

public class AnakYatim extends UangKeluar {
    private String usia;

    public AnakYatim(){}

    public AnakYatim(String id, String nama, String usia, double jumlah, String tanggal, String operator) {
        super(id, nama, jumlah, tanggal, operator);
        this.usia = usia;
    }

    public String getNama(){ return super.getTujuan(); }

    public String getJumlah(){ return String.valueOf(super.getJumlah()); }

    public String getUsia() {
        return usia;
    }

    public void setUsia(String usia) {
        this.usia = usia;
    }
}
