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

package org.masjidku.accounting.client.model.tpa;

import org.masjidku.accounting.client.model.UangKeluar;

public class TpaKeluar extends UangKeluar {
    public TpaKeluar() { this(null,null,"0",null,null);}

    public TpaKeluar(String id, String nama, String keterangan, String jumlah, String tanggal, String operator) {
        super(id, nama, keterangan, jumlah, tanggal, operator);
    }

    public TpaKeluar(String nama, String keterangan, String jumlah, String tanggal, String operator) {
        super.setTujuan(nama);
        super.setKeterangan(keterangan);
        super.setJumlah(jumlah);
        super.setTanggal(tanggal);
        super.setOperator(operator);
    }

    public String getNama() {
        return super.getTujuan();
    }

}
