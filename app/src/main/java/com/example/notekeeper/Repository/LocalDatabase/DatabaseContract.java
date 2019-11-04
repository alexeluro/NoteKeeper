package com.example.notekeeper.Repository.LocalDatabase;

import android.provider.BaseColumns;

public class DatabaseContract {

    public class DatabaseInfo implements BaseColumns{

        public static final String DATABASE_TABLE = "Notes";
        public static final String COLUMN_NOTE_TITLE = "Note_Title";
        public static final String COLUMN_NOTE_CONTENT = "Note_Content";

    }
}
