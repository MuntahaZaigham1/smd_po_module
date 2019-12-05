package com.example.smd_po_module.classes;

import android.util.Log;

import com.example.smd_po_module.classes.message;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class msg_data {


    public  ArrayList<message> getdata(String s_uid,String s_name){



        DatabaseReference ref1= FirebaseDatabase.getInstance().getReference("Message");

        //company comp;
        ArrayList<message> data;
        ArrayList<message> extractData;

        data = new ArrayList<message>();
        extractData = new ArrayList<message>();

        ref1.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
        public void onDataChange(DataSnapshot dataSnapshot) {


            // Result will be holded Here
            for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                data.add(dsp.getValue(message.class)); //add result into array list
                Log.e("here2","here2");
                Log.e("data:",data.get(0).getMsg());



            }
            for(int i=0;i<data.size();i++){
                if(data.get(i).getS_uid().equals(s_uid)&& data.get(i).getS_name().equals(s_name)){
                    extractData.add(data.get(i));
                    Log.e("data:",extractData.get(0).getMsg());
                }

            }

        }

        @Override
        public void onCancelled(DatabaseError error) {
            Log.e(TAG, "Failed to read user", error.toException());

        }

    });
        //extract info from db company
        //store itin this array




        return extractData;
    }

}
