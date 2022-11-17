package com.example.mobile_springlibrary;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class LoadCli extends AsyncTaskLoader<String> {
    private String mQueryString;
    LoadCli(Context context, String queryString) {
        super(context);
        mQueryString = queryString;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    //CARREGA O MÉTODO DA NETWORK UTILS PARA ENCONTRAR O CLIENTE CORRESPONDENTE (CARREGA EM SEGUNDO PLANO)
    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.searchCli(mQueryString);
    }
}
