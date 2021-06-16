package com.example.project;

public class UserDonation {
    private String uEmailAddress;
    private String charityName;
    private Integer accountNumber;
    private Integer accountPassword;
    private Integer donationAmount;

    public UserDonation(String uEmailAddress, String charityName, Integer accountNumber, Integer accountPassword, Integer donationAmount) {
        this.uEmailAddress = uEmailAddress;
        this.charityName = charityName;
        this.accountNumber = accountNumber;
        this.accountPassword = accountPassword;
        this.donationAmount = donationAmount;
    }

    public String getUEmailAddress() {
        return uEmailAddress;
    }

    public void setUEmailAddress(String uEmailAddress) {
        this.uEmailAddress = uEmailAddress;
    }

    public String getCharityName() {
        return charityName;
    }

    public void setCharityName(String charityName) {
        this.charityName = charityName;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(Integer accountPassword) {
        this.accountPassword = accountPassword;
    }

    public Integer getDonationAmount() {
        return donationAmount;
    }

    public void setDonationAmount(Integer donationAmount) {
        this.donationAmount = donationAmount;
    }
}
