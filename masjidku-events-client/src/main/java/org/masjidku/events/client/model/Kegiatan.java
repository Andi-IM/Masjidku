/*
 * Copyright (c) 2026. Creative Commons Legal Code
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

package org.masjidku.events.client.model;

public record Kegiatan(
        String idKegiatan,
        String nama,
        String waktu,
        String tanggal,
        String tempat,
        String operator
) {
    public String getIdKegiatan() { return idKegiatan; }
    public String getNama() { return nama; }
    public String getWaktu() { return waktu; }
    public String getTanggal() { return tanggal; }
    public String getTempat() { return tempat; }
    public String getOperator() { return operator; }

    public Kegiatan() {
        this("", "", null, null, "", "");
    }

    public Kegiatan(String nama,
                    String waktu,
                    String tanggal,
                    String tempat,
                    String operator) {
        this(null, nama, waktu, tanggal, tempat, operator);
    }
}
