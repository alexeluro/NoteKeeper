package com.example.notekeeper.Repository.LocalDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DbHandler extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "notes.db";
    public static final int DATABASE_VERSION = 1;


    String CREATE_TABLE = "CREATE TABLE "+ DatabaseContract.DatabaseInfo.DATABASE_TABLE +
            "("+ DatabaseContract.DatabaseInfo._ID + " INTEGER " + ", " +
            DatabaseContract.DatabaseInfo.COLUMN_NOTE_TITLE + " TEXT NOT NULL" + ",  " +
            DatabaseContract.DatabaseInfo.COLUMN_NOTE_CONTENT + " TEXT NOT NULL" + ")";

    String DROP_TABLE = "DROP TABLE IF EXISTS " + DatabaseContract.DatabaseInfo.DATABASE_TABLE ;

    public DbHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    public long addNote(String title, String content){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.DatabaseInfo.COLUMN_NOTE_TITLE, title);
        values.put(DatabaseContract.DatabaseInfo.COLUMN_NOTE_CONTENT, content);
        long insertResponse = sqLiteDatabase.insert(DatabaseContract.DatabaseInfo.DATABASE_TABLE, null, values);
//        sqLiteDatabase.close();
        return insertResponse;
    }

    public void deleteNote(SQLiteDatabase sqLiteDatabase){
        // This is where I stopped for the night
        sqLiteDatabase = getWritableDatabase();

    }

    public Cursor readNote(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " + DatabaseContract.DatabaseInfo.DATABASE_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }

}
