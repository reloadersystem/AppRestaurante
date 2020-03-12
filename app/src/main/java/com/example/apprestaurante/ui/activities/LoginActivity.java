package com.example.apprestaurante.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.apprestaurante.R;
import com.example.apprestaurante.rest.request.LoginRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.messaging.FirebaseMessaging;

import me.leolin.shortcutbadger.ShortcutBadger;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText tiet_usuario;
    TextInputEditText tiet_clave;

    LoginViewModel loginViewModel;
    //ok

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tiet_usuario = findViewById(R.id.tiet_usuario);
        tiet_clave = findViewById(R.id.tiet_clave);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        //ShortcutBadger.applyCount(getApplicationContext(), 12);
        FirebaseMessaging.getInstance().subscribeToTopic("weather")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "OK";
                        if (!task.isSuccessful()) {
                            msg = "Error";
                        }
                        Log.e("ABC", msg);
                        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void login(View view){
        LoginRequest request = new LoginRequest(tiet_usuario.getText().toString(),tiet_clave.getText().toString());
    }

    public void registrar(View view) {
        Intent intent = new Intent(getApplicationContext(), RegistrarActivity.class);
        startActivity(intent);
    }

}