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

public class DonasiAYatim extends DataDonasi {

    public DonasiAYatim(){ this(null, "", "0", null, ""); }

    public DonasiAYatim(String id, String donatur, String jumlah, String tanggal, String operator) {
        super(id, donatur, jumlah, tanggal, operator);
    }

    public DonasiAYatim(String donatur, String jumlah, String tanggal, String operator) {
        super(donatur, jumlah, tanggal, operator);
    }

    @Override
    public String getDonatur() {
        return super.getDonatur();
    }

    @Override
    public String getJumlah() {
        return super.getJumlah();
    }

    @Override
    public String getTanggal() {
        return super.getTanggal();
    }
}
