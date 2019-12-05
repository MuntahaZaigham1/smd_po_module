package com.example.smd_po_module.classes;

import android.util.Log;

import com.example.smd_po_module.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Student_data {
    public static ArrayList<student> getdata(){
        ArrayList<student> data = new ArrayList<student>();


        DatabaseReference ref1= FirebaseDatabase.getInstance().getReference("student");
        ref1.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                // Result will be holded Here
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    data.add(dsp.getValue(student.class)); //add result into array list


                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e(TAG, "Failed to read user", error.toException());

            }

        });
        //extract info from db company
        //store itin this array




        return data;
    }
}
