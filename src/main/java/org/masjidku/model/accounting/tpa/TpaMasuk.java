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

package org.masjidku.model.accounting.tpa;

import org.masjidku.model.accounting.DataDonasi;

public class TpaMasuk extends DataDonasi {
    public TpaMasuk() { this(null,null,"0",null,null); }

    public TpaMasuk(String id, String donatur, String jumlah, String tanggal, String operator) {
        super(id, donatur, jumlah, tanggal, operator);
    }

    public TpaMasuk(String nama, String jumlah, String tanggal, String operator) {
        super(nama, jumlah, tanggal, operator);
    }

    @Override
    public String getId() {
        return super.getId();
    }

    public String getNama() {
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

    @Override
    public String getOperator() {
        return super.getOperator();
    }
}
