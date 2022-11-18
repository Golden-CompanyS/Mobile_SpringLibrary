package com.example.mobile_springlibrary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmobilespringlibrary.R;
import com.example.mobile_springlibrary.ClassesBanco.DatabaseHelper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PerfilUser extends AppCompatActivity {
    //IMAGE PICKER Para Imagem do Usuário
    private static final String ARQUIVO_PREFERENCIAS = "ArquivoPreferencias";
    ImageView imgUser;
    Button btnSave;
    private int EXTERNAL_STORAGE_PERMISSION_CODE = 23;
    private static final int RESULT_SELECT_IMAGE = 1;
    private static final String IMAGE_KEY = "img_key";
    private boolean mImageUser;
    public static final String PREFERENCIAS_NAME = "com.example.android.usuario";
    private SharedPreferences mPreferences;
    // Banco de Dados
    private DatabaseHelper mydb ;
    int id_to_update = 0;

    // A IMPLEMENTAR
    // - RESTANTE DOS CAMPOS PARA ALTERAÇÃO NO BANCO (CHECAR FRONT) - LARISSA 17/11
    EditText edtNomCli, edtEmail, edtSenha, edtCPF, edtCEP, edtCel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        btnSave = (Button) findViewById(R.id.btnSave);
        imgUser = (ImageView) findViewById(R.id.imgUser);
        edtNomCli = (EditText)findViewById(R.id.edtTxtNomCli);
        edtEmail = (EditText) findViewById(R.id.edtTxtEmailCli);
        edtCel = (EditText) findViewById(R.id.edtTxtCelCli);
        edtCEP = (EditText) findViewById(R.id.edtCEPCli);
        edtSenha = (EditText) findViewById(R.id.edtSenhaCli);
        edtCPF = (EditText) findViewById(R.id.edtCPFCli);


        // Listener do botão de localização.
        imgUser.setOnClickListener(new View.OnClickListener() {
            /**
             * Toggle the tracking state.
             * @param v The track location button.
             */
            @Override
            public void onClick(View v) {
                //ADD MÉTODO PARA IMAGE PICKER
                viewGallery();
            }
        });

        SharedPreferences preferencias = getSharedPreferences(ARQUIVO_PREFERENCIAS, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();

        //inicializa as preferências do usuário
        mPreferences = getSharedPreferences(PREFERENCIAS_NAME, MODE_PRIVATE);
        //recupera as preferencias
        recuperar();

        // Recupera o estado da aplicação quando é recriado
        if (savedInstanceState != null) {
            mImageUser = savedInstanceState.getBoolean(
                    IMAGE_KEY);
        }

        mydb = new DatabaseHelper(this);
        // Banco de Dados
        id_to_update = preferencias.getInt("cliIdSession", 0);

        if (id_to_update == 0){
            Intent intent = new Intent(this, PerfilUser.class);
            startActivity(intent);
            finish();
        }

        // pegar imagem automaticamente
        String imagemBase64 = preferencias.getString("imgSource", null);
        if (imagemBase64 != null) {

            byte[] bytes = Base64.decode(imagemBase64, Base64.DEFAULT);
            InputStream inputStream = new ByteArrayInputStream(bytes);
            Bitmap imagemBitmap = BitmapFactory.decodeStream(inputStream);
            imgUser.setImageBitmap(imagemBitmap);
        }

    }

    //IMAGE PICKER
    public void viewGallery(){
        //inicia uma intent com ação de Seleção de Imagens
        Intent intent =     new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        //inicia uma activity que devolverá uma resposta
        startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), RESULT_SELECT_IMAGE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){
            Uri imagemUri = data.getData();
            try {
                Bitmap imagemBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imagemUri);

                //define como source da imagem
                imgUser.setImageBitmap(imagemBitmap);

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                imagemBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] b = byteArrayOutputStream.toByteArray();
                String imagemBase64 = Base64.encodeToString(b, Base64.DEFAULT);
    
                SharedPreferences preferencias = getSharedPreferences(ARQUIVO_PREFERENCIAS, 0);
                SharedPreferences.Editor editor = preferencias.edit();

                editor.putString("imgSource", imagemBase64).apply();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void recuperar() {
        SharedPreferences mPreferences = getSharedPreferences(PREFERENCIAS_NAME, 0);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(IMAGE_KEY, mImageUser);
        super.onSaveInstanceState(outState);
    }

    //BANCO DE DADOS - UPDATE DE CLIENTE
    // Banco de Dados
    public void altInfos(View view){
        id_to_update = preferencias.getInt("cliIdSession", 0);
        String emailNovo = edtEmail.getText().toString();
        String senhaNova = edtSenha.getText().toString();


                mydb.updateCli(id_to_update, emailNovo, senhaNova);

                Toast.makeText(getApplicationContext(), "Senha alterada com sucesso!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Senha atual incorreta", Toast.LENGTH_LONG).show();
            }
        }

    }


}
