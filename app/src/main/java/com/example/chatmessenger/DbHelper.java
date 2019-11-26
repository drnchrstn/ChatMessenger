package com.example.chatmessenger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.chatmessenger.ui.dashboard.ContactDb;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "chat_messenger";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS";



    public DbHelper( Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + ContactDb.TABLE_NAME + "(Id INTEGER PRIMARY KEY AUTOINCREMENT, Username TEXT, Password TEXT, Date TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL(DROP_TABLE + ContactDb.TABLE_NAME);
        onCreate(db);

    }
}
