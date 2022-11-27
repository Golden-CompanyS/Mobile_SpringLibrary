package com.example.appmobilespringlibrary.BD;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmobilespringlibrary.Activities.Carrinho;
import com.example.appmobilespringlibrary.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;

import java.io.Serializable;

public class Livro extends JSONArray implements Serializable {
    @SerializedName("ISBNLiv")
    @Expose
    public String ISBN;

    @SerializedName("TitLiv")
    @Expose
    public String titLivro;

    @SerializedName("PrecoLiv")
    @Expose
    public Double precoLiv;

    @SerializedName("SinopLiv")
    @Expose
    public String sinopLiv;

    @SerializedName("AnoLiv")
    @Expose
    public Integer anoLiv;

    @SerializedName("NumPag")
    @Expose
    public Integer numPag;

    @SerializedName("ImgLiv")
    @Expose
    public String imgLivro;

    @SerializedName("NomEdit")
    @Expose
    public String Editora;

    public Livro(){}

    public Livro(String isbn, String prodNome, Double prodValor,String sinopLiv, Integer anoLiv, Integer numPag, String imgLivro, String editora) {
        this.ISBN=isbn;
        this.titLivro=prodNome;
        this.precoLiv=prodValor;
        this.sinopLiv=sinopLiv;
        this.anoLiv=anoLiv;
        this.numPag=numPag;
        this.imgLivro=imgLivro;
        this.Editora=editora;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String isbn) {
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

    public Integer getNumPag(){return numPag;}

    public void setNumPag(Integer NumPag){ numPag=NumPag;}

    public String getImgLivro() {
        return imgLivro;
    }

    public void setImgLivro(String ImgLivro) {
        imgLivro = ImgLivro;
    }

    public String getEditora() {
        return Editora;
    }

    public void setEditora(String editora) {
        Editora = editora;
    }
    @Override
    public String toString() {
        return "ISBN: " + getISBN() +
                "\n titLivro: " + getProdNome()+
                "\n sinopLiv: " + getSinopLiv()+
                "\n PrecoLiv: " + getPrecoLiv()+
                "\n ImgLivro: " + getImgLivro()+
                "\n NomEdit: " + getEditora()+"\n";
    }


}
