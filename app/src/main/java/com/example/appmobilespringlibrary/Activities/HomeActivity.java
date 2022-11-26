package com.example.appmobilespringlibrary.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmobilespringlibrary.Pesquisa;
import com.example.appmobilespringlibrary.R;
import com.example.appmobilespringlibrary.fragment_livros;

public class HomeActivity extends AppCompatActivity {
   // private List<LivrosActivity> mlistLiv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //codigo da fragment
       // Intent intent = getIntent();

            //inicia na home
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,
                    new fragment_livros()).commit();

        ImageButton ImgBtnConta = (ImageButton) findViewById(R.id.imgBtnConta);
        ImgBtnConta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), PerfilUser.class));
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

        /*CONFIGURAR RECYCLER VIEW
        RecyclerView recycleViewLivros = (RecyclerView) findViewById(R.id.recyleViewLivros);
        recycleViewLivros.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), LivrosActivity.class));
                finish();
            }
        });*/
    }



}