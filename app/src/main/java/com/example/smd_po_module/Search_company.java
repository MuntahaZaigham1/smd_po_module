package com.example.smd_po_module;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smd_po_module.classes.Company_data;

import com.example.smd_po_module.classes.Student_data;
import com.example.smd_po_module.classes.company;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Search_company extends AppCompatActivity {
    RecyclerView recycler;
    adapter_company adapter;
    ArrayList<company> updated=new ArrayList<>();
   // DatabaseReference ref1= FirebaseDatabase.getInstance().getReference("Company");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_company);
        ImageView profileImage = findViewById(R.id.profile);
        Picasso.get().load(R.drawable.logo).into(profileImage);
        EditText search= findViewById(R.id.searchEditText);
        String name1=search.getText().toString();
        //search and show user the company details searched on basis of name
        Button srch=findViewById(R.id.search);
        recycler=findViewById(R.id.recyclerViewDemo);
        adapter=new adapter_company(this, Company_data.getdata());
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        updated=Company_data.getdata();
        Global.intent='L';
        srch.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                EditText search= findViewById(R.id.searchEditText);
                String name1=search.getText().toString();

                Query query = FirebaseDatabase.getInstance().getReference("Company")
                       .orderByChild("name")
                        .equalTo(name1);
                Log.e("here211","here211");

                Log.e("here1",name1);

                query.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.e("here22","here22");

                        Company_data.getdata().clear();
                        updated.clear();


                       // adapter.notifyDataSetChanged();

                        if (dataSnapshot.exists()) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                company comp = snapshot.getValue(company.class);
                                Log.e("here3",comp.getName());

                                updated.add(comp);
                                //Log.e("here22",Company_data.getdata().get(0).getName());

                                Log.e("here2","here2");

                            }
                            adapter.setList(updated);
                            adapter.refreshList();
                        }
                        else
                            Toast.makeText(getApplicationContext(), "not Found enter correct name to search", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                //searchCompany(name);

            }
        });






    }

    private void searchCompany(String searchName) {


    }


}
