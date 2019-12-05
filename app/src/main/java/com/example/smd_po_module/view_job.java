package com.example.smd_po_module;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smd_po_module.classes.company;
import com.example.smd_po_module.classes.job;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class view_job extends AppCompatActivity {
    String jid;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference cRef = database.getReference("Company");
    DatabaseReference jRef = database.getReference("Job");
    TextView name;

    TextView email_address;
    TextView address;
    TextView ceo;

    TextView position;
    TextView salary;
    TextView type_employment;
    TextView skill_set;
    TextView responsibilities;
    String cid;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_job);
        Bundle extras = getIntent().getExtras();
        jid = extras.getString("jid");
        cid=extras.getString("cid");
        Log.e(TAG, "jid    " + jid);
        Log.e(TAG, "cid    " + cid);
        name= findViewById(R.id.textView18);
        email_address= findViewById(R.id.textView13);
        address= findViewById(R.id.textView17);
        ceo= findViewById(R.id.textView15);
        position= findViewById(R.id.textView4);
        salary= findViewById(R.id.textView5);
        type_employment= findViewById(R.id.textView8);
        skill_set= findViewById(R.id.textView10);
        responsibilities= findViewById(R.id.textView10_0);
        id=showJob(jid);
        showCompany();
        Button b2;
        b2=findViewById(R.id.notify);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), notify_students1.class);
                startActivity(i);
            }
        });
        ImageView profileImage = findViewById(R.id.profile);
        Picasso.get().load(R.drawable.logo).into(profileImage);

    }

    private String showJob(String jid) {

        jRef.child(jid).addValueEventListener(new ValueEventListener() {
            job jb;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                jb = dataSnapshot.getValue(job.class);
                if(jb==null){}
                else {

                    position.setText(jb.getPosition());
                    salary.setText(jb.getSalary());
                    type_employment.setText(jb.getType_employement());
                    skill_set.setText(jb.getSkill_Set_req());
                    //cid=jb.getCid();
                    responsibilities.setText(jb.getResponsibilities());

                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }


        });

        return cid;


    }

    private void showCompany() {
        cRef.child(cid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                company comp = dataSnapshot.getValue(company.class);
                if(comp==null){}
                else {
                    Log.d(TAG, "User name: " + comp.getName() + ", email " + comp.getEmail());
                    name.setText(comp.getName());
                    //cmmi.setText(comp.getCmmi());
                    ceo.setText(comp.getCeo());
                    address.setText(comp.getAddress());
                    //no_recruites.setText(comp.getNo_recruits());
                    email_address.setText(comp.getEmail());
                    //phone.setText(comp.getPhone());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}
