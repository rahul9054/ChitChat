package com.labawsrh.aws.rvitemanimaion;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class ChatsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ChatAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        toolbar = findViewById(R.id.tool_bar);


        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewpager);
        mainAdapter = new ChatAdapter(getApplicationContext(), getSupportFragmentManager());


    }

    @Override
    protected void onStart() {
        super.onStart();
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(mainAdapter);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_group_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_person_black_24dp);

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("pause_chat", "pause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("destroy_chat", "destroy");
        //Toast.makeText(getApplicationContext(), "destroy_chat", Toast.LENGTH_LONG).show();

    }
}
