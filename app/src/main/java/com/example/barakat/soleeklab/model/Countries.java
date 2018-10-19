package com.example.barakat.soleeklab.model;

import com.google.gson.annotations.SerializedName;

public class Countries {
    @SerializedName("name")
    String CountryName;
    @SerializedName("region")
    String CountryRegion;
    @SerializedName("capital")
    String CapitalName;

    public Countries(String countryName, String countryRegion, String capitalName) {
        CountryName = countryName;
        CountryRegion = countryRegion;
        CapitalName = capitalName;
    }

    public Countries() {
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public String getCountryRegion() {
        return CountryRegion;
    }

    public void setCountryRegion(String countryRegion) {
        CountryRegion = countryRegion;
    }

    public String getCapitalName() {
        return CapitalName;
    }

    public void setCapitalName(String capitalName) {
        CapitalName = capitalName;
    }
}
