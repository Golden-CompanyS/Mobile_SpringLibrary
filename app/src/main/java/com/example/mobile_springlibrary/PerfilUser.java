package com.example.mobile_springlibrary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmobilespringlibrary.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        btnSave = (Button) findViewById(R.id.btnSave);
        imgUser = (ImageView) findViewById(R.id.imgUser);

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

        // Recupera o estado da aplicação quando é recriado
        if (savedInstanceState != null) {
            mImageUser = savedInstanceState.getBoolean(
                    IMAGE_KEY);
        }

        //inicializa as preferências do usuário
        mPreferences = getSharedPreferences(PREFERENCIAS_NAME, MODE_PRIVATE);
        //recupera as preferencias
        recuperar();

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


}
