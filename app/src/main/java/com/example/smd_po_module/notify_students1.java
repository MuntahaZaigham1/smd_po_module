package com.example.smd_po_module;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smd_po_module.classes.Student_data;
import com.example.smd_po_module.classes.student;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class notify_students1 extends AppCompatActivity {
    RecyclerView recycler;
    adapter_student adapter;
    ArrayList<student> updated=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_students1);
        ImageView profileImage = findViewById(R.id.profile);
        Picasso.get().load(R.drawable.logo).into(profileImage);
        Button srch=findViewById(R.id.search);
        EditText search= findViewById(R.id.searchEditText);
        Global.intent = 'S';
        recycler=findViewById(R.id.recyclerViewDemo);
        adapter=new adapter_student(this, Student_data.getdata());
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        updated= Student_data.getdata();
        srch.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                EditText search= findViewById(R.id.searchEditText);
                String name1=search.getText().toString();

                Query query = FirebaseDatabase.getInstance().getReference("student")
                        .orderByChild("name")
                        .equalTo(name1);


                query.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.e("here22","here22");

                        Student_data.getdata().clear();
                        updated.clear();


                        // adapter.notifyDataSetChanged();

                        if (dataSnapshot.exists()) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                student stud = snapshot.getValue(student.class);

                                updated.add(stud);
                                //Log.e("here22",Company_data.getdata().get(0).getName());


                            }
                            adapter.setList(updated);
                            adapter.refreshList();
                        }
                        else
                            Toast.makeText(getApplicationContext(), "not Found enter correct position to search", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                //searchCompany(name);

            }
        });
    }
}
