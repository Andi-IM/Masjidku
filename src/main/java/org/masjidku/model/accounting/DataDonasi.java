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

package org.masjidku.model.accounting;

public abstract class DataDonasi {
    private String id;
    private String donatur;
    private String jumlah;
    private String tanggal;
    private String operator;

    public DataDonasi(){}

    public DataDonasi(String id, String jumlah, String tanggal, String operator) {
        setId(id);
        setJumlah(jumlah);
        setTanggal(tanggal);
        setOperator(operator);
    }

    public DataDonasi(String id, String donatur, String jumlah, String tanggal, String operator) {
        setId(id);
        setDonatur(donatur);
        setJumlah(jumlah);
        setTanggal(tanggal);
        setOperator(operator);
    }

    public DataDonasi(String jumlah, String tanggal, String operator) {
        setJumlah(jumlah);
        setTanggal(tanggal);
        setOperator(operator);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDonatur() {
        return donatur;
    }

    public void setDonatur(String donatur) {
        this.donatur = donatur;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
