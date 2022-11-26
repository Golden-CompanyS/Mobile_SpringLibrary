package com.example.appmobilespringlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmobilespringlibrary.Activities.Carrinho;
import com.example.appmobilespringlibrary.Activities.HomeActivity;
import com.example.appmobilespringlibrary.Activities.PerfilUser;

public class Pesquisa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);

        ImageButton ImgBtnConta = (ImageButton) findViewById(R.id.imgBtnPerfil);
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

        ImageButton ImgBtnCarrinho = (ImageButton) findViewById(R.id.imgBtnCarrinho);
        ImgBtnCarrinho.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Carrinho.class));
                finish();
            }
        });
    }
}
