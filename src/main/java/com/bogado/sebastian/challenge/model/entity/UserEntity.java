package com.bogado.sebastian.challenge.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Users")
@Table(indexes = {
        @Index(name = "idx_user_email", columnList = "email")
})
public class UserEntity extends BaseEntity {
    @Column(nullable = false, name = "name")
    private String name;
    @Column(nullable = false, name = "email")
    private String email;
    @Column(nullable = false, name = "password")
    private String password;
    @Column(name = "token")
    private String token;
    @Column(name = "last_login")
    private LocalDateTime lastLogin;
    @Column(name = "is_active", nullable = false, columnDefinition = "boolean default true")
    private Boolean isActive;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<PhoneEntity> phones;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<PhoneEntity> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneEntity> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", lastLogin=" + lastLogin +
                ", isActive=" + isActive +
                '}';
    }
}
