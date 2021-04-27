package com.labawsrh.aws.rvitemanimaion;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class User_Adapter extends RecyclerView.Adapter<User_Adapter.UserHolder> implements Filterable {
    public List<String> list = new ArrayList<>();
    Context context;
    List<String> mDataFiltered;

    ArrayList<String> users = new ArrayList<>();


    public User_Adapter() {

    }


    public User_Adapter(Context context) {

        this.context = context;

    }


    @NonNull
    @Override
    public User_Adapter.UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_chat_item, parent, false);

        return new User_Adapter.UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final User_Adapter.UserHolder holder, final int position) {
        holder.name.setText(list.get(position));
        holder.message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("user_name", list.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return list.size();
    }


    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String Key = constraint.toString();
                if (Key.isEmpty()) {
                    list = mDataFiltered;

                } else {
                    List<String> lstFiltered = new ArrayList<>();
                    for (String row : list) {

                        if (row.toLowerCase().contains(Key.toLowerCase())) {
                            lstFiltered.add(row);
                        }

                    }

                    list = lstFiltered;

                }


                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {


                list = (List<String>) results.values;
                notifyDataSetChanged();

            }
        };

    }


    public class UserHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView message;


        public UserHolder(@NonNull View itemView) {

            super(itemView);
            name = itemView.findViewById(R.id.name);
            message = itemView.findViewById(R.id.cardView2);
        }
    }

    public void load_users(List<String> users) {
        list = users;
        mDataFiltered = list;


    }


}
