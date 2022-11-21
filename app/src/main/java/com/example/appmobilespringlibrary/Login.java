package com.example.appmobilespringlibrary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    private final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";
    private TextInputLayout LoginSenha;
    private EditText LoginEmail;
    private Button Logar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginEmail = findViewById(R.id.editTextEmailLogin);
        LoginSenha = findViewById(R.id.TextInputLayoutSenhaLogin);

        Logar = findViewById(R.id.btnLogin);

        Logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
                SharedPreferences.Editor editor = preferences.edit();

                String email = LoginEmail.getText().toString();
                editor.putString("nome", email);
                String senha = LoginSenha.getEditText().toString();
                editor.putString("email", senha);

                editor.commit();
                Toast.makeText(getBaseContext(), "Login cadastrado com sucesso", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Login.this, Home.class);
                intent.putExtra("EmailLogin", LoginEmail.getText().toString());
                intent.putExtra("SenhaLogin", LoginSenha.getEditText().toString());
                startActivity(intent);

            }
        });
    }
}
