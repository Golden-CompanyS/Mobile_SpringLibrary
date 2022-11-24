package com.example.appmobilespringlibrary;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class fragment_livros extends Fragment {

        private Retrofit retrofitHomeProd;

        List<Livro> produtoList;

        AdapterHomeRecycler adapterLivro;
        public RecyclerView recyclerViewLivros;

        SearchView editPesquisa;
        // Link da API
        String LinkApi = "";

        ProgressBar progressBar;
        ScrollView TelaToda;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            readDataLinkApi();

            //constrói a url
            retrofitHomeProd = new Retrofit.Builder()
                    .baseUrl(LinkApi+"api/SpringLibrary/")      //endere-ço do webservice
                    .addConverterFactory(GsonConverterFactory.create()) //conversor
                    .build();

            //lista os Livros
            MostraLivros();
        }

        @SuppressLint("MissingInflatedId")
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view= inflater.inflate(R.layout.fragment_livros, container, false);

           //FAZER A PARTE DE PESQUISA



            //inicia o recyclerView

            recyclerViewLivros=(RecyclerView)view.findViewById(R.id.recyclerview_livros);

            recyclerViewLivros.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


            adapterLivro = new AdapterHomeRecycler(getContext(), produtoList);


            recyclerViewLivros.setAdapter(adapterLivro);

            progressBar =(ProgressBar) view.findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);

            TelaToda =(ScrollView) view.findViewById(R.id.TelaToda);
            TelaToda.setVisibility(View.GONE);

            return view;
        }

        private void MostraLivrosPromo() {
            //pega categoria
            String sCat = "Tiro";

            //pesquisa
            RESTService restService = retrofitHomeProd.create(RESTService.class);
            Call<List<Livro>> call= restService.MostraProd();
            //executa e mostra a requisisao
            call.enqueue(new Callback<List<Livro>>() {
                @Override
                public void onResponse(Call<List<Livro>> call, Response<List<Livro>> response) {
                    if (response.isSuccessful()) {
                        produtoList = response.body();
                        adapterLivro.setMovieList(produtoList);

                        progressBar.setVisibility(View.GONE);
                        TelaToda.setVisibility(View.VISIBLE);
                    }
                }

                @SuppressLint("LongLogTag")
                @Override
                public void onFailure(Call<List<Livro>> call, Throwable t) {
                    Log.i("Ocorreu um erro ao tentar consultar o Perfil. Erro:", t.getMessage());
                }
            });
        }



        public  void DetalhesProd(){
            Intent DetelhesProd = new Intent(getActivity(), LivroEspecifico.class);
            DetelhesProd.putExtra("codProduto",3);
            startActivity(DetelhesProd);
        }

        public  void Pesquisa(){
            String TxtPesquisa=editPesquisa.getText().toString();
            editPesquisa.set("");

            //abre a tela pesquisa
            Intent TelaPesquisa = new Intent(getContext(),Pesquisa.class);
            TelaPesquisa.putExtra("TxtPesquisa",TxtPesquisa);
            startActivity(TelaPesquisa);
        }


        //ler Link Da api da memoria
        private void readDataLinkApi() {
            try {
                FileInputStream fin = getActivity().openFileInput("LinkApi.txt");
                int a;
                //constroi a string letra por letra
                StringBuilder temp = new StringBuilder();
                while ((a = fin.read()) != -1) {
                    temp.append((char)a);
                }

                LinkApi=temp.toString();
                fin.close();//fecha busca
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}