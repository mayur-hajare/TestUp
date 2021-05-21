package com.myur.testup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

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

    RecyclerView mrecyclerView,mrecyclerViewv;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference,referencenew;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_images);

        mrecyclerView=findViewById(R.id.recyclerViewh);
        mrecyclerView.setHasFixedSize(true);

        mrecyclerViewv=findViewById(R.id.recyclerViewv);
        mrecyclerViewv.setHasFixedSize(true);


        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mrecyclerViewv.setLayoutManager(new LinearLayoutManager(this));

        firebaseDatabase=FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference("Products");
        referencenew=firebaseDatabase.getReference("NewData");

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

        FirebaseRecyclerAdapter<Member2,ViewHolder>firebaseRecyclerAdapter1=
                new FirebaseRecyclerAdapter<Member2, ViewHolder>(Member2.class,
                        R.layout.imagee,
                        ViewHolder.class,
                        referencenew
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Member2 member2, int i) {
                        viewHolder.setnewdetails(getApplicationContext(),member2.getImage());


                    }
                };

            mrecyclerView.setAdapter(firebaseRecyclerAdapter1);
           mrecyclerViewv.setAdapter(firebaseRecyclerAdapter);
            // for Horizontal RecyclerView
        LinearLayoutManager layoutManager= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mrecyclerView.setLayoutManager(layoutManager);

        StaggeredGridLayoutManager staggeredGridLayoutManager= new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mrecyclerViewv.setLayoutManager(staggeredGridLayoutManager);
    }


}