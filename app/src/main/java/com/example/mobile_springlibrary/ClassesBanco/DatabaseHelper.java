package com.example.mobile_springlibrary.ClassesBanco;

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appmobilespringlibrary.Cliente;

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

        //INSERIR NOVO REGISTRO DE CLIENTE NO BANCO LOCAL
        public boolean insertCli(Cliente cliente) {
            SQLiteDatabase db = this.getWritableDatabase();

            db.execSQL("INSERT OR IGNORE INTO " + "Cliente"
                    + "("
                    + "UserName,"
                    + "UserEmail,"
                    + "UserPassword"
                    + ") VALUES('"
                    + cliente.getUserName()
                    + cliente.getUserEmail()
                    + cliente.getUserPassword()
                    + "')"
            );
            return  true;
        }
        //Consultar dados e apresentar na tela do perfil do usuário (selecionar a apartir do email
    public Cursor getDataCli(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery( "SELECT * FROM " + "Cliente" + " AS c"
                + " WHERE " + "UserEmail" + " = " + email, null);
        return cursor;
    }

    public boolean updateCli(int id, String email, String senha) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("UserEmail", email);
        contentValues.put("UserPassword", senha);

        db.update("Cliente", contentValues,
                COLUMN_ID_CLI + " = ? ", new String[] { Integer.toString(id) } );
        return true;
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
                                "UserPassword," +
                                "UserImage"
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
            user.setUserImage(cursor.getBlob(4));
        }

        return user;
    }
}
