package com.example.appmobilespringlibrary.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appmobilespringlibrary.BD.ItemCarrinho;
import com.example.appmobilespringlibrary.BD.Livro;
import com.example.appmobilespringlibrary.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterCarrinhoList extends BaseAdapter {
    int layout;
    Context context;
    List<Livro> livroList = new ArrayList<Livro>();
   ImageButton btnAdd, btnExcluir;

    public AdapterCarrinhoList(Context context, List<Livro> livro) {
        this.context = context;
        this.layout = layout;
        this.livroList = livro;
    }

    @Override
    public int getCount() {
        return livroList.size();
    }

    @Override
    public Object getItem(int position) {
        return livroList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setLivroList(List<Livro> listProd) {
        this.livroList = listProd;
        notifyDataSetChanged();
    }

    @SuppressLint("DefaultLocale")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context) .inflate(R.layout.listview_carrinho, parent, false);

        ImageView imgProd = (ImageView)view.findViewById(R.id.imgProduto);
        TextView nome = (TextView) view.findViewById(R.id.txtNomeLivro);
        TextView subtotal = (TextView) view.findViewById(R.id.txtSubtotalProduto);
        TextView qtd = (TextView) view.findViewById(R.id.txtqtdProd);
        Livro liv = (Livro) getItem(position);

        //set strings

        nome.setText(liv.getProdNome());
        subtotal.setText(liv.getPrecoLiv().toString());
        return view;
    }

}
