package com.example.androidassignments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class ChatDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Messages.db";
    private static final int DATABASE_VERSION = 4;
    public static final String KEY_ID = "_id";
    public static final String KEY_MESSAGE = "Message";
    public static final String TABLE_Of_MESSAGES = "tableOfMessages";
    public static final String ACTIVITY_NAME = "ChatDatabaseHelper";

    private static final String DATABASE_CREATE = "create table "
            + TABLE_Of_MESSAGES + "(" + KEY_ID
            + " integer primary key autoincrement, " + KEY_MESSAGE
            + " text not null);";

    public ChatDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
        Log.i(ACTIVITY_NAME, "Calling onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(ACTIVITY_NAME,
                "Calling onUpgrade, oldVersion: " + oldVersion + ", newVersion = "
                        + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Of_MESSAGES);
        onCreate(db);
    }


}
