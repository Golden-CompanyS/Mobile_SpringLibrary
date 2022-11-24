package com.example.appmobilespringlibrary;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RESTService {

    //LIVROS
    @GET("prodCategoria?")
    Call<List<Livro>> MostraProd();

    @GET("ProdDetalhado?")
    Call<Livro> MostraProdDetalhes(@Query("idProd") int codProd);

    @GET("PesquisaProd?")
    Call<List<Livro>>  PesquisaProduto(@Query("txtPesquisa") String txtPesquisa);

}
