package com.example.appmobilespringlibrary.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmobilespringlibrary.BD.Cliente;
import com.example.appmobilespringlibrary.Pagamento;
import com.example.appmobilespringlibrary.R;
import com.example.mobile_springlibrary.ClassesBanco.DatabaseHelper;

public class Dados extends AppCompatActivity {

    EditText edtTxtNome;
    Button btnConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados);

        edtTxtNome = findViewById(R.id.editTextNomeFantasia);
        btnConfirmar = findViewById(R.id.btnConfirmar);

        ImageButton ImgBtnVoltar = (ImageButton) findViewById(R.id.imgBtnVoltar);
        ImgBtnVoltar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Carrinho.class));
                finish();
            }
        });

        ImageButton ImgBtnHome = (ImageButton) findViewById(R.id.imgBtnHome);
        ImgBtnHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), HomeActivity.class));
                finish();
            }
        });

        ImageButton ImgBtnPesquisa = (ImageButton) findViewById(R.id.imgBtnPesquisar);
        ImgBtnPesquisa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Pesquisa.class));
                finish();
            }
        });

        ImageButton ImgBtnCarrinho = (ImageButton) findViewById(R.id.imgBtnCarrinho);
        ImgBtnCarrinho.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Carrinho.class));
                finish();
            }
        });

        Button confirmar = (Button) findViewById(R.id.btnConfirmar);
        confirmar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Pagamento.class));
                finish();
            }
        });

        btnConfirmar.setOnClickListener(v -> {
            String nameCli = edtTxtNome.getText().toString();
            Cliente cli = new Cliente(null, nameCli);

            DatabaseHelper mydb = new DatabaseHelper(Dados.this);

            try {
                mydb.insertCli(cli);
                Toast.makeText(getApplicationContext(), "Dados confirmados com sucesso", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getBaseContext(), Pagamento.class));
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Dados incompletos", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
