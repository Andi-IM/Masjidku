package org.masjidku.model;

import static org.masjidku.model.Jabatan.*;

public class User {
    private String userId;
    private String username;
    private String password;
    private String jabatan;
    private String notelp;
    private String status;
    private String alamat;
    private String created_at;
    private String updated_at;

    public User() {
    }

    public User(String userId, String username, String jabatan, String notelp, String alamat, String status) {
        this.userId = userId;
        this.username = username;
        this.jabatan = jabatan;
        this.notelp = notelp;
        this.alamat = alamat;
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Jabatan getJabatan() {
        switch (this.jabatan) {
            case "admin":
                return admin;
            case "ketua":
                return ketua;
            case "sekretaris":
                return sekretaris;
            case "bendahara":
                return bendahara;
        }
        return null;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getNotelp() {
        return notelp;
    }

    public void setNotelp(String notelp) {
        this.notelp = notelp;
    }

    public Boolean getStatus() {
        return this.status.equals("Aktif");
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
