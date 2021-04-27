package com.labawsrh.aws.rvitemanimaion;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Users extends AppCompatActivity {

    RecyclerView recyclerView;
    Model viewModel;
    LinearLayoutManager linearLayoutManager;
    User_Adapter Adapter;
    List<String> users_list;
    private ProgressBar progressBar;
    private DatabaseReference mdatabase;
    private LottieAnimationView animationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);



        mdatabase = FirebaseDatabase.getInstance().getReference();
        progressBar = findViewById(R.id.progressBar);
        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        animationView = findViewById(R.id.animation_view);
        // animationView.playAnimation();


        viewModel = new Model();

        users_list = viewModel.getUserList();


        recyclerView = findViewById(R.id.rv_group);
        progressBar = findViewById(R.id.progressBar);
        Adapter = new User_Adapter(this);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(Adapter);


        Log.e("list_check_users", users_list.size() + "");
        Adapter.load_users(users_list);


    }


    @Override
    public void onStart() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                    Adapter.notifyDataSetChanged();
                // animationView.cancelAnimation();
                // animationView.setVisibility(View.GONE);
            }
        }, 2000);


        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}









