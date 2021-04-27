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
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Group_Adapter extends RecyclerView.Adapter<Group_Adapter.BaseViewHolder> {

    private Context context;
    private List<GroupInfo> list;


    public Group_Adapter(Context context, List<GroupInfo> list) {
        Log.e("size", list.size() + "");
        this.list = list;
        this.context = context;

    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.group_chat_item, parent, false);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, final int position) {
        holder.name.setText(list.get(position).getName());
        holder.headline.setText(list.get(position).getHeadline());

            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, GroupChat.class);
                    intent.putExtra("group_name", list.get(position).getName());
                    intent.putExtra("headline" , list.get(position).getHeadline());
                    context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BaseViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        RelativeLayout relativeLayout;
        TextView headline ;
        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            relativeLayout = itemView.findViewById(R.id.relative_layout);
            headline = itemView.findViewById(R.id.headline) ;

        }
    }
}
