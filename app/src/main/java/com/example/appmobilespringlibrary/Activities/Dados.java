package com.example.appmobilespringlibrary.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmobilespringlibrary.Pagamento;
import com.example.appmobilespringlibrary.R;

public class Dados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados);

        Button confirmar = (Button) findViewById(R.id.btnConfirmar);
        confirmar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Pagamento.class));
                finish();
            }
        });
    }
}
