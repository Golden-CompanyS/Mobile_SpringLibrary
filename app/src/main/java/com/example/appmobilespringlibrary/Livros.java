package com.example.appmobilespringlibrary;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class Livros extends AppCompatActivity {

    public RecyclerView recycleViewRomances;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livros);


        RecyclerView recycleViewRomance = (RecyclerView) findViewById(R.id.recycleViewRomances);
        recycleViewRomance.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), LivroEspecifico.class));
                finish();
            }
        });
    }
}
