package com.example.project;

public class Charity {
    private Integer charityId;
    private String charityName;
    private String charityDescription;

    public Charity(Integer charityId, String charityName, String charityDescription) {
        this.charityId = charityId;
        this.charityName = charityName;
        this.charityDescription = charityDescription;
    }

    public Integer getCharityId() {
        return charityId;
    }

    public void setCharityId(Integer charityId) {
        this.charityId = charityId;
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
