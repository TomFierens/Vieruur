package com.example.tomfierens.vieruur.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.tomfierens.vieruur.data.MembersListContract.*;

/**
 * Created by Tom Fierens on 4/04/2018.
 */

public class MembersListDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "membersList.db";
    private static final int DATABASE_VERSION = 1;

    public MembersListDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        final String SQL_CREATE_MEMBERSLIST_TABLE = "CREATE TABLE " +
                MembersListEntry.TABLE_NAME + " ( " +
                MembersListEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MembersListEntry.COLUMN_MEMBER_NAME + " TEXT NOT NULL, " +
                MembersListEntry.COLUMN_CONSUMPTIONS + " INTEGER NOT NULL, " +
                MembersListEntry.COLUMN_GROUP + " TEXT NOT NULL" +
                ");";
        sqLiteDatabase.execSQL(SQL_CREATE_MEMBERSLIST_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MembersListEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
