package com.labawsrh.aws.rvitemanimaion;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RecyclerView message_rv;
    MessageAdapter messageAdapter;
    Button send_button;
    EditText editText;
    RelativeLayout rootLayout;
    Toolbar toolbar;
    TextView username;
    RelativeLayout relativeLayout;
    TextView last_seen;
    private DatabaseReference mdatabase;
    String user_name;
    ValueEventListener seen_Listener;
    private int position;
    Snackbar snackbar;
    private boolean is_connected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        FirebaseDatabase.getInstance().setPersistenceEnabled(false);
        setContentView(R.layout.activity_message);
        username = findViewById(R.id.user_name);
        last_seen = findViewById(R.id.last_seen);
        relativeLayout = findViewById(R.id.root_layout);
        // FirebaseDatabase.getInstance().getReference("users_chat").keepSynced(false);
        mdatabase = FirebaseDatabase.getInstance().getReference();


        toolbar = findViewById(R.id.tool_bar);
        send_button = findViewById(R.id.send);
        rootLayout = findViewById(R.id.root_layout);
        message_rv = findViewById(R.id.news_rv);
        editText = findViewById(R.id.message_input);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                check_typing_status(user_name);
                if (s.toString().trim().length() == 0) {
                    check_typing_status("noOne");
                } else {
                    check_typing_status(user_name);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);

        user_name = getIntent().getStringExtra("user_name");
        username.setText(user_name);

        send_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (editText.getText().toString().trim().length() != 0) {
                    send_message(editText.getText().toString().trim());
                    editText.setText("");
                } else {
                    Toast.makeText(getApplicationContext(), "please enter message", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Handler handler = new Handler()

        Repository repository = new Repository(new chat_message_interface() {
            @Override
            public void add_message(MessageItem messageItem) {
                Log.e("message item", messageItem.getMessage());
                messageAdapter.setData(messageItem);
                messageAdapter.notifyDataSetChanged();
                // messageAdapter.notifyItemInserted(message_rv.getLayoutManager().getItemCount());
                message_rv.smoothScrollToPosition(message_rv.getLayoutManager().getItemCount());
            }
        });
        repository.getChats(user_name, User_Details.user_name);


        messageAdapter = new MessageAdapter(this, user_name);
        message_rv.setAdapter(messageAdapter);
        message_rv.setLayoutManager(new LinearLayoutManager(this));
        message_rv.scrollToPosition(message_rv.getLayoutManager().getItemCount());

        snackbar = Snackbar.make(relativeLayout, "No Connection", Snackbar.LENGTH_INDEFINITE);

        seen_message(user_name);

    }


    public void check_user_status() {

        mdatabase.child("users").child(user_name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String user_name = snapshot.child("name").getValue().toString();
                String user_online = snapshot.child("online").getValue().toString();
                String typing = snapshot.child("typingTo").getValue().toString();

                if (typing.equals(User_Details.user_name)) {
                    last_seen.setText("Typing....");
                } else if (user_online.equals("Online")) {
                    last_seen.setText("Online");
                } else {
                    long lastTime = Long.parseLong(user_online);
                    GetTimeAgo getTimeAgo = new GetTimeAgo();
                    String last_seen_time = getTimeAgo.getTime(lastTime, getApplicationContext());
                    last_seen.setText(last_seen_time);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void check_online_status(String online) {

        DatabaseReference databaseReference = mdatabase.child("users").child(User_Details.user_name);
        Map map = new HashMap();
        map.put("online", online);

        databaseReference.updateChildren(map);

    }

    private void check_typing_status(String typing) {

        DatabaseReference databaseReference = mdatabase.child("users").child(User_Details.user_name);
        Map map = new HashMap();
        map.put("typingTo", typing);

        databaseReference.updateChildren(map);

    }


    private void seen_message(String user_id) {
        seen_Listener = FirebaseDatabase.getInstance().getReference().child("users_chat").child(user_id).child(User_Details.user_name).limitToLast(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    MessageItem messageItem = dataSnapshot.getValue(MessageItem.class);
                    Map map = new HashMap();
                    map.put("seen", true);
                    dataSnapshot.getRef().updateChildren(map);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void send_message(String message) {

        Map chatAddMap = new HashMap();
        chatAddMap.put("seen", false);
        chatAddMap.put("timestamp", System.currentTimeMillis());
        chatAddMap.put("message", message);
        chatAddMap.put("sender", User_Details.user_name);
        chatAddMap.put("type", "Text");

        String push = mdatabase.child("users_chat").child(User_Details.user_name).child(user_name).push().getKey();

        Map chatUserMap = new HashMap();

        chatUserMap.put("users_chat" + "/" + User_Details.user_name + "/" + user_name + "/" + push, chatAddMap);
        chatUserMap.put("users_chat" + "/" + user_name + "/" + User_Details.user_name + "/" + push, chatAddMap);


        mdatabase.updateChildren(chatUserMap);

        mdatabase.child("users_connection").child(User_Details.user_name).child(user_name).child("timestamp").setValue(System.currentTimeMillis() + "");
        mdatabase.child("users_connection").child(user_name).child(User_Details.user_name).child("timestamp").setValue(System.currentTimeMillis() + "");


    }

    @Override
    protected void onStart() {
        super.onStart();

        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {
                    is_connected = true;
                    check_user_status();
                    check_online_status("Online");
                    last_seen.setVisibility(View.VISIBLE);
                    editText.setVisibility(View.VISIBLE);
                    send_button.setVisibility(View.VISIBLE);
                    snackbar.dismiss();
                    Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_LONG).show();
                } else {
                    is_connected = false;
                    last_seen.setVisibility(View.GONE);
                    editText.setVisibility(View.GONE);
                    send_button.setVisibility(View.GONE);
                    show_snack_bar_Offline();

                    Toast.makeText(getApplicationContext(), "not Connected", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    private void show_snack_bar_Offline() {
        snackbar.show();
    }

    @Override

    protected void onPause() {

        super.onPause();
        FirebaseDatabase.getInstance().getReference().child("users_chat").child(user_name).child(User_Details.user_name).limitToLast(1).removeEventListener(seen_Listener);
//        check_user_status();
        check_online_status(System.currentTimeMillis() + "");
        check_typing_status("noOne");

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

    }
}
