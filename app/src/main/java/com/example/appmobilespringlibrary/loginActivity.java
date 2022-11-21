package com.example.appmobilespringlibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobile_springlibrary.ClassesBanco.DatabaseHelper;
import com.google.android.material.textfield.TextInputLayout;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class loginActivity extends AppCompatActivity /*implements LoaderManager.LoaderCallbacks<String> */{

    private static final String FILE_NAME = "usuarioLogado.json";
    private EditText edtTxtEmail;
    private TextInputLayout edtTxtPassword;
    private TextView txtcadastro;
    private Button btnLogin;
    //Acesso Banco de dados
    private DatabaseHelper mydb ;
    int id_to_update = 0;
    String emailUser;
    String senhaUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtTxtEmail = findViewById(R.id.editTextEmailLogin);
        edtTxtPassword = findViewById(R.id.TextInputLayoutSenhaLogin);
        //txtcadastro = findViewById(R.id.txtCadastro);
        btnLogin = findViewById(R.id.btnLogin);

        mydb = new DatabaseHelper(this);
        //if(getSupportLoaderManager().getLoader(0) != null) {
          //  getSupportLoaderManager().initLoader(0, null, this);
        //}


        // ON CLICK DO BOTÃO DE LOGIN - LIGAR A VERIFICAÇÃO COM OS DADOS DA API
        btnLogin.setOnClickListener(v -> {
           /* String emailLogin = String.valueOf(edtTxtEmail.getText());
            String passwordLogin = String.valueOf(edtTxtPassword.getText());

            checkField();

            mydb = new DatabaseHelper(getApplicationContext());

            if(mydb.checkLogin(emailLogin, passwordLogin)){
                Cliente cli = mydb.selectCliByEmail(emailLogin);
                //CONECTAR COM A API E ARRUMAR A INSERÇÃO DO CLIENTE NO BANCO COM O RESTANTE DAS INFORMAÇÕES
                new Cliente(emailLogin, passwordLogin);
                    Gson gson = new Gson();
                String json = gson.toJson(cli);
                printUser(json);*/

                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            //}
            //else{
            //    Toast.makeText(loginActivity.this, "Usuário ou senha não correspondem", Toast.LENGTH_SHORT).show();
           // }
        });

        //txtcadastro.setOnClickListener(v -> {
        //    Intent intent = new Intent(this, cadastroActivity.class);
        //    startActivity(intent);
        //});

        //ADICIONAR ONCLICK DO BOTÃO DE CADASTRAR

        //SUPORTE DO LOADER MANAGER
       // if (getSupportLoaderManager().getLoader(0) != null) {
          //  getSupportLoaderManager().initLoader(0, null, this);
        //}
    }

    // VALIDAR CAMPOS
    private void checkField(){
        boolean verification = false;

        String email = edtTxtEmail.getText().toString();
        String senha = edtTxtPassword.getEditText().toString();

        if (verification = nullField(email)) {
            edtTxtEmail.requestFocus();
            Toast.makeText(this, "Preencha o campo e-mail.", Toast.LENGTH_SHORT).show();
        } else if (verification = nullField(senha)) {
            edtTxtPassword.requestFocus();
            Toast.makeText(this, "Preencha o campo senha.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean nullField (String field){
        boolean verification = (TextUtils.isEmpty(field) || field.trim().isEmpty());
        return verification;
    }

    //Verificação de dados com a API
   /* private View.OnClickListener onClickSearch = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            // TODO Auto-generated method stub
            // Verifica o status da conexão de rede
            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = null;
            if (connMgr != null) {
                networkInfo = connMgr.getActiveNetworkInfo();
            }

            // resgata o texto de busca
            emailUser = edtTxtEmail.getText().toString();
            senhaUser = edtTxtPassword.getText().toString();

            // Se a rede estiver disponivel e o campo de busca não estiver vazio, iniciar o Loader CarregaInvocador
            if (networkInfo != null && networkInfo.isConnected()
                    && emailUser.length() != 0 && senhaUser.length() != 0) {

                Bundle queryBundle = new Bundle();
                queryBundle.putString("emailUser", emailUser);
                getSupportLoaderManager().restartLoader(0, queryBundle, loginActivity.this);
                Toast.makeText(getApplicationContext(), "Procurando pelo personagem...", Toast.LENGTH_SHORT).show();
            }
            // atualiza a textview para informar que não há conexão ou termo de busca
            else {
                if (emailUser.length() == 0 || senhaUser.length() == 0) {
                   Toast.makeText(loginActivity.this, "Preencha o campo", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(loginActivity.this, "⚠  Verifique sua conexão!", Toast.LENGTH_SHORT).show();
                }
            }

            //Consultar o Cliente
            CliDAO cliDAO = new CliDAO(loginActivity.this);

            try{
                cliDAO.selectUserByEmail(emailUser);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    };


    //Create loader com a busca de usuário pelo email
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String emailUser = "";
        if (args != null) {
            emailUser = args.getString("emailUser");
        }
        return new LoadCli(this, emailUser);
    }*/

    // SAVED INSTANCE
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String email = edtTxtEmail.getText().toString();
        outState.putString("UserEmail", email);
    }

    // ARMAZENAR DADOS
    private void printUser(String json) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(json.getBytes());
            Toast.makeText(this, "Usuário logado.", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



   /* @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        String nomCli = null;
        String emailCli = null;
        String senhaCli= null;

        try{
            JSONArray jsonArray = new JSONArray(data);
            String stringArray = jsonArray.toString();
            String[] arrayCli = stringArray.split(",");

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("nomCli", arrayCli[2]);
            jsonObject.put("emailCli", arrayCli[3]);
            jsonObject.put("senhaCli", arrayCli[4]);

            Intent it = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(it);
            if(emailCli != null && nomCli != null && senhaCli != null){
                getSupportLoaderManager().destroyLoader(0);
            }
        }catch(JSONException e){
           e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }*/
}