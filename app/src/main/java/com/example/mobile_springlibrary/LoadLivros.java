package com.example.mobile_springlibrary;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class LoadLivros extends AsyncTaskLoader<String> {
    private String mQueryString;
    LoadLivros(Context context, String queryString) {
        super(context);
        mQueryString = queryString;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    //CARREGA O MÉTODO DA NETWORK UTILS PARA LISTAR OS LIVROS
    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.searchLivros();
    }
}
