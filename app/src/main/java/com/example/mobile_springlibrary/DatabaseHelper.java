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
            db.execSQL("CREATE TABLE tbUser(" +
                    "UserCode INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "UserName TEXT NOT NULL," +
                    "UserPassword TEXT NOT NULL,"+
                    "UserImage BYTE)");
        }

        @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL(
                    "drop table if exists tbUser"
            );
            onCreate(db);
        }

        public Cursor getNewQuery(String sql){
            SQLiteDatabase database=getWritableDatabase();
            Cursor cursor = database.rawQuery(sql, null);
            return cursor;
        }
}
