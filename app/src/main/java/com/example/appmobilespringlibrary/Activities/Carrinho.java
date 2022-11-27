package com.example.appmobilespringlibrary.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmobilespringlibrary.Adapters.AdapterCarrinhoList;
import com.example.appmobilespringlibrary.BD.Livro;
import com.example.appmobilespringlibrary.Pesquisa;
import com.example.appmobilespringlibrary.R;
import com.example.appmobilespringlibrary.RESTService;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropSquareTransformation;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Carrinho extends AppCompatActivity {

    ListView listCart;
    AdapterCarrinhoList adapterCarrinhoList;

    String ISBN;
    String LinkApi = "https://nextpurplerock7.conveyor.cloud/api/SpringLibrary/";
    List<Livro> livro;
    Livro liv = new Livro();
    private Retrofit retrofitProd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        //PEGAR O CÓDIGO DO LIVRO (ISBN)
        Intent intent = getIntent();
        ISBN = intent.getStringExtra("ISBNLiv");

        //mostra prod
        retrofitProd = new Retrofit.Builder()
                .baseUrl(LinkApi)                             //ENDEREÇO DA API
                .addConverterFactory(GsonConverterFactory.create()) //conversor
                .build();

        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient=new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        listCart=(ListView) findViewById(R.id.listViewCarrinho);
        adapterCarrinhoList = new AdapterCarrinhoList(getApplicationContext(), livro);
        listCart.setAdapter(adapterCarrinhoList);
        Button continuarCarrinho = (Button) findViewById(R.id.btnContinuarCarrinho);
        continuarCarrinho.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Dados.class));
                finish();
            }
        });

        ImageButton ImgBtnConta = (ImageButton) findViewById(R.id.imgBtnConta);
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

        ImageButton ImgBtnPesquisa = (ImageButton) findViewById(R.id.imgBtnPesquisar);
        ImgBtnPesquisa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Pesquisa.class));
                finish();
            }
        });
        MostraLivro();
    }

    private void MostraLivro() {
        //pesquisa
        RESTService restService = retrofitProd.create(RESTService.class);
        Call<List<Livro>> call = restService.MostraProdDetalhes(ISBN);

        //executa e mostra a requisisao
        call.enqueue(new Callback<List<Livro>>() {
            @Override
            public void onResponse(Call<List<Livro>> call, Response<List<Livro>> response) {
                if (response.isSuccessful()) {
                    livro = response.body();
                    liv = livro.get(0);
                    adapterCarrinhoList.setLivroList(livro);
                }
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(Call<List<Livro>> call, Throwable t) {
                Log.i("Ocorreu um erro ao tentar consultar o produto. Erro:", t.getMessage());
                Toast.makeText(Carrinho.this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
            }
        });
    }}
