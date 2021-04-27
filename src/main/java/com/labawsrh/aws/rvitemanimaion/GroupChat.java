package com.labawsrh.aws.rvitemanimaion;

import android.os.Bundle;
import android.os.Handler;
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

public class GroupChat extends AppCompatActivity {
    RecyclerView message_rv;
    Group_chat_Adapter groupChatAdapter ;
    Button send_button;
    EditText editText;
    RelativeLayout rootLayout;
    Toolbar toolbar;
    TextView group;
    RelativeLayout relativeLayout;
    TextView groupInfo ;
    private DatabaseReference mdatabase;
    String group_name , headline ;
    ValueEventListener seen_Listener;
    private int position;
    Snackbar snackbar;
    private boolean is_connected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        FirebaseDatabase.getInstance().setPersistenceEnabled(false);
        setContentView(R.layout.activity_group_chat);
        group = findViewById(R.id.group_name);
        groupInfo = findViewById(R.id.group_info);

        relativeLayout = findViewById(R.id.root_layout);
        // FirebaseDatabase.getInstance().getReference("users_chat").keepSynced(false);
        mdatabase = FirebaseDatabase.getInstance().getReference();


        toolbar = findViewById(R.id.tool_bar);
        send_button = findViewById(R.id.send);
        rootLayout = findViewById(R.id.root_layout);
        message_rv = findViewById(R.id.news_rv);
        editText = findViewById(R.id.message_input);

//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                check_typing_status(User_Details.user_name);
//                if (s.toString().trim().length() == 0) {
//                    check_typing_status("noOne");
//                } else {
//                    check_typing_status(User_Details.user_name);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
       // actionBar.setDisplayHomeAsUpEnabled(true);

        group_name = getIntent().getStringExtra("group_name");
        group.setText(group_name);
        headline = getIntent().getStringExtra("headline") ;
        groupInfo.setText(headline);

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
                groupChatAdapter.setData(messageItem);
                groupChatAdapter.notifyDataSetChanged();
                // messageAdapter.notifyItemInserted(message_rv.getLayoutManager().getItemCount());
                message_rv.smoothScrollToPosition(message_rv.getLayoutManager().getItemCount());
            }
        });
        repository.getGroupChats(group_name);


        groupChatAdapter = new Group_chat_Adapter(this, group_name);
        message_rv.setAdapter(groupChatAdapter);
        message_rv.setLayoutManager(new LinearLayoutManager(this));
        message_rv.scrollToPosition(message_rv.getLayoutManager().getItemCount());

        snackbar = Snackbar.make(relativeLayout, "No Connection", Snackbar.LENGTH_INDEFINITE);



    }




//
//    private void check_typing_status(String typing) {
//
//        DatabaseReference databaseReference = mdatabase.child("Groups").child(group_name);
//        Map map = new HashMap();
//        map.put("typing", typing);
//
//        databaseReference.updateChildren(map);
//
//    }




    private void send_message(String message) {

        Map chatAddMap = new HashMap();
        chatAddMap.put("seen", false);
        chatAddMap.put("timestamp", System.currentTimeMillis());
        chatAddMap.put("message", message);
        chatAddMap.put("sender", User_Details.user_name);
        chatAddMap.put("type", "Text");

        String push = mdatabase.child("group_chat").child(group_name).push().getKey();

        Map chatUserMap = new HashMap();

        chatUserMap.put("group_chat"  + "/" + group_name + "/" + push, chatAddMap);



        mdatabase.updateChildren(chatUserMap);



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
                    groupInfo.setVisibility(View.VISIBLE);
                    editText.setVisibility(View.VISIBLE);
                    send_button.setVisibility(View.VISIBLE);
                    snackbar.dismiss();
                    Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_LONG).show();
                } else {
                    is_connected = false;
                    groupInfo.setVisibility(View.GONE);
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


       // check_typing_status("noOne");

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

    }
}