package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Foodmenu2 extends AppCompatActivity {
    private EditText name,price,desc;
    private FirebaseAuth firebaseAuth;
    private Button save;
    String phone1,pin1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodmenu2);
        Intent i = getIntent();
        phone1 = i.getStringExtra("PHONE");
        pin1 = i.getStringExtra("PIN");
        name = findViewById(R.id.etfm1);
        price = findViewById(R.id.etfm2);
        desc = findViewById(R.id.etmfm3);
        save = findViewById(R.id.btnfm21);



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = name.getText().toString();
                String price1 = price.getText().toString();
                String desc1 = desc.getText().toString();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference Users = database.getReference("MENU").child(phone1).child(name1);
                DatabaseReference food = database.getReference("Items").child(phone1);
                food.child(name1).setValue(name1);
                Users.child("Item").setValue(name1);
                Users.child("Price").setValue(price1+"Rs");
                Users.child("Description").setValue(desc1);

                Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_LONG).show();
            }
        });


    }
}