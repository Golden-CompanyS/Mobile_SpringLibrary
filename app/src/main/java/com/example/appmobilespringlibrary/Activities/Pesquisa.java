package com.example.appmobilespringlibrary.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmobilespringlibrary.Activities.Carrinho;
import com.example.appmobilespringlibrary.Activities.HomeActivity;
import com.example.appmobilespringlibrary.Activities.PerfilUser;
import com.example.appmobilespringlibrary.Adapters.AdapterHomeRecycler;
import com.example.appmobilespringlibrary.BD.Livro;
import com.example.appmobilespringlibrary.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Pesquisa extends AppCompatActivity {
    private Retrofit retrofitPesquisaProd;

    List<Livro> produtoList;

    AdapterHomeRecycler adapterPesquisas;
    public RecyclerView recyclerViewPesquisa;
    EditText editPesquisa;
    String LinkApi;
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

        private void PesquisaProds() {
            //pega pesquisa
            Intent intent = getIntent();
            String Txtpesquisa= intent.getStringExtra("TxtPesquisa");

            //pesquisa
            RESTService restService = retrofitPesquisaProd.create(RESTService.class);
            Call<List<Produto>> call= restService.PesquisaProduto(Txtpesquisa);
            //executa e mostra a requisisao
            call.enqueue(new Callback<List<Produto>>() {
                @Override
                public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response) {
                    if (response.isSuccessful()) {
                        produtoList = response.body();
                        adapterPesquisas.setMovieList(produtoList);
                        Log.i("jOGOS Pesquisados", String.valueOf(produtoList));
                    }
                }

                @Override
                public void onFailure(Call<List<Produto>> call, Throwable t) {
                    Log.i("Ocorreu um erro ao tentar consultar o Perfil. Erro:", t.getMessage());
                }
            });
    }
}
