package com.example.appmobilespringlibrary.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmobilespringlibrary.Adapters.AdapterCarrinhoList;
import com.example.appmobilespringlibrary.Pesquisa;
import com.example.appmobilespringlibrary.R;

public class Carrinho extends AppCompatActivity {

    ImageView imgLivro;
    TextView qtdProd, titLiv, total ;
    String ISBN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        Intent intent = getIntent();
        ISBN = intent.getStringExtra("ISBNLiv");

        AdapterCarrinhoList adapter = new AdapterCarrinhoList(getApplicationContext(),R.layout.listview_carrinho, carrinhoList);
        listViewProduct = findViewById(R.id.listviewCarrinho);
        listViewProduct.setAdapter(adapter);
        imgLivro = findViewById(R.id.imgProduto);
        titLiv = findViewById(R.id.txtNomeLivro);
        total = findViewById(R.id.txtSubtotalProduto);

        Button continuarCarrinho = (Button) findViewById(R.id.btnContinuarCarrinho);
        continuarCarrinho.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Dados.class));
                finish();
            }
        });

        ImageButton ImgBtnConta = (ImageButton) findViewById(R.id.imgBtnConta);
        ImgBtnConta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), PerfilUser.class));
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
    }
}
