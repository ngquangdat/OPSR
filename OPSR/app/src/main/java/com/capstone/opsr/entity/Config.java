package com.capstone.opsr.entity;

public class Config {
    private String language;
    private String country;

    public Config(String language, String country) {
        this.language = language;
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
