package com.example.mobile_springlibrary.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mobile_springlibrary.ClassesBanco.Cliente;
import com.example.mobile_springlibrary.ClassesBanco.DatabaseHelper;

public class CliDAO {
    private final DatabaseHelper databaseHelper;
    private final SQLiteDatabase database;

    public CliDAO(Context context){
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
    }

    public long InsertCli(Cliente cli){
        ContentValues values = new ContentValues();
        values.put("UserName", cli.getUserName());
        values.put("UserEmail", cli.getUserEmail());
        values.put("UserPassword", cli.getUserPassword());
        values.put("UserImage", cli.getUserImage());

        return database.insert("tbUser", null, values);
    }

    public Cliente selectUserByEmail(String email){
        Cliente cli = new Cliente();
        Cursor cursor = database.query("Cliente",
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
            cli.setUserCode(cursor.getString(0));
            cli.setUserName(cursor.getString(1));
            cli.setUserEmail(cursor.getString(2));
            cli.setUserPassword(cursor.getString(3));
            cli.setUserImage(cursor.getBlob(4));
        }

        return cli;
    }

    public long updateCli(Cliente cli){
        ContentValues values = new ContentValues();
        values.put("UserName", cli.getUserName());
        values.put("UserEmail", cli.getUserEmail());
        values.put("UserPassword", cli.getUserPassword());
        values.put("UserImage", cli.getUserImage());

        return database.update("tbUser", values, "userCode = ?", new String[]{cli.getUserCode()});
    }

    public Boolean checkLogin(String email, String password){
        Cursor cursor = database.rawQuery("SELECT * FROM Cliente WHERE userEmail = ? AND userPassword = ?", new String[]{email, password});

        return cursor.getCount() > 0;
    }
}
