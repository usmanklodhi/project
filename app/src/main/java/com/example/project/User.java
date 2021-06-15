package com.example.project;

public class User {
    private String emailID;
    private String fullName;
    private String password;
    private Boolean isAdmin;

    User(String emailID, String fullName, String password, Boolean isAdmin) {
        this.emailID = emailID;
        this.fullName = fullName;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}
