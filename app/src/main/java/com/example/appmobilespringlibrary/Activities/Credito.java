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

public class Credito extends AppCompatActivity {

    EditText edtNumCartao;
    Button btnPagar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartao);

        edtNumCartao = findViewById(R.id.editTextNumberNumCartao);
        btnPagar = findViewById(R.id.btnPagar);

        ImageButton ImgBtnVoltar = (ImageButton) findViewById(R.id.imgBtnVoltar);
        ImgBtnVoltar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Pagamento.class));
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

        btnPagar.setOnClickListener(v -> {
            String nameCli = edtNumCartao.getText().toString();
            Cliente cli = new Cliente(null, nameCli);

            DatabaseHelper mydb = new DatabaseHelper(Credito.this);

            try {
                mydb.insertCli(cli);
                Toast.makeText(getApplicationContext(), "Pagamento realizado", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getBaseContext(), HomeActivity.class));
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Dados incompletos", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
