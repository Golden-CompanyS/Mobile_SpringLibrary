package com.example.mobile_springlibrary;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseHelper extends SQLiteOpenHelper {


        private static final String name="dbSpringLibrary.db";
        private static final int version=1;
        public DatabaseHelper(Context context){
            super(context, name, null, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL("CREATE TABLE Cliente(" +
                    "UserCode INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "UserName TEXT NOT NULL," +
                    "UserPassword TEXT NOT NULL,"+
                    "UserImage BYTE)");
            db.execSQL("CREATE TABLE Livro(" +
                    "ISBNLivro INTEGER PRIMARY KEY," +
                    "titLivro TEXT NOT NULL," +
                    "titOriLiv TEXT NOT NULL," +
                    "sinopLiv TEXT NOT NULL," +
                    "imgLiv BYTE," +
                    "precoLiv FLOAT NOT NULL," +
                    "numPagLiv INTEGER NOT NULL," +
                    "anoLiv INTEGER NOT NULL," +
                    "numEdicaoLiv INTEGER NOT NULL)");
            db.execSQL("CREATE TABLE Carrinho(" +
                    "IdCarrinho INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "valTotal FLOAT NOT NULL)");
            db.execSQL("CREATE TABLE ItemCarrinho(" +
                    "IdItem INTEGER PRIMARY KEY," +
                    "nomItem TEXT NOT NULL," +
                    "PrecoItem FLOAT NOT NULL," +
                    "qtdItem INTEGER NOT NULL," +
                    "IdProd INTEGER NOT NULL," +
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
}
