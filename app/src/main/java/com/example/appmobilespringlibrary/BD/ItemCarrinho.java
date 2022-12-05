package com.example.appmobilespringlibrary.BD;

public class ItemCarrinho {
    String IdProd, NomItem;
    int qtdProd, IdItem;
    Double precoProd;
    public ItemCarrinho(String nomItem, String idProd, Double precoprod,int qtdprod, int idItem) {
        this.IdProd=idProd;
        this.NomItem=nomItem;
        this.precoProd=precoprod;
        this.qtdProd=qtdprod;
        this.IdItem=idItem;
    }

    public ItemCarrinho() {

    }

    public ItemCarrinho(String isbnLiv, String titLivro, Double precoLiv) {
        this.IdProd=isbnLiv;
        this.NomItem=titLivro;
        this.precoProd=precoLiv;
    }

    public int getIdItem(){return IdItem;}

    public void setIdItem(int idItem){IdItem=idItem;}
    public String getIdProd() {
        return IdProd;
    }

    public void setIdProd(String idProd) {
        IdProd = idProd;
    }

    public String getProdNome() {
        return NomItem;
    }

    public void setProdNome(String prodNome) {
        NomItem = prodNome;
    }

    public Double getPrecoProd() {
        return precoProd;
    }

    public void setPrecoProd(Double precoprod) {
        precoProd = precoprod;
    }

    public Integer getQtdProd() {
        return qtdProd;
    }

    public void setQtdProd(Integer qtdprod) {
        qtdProd = qtdprod;
    }

    @Override
    public String toString() {
        return "IdProd: " + getIdProd() +
                "\n IdItem: " + getIdItem()+
                "\n prodNome: " + getProdNome()+
                "\n precoProd: " + getPrecoProd()+
                "\n qtdProd: " + getQtdProd()+ "\n";
    }
}
