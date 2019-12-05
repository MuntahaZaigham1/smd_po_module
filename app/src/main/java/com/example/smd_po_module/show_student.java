package com.example.smd_po_module;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smd_po_module.classes.company;
import com.example.smd_po_module.classes.student;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class show_student extends AppCompatActivity {
    String text;
    Context context;
    AlertDialog.Builder builder;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference sRef = database.getReference("student");
    TextView name;
    TextView phone;
    TextView email_address;
    TextView batch;
    TextView gpa;
    TextView degree;
    TextView experience;
    TextView skills;


    String sid;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_student);
        ImageView profileImage = findViewById(R.id.profile);
        Bundle extras = getIntent().getExtras();
        sid = extras.getString("sid");
        name= findViewById(R.id.textView4);
        phone= findViewById(R.id.phone1);
        email_address= findViewById(R.id.textView11);
        batch= findViewById(R.id.textView8);
        gpa= findViewById(R.id.textView21);
        degree=findViewById(R.id.textView5);
        experience= findViewById(R.id.textView10);
        skills= findViewById(R.id.textView14);
        showStudent();
        Button but = findViewById(R.id.button12);
        builder = new AlertDialog.Builder(this);
        but.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                builder.setMessage("Do you want to Notify him/her about this job ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                i.putExtra("name", sid);
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


    }

    private void showStudent() {
        sRef.child(sid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                student stud = dataSnapshot.getValue(student.class);
                if(stud==null){}
                else {
                    Log.d(TAG, "User name: " + stud.getName() + ", email " + stud.getEmail());

                    name.setText(stud.getName());
                    gpa.setText(stud.getGpa());
                    batch.setText(stud.getBatch());
                    degree.setText(stud.getDegree());
                    experience.setText(stud.getExperience());
                    email_address.setText(stud.getEmail());
                    phone.setText(stud.getPhoneNo());
                    skills.setText(stud.getSkills());
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
