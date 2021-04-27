package com.labawsrh.aws.rvitemanimaion;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository {

    public static final String CHATS_NODE = "chats";
    public static final String CHAT_MESSAGE_TEXT_NODE = "messageText";
    public static final String CHAT_MESSAGE_SENDER_NODE = "senderId";
    private int i = 0;
    public chat_message_interface chat_message_interface;

    private MutableLiveData<List<String>> chatsListLivedata = new MutableLiveData<>();
    private MutableLiveData<List<Message>> messagesLivedata = new MutableLiveData<>();

    private DatabaseReference userChatDbref, chatDbref, chatMessageDbref;


    public Repository(chat_message_interface chat_message_interface) {
        this.chat_message_interface = chat_message_interface;
    }

    public Repository() {
    }


    public void getChats(String userId, String current_user_id) {
        userChatDbref = FirebaseDatabase.getInstance().getReference().child("users_chat").child(current_user_id).child(userId);

        userChatDbref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                MessageItem message = snapshot.getValue(MessageItem.class);
                Log.e("message", message.getMessage() + "");
                chat_message_interface.add_message(message);


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //chatsListLivedata.setValue(chatsList);
        // return chatsList;
    }

    public List<String> getUsers() {
        final List<String> usersList = new ArrayList<>();
        chatDbref = FirebaseDatabase.getInstance().getReference().child("users");

        chatDbref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.exists()) {
                    if (!dataSnapshot.getKey().equals(User_Details.user_name)) {
                        Log.e("users", dataSnapshot.getKey());

                        usersList.add(dataSnapshot.getKey());

                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //Todo
        // return LiveData


        //  Log.e("repository", usersList.size() + "");


        //  messagesLivedata.setValue(messageList);


        return usersList;
    }

    public void getGroupChats(String groupId) {
        userChatDbref = FirebaseDatabase.getInstance().getReference().child("group_chat").child(groupId) ;

        userChatDbref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                MessageItem message = snapshot.getValue(MessageItem.class);
                Log.e("message", message.getMessage() + "");
                chat_message_interface.add_message(message);


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //chatsListLivedata.setValue(chatsList);
        // return chatsList;
    }

}

