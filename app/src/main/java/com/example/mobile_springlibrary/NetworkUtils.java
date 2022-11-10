package com.example.mobile_springlibrary;

import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.net.HttpURLConnection;

public class NetworkUtils {
    private static final String URL_API = "";
    private static HttpURLConnection connec;
    private static BufferedReader reader;
    private static String springLib;
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    static String url = null;

    static String searchBook(String queryString){
        String BookJSONString = null;

        try{
            BookJSONString = searchBook(queryString);
            if(BookJSONString == null){
                return null;
            }

            JSONObject bookJSONObject = new JSONObject(BookJSONString);

            String ISBN = null;
            String titLiv = null;
            String titOriLiv = null;
            String sinopLiv = null;
            String imgLiv = null;
            String numPagLiv = null;
            String anoLiv = null;
            String numEdicaoLiv = null;

            try{
                ISBN = bookJSONObject.getString("isbn");
                titLiv = bookJSONObject.getString("titLiv");
                titOriLiv = bookJSONObject.getString("titOriLiv");
                sinopLiv = bookJSONObject.getString("sinopLiv");
               // imgLiv = imgLiv.get("product_type");
                numPagLiv = bookJSONObject.getString("numPagLiv");
                anoLiv = bookJSONObject.getString("anoLiv");
                numEdicaoLiv = bookJSONObject.getString("numEdicaoLiv");
            } catch (JSONException e){
                e.printStackTrace();
            }

            JSONObject productJSONObject = new JSONObject();
            try{
                productJSONObject.put("isbn", ISBN);
                productJSONObject.put("titLiv", titLiv);
                productJSONObject.put("titOriLiv", titOriLiv);
                productJSONObject.put("sinopLiv", sinopLiv);
                productJSONObject.put("numPagLiv", numPagLiv);
                productJSONObject.put("anoLiv", anoLiv);
                productJSONObject.put("numEdicaoLiv", numEdicaoLiv);

                BookJSONString = bookJSONObject.toString();
            } catch (JSONException e){
                e.printStackTrace();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(LOG_TAG, BookJSONString);
        return BookJSONString;
    }

    static String searchCli(String queryCli){
        String cliJSONString = null;

        try{
            cliJSONString = searchCli(queryCli);
            if(cliJSONString == null){
                return null;
            }

            JSONObject cliJSONObject = new JSONObject(cliJSONString);

            String IdCli = null;
            String nomCli = null;
            String titOriLiv = null;
            String sinopLiv = null;
            String imgLiv = null;
            String numPagLiv = null;
            String anoLiv = null;
            String numEdicaoLiv = null;

            try{
                ISBN = bookJSONObject.getString("isbn");
                titLiv = bookJSONObject.getString("titLiv");
                titOriLiv = bookJSONObject.getString("titOriLiv");
                sinopLiv = bookJSONObject.getString("sinopLiv");
                // imgLiv = imgLiv.get("product_type");
                numPagLiv = bookJSONObject.getString("numPagLiv");
                anoLiv = bookJSONObject.getString("anoLiv");
                numEdicaoLiv = bookJSONObject.getString("numEdicaoLiv");
            } catch (JSONException e){
                e.printStackTrace();
            }

            JSONObject productJSONObject = new JSONObject();
            try{
                productJSONObject.put("isbn", ISBN);
                productJSONObject.put("titLiv", titLiv);
                productJSONObject.put("titOriLiv", titOriLiv);
                productJSONObject.put("sinopLiv", sinopLiv);
                productJSONObject.put("numPagLiv", numPagLiv);
                productJSONObject.put("anoLiv", anoLiv);
                productJSONObject.put("numEdicaoLiv", numEdicaoLiv);

                BookJSONString = bookJSONObject.toString();
            } catch (JSONException e){
                e.printStackTrace();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(LOG_TAG, BookJSONString);
        return BookJSONString;
    }
}
