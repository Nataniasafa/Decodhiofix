package com.example.decocioapp.network;

import com.example.decocioapp.model.ResponseLogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterfaceClient {
    @FormUrlEncoded
    @POST("exec")
    Call<ResponseLogin> loginSiswa(
            @Field(value = "sheetName", encoded = true)String sheetName,
            @Field(value = "action", encoded = true)String login,
            @Field(value = "email", encoded = true)String email,
            @Field(value = "pass", encoded = true)String pass
    );
}
