package com.example.mobile_springlibrary.ClassesBanco;

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appmobilespringlibrary.BD.Cliente;
import com.example.appmobilespringlibrary.BD.Livro;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {


        private static final String name="dbSpringLibrary.db";
        private static final int version=1;
        public DatabaseHelper(Context context){
            super(context, name, null, version);
        }
        public static final String COLUMN_ID_CLI = "UserCode";

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL("CREATE TABLE Cliente(" +
                    COLUMN_ID_CLI +
                    " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "UserName TEXT NOT NULL," +
                    "UserEmail TEXT NOT NULL," +
                    "UserPassword TEXT NOT NULL)");
            db.execSQL("CREATE TABLE Livro(" +
                    "ISBNLivro TEXT PRIMARY KEY," +
                    "titLivro TEXT NOT NULL," +
                    "titOriLiv TEXT NOT NULL," +
                    "sinopLiv TEXT NOT NULL," +
                    "imgLiv TEXT," +
                    "precoLiv FLOAT NOT NULL," +
                    "anoLiv INTEGER NOT NULL," +
                    "numEdicaoLiv INTEGER NOT NULL)");
            db.execSQL("CREATE TABLE Carrinho(" +
                    "IdCarrinho INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "valTotal FLOAT NOT NULL)");
            db.execSQL("CREATE TABLE ItemCarrinho(" +
                    "IdItem INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nomItem TEXT NOT NULL," +
                    "PrecoItem FLOAT NOT NULL," +
                    "qtdItem INTEGER," +
                    "IdProd TEXT NOT NULL," +
                    "FOREIGN KEY (IdProd) REFERENCES Livro(ISBNLivro))");
        }

        @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL( "drop table if exists Cliente");
            db.execSQL( "drop table if exists Livro");
            db.execSQL( "drop table if exists Carrinho");
            db.execSQL( "drop table if exists ItemCarrinho");
            onCreate(db);
        }

        public Cursor getNewQuery(String sql){
            SQLiteDatabase database=getWritableDatabase();
            Cursor cursor = database.rawQuery(sql, null);
            return cursor;
        }

        //INSERIR NOVO REGISTRO DE CLIENTE NO BANCO LOCAL
    public long insertCli(Cliente cliente){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("UserName", cliente.getUserName());
        values.put("UserEmail", cliente.getUserEmail());
        values.put("UserPassword", cliente.getUserPassword());

        return db.insert("Cliente", null, values);
    }
    //Consultar dados e apresentar na tela do perfil do usuário (selecionar a apartir do email
    public Cursor getDataCli(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery( "SELECT * FROM " + "Cliente" + " AS c"
                + " WHERE " + "UserEmail" + " = " + email, null);
        return cursor;
    }

    public long UpdateCli(Cliente cliente){
            SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("UserName", cliente.getUserName());
        values.put("UserEmail", cliente.getUserEmail());
        values.put("UserPassword", cliente.getUserPassword());

        return db.update("Cliente", values, "userCode = ?", new String[]{cliente.getUserCode()});
    }
    // Operações Login

    public Boolean checkLogin(String email, String password){
        Cursor cursor = getWritableDatabase().rawQuery("SELECT * FROM Cliente WHERE userEmail = ? AND userPassword = ?", new String[]{email, password});

        return cursor.getCount() > 0;
    }

    public int getCliIdSession(String email, String senha){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT c." + COLUMN_ID_CLI + " FROM " + "Cliente" + " AS c"
                + " WHERE c." + "UserEmail" + " = '" + email + "' AND c." + "UserPassword" + " = '" + senha + "'", null);
        cursor.moveToFirst();
        int cli_id_session = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID_CLI));
        if (cursor.getCount() > 0){
            return cli_id_session;
        } else {
            return 0;
        }
    }
    public Cliente selectCliByEmail(String email){
        Cliente user = new Cliente();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query("Cliente",
                new String[]{
                        "UserCode, " +
                                "UserName, " +
                                "UserEmail, " +
                                "UserPassword"
                },
                "UserEmail = ?",
                new String[]{email},
                null,
                null,
                null,
                String.valueOf(1));

        while (cursor.moveToNext()){
            user.setUserCode(cursor.getString(0));
            user.setUserName(cursor.getString(1));
            user.setUserEmail(cursor.getString(2));
            user.setUserPassword(cursor.getString(3));
        }

        return user;
    }

    //SELECIONAR O LIVRO PARA COLOCAR NA PÁGINA DE CARRINHO
    public long insertLivro(Livro livro){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("ISBN", livro.getISBN());
        values.put("titLivro", livro.getProdNome());
        values.put("precoLiv", livro.getPrecoLiv());
        values.put("imgLiv", livro.getImgLivro());
        values.put("anoLiv", livro.getAnoLiv());
        values.put("sinopLiv", livro.getSinopLiv());

        return db.insert("Livro", null, values);
    }
    public Livro selectLivByISBN(String ISBN){
            Livro livro = new Livro();
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.query("Livro",
                    new String[]{
                            "ISBN, "+
                                    "titLivro, "+
                                    "imgLiv, " +
                                    "precoLiv"
                    },
                    "ISBN= ?",
                    new String[]{ISBN}, null, null, null,
                    String.valueOf(1));

            while(cursor.moveToNext()){
                livro.setISBN(cursor.getString(0));
                livro.setProdNome(cursor.getString(1));
                livro.setImgLivro(cursor.getString(2));
                livro.setPrecoLiv(Double.valueOf(cursor.getString(3)));
            }
            return livro;
    }
    public long insertItemCarrinho(Livro livro){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("IdProd", livro.getISBN());
        values.put("nomItem", livro.getProdNome());
        values.put("precoItem", livro.getPrecoLiv());

        return db.insert("ItemCarrinho", null, values);
    }

    public Cursor getDataItemCarrinho(String ISBN){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query("ItemCarrinho",
                new String[]{
                        "IdItem, "+
                                "nomItem, "+
                                "IdProd, " +
                                "precoItem"
                },
                "IdProd= ?",
                new String[]{ISBN}, null, null, null,
                String.valueOf(1));

        return cursor;
    }

    public ArrayList<String> getAllItemCarrinho() {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery( "SELECT " + "IdItem, nomItem, IdProd, precoItem" + " FROM " + "ItemCarrinho", null );
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            array_list.add(cursor.getString(cursor.getColumnIndexOrThrow("IdProd")));
            cursor.moveToNext();
        }
        return array_list;
    }

}
