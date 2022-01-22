package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class payment extends AppCompatActivity {
    String name1,pin1,phone1,trm,gett;
    private Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        confirm = findViewById(R.id.buttonpay1);

        Intent i = getIntent();
        phone1 = i.getStringExtra("PHONE");
        pin1 = i.getStringExtra("PIN");
        name1 = i.getStringExtra("NAME");
        trm = i.getStringExtra("TRM");
        gett = i.getStringExtra("GETT");

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference Users = database.getReference("Orders").child(trm).child(phone1).child("Table");
                Users.child("Table").setValue("Table "+gett);
                Intent i = new Intent(payment.this,transition.class);
                startActivity(i);
                finish();
            }
        });

    }
}