package com.example.appmobilespringlibrary;

import android.net.Uri;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkUtils {
    //Adicionar a URL para a API e concatenar com a parte da URL de busca (getCliByEmail)
    private static final String URL_API = "https://foundorangeleaf83.conveyor.cloud/";
    private static HttpURLConnection connec;
    private static BufferedReader reader;
    private static String springLib;
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    static String url = null;

    static String searchBook(String queryString) {
        String BookJSONString = null;

        try {
            BookJSONString = searchBook(queryString);
            if (BookJSONString == null) {
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

            try {
                ISBN = bookJSONObject.getString("isbn");
                titLiv = bookJSONObject.getString("titLiv");
                titOriLiv = bookJSONObject.getString("titOriLiv");
                sinopLiv = bookJSONObject.getString("sinopLiv");
                // imgLiv = imgLiv.get("product_type");
                numPagLiv = bookJSONObject.getString("numPagLiv");
                anoLiv = bookJSONObject.getString("anoLiv");
                numEdicaoLiv = bookJSONObject.getString("numEdicaoLiv");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONObject productJSONObject = new JSONObject();
            try {
                productJSONObject.put("isbn", ISBN);
                productJSONObject.put("titLiv", titLiv);
                productJSONObject.put("titOriLiv", titOriLiv);
                productJSONObject.put("sinopLiv", sinopLiv);
                productJSONObject.put("numPagLiv", numPagLiv);
                productJSONObject.put("anoLiv", anoLiv);
                productJSONObject.put("numEdicaoLiv", numEdicaoLiv);

                BookJSONString = bookJSONObject.toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(LOG_TAG, BookJSONString);
        return BookJSONString;
    }

    static String searchCli(String queryCli) throws IOException {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String cliJSONString = null;

    try {
        // Construção da URI de Busca
        Uri builtURI = Uri.parse(URL_API).buildUpon()
                .appendEncodedPath("api")
                .appendEncodedPath("SpringLibrary")
                .appendQueryParameter("email", queryCli)
                .build();
        // Converte a URI para a URL.
        URL requestURL = new URL(builtURI.toString());
        // Abre a conexão de rede
        urlConnection = (HttpURLConnection) requestURL.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.connect();
        // Busca o InputStream.
        InputStream inputStream = urlConnection.getInputStream();
        // Cria o buffer para o input stream
        reader = new BufferedReader(new InputStreamReader(inputStream));
        // Usa o StringBuilder para receber a resposta.
        StringBuilder builder = new StringBuilder();
        String linha;
        while ((linha = reader.readLine()) != null) {
            // Adiciona a linha a string.
            builder.append(linha);
            builder.append("\n");
        }
        if (builder.length() == 0) {
            // se o stream estiver vazio não faz nada
            return null;
        }
        cliJSONString = builder.toString();
        // fecha a conexão e o buffer.
        if (urlConnection != null) {
            urlConnection.disconnect();
        }
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            cliJSONString = searchCli(queryCli);
            if (cliJSONString == null) {
                return null;
            }

            JSONObject cliJSONObject = new JSONObject(cliJSONString);

            String IdCli = null;
            String nomCli = null;
            String emailCli = null;
            String senhaCli = null;

            try {
                IdCli = cliJSONObject.getString("IdCli");
                nomCli = cliJSONObject.getString("nomCli");
                emailCli = cliJSONObject.getString("emailCli");
                senhaCli = cliJSONObject.getString("senhaCli");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONObject ClienteJSONObject = new JSONObject();
            try {
                ClienteJSONObject.put("IdCli", IdCli);
                ClienteJSONObject.put("nomCli", nomCli);
                ClienteJSONObject.put("emailCli", emailCli);
                ClienteJSONObject.put("senhaCli", senhaCli);

                cliJSONString = cliJSONObject.toString();
            } catch (JSONException e) {
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
    static String searchLivros() {
        String livJSONString = null;
        try {
            JSONObject livJSONObject = new JSONObject();
            String PrecoLiv = null;
            String titLiv = null;
            String imgLiv = null;

            try {
                titLiv = livJSONObject.getString("titLiv");
                PrecoLiv = livJSONObject.getString("PrecoLiv");
                imgLiv = livJSONObject.getString("imgLiv");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONObject LivroJSONObject = new JSONObject();
            try {
                LivroJSONObject.put("titLiv", titLiv);
                LivroJSONObject.put("PrecoLiv", PrecoLiv);
                LivroJSONObject.put("imgLiv", imgLiv);

                livJSONString = livJSONObject.toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(LOG_TAG, livJSONString);
        return livJSONString;
    }

    //INSTANCIAR ELEMENTOS RECYCLER

}
