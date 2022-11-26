package com.example.appmobilespringlibrary;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.appmobilespringlibrary.Adapters.AdapterHomeRecycler;
import com.example.appmobilespringlibrary.BD.Livro;
import com.example.appmobilespringlibrary.R;
import com.example.appmobilespringlibrary.RESTService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class fragment_livros extends Fragment {


    List<Livro> produtoList;
    private Retrofit retrofitHomeProd;
    AdapterHomeRecycler adapterLivro;
    public RecyclerView recyclerViewLivros;


    TextView txtTitLiv, txtPrecoLiv, txtISBNLiv;
    ImageView imgLivro;
    // Link da API
    String LinkApi = "https://smallbrushedroof2.conveyor.cloud/api/SpringLibrary/";

    ProgressBar progressBar;
    ScrollView TelaToda;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retrofitHomeProd = new Retrofit.Builder()
              .baseUrl(LinkApi)                                       //endere-ço do webservice
                .addConverterFactory(GsonConverterFactory.create()) //conversor
               .build();

        // inicia a mostra de livros
        MostraLivros();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_livros, container, false);
       // View item_livro = inflater.inflate(R.layout.item_livro, container, false);
        //INICIA AS TEXT VIEW
        txtTitLiv = (TextView) view.findViewById(R.id.txtTitLivro);
        txtPrecoLiv = (TextView) view.findViewById(R.id.TxtViewProdPreco);
        imgLivro = (ImageView) view.findViewById(R.id.imgviewProd);

        //inicia o recyclerView
        recyclerViewLivros = (RecyclerView) view.findViewById(R.id.recyclerview_livros);
        recyclerViewLivros.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapterLivro = new AdapterHomeRecycler(getContext(), produtoList);
        recyclerViewLivros.setAdapter(adapterLivro);

        //TelaToda = (ScrollView) view.findViewById(R.id.TelaToda);
        //TelaToda.setVisibility(View.GONE);

        return view;
    }

    public void MostraLivros() {
        //pesquisa
        RESTService restService = retrofitHomeProd.create(RESTService.class);
        Call<List<Livro>> call = restService.ShowAllLivros();
        //executa e mostra a requisição
        call.enqueue(new Callback<List<Livro>>() {
            @Override
            public void onResponse(Call<List<Livro>> call, Response<List<Livro>> response) {
                if (response.isSuccessful()) {
                    produtoList = response.body();
                    adapterLivro.setLivroList(produtoList);
                }
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(Call<List<Livro>> call, Throwable t) {
                Log.i("Ocorreu um erro ao tentar consultar o Perfil. Erro:", t.getMessage());
            }
        });

    }
}