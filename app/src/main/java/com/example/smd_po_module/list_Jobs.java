package com.example.smd_po_module;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.smd_po_module.classes.Company_data;
import com.example.smd_po_module.classes.Job_data;
import com.example.smd_po_module.classes.company;
import com.example.smd_po_module.classes.job;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class list_Jobs extends AppCompatActivity {
    RecyclerView recycler;
    adapter_job adapter;
    String cid;
    ArrayList<job> updated=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__jobs);
        Bundle extras = getIntent().getExtras();
        cid = extras.getString("cid");
        Button srch=findViewById(R.id.search);
        EditText search= findViewById(R.id.searchEditText);

        ImageView profileImage = findViewById(R.id.profile);
        Picasso.get().load(R.drawable.logo).into(profileImage);
        recycler=findViewById(R.id.recyclerViewDemo);
        adapter=new adapter_job(this, Job_data.getdata(cid));
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        updated= Job_data.getdata(cid);

        Global.intent='J';
        Button but=findViewById(R.id.notify123);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Add_Job2.class);
                i.putExtra("cid",cid);
                startActivity(i);
            }
        });
        srch.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                EditText search= findViewById(R.id.searchEditText);
                String name1=search.getText().toString();

                Query query = FirebaseDatabase.getInstance().getReference("Job")
                        .orderByChild("position")
                        .equalTo(name1);


                query.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.e("here22","here22");

                        Job_data.getdata(cid).clear();
                        updated.clear();


                        // adapter.notifyDataSetChanged();

                        if (dataSnapshot.exists()) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                job jb = snapshot.getValue(job.class);

                                updated.add(jb);
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
