package org.masjidku.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "profil_user")
public class UserProfileEntity {

    @Id
    @Column(name = "userid", length = 30, nullable = false)
    private String userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", referencedColumnName = "userid", insertable = false, updatable = false)
    private UserEntity user;

    @Column(name = "notelp", length = 15)
    private String notelp;

    @Column(name = "alamat", length = 50)
    private String alamat;

    public UserProfileEntity() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getNotelp() {
        return notelp;
    }

    public void setNotelp(String notelp) {
        this.notelp = notelp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
