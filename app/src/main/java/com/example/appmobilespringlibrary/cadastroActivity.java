package com.example.appmobilespringlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmobilespringlibrary.R;
import com.example.mobile_springlibrary.ClassesBanco.DatabaseHelper;
import com.google.android.material.textfield.TextInputEditText;

public class cadastroActivity extends AppCompatActivity {
    EditText edtTxtEmail, edtNomeUser;
    TextInputEditText edtSenha, confSenha;
    Button btnCadastrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_etapa1);

        edtTxtEmail = findViewById(R.id.editTextEmail);
        edtSenha = findViewById(R.id.edtInputTextSenha);
        confSenha = findViewById(R.id.edtInputConfSenha);
        edtNomeUser = findViewById(R.id.edtTxtNomeCli);
        btnCadastrar = findViewById(R.id.btnContinuarCad);

        //Cadastro no Banco local e volta para o login
        btnCadastrar.setOnClickListener(v -> {
            String nameCli = edtNomeUser.getText().toString();
            String email = edtTxtEmail.getText().toString();
            String senha = edtSenha.getText().toString();
            Cliente cli = new Cliente(null, nameCli, email, senha);

            DatabaseHelper mydb = new DatabaseHelper(cadastroActivity.this);

            try {
                mydb.insertCli(cli);
                Toast.makeText(getApplicationContext(), "Cadastro efetuado com sucesso", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getBaseContext(), loginActivity.class));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
}

