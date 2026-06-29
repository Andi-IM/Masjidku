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

package org.masjidku.events.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tamu")
public class Tamu {

    @Id
    @Column(name = "tamuID", length = 5)
    private String idTamu;

    @Column(name = "tamuNama", length = 50)
    private String nama;

    @Column(name = "tamuAlamat", length = 255)
    private String alamat;

    @Column(name = "tamuNotelp", length = 20)
    private String notelp;

    @Column(name = "operator", length = 50)
    private String operator;

    public Tamu() {
        this(null, null, null, null, null);
    }

    public Tamu(String idTamu, String nama, String alamat, String notelp, String operator) {
        setIdTamu(idTamu);
        setNama(nama);
        setAlamat(alamat);
        setNotelp(notelp);
        setOperator(operator);
    }

    public Tamu(String namaTamu, String alamat, String noTelp, String operator) {
        setIdTamu(null);
        setNama(namaTamu);
        setAlamat(alamat);
        setNotelp(noTelp);
        setOperator(operator);
    }

    public String getIdTamu() {
        return idTamu;
    }

    public void setIdTamu(String idTamu) {
        this.idTamu = idTamu;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNotelp() {
        return notelp;
    }

    public void setNotelp(String notelp) {
        this.notelp = notelp;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
