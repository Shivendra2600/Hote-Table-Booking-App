package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class table_status extends AppCompatActivity {
    private EditText ts;
    private Button save;
    String phone1,pin1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_status);

        ts= findViewById(R.id.etts1);
        save = findViewById(R.id.buttonts1);

        Intent i = getIntent();
        phone1 = i.getStringExtra("PHONE");
        pin1 = i.getStringExtra("PIN");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = ts.getText().toString();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference Users = database.getReference("Table Status").child(phone1);
                Users.child("Free Tables").setValue(status);
                Toast.makeText(getApplicationContext(),"Table Status Updated",Toast.LENGTH_LONG).show();
            }
        });


    }
}