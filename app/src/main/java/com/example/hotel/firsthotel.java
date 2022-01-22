package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class firsthotel extends AppCompatActivity {
    String name1,email1,pin1,phone1,address1;
    private Button upt,table,book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firsthotel);
        Intent i = getIntent();
        name1 = i.getStringExtra("NAME");
        pin1 = i.getStringExtra("PIN");
        phone1 = i.getStringExtra("PHONE");
        upt = findViewById(R.id.buttonfirsthotel1);
        table = findViewById(R.id.buttonfirsthotel2);
        book = findViewById(R.id.buttonfirsthotel3);
        upt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(firsthotel.this,Foodmenu.class);
                i.putExtra("PHONE",phone1);
                i.putExtra("PIN",pin1);
                startActivity(i);
            }
        });

        table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(firsthotel.this,table_status.class);
                i.putExtra("PHONE",phone1);
                i.putExtra("PIN",pin1);
                startActivity(i);
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(firsthotel.this,bookings.class);
                i.putExtra("PHONE",phone1);
                i.putExtra("PIN",pin1);
                startActivity(i);
            }
        });


    }
}