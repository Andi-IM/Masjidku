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

package org.masjidku.model.accounting.operasional;

import org.masjidku.model.accounting.UangKeluar;

public class Operasional extends UangKeluar {
    public Operasional(){ this(null, null, null, "0", null, null); }

    public Operasional(String id, String tujuan, String keterangan, String jumlah, String tanggal, String operator) {
        super(id, tujuan, keterangan, jumlah, tanggal, operator);
    }

    public Operasional(String nama, String keterangan, String jumlah, String tanggal, String operator) {
        super.setTujuan(nama);
        super.setKeterangan(keterangan);
        super.setJumlah(jumlah);
        super.setTanggal(tanggal);
        super.setOperator(operator);
    }

    @Override
    public String getId() {
        return super.getId();
    }

    public String getNama() {
        return super.getTujuan();
    }

    @Override
    public String getKeterangan() {
        return super.getKeterangan();
    }

    @Override
    public String getJumlah() {
        return super.getJumlah();
    }

    @Override
    public String getOperator() {
        return super.getOperator();
    }
}
