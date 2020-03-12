package com.example.apprestaurante.rest;

import com.example.apprestaurante.rest.request.LoginRequest;
import com.example.apprestaurante.rest.response.CategoriaResponse;
import com.example.apprestaurante.rest.response.LoginResponse;
import com.example.apprestaurante.rest.response.PlatoCategoriaResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @GET("categorias")
    Call<CategoriaResponse> getCategorias();

    @GET("platos/categoria/{id_categoria}")
    Call<PlatoCategoriaResponse> getPlatoCategoria(@Path("id_categoria") String id_categoria);

    @POST("auth/usuarios-login")
    Call<LoginResponse> login (@Body LoginRequest request);

}