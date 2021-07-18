package com.example.androidassignments;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class MessageItemDataSource {

    // Database fields
    private SQLiteDatabase database;
    private final ChatDatabaseHelper dbOpenHelper;
    private final String[] columns = {ChatDatabaseHelper.KEY_ID,
            ChatDatabaseHelper.KEY_MESSAGE};
    public static final String ACTIVITY_NAME = "ChatWindow";

    // call to database constructor
    MessageItemDataSource(Context context) {
        dbOpenHelper = new ChatDatabaseHelper(context);
    }


    public void open() throws SQLException {
        database = dbOpenHelper.getWritableDatabase();
    }

    public void close() {
        dbOpenHelper.close();
    }

    public ArrayList<String> getAllMessage() {
        ArrayList<String> messages = new ArrayList<>();
        Cursor cursor = database.query(ChatDatabaseHelper.TABLE_Of_MESSAGES,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            String message = cursor.getString(1);
            Log.i(ACTIVITY_NAME, "SQL MESSAGE: " + message);
            Log.i(ACTIVITY_NAME, "Cursor's column count = " + cursor.getColumnCount());
            Log.i(ACTIVITY_NAME, "Cursor's column name = " + cursor.getColumnName(0));
            Log.i(ACTIVITY_NAME, "Cursor's column name = " + cursor.getColumnName(1));
            messages.add(message);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return messages;

    }

    public void addMessage(String message) {
        ContentValues values = new ContentValues();
        values.put(ChatDatabaseHelper.KEY_MESSAGE, message);
        long insertId = database.insert(ChatDatabaseHelper.TABLE_Of_MESSAGES, null,
                values);

        Cursor cursor = database.query(ChatDatabaseHelper.TABLE_Of_MESSAGES,
                columns, ChatDatabaseHelper.KEY_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        cursor.close();
    }

}
