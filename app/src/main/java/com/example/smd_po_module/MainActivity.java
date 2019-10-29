package com.example.smd_po_module;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView profileImage = findViewById(R.id.profile);
        Picasso.get().load(R.drawable.po_profile).into(profileImage);
        TextView po_name = findViewById(R.id.po_name);
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("name");
        po_name.setText(name);
        TextView po_role = findViewById(R.id.po_role);
        po_role.setText("Placement Officer");
        Button b1;
        b1=findViewById(R.id.add_Company);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Add_Job.class);
                startActivity(i);
            }
        });
        Button b2;
        b2=findViewById(R.id.notify);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), notify_students1.class);
                startActivity(i);
            }
        });




    }
}