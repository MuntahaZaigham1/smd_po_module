package com.example.smd_po_module;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smd_po_module.data.DATAforSearch;


public class search extends AppCompatActivity {
    poSearchadapter po;
    RecyclerView recycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        recycler= findViewById(R.id.recyclerViewDemo);
        po=new poSearchadapter(this, DATAforSearch.getData());
        recycler.setAdapter(po);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }
}