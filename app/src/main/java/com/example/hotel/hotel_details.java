package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class hotel_details extends AppCompatActivity {
    String name1,pin1,phone1,trm;
    private Button menu,table,crt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_details);

        menu = findViewById(R.id.buttonhd1);
        table = findViewById(R.id.buttonhd2);
        crt = findViewById(R.id.buttonhd3);

        Intent i = getIntent();
        phone1 = i.getStringExtra("PHONE");
        pin1 = i.getStringExtra("PIN");
        name1 = i.getStringExtra("NAME");
        trm = i.getStringExtra("TRM");

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(hotel_details.this, com.example.hotel.menu.class);
                i.putExtra("NAME",name1);
                i.putExtra("PIN",pin1);
                i.putExtra("PHONE",phone1);
                i.putExtra("TRM",trm);
                startActivity(i);
            }
        });

        crt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(hotel_details.this,bill.class);
                i.putExtra("NAME",name1);
                i.putExtra("PIN",pin1);
                i.putExtra("PHONE",phone1);
                i.putExtra("TRM",trm);
                startActivity(i);
            }
        });

    }
}