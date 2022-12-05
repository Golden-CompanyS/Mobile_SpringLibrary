package com.example.appmobilespringlibrary.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmobilespringlibrary.Adapters.AdapterCarrinhoList;
import com.example.appmobilespringlibrary.BD.ItemCarrinho;
import com.example.appmobilespringlibrary.BD.Livro;
import com.example.appmobilespringlibrary.R;
import com.example.appmobilespringlibrary.RESTService;
import com.example.mobile_springlibrary.ClassesBanco.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Carrinho extends AppCompatActivity {

    ListView listCart;
    List<ItemCarrinho> carrinhoList = new ArrayList<ItemCarrinho>();
    AdapterCarrinhoList adapterCarrinhoList;
    String ISBN;
    String LinkApi = "https://fastgoldpage14.conveyor.cloud/api/SpringLibrary/";
    List<Livro> livro;
    Livro liv = new Livro();
    private Retrofit retrofitProd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
        DatabaseHelper mydb = new DatabaseHelper(Carrinho.this);

        /*
        //PEGAR O CÓDIGO DO LIVRO (ISBN)
        Intent intent = getIntent();
        ISBN = intent.getStringExtra("ISBNLiv");

        //SETAR A LISTA COM OS PRODUTOS NO CARRINHO
        ListView lista = (ListView)findViewById(R.id.listViewCarrinho);
        ArrayList arrayList = mydb.getAllItemCarrinho();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.listview_carrinho , arrayList);
        lista.setAdapter(arrayAdapter);
        Cursor cursor = mydb.getDataItemCarrinho(ISBN);
        cursor.moveToFirst();

        ItemCarrinho item = new ItemCarrinho();
        item.setIdProd(cursor.getString(cursor.getColumnIndexOrThrow("IdProd")));
        item.setProdNome(cursor.getString(cursor.getColumnIndexOrThrow("nomItem")));
        item.setPrecoProd(Double.valueOf(cursor.getString(cursor.getColumnIndexOrThrow("PrecoItem"))));

        carrinhoList.add(item);
        listCart=(ListView) findViewById(R.id.listViewCarrinho);
        adapterCarrinhoList = new AdapterCarrinhoList(getApplicationContext(), livro);
        listCart.setAdapter(adapterCarrinhoList);
        if (!cursor.isClosed()){
            cursor.close();
        }


        //mostra prod
        retrofitProd = new Retrofit.Builder()
                .baseUrl(LinkApi)                             //ENDEREÇO DA API
                .addConverterFactory(GsonConverterFactory.create()) //conversor
                .build();

        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient=new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();


        Button continuarCarrinho = (Button) findViewById(R.id.btnContinuarCarrinho);
        continuarCarrinho.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Dados.class));
                finish();
            }
        });
*/
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

        Button BtnContinuar = (Button) findViewById(R.id.btnContinuarCarrinho);
        BtnContinuar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Dados.class));
                finish();
            }
        });

        //MostraLivro();
    }
/*
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
    }*/

}
