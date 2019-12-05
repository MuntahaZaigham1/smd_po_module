package com.example.smd_po_module;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.smd_po_module.classes.job;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class add_more_jobs extends AppCompatActivity {
    AlertDialog.Builder builder;
    EditText position;
    EditText salary;
    EditText type_employment;
    EditText skill_set;
    EditText responsibilities;
    String cid;
    String pid;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference jRef = database.getReference("Job");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_more_jobs);
        ImageView profileImage = findViewById(R.id.profile);
        Picasso.get().load(R.drawable.logo).into(profileImage);
        Bundle extras = getIntent().getExtras();
        cid = extras.getString("cid");
        position= findViewById(R.id.TextView);
        salary= findViewById(R.id.editText);
        type_employment= findViewById(R.id.editText1);
        skill_set= findViewById(R.id.editText2);
        responsibilities= findViewById(R.id.editText4);

        Button cancel= findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("name","name");
                startActivity(i);
                Toast.makeText(getApplicationContext(),"Okay cancelled.",
                        Toast.LENGTH_SHORT).show();
            }
        });

                Button but=findViewById(R.id.button);
        builder = new AlertDialog.Builder(this);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //Setting message manually and performing action on button click
                builder.setMessage("Do you want to add this job offer ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                addJob();
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                i.putExtra("name","name");
                                startActivity(i);
                                //finish();
                                Toast.makeText(getApplicationContext(),"Going to add this offer",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(),"you choose no ",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Confirmation");
                alert.show();
            }
        });
    }
    private void addJob() {
        String pos=position.getText().toString().trim();
        String sal=salary.getText().toString().trim();
        String toe=type_employment.getText().toString().trim();
        String skill=skill_set.getText().toString().trim();
        String resp=responsibilities.getText().toString().trim();
        if(!(TextUtils.isEmpty(pos) && TextUtils.isEmpty(sal) && TextUtils.isEmpty(toe)
                && TextUtils.isEmpty(skill) && TextUtils.isEmpty(resp))){


            String id=jRef.push().getKey();

            job jb=new job(cid,id,pos,sal,toe,skill,resp);

            jRef.child(id).setValue(jb);
            Toast.makeText(this, "Job Added", Toast.LENGTH_SHORT).show();


        }
        else{
            Toast.makeText(this, "Enter all fields first..", Toast.LENGTH_SHORT).show();

        }


    }
}
