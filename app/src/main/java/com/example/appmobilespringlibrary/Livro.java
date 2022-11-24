package com.example.appmobilespringlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Livro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livroespecifico);


        Button addCarrinho = (Button) findViewById(R.id.btnAddCarrinho);
        addCarrinho.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Carrinho.class));
                finish();
            }
        });
    }

    @SerializedName("isbn")
    @Expose
    public Integer ISBN;

    @SerializedName("titLivro")
    @Expose
    public String titLivro;

    @SerializedName("precoLivro")
    @Expose
    public Double precoLiv;

    @SerializedName("sinopLiv")
    @Expose
    public String sinopLiv;

    @SerializedName("anoLiv")
    @Expose
    public Integer anoLiv;

    @SerializedName("numPag")
    @Expose
    public Integer numPag;

    @SerializedName("imgLivro")
    @Expose
    public String imgLivro;

    public Livro(int isbn, String prodNome, Double prodValor,String sinopLiv, Integer anoLiv, Integer numPag, String imgLivro) {
        this.ISBN=isbn;
        this.titLivro=prodNome;
        this.precoLiv=prodValor;
        this.sinopLiv=sinopLiv;
        this.anoLiv=anoLiv;
        this.numPag=numPag;
        this.imgLivro=imgLivro;
    }

    public Integer getISBN() {
        return ISBN;
    }

    public void setISBN(Integer isbn) {
        ISBN = isbn;
    }

    public String getProdNome() {
        return titLivro;
    }

    public void setProdNome(String prodNome) {
        titLivro = prodNome;
    }

    public String getSinopLiv() {
        return sinopLiv;
    }

    public void setSinopLiv(String SinopLiv) {
        sinopLiv = SinopLiv;
    }

    public Integer getAnoLiv() {
        return anoLiv;
    }

    public void setAnoLiv(Integer AnoLiv) {
        anoLiv = AnoLiv;
    }


    public Double getPrecoLiv() {
        return precoLiv;
    }

    public void setPrecoLiv(Double PrecoLiv) {
        precoLiv = PrecoLiv;
    }

    public String getImgLivro() {
        return imgLivro;
    }

    public void setImgLivro(String ImgLivro) {
        imgLivro = ImgLivro;
    }

    @Override
    public String toString() {
        return "ISBN: " + getISBN() +
                "\n titLivro: " + getProdNome()+
                "\n sinopLiv: " + getSinopLiv()+
                "\n PrecoLiv: " + getPrecoLiv()+
                "\n ImgLivro: " + getImgLivro()+"\n";
    }


    //IMPLEMENTAR URL E CONCATENAR COM O MÉTODO DA API DE ENCONTRAR LIVRO POR NOME
    // /LISTAR TODOS OS LIVROS/INFO DE LIVRO ESPECÍFICO

}
