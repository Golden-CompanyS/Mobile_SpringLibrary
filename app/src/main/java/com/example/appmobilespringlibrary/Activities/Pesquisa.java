package com.example.appmobilespringlibrary.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmobilespringlibrary.Activities.Carrinho;
import com.example.appmobilespringlibrary.Activities.HomeActivity;
import com.example.appmobilespringlibrary.Activities.PerfilUser;
import com.example.appmobilespringlibrary.Adapters.AdapterHomeRecycler;
import com.example.appmobilespringlibrary.BD.Livro;
import com.example.appmobilespringlibrary.R;
import com.example.appmobilespringlibrary.RESTService;
import com.example.appmobilespringlibrary.fragment_livros;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Pesquisa extends AppCompatActivity {
    private Retrofit retrofitPesquisaProd;

    List<Livro> produtoList;

    AdapterHomeRecycler adapterPesquisas = new AdapterHomeRecycler(Pesquisa.this, produtoList);
    public RecyclerView recyclerViewPesquisa;
    String LinkApi = "https://differentashsled32.conveyor.cloud/api/SpringLibrary/";
    String gen;
    EditText edtPesq;
    ImageButton btnPesq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);

        retrofitPesquisaProd = new Retrofit.Builder()
                .baseUrl(LinkApi)
                .addConverterFactory(GsonConverterFactory.create()) //conversor
                .build();
        //inicia na home
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_containerPesquisa,
                new fragment_livros()).commit();

        //PesquisaProds();
        edtPesq = findViewById(R.id.edtPesquisar);
        btnPesq = findViewById(R.id.btnPesq);
        edtPesq.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int i, KeyEvent keyEvent) {
                if(keyEvent!=null&&(keyEvent.getKeyCode()==KeyEvent.KEYCODE_ENTER)|| (i== EditorInfo.IME_ACTION_DONE)){
                    NewPesquisa();
                }
                return false;
            }
        });
        btnPesq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewPesquisa();
            }
        });

        ImageButton ImgBtnConta = (ImageButton) findViewById(R.id.imgBtnPerfil);
        ImgBtnConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), PerfilUser.class));
                finish();
            }
        });

        ImageButton ImgBtnHome = (ImageButton) findViewById(R.id.imgBtnHome);
        ImgBtnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), HomeActivity.class));
                finish();
            }
        });

        ImageButton ImgBtnCarrinho = (ImageButton) findViewById(R.id.imgBtnCarrinho);
        ImgBtnCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Carrinho.class));
                finish();
            }
        });

    }

    /*public void PesquisaProds() {
        //pega pesquisa
        Intent intent = getIntent();
        String gen = intent.getStringExtra("genero");
        //pesquisa
        RESTService restService = retrofitPesquisaProd.create(RESTService.class);
        Call<List<Livro>> call= restService.MostraProdPorCat(gen);
        //executa e mostra a requisisao
        call.enqueue(new Callback<List<Livro>>() {
            @Override
            public void onResponse(Call<List<Livro>> call, Response<List<Livro>> response) {
                if (response.isSuccessful()) {
                    produtoList = response.body();
                    adapterPesquisas.setLivroList(produtoList);
                    Log.i("livros pesquisados", String.valueOf(produtoList));
                }
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(Call<List<Livro>> call, Throwable t) {
                Log.i("Ocorreu um erro ao tentar consultar o Perfil. Erro:", t.getMessage());
            }
        });
    }*/
    public  void NewPesquisa(){
        if(!edtPesq.equals("")){
            String TxtPesquisa=edtPesq.getText().toString();

            this.finish();
            //abre a tela pesquisa
            Intent pesquisa1 = new Intent(getApplicationContext(),Pesquisa.class);
            pesquisa1.putExtra("genero",TxtPesquisa);
            startActivity(pesquisa1);
        }

    }
}
