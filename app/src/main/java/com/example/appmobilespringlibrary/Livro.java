package com.example.appmobilespringlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Livro extends AppCompatActivity {
    public static final String url ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livroespecifico);


        Button addCarrinho = (Button) findViewById(R.id.btnAddCarrinho);
        addCarrinho.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Carrinho.class));
                finish();
            }
        });
    }

    String titLivro;
    String sinopLiv;
    String precoLiv;
    String ISBN;
    String anoLiv;
    String Autor;

    //IMPLEMENTAR URL E CONCATENAR COM O MÉTODO DA API DE ENCONTRAR LIVRO POR NOME
    // /LISTAR TODOS OS LIVROS/INFO DE LIVRO ESPECÍFICO

}
