package com.example.appmobilespringlibrary.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmobilespringlibrary.BD.Livro;
import com.example.appmobilespringlibrary.R;
import com.example.appmobilespringlibrary.RESTService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.picasso.transformations.CropSquareTransformation;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LivroEspecifico extends AppCompatActivity {
    String LinkApi = "https://differentgreendog2.conveyor.cloud/api/SpringLibrary/";

    private Retrofit retrofitProd;

    List<Livro> livro;
    ArrayList<Livro> liv = new ArrayList<>();
    ImageView imgProd;
    TextView textNomeProd, textISBN, textDateLanc, textDesc, textPreco, textEditora;
    String ISBN;
    ScrollView TelaToda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livroespecifico);
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

        imgProd = (ImageView) findViewById(R.id.imgLivroEspecifico);
        textNomeProd =(TextView) findViewById(R.id.txtTitLivro);
        textISBN =(TextView) findViewById(R.id.txtISBN);
        textPreco =(TextView) findViewById(R.id.txtPrecoLivroEspecifico);
        textDateLanc = (TextView)findViewById(R.id.txtViewAnoEdicao);
        textDesc = (TextView)findViewById(R.id.txtNomeLiv);
        textEditora = (TextView)findViewById(R.id.txtEdit);

        //ADICIONAR LIVRO NO CARRINHO
        Button addCarrinho = (Button) findViewById(R.id.btnAddCarrinho);
        addCarrinho.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Carrinho.class));
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
                    
                    // livro = liv.get(0);
                   //mostra dados na tela
                    Picasso.get()
                            .load(livro.getImgLivro())
                            .placeholder(R.mipmap.ic_launcher_round)
                            .transform(new CropSquareTransformation())
                            .error(R.mipmap.ic_launcher_round)
                            .into(imgProd);
                    textNomeProd.setText(livro.getProdNome());
                    textISBN.setText(livro.getISBN());
                    textDateLanc.setText(livro.getAnoLiv());
                    textDesc.setText(livro.getSinopLiv());
                    textEditora.setText(livro.getEditora());

                    String precoProd = livro.getPrecoLiv().toString();
                    String penultimaChar = String.valueOf(precoProd.charAt(precoProd.length() - 2));
                    if (penultimaChar.equals(".")) {
                        textPreco.setText("R$" + precoProd + "0");
                    } else{
                        textPreco.setText("R$" + precoProd);
                }

                }
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(Call<List<Livro>> call, Throwable t) {
                Log.i("Ocorreu um erro ao tentar consultar o produto. Erro:", t.getMessage());
                Toast.makeText(LivroEspecifico.this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
