package com.example.barakat.soleeklab.rest;

import com.example.barakat.soleeklab.model.Countries;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("all")
    Call<List<Countries>> getAllCountries();
}
