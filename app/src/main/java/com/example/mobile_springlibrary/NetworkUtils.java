package com.example.mobile_springlibrary;

import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.net.HttpURLConnection;

public class NetworkUtils {
    //Adicionar a URL para a API e concatenar com a parte da URL de busca (getCliByEmail)
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
            String emailCli = null;
            String senhaCli = null;
            String imgLiv = null;
            String numPagLiv = null;
            String anoLiv = null;
            String numEdicaoLiv = null;

            try{
                IdCli = cliJSONObject.getString("IdCli");
                nomCli = cliJSONObject.getString("nomCli");
                emailCli = cliJSONObject.getString("emailCli");
                senhaCli = cliJSONObject.getString("senhaCli");

                // imgCli = imgLiv.get("product_type");
                numPagLiv = cliJSONObject.getString("numPagCli");
                anoLiv = cliJSONObject.getString("anoLiv");
                numEdicaoLiv = cliJSONObject.getString("numEdicaoLiv");
            } catch (JSONException e){
                e.printStackTrace();
            }

            JSONObject ClienteJSONObject = new JSONObject();
            try{
                ClienteJSONObject.put("IdCli", IdCli);
                ClienteJSONObject.put("nomCli", nomCli);
                ClienteJSONObject.put("emailCli", emailCli);
                ClienteJSONObject.put("senhaCli", senhaCli);
                ClienteJSONObject.put("numPagLiv", numPagLiv);
                ClienteJSONObject.put("anoLiv", anoLiv);
                ClienteJSONObject.put("numEdicaoLiv", numEdicaoLiv);


                cliJSONString = cliJSONObject.toString();
            } catch (JSONException e){
                e.printStackTrace();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(LOG_TAG, cliJSONString);
        return cliJSONString;
    }

    //MÉTODO DE INSERIR NA API O REGISTRO DE CLIENTE - UTILIZAR NA TELA DE CADASTRO PARA FÍSICO E JURÍDICO

    //MÉTODO PARA BUSCAR LIVROS
    static String searchLivros(){
        String livJSONString = null;
        try{
            JSONObject livJSONObject = new JSONObject();
            String PrecoLiv = null;
            String titLiv = null;
            String imgLiv =  null;

            try{
                titLiv = livJSONObject.getString("titLiv", titLiv);
                PrecoLiv = livJSONObject.getString("PrecoLiv", PrecoLiv);
                imgLiv = livJSONObject.getString("imgLiv", imgLiv);
            }catch (JSONException e){
                e.printStackTrace();
            }

            JSONObject LivroJSONObject = new JSONObject();
            try{
                LivroJSONObject.put("titLiv", titLiv);
                LivroJSONObject.put("PrecoLiv", PrecoLiv);
                LivroJSONObject.put("imgLiv", imgLiv);

                livJSONString = livJSONObject.toString();
            } catch (JSONException e){
                e.printStackTrace();
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
        Log.d(LOG_TAG, livJSONString);
        return livJSONString;
    }
}
