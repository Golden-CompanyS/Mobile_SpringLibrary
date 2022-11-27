package com.example.appmobilespringlibrary;

import com.example.appmobilespringlibrary.BD.Livro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RESTService {
    //*********************//
    //      Produto       //
    //*********************//
    @GET("getLivByGenero?")
    Call<List<Livro>> MostraProdPorCat(@Query("genero") String gen);

    @GET("getAllLivros")
    Call<List<Livro>> ShowAllLivros();

    @GET("getLivByISBN?")
    Call<Livro> MostraProdDetalhes(@Query("isbn") String ISBNLiv);

    @GET("getLivByName?")
    Call<List<Livro>>  PesquisaProduto(@Query("txtPesquisa") String txtPesquisa);

}
