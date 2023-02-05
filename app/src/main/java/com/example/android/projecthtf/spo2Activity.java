package com.example.android.projecthtf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.projecthtf.MainActivity;
import com.example.android.projecthtf.MyAdapter;
import com.example.android.projecthtf.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class spo2Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<User> list;
    DatabaseReference databaseReference;
    MyAdapter adapter;
    Query tempQuery;
    Button refreshBtn;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(spo2Activity.this, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spo2);
        getSupportActionBar().setTitle("SPO2 order");
        recyclerView =findViewById(R.id.recView);
        refreshBtn=findViewById(R.id.refreshBtn);
        databaseReference= FirebaseDatabase.getInstance().getReference("users");
        tempQuery=databaseReference.orderByChild("spo2");
        list=new ArrayList<>();
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(spo2Activity.this));
        adapter=new MyAdapter(this,list);
        recyclerView.setAdapter(adapter);

        tempQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    User user=dataSnapshot.getValue(User.class);
//                    Log.v("user",String.valueOf(tempQuery));
                    list.add(user);
                }
                //Collections.reverse(list);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });

    }
}