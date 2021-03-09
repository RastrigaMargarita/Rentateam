package com.example.rentateam.jsonModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonGetter {

    @GET("api/users")
    Call<ResponseData> getUsers();

}
