package com.myur.testup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.myur.testup.Adapters.ItemAdapter;
import com.myur.testup.Adapters.ViewHolder;

public class DashboardActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseDatabase=FirebaseDatabase.getInstance();
       databaseReference=firebaseDatabase.getReference("Data");

    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<ItemAdapter,ViewHolder>firebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<ItemAdapter, ViewHolder>(
                        ItemAdapter.class,
                        R.layout.image,
                        ViewHolder.class,
                        databaseReference) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, ItemAdapter itemAdapter, int i) {

                      viewHolder.setdetails(getApplicationContext(),itemAdapter.getTitle(),itemAdapter.getImage());
                    }
                };
            recyclerView.setAdapter(firebaseRecyclerAdapter);

        }

    }