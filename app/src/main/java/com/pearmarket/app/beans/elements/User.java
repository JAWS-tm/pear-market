package com.pearmarket.app.beans.elements;

import java.io.Serializable;

/**
 * JavaBeans d'un utilisateur
 */
public class User implements Serializable {
    private String name;
    private String firstname;
    private String email;
    private String address;
    private String phone;
    private Boolean isAdmin;
    private Boolean isBlocked;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    /**
     * Split l'adresse et l'affiche avec des espaces entre chaques "champs"
     * @return la chaine générée
     */
    public String getFormattedAddress() {
        return address.replaceAll("\\\\", " ");
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
