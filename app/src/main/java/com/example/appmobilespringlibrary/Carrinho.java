package com.example.appmobilespringlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Carrinho extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        Button continuarCarrinho = (Button) findViewById(R.id.btnContinuarCarrinho);
        continuarCarrinho.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Dados.class));
                finish();
            }
        });
    }
}
