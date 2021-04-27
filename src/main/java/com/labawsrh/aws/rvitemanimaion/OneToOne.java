package com.labawsrh.aws.rvitemanimaion;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OneToOne extends Fragment {
    RecyclerView recyclerView;
    Model viewModel;
    LinearLayoutManager linearLayoutManager;

    int totalItemCount;
    int lastVisibleItem;
    boolean loading;
    private int visibleThreshold = 5;
    OneToOneAdapter Adapter;
    List<String> users_list;
    private ProgressBar progressBar;
    private ValueEventListener valueEventListener;
    private Button button;
    Repository repository;
    private DatabaseReference mdatabase;
    private FloatingActionButton actionButton;
    private LottieAnimationView lottieAnimationView;
    Snackbar snackbar;
    private RelativeLayout relativeLayout ;
    private Button try_again ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.e("create", "on create");


        View view = inflater.inflate(R.layout.fragment_one_to_one, container, false);
        users_list = new ArrayList<>();

        mdatabase = FirebaseDatabase.getInstance().getReference();
        progressBar = view.findViewById(R.id.progressBar);
        relativeLayout = view.findViewById(R.id.relative_layout) ;
        actionButton = view.findViewById(R.id.action_button);
        lottieAnimationView = view.findViewById(R.id.animation_view);
        try_again = view.findViewById(R.id.again);

        try_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (User_Details.connected) {
                    snackbar.dismiss();
                    try_again.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    lottieAnimationView.setVisibility(View.GONE);
                    lottieAnimationView.cancelAnimation();
                    get_chats();
                }
            }
        });

        snackbar = Snackbar.make(User_Details.linearLayout, "Some Went Wrong", Snackbar.LENGTH_INDEFINITE) ;

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Users.class);
                startActivity(intent);
            }
        });

//        viewModel = new Model();
//
//        users_list = viewModel.getUserList();

        progressBar.setVisibility(View.VISIBLE);


        recyclerView = view.findViewById(R.id.rv_group);
        progressBar = view.findViewById(R.id.progressBar);
        Adapter = new OneToOneAdapter(getContext(), users_list);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(Adapter);

//        Log.e("list_check_one_to_one", users_list.size() + "");
//
//        Adapter.load_users(users_list);




        return view;
    }


    @Override
    public void onStart() {

        if (User_Details.connected) {
            get_chats();
        } else {
            lottieAnimationView.setVisibility(View.VISIBLE);
            lottieAnimationView.playAnimation();
            snackbar.show();
            try_again.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
        }


//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Adapter.notifyDataSetChanged();
//                Collections.sort(users_list, new Comparator<String>() {
//                    @Override
//                    public int compare(String o1, String o2) {
//                        //   Log.e("compare", "comparator");
//                        return o2.compareTo(o1);
//
//                    }
//                });
//
//                progressBar.setVisibility(View.INVISIBLE);
//            }
//        }, 5000);


        super.onStart();
    }

    @Override
    public void onResume() {
        Log.e("On resume", "On resume state");
        super.onResume();

    }

    public void get_chats() {

        FirebaseDatabase.getInstance().getReference().child("users_connection").child(User_Details.user_name).orderByChild("timestamp").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users_list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (!dataSnapshot.getKey().equals(User_Details.user_name)) {
                        Log.e("users  ", dataSnapshot.getKey());
                        users_list.add(dataSnapshot.getKey());
                    }
                }
                Collections.reverse(users_list);
                Adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}


//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//
//                totalItemCount = linearLayoutManager.getItemCount();
//                Log.e("item_count :", totalItemCount + "");
//
//
//                lastVisibleItem = linearLayoutManager
//                        .findLastVisibleItemPosition();
//                Log.e("last_visible _item :", lastVisibleItem + "");
//
//
//                if (!loading && totalItemCount <= (lastVisibleItem) + 1) {
//                    // End has been reached
//                    // Do something
//                    progressBar.setVisibility(View.VISIBLE);
//                    Log.e("load more : ", "loading");
//                    if (true) {
//
//                        load_more();
//
//                    }
//
//                    loading = true;
//                }
//            }
//
//
//        });


//    private void load_more() {
//
//        //    final List<String> stringList = repository.getChatsList("Rahul");
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//
//                Adapter.load_Data("hii");
//                //  more_chat = null;
//
//                Adapter.notifyDataSetChanged();
//                progressBar.setVisibility(View.GONE);
//                loading = true;
//            }
//        }, 5000);


//    }


//    @Override
//    public void add_message(String time) {
//        Adapter.setData(time);
//        Adapter.notifyDataSetChanged();
//    }