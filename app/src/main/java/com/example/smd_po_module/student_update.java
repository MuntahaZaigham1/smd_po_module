package com.example.smd_po_module;

import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class student_update extends AppCompatActivity {
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_update);

        EditText editText = findViewById(R.id. editText4 ) ;
        editText.setFilters( new InputFilter[]{ new ValidateFilter()}) ;
        EditText editText3 = findViewById(R.id. editText9 ) ;
        editText3.setFilters( new InputFilter[]{ new num()}) ;
        EditText editText4 = findViewById(R.id. editText16 ) ;
        editText4.setFilters( new InputFilter[]{ new ValidateFilterNum()}) ;
        EditText editText5 = findViewById(R.id. editText14 ) ;
        editText5.setFilters( new InputFilter[]{ new ValidateFilterNum()}) ;
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            EditText emailId = findViewById(R.id.editText5);
            @Override
            public void onClick(View v) {
                if(emailId.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"enter email address",Toast.LENGTH_SHORT).show();
                }else {
                    if (emailId.getText().toString().trim().matches(emailPattern)) {
                        Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        EditText editText1 = findViewById(R.id. editText6 ) ;
        editText1.setFilters( new InputFilter[]{ new ValidateFilterNum()}) ;
        EditText editText12 = findViewById(R.id. editText12 ) ;
        if(editText12.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Phone is required", Toast.LENGTH_SHORT).show();
        }

        EditText editText2 = findViewById(R.id. editText7 ) ;
        editText2.setFilters( new InputFilter[]{ new ValidateFilter()}) ;
    }
}
