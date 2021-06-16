package com.example.project;

public class Charity {
    private String charityName;
    private String charityDescription;

    public Charity(String charityName, String charityDescription) {
        this.charityName = charityName;
        this.charityDescription = charityDescription;
    }

    public String getCharityName() {
        return charityName;
    }

    public void setCharityName(String charityName) {
        this.charityName = charityName;
    }

    public String getCharityDescription() {
        return charityDescription;
    }

    public void setCharityDescription(String charityDescription) {
        this.charityDescription = charityDescription;
    }
}
