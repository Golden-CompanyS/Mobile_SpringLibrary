package com.example.mobile_springlibrary;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.net.HttpURLConnection;

public class NetworkUtils {
    private static final String URL_API = "";
    private static HttpURLConnection connec;
    private static BufferedReader reader;
    private static String springLib;
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
                ISBN = makeupJSONObject.getString("isbn");
                priceProduct = makeupJSONObject.getString("price");
                descriptionProduct = makeupJSONObject.getString("description");
                brandProduct = makeupJSONObject.getString("brand");
                typeProduct = makeupJSONObject.getString("product_type");
            } catch (JSONException e){
                e.printStackTrace();
            }

            JSONObject productJSONObject = new JSONObject();
            try{
                productJSONObject.put("name", nameProduct);
                productJSONObject.put("price", priceProduct);
                productJSONObject.put("description", descriptionProduct);
                productJSONObject.put("brand", brandProduct);
                productJSONObject.put("product_type", typeProduct);

                makeupJSONString = makeupJSONObject.toString();
            } catch (JSONException e){
                e.printStackTrace();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(LOG_TAG, makeupJSONString);
        return makeupJSONString;
    }
}
