package com.example.appmobilespringlibrary.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmobilespringlibrary.Activities.Carrinho;
import com.example.appmobilespringlibrary.R;

public class LivroEspecifico extends AppCompatActivity {

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
}
