package com.labawsrh.aws.rvitemanimaion;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ChatAdapter mainAdapter;
    private DatabaseReference mdatabase;
    private LinearLayout linearLayout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        linearLayout = findViewById(R.id.layout) ;
        User_Details.linearLayout = linearLayout ;

        // FirebaseDatabase.getInstance().getReference("users").keepSynced(true);
        //   FirebaseDatabase.getInstance().getReference().child("users").child(User_Details.user_name).child("online").onDisconnect().setValue(System.currentTimeMillis() + "");
        toolbar = findViewById(R.id.tool_bar);

        mdatabase = FirebaseDatabase.getInstance().getReference();
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewpager);
        mainAdapter = new ChatAdapter(getApplicationContext(), getSupportFragmentManager());
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(mainAdapter);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_person_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_group_item_black_24dp);

        mdatabase.child("users").child(User_Details.user_name).child("online").setValue("Online");
        mdatabase.child("users").child(User_Details.user_name).child("name").setValue(User_Details.user_name);
        mdatabase.child("users").child(User_Details.user_name).child("typingTo").setValue("noOne");
        mdatabase.child("users").child(User_Details.user_name).child("tier").setValue(User_Details.tier);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mdatabase.child("users").child(User_Details.user_name).child("online").setValue("Online");

    }

    @Override
    protected void onPause() {
        super.onPause();
        mdatabase.child("users").child(User_Details.user_name).child("online").setValue(System.currentTimeMillis() + "");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.e("destroy_chat", "destroy");


    }


}
