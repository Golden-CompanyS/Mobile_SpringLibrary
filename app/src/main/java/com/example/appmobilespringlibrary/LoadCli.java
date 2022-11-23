package com.example.appmobilespringlibrary;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.io.IOException;

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
        try {
            return NetworkUtils.searchCli(mQueryString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
