package com.labawsrh.aws.rvitemanimaion;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.utils.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.NewsViewHolder> {


    public List<MessageItem> list = new ArrayList<>();
    Context mContext;

    String user_id;
    // TextView seen;
    private ValueEventListener valueEventListener;
    int i = 0;


    public MessageAdapter(Context mContext, String user_id) {
        this.mContext = mContext;
        this.user_id = user_id;
    }


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (i == 0) {

            View layout;
            layout = LayoutInflater.from(mContext).inflate(R.layout.item_message_sender, viewGroup, false);
            return new NewsViewHolder(layout);
        } else {
            View layout;
            layout = LayoutInflater.from(mContext).inflate(R.layout.item_message_receiver_one_to_one, viewGroup, false);
            return new NewsViewHolder(layout);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int position) {
        int i = list.size() - 1;
        Log.e("seen", i + " - " + position + " , " + list.size() + "  ,  " + list.get(position).getMessage());


        if (position == list.size() - 1 && list.get(position).getSender().equals(User_Details.user_name)) {

            Log.e("seen ekk barrr plzzzzz", position + "  ");

            show_seen(user_id, newsViewHolder.seen, position);

//                newsViewHolder.seen.setText("seen");
//            } else {
//                newsViewHolder.seen.setText("Delivered");
//            }

            // newsViewHolder.container.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_scale_animation));


        } else {

            newsViewHolder.seen.setVisibility(View.INVISIBLE);

            //  newsViewHolder.container.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_scale_animation_sender));
        }


        newsViewHolder.message.setText(list.get(position).message);

        long previousTs = 0;
        if (position > 0) {
            MessageItem pm = list.get(position - 1);
            previousTs = pm.getTimestamp();
        }

        setTimeTextVisibility(list.get(position).getTimestamp(), previousTs, newsViewHolder.time_text);


    }

    public String getFormattedDate(Context context, long smsTimeInMilis) {
        Calendar smsTime = Calendar.getInstance();
        smsTime.setTimeInMillis(smsTimeInMilis);

        Calendar now = Calendar.getInstance();

        final String timeFormatString = "h:mm aa";
        final String dateTimeFormatString = "EEEE, MMMM d";
        final long HOURS = 60 * 60 * 60;
        if (now.get(Calendar.DATE) == smsTime.get(Calendar.DATE)) {
            return "Today ";
        } else if (now.get(Calendar.DATE) - smsTime.get(Calendar.DATE) == 1) {
            return "Yesterday ";
        } else if (now.get(Calendar.YEAR) == smsTime.get(Calendar.YEAR)) {
            return DateFormat.format(dateTimeFormatString, smsTime).toString();
        } else {
            return DateFormat.format("MMMM dd yyyy, h:mm aa", smsTime).toString();
        }
    }

    private void setTimeTextVisibility(long ts1, long ts2, TextView timeText) {

        if (ts2 == 0) {
            timeText.setVisibility(View.VISIBLE);
            timeText.setText(getFormattedDate(mContext, ts1));
        } else {
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal1.setTimeInMillis(ts1);
            cal2.setTimeInMillis(ts2);

            boolean same = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                    cal1.get(Calendar.DATE) == cal2.get(Calendar.DATE);

            if (same) {
                timeText.setVisibility(View.GONE);
                timeText.setText("");
            } else {
                timeText.setVisibility(View.VISIBLE);
                timeText.setText(getFormattedDate(mContext, ts1));
            }

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    private void show_seen(String user_id, final TextView seen, final int position) {
        FirebaseDatabase.getInstance().getReference().child("users_chat").child(User_Details.user_name).child(user_id).limitToLast(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    MessageItem messageItem = dataSnapshot.getValue(MessageItem.class);
                    Log.e(" ekk barr call - " + position, messageItem.getMessage());

                    if (position == list.size() - 1) {
                        if (messageItem.isSeen()) {
                            seen.setVisibility(View.VISIBLE);
                            seen.setText("Seen");
                        } else {
                            seen.setVisibility(View.VISIBLE);
                            seen.setText("Delivered");
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    @Override
    public int getItemViewType(int position) {
        if (list.get(position) != null) {
            // Log.e("name", list.get(position).getSender());
            if (list.get(position).getSender().equals(User_Details.user_name)) {
                return 0;

            } else {

                return 1;
            }
        } else {
            return 2;
        }
    }

    public void setData(MessageItem messageItem) {
        // Log.e("called_3", list.size() + "");
        list.add(messageItem);
        notifyDataSetChanged();

    }


    public class NewsViewHolder extends RecyclerView.ViewHolder {


        TextView message, date;
        RelativeLayout container;
        TextView seen;
        TextView time_text;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
            message = itemView.findViewById(R.id.message);
            seen = itemView.findViewById(R.id.seen_message);
            time_text = itemView.findViewById(R.id.timeText);

        }


    }
}
