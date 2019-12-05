package com.example.smd_po_module;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.smd_po_module.classes.company;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class company_update extends AppCompatActivity {
    EditText name;
    EditText phone;
    EditText email_address;
    EditText address;
    EditText ceo;
    EditText cmmi;
    EditText no_recruites;
    String cid;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference cRef = database.getReference("Company");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_update);
        Bundle extras = getIntent().getExtras();
        cid = extras.getString("cid");
        ImageView profileImage = findViewById(R.id.profile);
        Picasso.get().load(R.drawable.logo).into(profileImage);
        Button but=findViewById(R.id.button);
        name= findViewById(R.id.TextView);
        phone= findViewById(R.id.editText);
        email_address= findViewById(R.id.editText2);
        address= findViewById(R.id.editText3);
        ceo= findViewById(R.id.editText5);
        cmmi=findViewById(R.id.editText6);
        no_recruites= findViewById(R.id.editText4);
        showCompany();
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateCompany();
                if(cid==null){

                }
                else {

                    Intent i = new Intent(getApplicationContext(), view_company.class);
                    i.putExtra("cid", cid);
                    startActivity(i);
                }

            }
        });

    }

    private void showCompany() {

        cRef.child(cid).addValueEventListener(new ValueEventListener() {
            company comp;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                comp = dataSnapshot.getValue(company.class);

                Log.d(TAG, "User name: " + comp.getName() + ", email " + comp.getEmail());
                name.setText(comp.getName());
                cmmi.setText(comp.getCmmi());
                ceo.setText(comp.getCeo());
                address.setText(comp.getAddress());
                no_recruites.setText(comp.getNo_recruits());
                email_address.setText(comp.getEmail());
                phone.setText(comp.getPhone());

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }

    private void updateCompany() {
        cRef.child(cid).addValueEventListener(new ValueEventListener() {
            company comp;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                comp = dataSnapshot.getValue(company.class);
                String name1= name.getText().toString().trim();
                String phone1= phone.getText().toString().trim();
                String email_address1=email_address.getText().toString().trim();
                String address1= address.getText().toString().trim();
                String ceo1= ceo.getText().toString().trim();
                String cmmi1=cmmi.getText().toString().trim();
                String no_recruites1= no_recruites.getText().toString().trim();

                if(!(TextUtils.isEmpty(name1) && TextUtils.isEmpty(phone1) && TextUtils.isEmpty(email_address1)
                        && TextUtils.isEmpty(address1) && TextUtils.isEmpty(ceo1) && TextUtils.isEmpty(cmmi1)
                        && TextUtils.isEmpty(no_recruites1))) {
                    comp.setAddress(address1);
                    comp.setCeo(ceo1);
                    comp.setEmail(email_address1);
                    comp.setPhone(phone1);
                    comp.setName(name1);
                    comp.setCmmi(cmmi1);
                    comp.setNo_recruits(no_recruites1);

                    cRef.child(cid).setValue(comp);
                }

                Log.d(TAG, "User name: " + comp.getName() + ", email " + comp.getEmail());



            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to Update value.", error.toException());
            }
        });

    }
}
