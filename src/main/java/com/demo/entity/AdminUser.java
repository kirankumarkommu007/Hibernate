package com.demo.entity;

import jakarta.persistence.Entity;

@Entity
public class AdminUser extends User {
    private String adminLevel;

    // Getters and setters
    public String getAdminLevel() {
        return adminLevel;
    }

    public void setAdminLevel(String adminLevel) {
        this.adminLevel = adminLevel;
    }
}
