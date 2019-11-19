package com.example.decocioapp.Masuk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.decocioapp.MainActivity;
import com.example.decocioapp.R;
import com.example.decocioapp.SharedPreferenceManager;
import com.example.decocioapp.model.ResponseLogin;
import com.example.decocioapp.network.InterfaceClient;
import com.example.decocioapp.network.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    EditText etEmail, etPass;
    Button btnLogin;
    ProgressDialog progressDialog;
    SharedPreferenceManager sharedPreferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = findViewById(R.id.et_email);
        etPass = findViewById(R.id.et_pass);
        sharedPreferencesManager = new SharedPreferenceManager(this);
        if (sharedPreferencesManager.getSigned()) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        }
        progressDialog = new ProgressDialog(this);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messeageEmail = "Email tidak boleh kosong";
                String messeagePass = "paaword tidak boleh kosong";
                progressDialog.setMessage("Loading...");
                progressDialog.show();

                if (etEmail.getText().toString().isEmpty()) {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, messeageEmail, Toast.LENGTH_SHORT)
                            .show();
                    return;
                }
                if (etPass.getText().toString().isEmpty()) {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, messeagePass, Toast.LENGTH_SHORT)
                            .show();
                    return;
                }
                String email = etEmail.getText().toString().trim();
                String pass = etPass.getText().toString().trim();

                InterfaceClient interfaceClient = RetrofitConfig.createService(InterfaceClient.class);
                Call<ResponseLogin> requestLogin = interfaceClient.loginSiswa("siswa", "Login", email, pass);

                requestLogin.enqueue(new Callback<ResponseLogin>() {
                    @Override
                    public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                        progressDialog.dismiss();
                        if (response.body().getHasil().equals("succes")) {
                            sharedPreferencesManager.saveBoolean(sharedPreferencesManager.SP_SIGNED, true);
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Login gagal", Toast.LENGTH_SHORT).show();
                        }
                    }


                    @Override
                    public void onFailure(Call<ResponseLogin> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Koneksi error", Toast.LENGTH_SHORT).show();
                    }

                });
            }
        });

    }

    }