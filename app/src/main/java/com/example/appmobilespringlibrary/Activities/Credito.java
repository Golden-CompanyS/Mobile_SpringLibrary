package com.example.appmobilespringlibrary.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmobilespringlibrary.Activities.Compra;
import com.example.appmobilespringlibrary.R;

public class Credito extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartao);

        Button pagar = (Button) findViewById(R.id.btnPagar);
        pagar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Compra.class));
                finish();
            }
        });
    }
}
