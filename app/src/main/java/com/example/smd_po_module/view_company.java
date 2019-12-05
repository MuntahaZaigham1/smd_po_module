package com.example.smd_po_module;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.smd_po_module.classes.company;
import com.example.smd_po_module.classes.job;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class view_company extends Activity {
    String text;
    Context context;
    AlertDialog.Builder builder;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference cRef = database.getReference("Company");
    DatabaseReference jRef = database.getReference("Job");

    TextView name;
    TextView phone;
    TextView email_address;
    TextView address;
    TextView ceo;
    TextView cmmi;
    TextView no_recruites;
    String cid;
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_view_company);
        Bundle extras = getIntent().getExtras();
        cid = extras.getString("cid");

        name= findViewById(R.id.textView4);
        phone= findViewById(R.id.phone1);
        email_address= findViewById(R.id.textView11);
        address= findViewById(R.id.textView14);
        ceo= findViewById(R.id.textView5);
        cmmi=findViewById(R.id.textView8);
        no_recruites= findViewById(R.id.textView10);
        showCompany();

        Button button = findViewById(R.id.button2);
        TextView tv = (TextView) findViewById(R.id.textView14);
        //Collecting data
        text= address.getText().toString();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action on click
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("google.navigation:q="+text));
                startActivity(intent);
            }
        });

        Button but = findViewById(R.id.button1);
        builder = new AlertDialog.Builder(this);
        but.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                builder.setMessage("Do you want to delete this company ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                deleteCompany(cid);
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                i.putExtra("name", cid);
                                startActivity(i);
                                Toast.makeText(getApplicationContext(), "you choose yes ",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(), "you choose no ",
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

        Button butt = findViewById(R.id.button);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), company_update.class);
                i.putExtra("cid", cid);
                startActivity(i);
            }
        });
        Button butt1 = findViewById(R.id.button12);
        butt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global.intent = 'N';
                Global.intent = 'y';
                Intent i = new Intent(getApplicationContext(), list_Jobs.class);
                i.putExtra("cid", cid);
                startActivity(i);
            }
        });
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
                    cmmi.setText(comp.getCmmi());
                    ceo.setText(comp.getCeo());
                    address.setText(comp.getAddress());
                    no_recruites.setText(comp.getNo_recruits());
                    email_address.setText(comp.getEmail());
                    phone.setText(comp.getPhone());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
    private void deleteCompany(String cid) {
        ArrayList <job> data=new ArrayList<>();
        ArrayList<job> extractedData=new ArrayList<>();
        jRef.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                // Result will be holded Here
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {

                    data.add(dsp.getValue(job.class)); //add result into array list


                }
                for(int i=0;i<data.size();i++){
                    Log.e("befr cid matches","before cid amtches");
                    if(data.get(i).getCid().equals(cid)){
                        extractedData.add(data.get(i));
                        jRef.child(data.get(i).getJid()).setValue(null);
                        Log.e("after cid matches","after cid amtches");
                        //  Log.e("befr cid matches",extractedData.get(i).getPosition());

                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e(TAG, "Failed to read user", error.toException());

            }

        });


        cRef.child(cid).setValue(null);
    }

}
