package com.labawsrh.aws.rvitemanimaion;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupHolder> {
    public List<String> list;
    Context context;


    public GroupAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public GroupHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_item, parent, false);

        return new GroupHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GroupAdapter.GroupHolder holder, int position) {
         holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 holder.relativeLayout.setEnabled(false);
                 Intent intent = new Intent(context , MainActivity.class) ;
                 context.startActivity(intent);
             }
         });
        holder.name.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class GroupHolder extends RecyclerView.ViewHolder {

        TextView name;
        RelativeLayout relativeLayout ;

        public GroupHolder(@NonNull View itemView) {

            super(itemView);
            name = itemView.findViewById(R.id.name);
            relativeLayout = itemView.findViewById(R.id.relative_layout) ;
        }
    }
}
