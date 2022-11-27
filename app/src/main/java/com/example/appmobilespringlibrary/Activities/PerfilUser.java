package com.example.appmobilespringlibrary.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmobilespringlibrary.BD.Cliente;
import com.example.appmobilespringlibrary.Pesquisa;
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

    EditText edtNomCli, edtEmail, edtSenha, edtCPF, edtDataNasc, edtCel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        btnSave = (Button) findViewById(R.id.btnSalvar);
        imgUser = (ImageView) findViewById(R.id.imgPerfil);
        edtNomCli = (EditText)findViewById(R.id.editTextPersonNomePerfil);
        edtEmail = (EditText) findViewById(R.id.editTextEmailPerfil);
        edtCel = (EditText) findViewById(R.id.editTextCelPerfil);
        edtCPF = (EditText) findViewById(R.id.editTextNumberCPF);
        edtDataNasc = (EditText) findViewById(R.id.editTextDateDataNasc);


        ImageButton ImgBtnVoltar = (ImageButton) findViewById(R.id.imgBtnVoltar);
        ImgBtnVoltar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), HomeActivity.class));
                finish();
            }
        });

        ImageButton ImgBtnHome = (ImageButton) findViewById(R.id.imgBtnHome);
        ImgBtnHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), HomeActivity.class));
                finish();
            }
        });

        ImageButton ImgBtnPesquisa = (ImageButton) findViewById(R.id.imgBtnPesquisar);
        ImgBtnPesquisa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Pesquisa.class));
                finish();
            }
        });

        ImageButton ImgBtnCarrinho = (ImageButton) findViewById(R.id.imgBtnCarrinho);
        ImgBtnCarrinho.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Carrinho.class));
                finish();
            }
        });

        imgUser.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //ADD MÉTODO PARA IMAGE PICKER
                viewGallery();
            }
        });
        SharedPreferences preferencias = getSharedPreferences(ARQUIVO_PREFERENCIAS, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();


        // Banco de Dados
        id_to_update = preferencias.getInt("cliIdSession", 0);

        mydb = new DatabaseHelper(this);


        //inicializa as preferências do usuário
        mPreferences = getSharedPreferences(PREFERENCIAS_NAME, MODE_PRIVATE);
        //recupera as preferencias
        recuperar();

        // Recupera o estado da aplicação quando é recriado
        if (savedInstanceState != null) {
            mImageUser = savedInstanceState.getBoolean(
                    IMAGE_KEY);
        }
        // Banco de Dados


        //BANCO DE DADOS - UPDATE DE CLIENTE
        btnSave.setOnClickListener(v -> {
            String nameCli = edtNomCli.getText().toString();
            String email = edtEmail.getText().toString();
            String senha = edtSenha.getText().toString();
            Cliente cli = new Cliente(null, nameCli, email, senha);

            DatabaseHelper mydb = new DatabaseHelper(PerfilUser.this);

            try {
                mydb.UpdateCli(cli);
                Toast.makeText(getApplicationContext(), "Alterações salvas com sucesso", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Ocorreu um erro ao tentar salvar", Toast.LENGTH_SHORT).show();

            }
        });

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



}



