package com.thesis.pdm.hallergen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelsUser {

    private String userUID;
    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private String password;
    private String email;

    private List<ModelsContact> contacts = new ArrayList<>();
    private List<ModelsFamily> family = new ArrayList<>();



    public List<ModelsContact> getContacts() {
        return contacts;
    }

    public void setContacts(List<ModelsContact> contacts) {
        this.contacts = contacts;
    }

    public List<ModelsFamily> getFamily() {
        return family;
    }

    public void setFamily(List<ModelsFamily> family) {
        this.family = family;
    }

    public String getUserUID() { return userUID; }

    public void setUserUID(String userUID) { this.userUID = userUID; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getMiddleName() { return middleName; }

    public void setMiddleName(String middleName) { this.middleName = middleName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }


}
