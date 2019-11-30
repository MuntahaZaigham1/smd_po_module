package com.example.smd_po_module;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smd_po_module.ui.login.PO_login;

public class MainActivityAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_main);


        Button b1;
        b1=findViewById(R.id.add_PO);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), add_po.class);
                startActivity(i);
            }
        });
        Button b2;
        b2=findViewById(R.id.search);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), search.class);
                startActivity(i);
            }
        });
        Button b3;
        b3=findViewById(R.id.search1);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global.intent='L';

                Intent i = new Intent(getApplicationContext(), Search_company.class);
                startActivity(i);
            }
        });
        Button b5;
        b5=findViewById(R.id.log_out);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PO_login.class);
                startActivity(i);
            }
        });
    }
}

