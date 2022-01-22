package com.example.hotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class bboktable extends AppCompatActivity {
    String name1,pin1,phone1,trm,gett;
    private TextView at;
    private EditText bt;
    private Button pay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bboktable);

        at = findViewById(R.id.tvbt1);
        bt = findViewById(R.id.etbt1);
        pay = findViewById(R.id.buttonbt1);


        Intent i = getIntent();
        phone1 = i.getStringExtra("PHONE");
        pin1 = i.getStringExtra("PIN");
        name1 = i.getStringExtra("NAME");
        trm = i.getStringExtra("TRM");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference Users = database.getReference("Table Status").child(trm);
        Users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String tables = snapshot.child("Free Tables").getValue(String.class);
                at.setText(tables);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String gett = bt.getText().toString();
            Intent i = new Intent(bboktable.this,payment.class);
            i.putExtra("NAME",name1);
            i.putExtra("PIN",pin1);
            i.putExtra("PHONE",phone1);
            i.putExtra("TRM",trm);
            i.putExtra("GETT",gett);
            startActivity(i);
            }
        });




    }
}