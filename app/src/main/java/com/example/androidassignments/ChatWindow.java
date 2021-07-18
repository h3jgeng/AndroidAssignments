package com.example.androidassignments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {

    private static ArrayList<String> chatMessages = new ArrayList<>();
    private MessageItemDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        // 1. call to create database
        datasource = new MessageItemDataSource(this);

        // 2. open Database for writing

        datasource.open();

        //3. list all the items in table on the main screen

        chatMessages = datasource.getAllMessage();

        ListView chatListView = findViewById(R.id.chatView);
        EditText myEditText = findViewById(R.id.edit_text);
        Button sendBtn = findViewById(R.id.send_btn);
        ChatAdapter messageAdapter = new ChatAdapter(this);

        sendBtn.setOnClickListener(v -> {
            chatMessages.add(myEditText.getText().toString());
            messageAdapter.notifyDataSetChanged();
            datasource.addMessage(myEditText.getText().toString());
            myEditText.setText("");
        });

        chatListView.setAdapter(messageAdapter);
    }



    private class ChatAdapter extends ArrayAdapter<String> {

        public ChatAdapter(Context context) {
            super(context, 0);
        }

        @Override
        public int getCount() {
            return chatMessages.size();
        }

        @NonNull
        @Override
        public String getItem(int position) {
            return chatMessages.get(position);
        }

        @SuppressLint("InflateParams")
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();

            View result;
            if (position % 2 == 0)
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            else
                result = inflater.inflate(R.layout.chat_row_outgoing, null);

            TextView message = (TextView) result.findViewById(R.id.message_text);
            message.setText(getItem(position));
            return result;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        datasource.close();
    }

}