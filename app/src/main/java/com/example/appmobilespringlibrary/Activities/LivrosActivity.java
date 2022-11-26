package com.example.appmobilespringlibrary.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmobilespringlibrary.BD.Livro;
import com.example.appmobilespringlibrary.R;

public class LivrosActivity extends AppCompatActivity {

    public RecyclerView recycleViewRomances;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livros);


        // RecyclerView recycleViewRomance = (RecyclerView) findViewById();
        //recycleViewRomance.setOnClickListener(new View.OnClickListener(){
           // @Override
          //  public void onClick(View v) {
         //       startActivity(new Intent(getBaseContext(), Livro.class));
        //        finish();
         //   }
      //  });
    }
}
