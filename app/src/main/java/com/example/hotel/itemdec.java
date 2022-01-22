package com.example.hotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class itemdec extends AppCompatActivity {
    String name1,pin1,phone1,trm,food,decdb,pricedb,pricetrm,comb,Neto,Neto1,totdbtrm,s1,s2;
    private EditText dec,qty;
    private TextView price;
    private Button add,val;
    int pricedb2,prtrm,neto,totdb,totlen,qtyst,p1,q1,cal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemdec);

        dec = findViewById(R.id.etid1);
        price = findViewById(R.id.tvid1);
        add = findViewById(R.id.buttonid1);
        val = findViewById(R.id.buttonid2);
        qty = findViewById(R.id.etid2);


        Intent i = getIntent();
        phone1 = i.getStringExtra("PHONE");
        pin1 = i.getStringExtra("PIN");
        name1 = i.getStringExtra("NAME");
        trm = i.getStringExtra("TRM");
        food = i.getStringExtra("FOOD");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference Users = database.getReference("MENU").child(trm).child(food);
        Users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                decdb = snapshot.child("Description").getValue(String.class);
                pricedb = snapshot.child("Price").getValue(String.class);
                pricedb2 = pricedb.length();
                pricetrm = pricedb.substring(0,pricedb2-2);
                price.setText(pricedb);
                dec.setText(decdb);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////


        val.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Qty = qty.getText().toString();
                p1 = Integer.parseInt(pricetrm);
                q1 = Integer.parseInt(Qty);
                cal = p1*q1;
                s1 = String.valueOf(cal);
                s2 = s1+"Rs";
                price.setText(s2);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference cart = database.getReference("Cart").child(phone1);
                DatabaseReference orders_list = database.getReference("Order List").child(trm);
                DatabaseReference orders = database.getReference("Orders").child(trm).child(phone1).child("Food");
                orders_list.child("("+phone1+")"+" "+name1).setValue("("+phone1+")"+" "+name1);
                cart.child(food+"       "+s2).setValue(food+"       "+s2);
                orders.child(food+"       "+s2).setValue(food+"       "+s2);

                DatabaseReference tot = database.getReference("Total").child(phone1);
                tot.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String dbt = snapshot.child("Value").getValue(String.class);

                        if (dbt == null){

                            DatabaseReference ttl = database.getReference("Total").child(phone1);
                            ttl.child("Value").setValue(s2);
                            Toast.makeText(getApplicationContext(),"Item Added to cart",Toast.LENGTH_LONG).show();
                        }

                        else {

                            prtrm = Integer.parseInt(pricetrm);
                            totlen = dbt.length();
                            totdbtrm = dbt.substring(0,totlen-2);
                            totdb = Integer.parseInt(totdbtrm);
                            neto = totdb+cal;
                            Neto = String.valueOf(neto);
                            Neto1 = neto+"Rs";
                            DatabaseReference ttl = database.getReference("Total").child(phone1);
                            ttl.child("Value").setValue(Neto1);
                            Toast.makeText(getApplicationContext(),"Item Added to cart",Toast.LENGTH_LONG).show();

                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

    }
}