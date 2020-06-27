package com.labawsrh.aws.rvitemanimaion;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.ArrayList;
import java.util.List;

public class ViewModel extends AndroidViewModel {

    List<String> list;
    List<MessageItem> mData;


    public ViewModel(@NonNull Application application) {
        super(application);


        list = new ArrayList<>();
        list.add("Rahul khandelwal");
        list.add("Om parashar");
        list.add("Rahul khandelwal");
        list.add("Om parashar");
        list.add("Rahul khandelwal");
        list.add("Om parashar");
        list.add("Rahul khandelwal");
        list.add("Om parashar");

        mData = new ArrayList<>() ;
        mData.add(new MessageItem("OnePlus 6T Camera Review:", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "6 july 1994", R.drawable.user));
        mData.add(new MessageItem("I love Programming And Design", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,", "6 july 1994", R.drawable.circul6));
        mData.add(new MessageItem("My first trip to Thailand story ", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "6 july 1994", R.drawable.uservoyager));
        mData.add(new MessageItem("After Facebook Messenger, Viber now gets...", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,", "6 july 1994", R.drawable.useillust));
        mData.add(new MessageItem("Isometric Design Grid Concept", "lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit", "6 july 1994", R.drawable.circul6));
        mData.add(new MessageItem("Android R Design Concept 4K", "lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit ", "6 july 1994", R.drawable.user));
        mData.add(new MessageItem("OnePlus 6T Camera Review:", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "6 july 1994", R.drawable.user));
        mData.add(new MessageItem("I love Programming And Design", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,", "6 july 1994", R.drawable.circul6));
        mData.add(new MessageItem("My first trip to Thailand story ", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "6 july 1994", R.drawable.uservoyager));
        mData.add(new MessageItem("After Facebook Messenger, Viber now gets...", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,", "6 july 1994", R.drawable.useillust));
        mData.add(new MessageItem("Isometric Design Grid Concept", "lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit", "6 july 1994", R.drawable.circul6));
        mData.add(new MessageItem("Android R Design Concept 4K", "lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit ", "6 july 1994", R.drawable.user));
        mData.add(new MessageItem("OnePlus 6T Camera Review:", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "6 july 1994", R.drawable.user));
        mData.add(new MessageItem("I love Programming And Design", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,", "6 july 1994", R.drawable.circul6));
        mData.add(new MessageItem("My first trip to Thailand story ", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "6 july 1994", R.drawable.uservoyager));
        mData.add(new MessageItem("After Facebook Messenger, Viber now gets...", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,", "6 july 1994", R.drawable.useillust));
        mData.add(new MessageItem("Isometric Design Grid Concept", "lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit", "6 july 1994", R.drawable.circul6));
        mData.add(new MessageItem("Android R Design Concept 4K", "lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit lorem ipsum dolor sit ", "6 july 1994", R.drawable.user));

    }

    public List<String> getList() {
        return list;
    }

    public List<MessageItem> getmData() {
        return mData;
    }
}
