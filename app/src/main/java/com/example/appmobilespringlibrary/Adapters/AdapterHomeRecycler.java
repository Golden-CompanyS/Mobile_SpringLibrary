package com.example.appmobilespringlibrary.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.appmobilespringlibrary.Activities.LivroEspecifico;
import com.example.appmobilespringlibrary.BD.Livro;
import com.example.appmobilespringlibrary.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.picasso.transformations.CropSquareTransformation;

public class AdapterHomeRecycler extends RecyclerView.Adapter<AdapterHomeRecycler.LivroViewHolder>  {
    Context context;
    List<Livro> livroList = new ArrayList<Livro>();

    public AdapterHomeRecycler(Context context, List<Livro> livro1) {
        this.context = context;
        this.livroList = livro1;
    }

    public void setLivroList(List<Livro> livro1) {
        this.livroList = livro1;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return livroList == null ? 0 : livroList.size();
    }

    @NonNull
    @Override
    public AdapterHomeRecycler.LivroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_livro,parent,false);
        return new LivroViewHolder(view);
    }
    public class LivroViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitLivro, txtPrecoLiv;
        ImageView imgLivro;


        public LivroViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitLivro = (TextView)itemView.findViewById(R.id.txtViewProdNome);
            txtPrecoLiv = (TextView) itemView.findViewById(R.id.TxtViewProdPreco);
            imgLivro = (ImageView) itemView.findViewById(R.id.imgviewProd);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull LivroViewHolder holder, int position) {
        //define da onde vem os valores
        Picasso.get()
                .load(livroList.get(position).getImgLivro())
                .placeholder(R.mipmap.ic_launcher_round)
                .transform(new CropSquareTransformation())
                .error(R.mipmap.ic_launcher_round)
                .into(holder.imgLivro);

        //seta o preço do livro de acordo com a busca na API
        String prodNome = livroList.get(position).getProdNome();
        holder.txtTitLivro.setText(prodNome);

        //seta o preço do livro de acordo com a busca na API
        String precoProd = livroList.get(position).getPrecoLiv().toString();
        holder.txtPrecoLiv.setText("R$ " + precoProd);

        //onclick abre produto
        String ISBNLiv = livroList.get(position).getISBN();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("ISBNLiv:", String.valueOf(ISBNLiv));

                Intent AbreProd = new Intent(context, LivroEspecifico.class);
                AbreProd.putExtra("ISBNLiv", ISBNLiv);
                context.startActivity(AbreProd);
            }
        });


    }
}
