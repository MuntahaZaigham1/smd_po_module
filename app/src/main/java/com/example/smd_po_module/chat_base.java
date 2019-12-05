package com.example.smd_po_module;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.smd_po_module.classes.message;
import com.example.smd_po_module.classes.msg_data;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class chat_base extends AppCompatActivity {

    EditText editText;
    private View context;
    ArrayAdapter<String> arrayAdapter;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mRef = database.getReference("Message");
    //get u1 from shared preference it is going to send message
    //get u2 from the page it was called thru bundle it is recieving the message
    ArrayList<message> chat=new ArrayList<>();
    ArrayList<message> data;
    ArrayList<message> extractData;

    String s_uid="L164329";
    String s_name="Abiha";
    String r_uid="L164387";
    String r_name="Muntaha";
    msg_data msg_data1=new msg_data();
    ArrayList<String> chats = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_base);
        ListView chatList=(ListView)findViewById(R.id.messages_view);
       // Bundle extras = getIntent().getExtras();
       // r_uid = extras.getString("r_uid");
      //  r_name = extras.getString("r_name");
        data = new ArrayList<message>();

        extractData = new ArrayList<message>();


        getchats(s_uid,s_name);

        // Create The Adapter with passing ArrayList as 3rd parameter
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, chats);

        // Set The Adapter
        chatList.setAdapter(arrayAdapter);
        editText=findViewById(R.id.editText);
        ImageButton b1=findViewById(R.id.send);
        b1.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                sendMessage(context);

            }
        });


    }
    void getchats(String s_uid, String s_name)
    {
        Log.e("here","here");

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                // Result will be holded Here
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    data.add(dsp.getValue(message.class)); //add result into array list
                    Log.e("here2","here2");
                    Log.e("data:",data.get(0).getMsg());



                }
                for(int i=0;i<data.size();i++){
                    if((data.get(i).getS_uid().equals(s_uid)&& data.get(i).getS_name().equals(s_name) )
                            || (data.get(i).getR_name().equals(s_name) && data.get(i).getR_uid().equals(s_uid)) ){
                        runOnUiThread(new Runnable() {
                            public void run() {
                                arrayAdapter.notifyDataSetChanged();
                            }
                        });
                        chats.add(data.get(i).getS_name()+"  :  "+data.get(i).getMsg());
                        //chats.add(data.get(i).getR_name()+"  :  "+data.get(i).getMsg());

                        extractData.add(data.get(i));
                        Log.e("data:",chats.get(0));
                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e(TAG, "Failed to read user", error.toException());

            }

        });


    }



    public void sendMessage(View view) {
        String message = editText.getText().toString();
        if (message.length() > 0) {
        //    scaledrone.publish("observable-room", message);
            runOnUiThread(new Runnable() {
                public void run() {
                    arrayAdapter.notifyDataSetChanged();
                }
            });
            String name=storeMsg(editText.getText().toString());
            chats.add(name+"  :  "+editText.getText().toString());
            editText.getText().clear();
            //chats.add("PO-1: I need to implement it further ");

        }
    }

    private String storeMsg(String msg) {
        message mes;
        if(!(TextUtils.isEmpty(msg))){
            String id=mRef.push().getKey();

            mes=new message(id,s_uid,s_name,r_uid,r_name,msg);

            mRef.child(id).setValue(mes);
            Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show();
            return mes.getS_name();
        }
        else{
            Toast.makeText(this, "Enter  message first..", Toast.LENGTH_SHORT).show();
            return null;
        }
    }


}
