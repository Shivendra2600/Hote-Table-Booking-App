package com.example.hotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class bookings extends AppCompatActivity {
    String phone1,pin1,trm;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);

        lv = findViewById(R.id.lvbook1);

        Intent i = getIntent();
        phone1 = i.getStringExtra("PHONE");
        pin1 = i.getStringExtra("PIN");

        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.list_item,list);
        lv.setAdapter(adapter);


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Order List").child(phone1);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    list.add(dataSnapshot.getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String text = ((TextView) view).getText().toString();
                trm = text.substring(1,11);
                Intent i = new Intent(bookings.this,order_details.class);
                i.putExtra("PIN",pin1);
                i.putExtra("PHONE",phone1);
                i.putExtra("TRM",trm);
                startActivity(i);

            }
        });



    }
}