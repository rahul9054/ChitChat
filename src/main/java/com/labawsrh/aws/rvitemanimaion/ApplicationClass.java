package com.labawsrh.aws.rvitemanimaion;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ApplicationClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        //  FirebaseDatabase.getInstance().setPersistenceCacheSizeBytes(102400000);


        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {
                    User_Details.connected = true;

                    Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_LONG).show();
                } else {
                    User_Details.connected = false;
                    Toast.makeText(getApplicationContext(), "not Connected", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
