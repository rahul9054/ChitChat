package com.labawsrh.aws.rvitemanimaion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StartChatting extends AppCompatActivity {

    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_chatting);

        editText = findViewById(R.id.edit_text);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editText.getText().toString().length() != 0) {
                    User_Details.user_name = editText.getText().toString().trim();
                     User_Details.tier = 2 ;
                    Intent intent = new Intent(getApplicationContext(), ChatsActivity.class);
                    intent.putExtra("user_name", editText.getText().toString().trim());
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Enter  Name", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
