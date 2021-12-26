package com.learning.todolist.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "db_test";
    public static final Integer DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "todos";

    public DbHandler(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCmd =
                "CREATE TABLE " +  TABLE_NAME + " (id INTEGER PRIMARY KEY, title TEXT, description TEXT)";
        db.execSQL(sqlCmd);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sqlCmdDrop = "DROP TABLE IF EXISTS " + TABLE_NAME;
        String sqlCmd =
                "CREATE TABLE " + TABLE_NAME +  " (id INTEGER PRIMARY KEY, title TEXT, description TEXT)";
        db.execSQL(sqlCmdDrop);
        db.execSQL(sqlCmd);

    }
}