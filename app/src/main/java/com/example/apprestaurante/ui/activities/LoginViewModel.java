package com.example.apprestaurante.ui.activities;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.apprestaurante.helpers.Helper;
import com.example.apprestaurante.rest.request.LoginRequest;
import com.example.apprestaurante.rest.response.CategoriaResponse;
import com.example.apprestaurante.rest.response.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    MutableLiveData<LoginResponse> loginResponse;

    public LoginViewModel(){
        loginResponse =  new MutableLiveData<>();

    }

    public MutableLiveData<LoginResponse> getLoginResponse() {
        return loginResponse;
    }

    public void login(LoginRequest request){

        Call<LoginResponse> call = Helper.getApiService().login(request);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                loginResponse.setValue(response.body());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }
}
