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

package org.masjidku.model.accounting.zakat;

import org.masjidku.model.accounting.DataDonasi;

public class ZakatMasuk extends DataDonasi {
    public ZakatMasuk(String id, String donatur, String jumlah, String tanggal, String operator) {
        super(id, donatur, jumlah, tanggal, operator);
    }

    public ZakatMasuk() { this(null, null, "0", null, null); }

    public ZakatMasuk(String donatur, String jumlah, String tanggal, String operator) {
        super(donatur, jumlah, tanggal, operator);
    }
}
