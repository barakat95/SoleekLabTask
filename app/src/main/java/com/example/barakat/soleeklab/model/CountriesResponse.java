package com.example.barakat.soleeklab.model;

import java.util.ArrayList;
import java.util.List;

public class CountriesResponse {
    List<Countries> countries;

    public CountriesResponse() {
    }

    public List<Countries> getCountries() {
        return countries;
    }

    public void setCountries(List<Countries> countries) {
        this.countries = countries;
    }
}
