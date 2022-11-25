package com.example.appmobilespringlibrary;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropSquareTransformation;

public class AdapterHomeRecycler extends RecyclerView.Adapter<AdapterHomeRecycler.ProdutoViewHolder>{
    Context context;
    List<Livro> livroList;

    public AdapterHomeRecycler(Context context, List<Livro> livList){
        this.context=context;
        this.livroList=livList;
    }

    public void setMovieList(List<Livro> livros) {
        this.livroList = livros;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    //define o layout usado
    public AdapterHomeRecycler.ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_livro,parent,false);
        return new ProdutoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHomeRecycler.ProdutoViewHolder holder, int position) {
        //define da onde vem os valores
        Picasso.get()
                .load(livroList.get(position).getImgLivro())
                .placeholder(R.mipmap.ic_launcher_round)
                .transform(new CropSquareTransformation())
                .error(R.mipmap.ic_launcher_round)
                .into(holder.imgviewProd);

        String prodNome =livroList.get(position).getProdNome();

        if(prodNome.length()>12){
            holder.txtViewProdNome.setText(prodNome.substring(0, 9)+"...");
        }else {
            holder.txtViewProdNome.setText(prodNome);
        }

        String precoProd=livroList.get(position).getPrecoLiv().toString();
        String penultimaChar= String.valueOf(precoProd.charAt(precoProd.length() - 2));
        if(penultimaChar.equals(".")){
            holder.TxtViewProdPreco.setText("R$ "+precoProd+"0");
        }
        else
            holder.TxtViewProdPreco.setText("R$ "+precoProd);

        //onclick abre produto
        int ISBN= livroList.get(position).getISBN();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("ISBN:", String.valueOf(ISBN));

                Intent AbreProd = new Intent(context, LivroEspecifico.class);
                AbreProd.putExtra("ISBN",ISBN);
                context.startActivity(AbreProd);
            }
        });
    }

    @Override
    public int getItemCount() {
        return livroList == null ? 0 : livroList.size();
    }

    //define os campo com o layout
    public class ProdutoViewHolder extends RecyclerView.ViewHolder {
        ImageView imgviewProd;
        TextView txtViewProdNome;
        TextView TxtViewProdPreco;

        public ProdutoViewHolder(@NonNull View itemView) {
            super(itemView);

            imgviewProd = itemView.findViewById(R.id.imgviewProd);
            txtViewProdNome = itemView.findViewById(R.id.txtViewProdNome);
            TxtViewProdPreco = itemView.findViewById(R.id.TxtViewProdPreco);
        }
    }
}
