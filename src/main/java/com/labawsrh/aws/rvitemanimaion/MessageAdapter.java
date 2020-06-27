package com.labawsrh.aws.rvitemanimaion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.NewsViewHolder> implements Filterable {


    Context mContext;
    List<MessageItem> mData;
    List<MessageItem> mDataFiltered;
    boolean isDark = false;


    public MessageAdapter(Context mContext, List<MessageItem> mData, boolean isDark) {
        this.mContext = mContext;
        this.mData = mData;
        this.isDark = isDark;
        this.mDataFiltered = mData;
    }

    public MessageAdapter(Context mContext, List<MessageItem> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.mDataFiltered = mData;

    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.item_message_sender, viewGroup, false);
        return new NewsViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int position) {

        // bind data here

        // we apply animation to views here
        // first lets create an animation for user photo
        if (position % 2 == 0) {
            newsViewHolder.img_user.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_transition_animation));

            // lets create the animation for the whole card
            // first lets create a reference to it
            // you ca use the previous same animation like the following

            // but i want to use a different one so lets create it ..
            newsViewHolder.container.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_scale_animation));
        } else {
            newsViewHolder.img_user.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_transition_sender));

            // lets create the animation for the whole card
            // first lets create a reference to it
            // you ca use the previous same animation like the following

            // but i want to use a different one so lets create it ..
            newsViewHolder.container.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_scale_animation_sender));
        }


        newsViewHolder.tv_title.setText(mDataFiltered.get(position).getTitle());
        newsViewHolder.tv_content.setText(mDataFiltered.get(position).getContent());
        newsViewHolder.tv_date.setText(mDataFiltered.get(position).getDate());
        newsViewHolder.img_user.setImageResource(mDataFiltered.get(position).getUserPhoto());


    }

    @Override
    public int getItemCount() {
        return mDataFiltered.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String Key = constraint.toString();
                if (Key.isEmpty()) {

                    mDataFiltered = mData;

                } else {
                    List<MessageItem> lstFiltered = new ArrayList<>();
                    for (MessageItem row : mData) {

                        if (row.getTitle().toLowerCase().contains(Key.toLowerCase())) {
                            lstFiltered.add(row);
                        }

                    }

                    mDataFiltered = lstFiltered;

                }


                FilterResults filterResults = new FilterResults();
                filterResults.values = mDataFiltered;
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {


                mDataFiltered = (List<MessageItem>) results.values;
                notifyDataSetChanged();

            }
        };


    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {


        TextView tv_title, tv_content, tv_date;
        ImageView img_user;
        RelativeLayout container;


        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_content = itemView.findViewById(R.id.tv_description);
            tv_date = itemView.findViewById(R.id.tv_date);
            img_user = itemView.findViewById(R.id.img_user);


            if (isDark) {
                setDarkTheme();
            }


        }


        private void setDarkTheme() {

            container.setBackgroundResource(R.drawable.card_bg_dark);

        }


    }
}
