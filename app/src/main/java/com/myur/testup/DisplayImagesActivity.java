package com.myur.testup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DisplayImagesActivity extends AppCompatActivity {

    RecyclerView mrecyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_images);

        mrecyclerView=findViewById(R.id.recyclerView);
        mrecyclerView.setHasFixedSize(true);

        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));


        firebaseDatabase=FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference("Data");


    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Member,ViewHolder>firebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<Member, ViewHolder>(Member.class,
                R.layout.image,
                        ViewHolder.class,
                        reference
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Member member, int i) {
                        viewHolder.setdetails(getApplicationContext(),member.getTitle(),member.getImage());


                    }
                };

            mrecyclerView.setAdapter(firebaseRecyclerAdapter);
    }


}