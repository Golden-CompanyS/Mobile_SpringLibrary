package com.example.appmobilespringlibrary;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmobilespringlibrary.R;
import com.google.android.material.textfield.TextInputEditText;

public class cadastroActivity extends AppCompatActivity {
    EditText edtTxtEmail;
    TextInputEditText edtSenha, confSenha;
    Button btnCadastrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_etapa1);

        edtTxtEmail = findViewById(R.id.editTextEmail);
        edtSenha = findViewById(R.id.edtInputTextSenha);
        confSenha = findViewById(R.id.edtInputConfSenha);



    }
}
