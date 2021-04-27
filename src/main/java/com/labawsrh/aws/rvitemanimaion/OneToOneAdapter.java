package com.labawsrh.aws.rvitemanimaion;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.L;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OneToOneAdapter extends RecyclerView.Adapter<OneToOneAdapter.GroupHolder> {
    public List<String> list = new ArrayList<>();
    Context context;
    String the_Last_message;
//    TextView last_message;


    ArrayList<String> users = new ArrayList<>();


    public OneToOneAdapter() {

    }


    public OneToOneAdapter(Context context, List<String> list) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public GroupHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.one_to_one_chat_item, parent, false);

        return new GroupHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final OneToOneAdapter.GroupHolder holder, final int position) {
        Log.e("list", list.size() + " - " + list.get(position));

        lastMessage(list.get(position), holder.last_message);
        is_last_seen(holder.cardView, list.get(position));

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   holder.relativeLayout.setEnabled(false);
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("user_name", list.get(position));
                context.startActivity(intent);

            }
        });
        holder.name.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        //  Log.e("called_2", list.size() + "");
        return list.size();
    }

    public class GroupHolder extends RecyclerView.ViewHolder {

        TextView name;
        RelativeLayout relativeLayout;
        TextView last_message;
        CardView cardView;

        public GroupHolder(@NonNull View itemView) {

            super(itemView);
            name = itemView.findViewById(R.id.name);
            relativeLayout = itemView.findViewById(R.id.relative_layout);
            last_message = itemView.findViewById(R.id.last_mssg);
            cardView = itemView.findViewById(R.id.cardView2);
        }
    }


    public void load_users(List<String> users) {
        if (users.size() == 0) {
            list = users;
            Log.e("list_load", users.size() + "");
        }

    }

    private void lastMessage(String user_id, final TextView last_message) {
        the_Last_message = "default";
        FirebaseDatabase.getInstance().getReference().child("users_chat").child(User_Details.user_name).child(user_id).limitToLast(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    MessageItem messageItem = dataSnapshot.getValue(MessageItem.class);
                    the_Last_message = messageItem.getMessage();
                }

                switch (the_Last_message) {
                    case "default":
                        last_message.setText("No message");
                        break;

                    default:
                        last_message.setText(the_Last_message);
                        break;
                }

                the_Last_message = "default";
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void is_last_seen(final CardView isSeen, String user_id) {

        FirebaseDatabase.getInstance().getReference().child("users_chat").child(user_id).child(User_Details.user_name).limitToLast(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        MessageItem messageItem = dataSnapshot.getValue(MessageItem.class);
                        if (messageItem.isSeen()) {
                            isSeen.setVisibility(View.GONE);
                        } else {
                            isSeen.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
