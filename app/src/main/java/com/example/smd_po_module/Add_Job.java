package com.example.smd_po_module;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smd_po_module.classes.company;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.smd_po_module.R.color.red;

public class Add_Job extends AppCompatActivity {

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    EditText name;
    EditText phone;
    EditText email_address;
    EditText address;
    EditText ceo;
    EditText cmmi;
    EditText no_recruites;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference cRef = database.getReference("Company");
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__job);



        ImageView profileImage = findViewById(R.id.profile);
        Picasso.get().load(R.drawable.logo).into(profileImage);
        Button but=findViewById(R.id.button);
        EditText edit=findViewById(R.id.TextView);
        edit.setFilters( new InputFilter[]{ new ValidateFilter()});

         name= findViewById(R.id.TextView);
         phone= findViewById(R.id.editText);
         email_address= findViewById(R.id.editText2);
         address= findViewById(R.id.editText3);
         ceo= findViewById(R.id.editText5);
         cmmi=findViewById(R.id.editText6);
        no_recruites= findViewById(R.id.editText4);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cid=addCompany();
                if(cid==null){

                }
                else {

                    Intent i = new Intent(getApplicationContext(), Add_Job2.class);
                    i.putExtra("cid", cid);
                    startActivity(i);
                }

            }
        });


    }

    private String addCompany() {
        String name1= name.getText().toString().trim();
        String phone1= phone.getText().toString().trim();
        String email_address1=email_address.getText().toString().trim();
        String address1= address.getText().toString().trim();
        String ceo1= ceo.getText().toString().trim();
        String cmmi1=cmmi.getText().toString().trim();
        String no_recruites1= no_recruites.getText().toString().trim();

        if(!(TextUtils.isEmpty(name1) && TextUtils.isEmpty(phone1) && TextUtils.isEmpty(email_address1)
                && TextUtils.isEmpty(address1) && TextUtils.isEmpty(ceo1) && TextUtils.isEmpty(cmmi1)
                && TextUtils.isEmpty(no_recruites1))){
            String id=cRef.push().getKey();


            company comp=new company(id,name1,ceo1,cmmi1,address1,no_recruites1,email_address1,phone1);

            cRef.child(id).setValue(comp);
            Toast.makeText(this, "Company Added", Toast.LENGTH_SHORT).show();
            return id;

        }
        else{
            Toast.makeText(this, "Enter all fields first..", Toast.LENGTH_SHORT).show();
            return null;
        }


    }
}
