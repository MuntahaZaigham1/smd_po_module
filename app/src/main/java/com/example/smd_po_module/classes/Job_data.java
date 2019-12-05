package com.example.smd_po_module.classes;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Job_data {
    public static ArrayList<job> getdata(String cid){
        ArrayList<job> data = new ArrayList<job>();
        ArrayList <job> extractedData=new ArrayList<>();

        DatabaseReference ref1= FirebaseDatabase.getInstance().getReference("Job");




        ref1.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                // Result will be holded Here
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {

                    data.add(dsp.getValue(job.class)); //add result into array list


                }
                for(int i=0;i<data.size();i++){
                    Log.e("befr cid matches","before cid amtches");
                    if(data.get(i).getCid().equals(cid)){
                        extractedData.add(data.get(i));
                        Log.e("after cid matches","after cid amtches");
                      //  Log.e("befr cid matches",extractedData.get(i).getPosition());

                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e(TAG, "Failed to read user", error.toException());

            }

        });


        return extractedData;
    }
}
