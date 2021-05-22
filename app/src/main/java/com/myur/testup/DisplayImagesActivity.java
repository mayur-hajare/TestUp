package com.myur.testup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.load.model.ModelLoader;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DisplayImagesActivity extends AppCompatActivity {

    RecyclerView mrecyclerView,mrecyclerViewv;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference,referencenew;

    CheckNetworkListner checkNetworkListner=new CheckNetworkListner();

    FirebaseRecyclerOptions<Member>options;
    FirebaseRecyclerOptions<Member2>option;
    FirebaseRecyclerAdapter<Member,ViewHolder>adapter;
    FirebaseRecyclerAdapter<Member2,ViewHolder>adapterNew;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        IntentFilter filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(checkNetworkListner,filter);


        setContentView(R.layout.activity_display_images);
        mrecyclerViewv=findViewById(R.id.recyclerViewv);
        mrecyclerView=findViewById(R.id.recyclerViewh);
        mrecyclerViewv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mrecyclerViewv.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mrecyclerView.setHasFixedSize(true);
        reference=FirebaseDatabase.getInstance().getReference().child("Products");
        referencenew=FirebaseDatabase.getInstance().getReference().child("NewData");
        LoadData();
        LoadNewData();

    }
    @Override
    protected void onStart() {
    IntentFilter filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    registerReceiver(checkNetworkListner,filter);

        super.onStart();
    }

    private void LoadNewData() {
        option=new FirebaseRecyclerOptions.Builder<Member2>().setQuery(referencenew,Member2.class).build();
        adapterNew=new FirebaseRecyclerAdapter<Member2, ViewHolder>(option) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Member2 model) {
                holder.textView.setText(model.getTitle());
                Picasso.get().load(model.getImage()).into(holder.imageView);
            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.image,parent,false);

                return new ViewHolder(v);

            }
        };adapterNew.startListening();
        mrecyclerView.setAdapter(adapterNew);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mrecyclerView.setLayoutManager(layoutManager);

    }

    private void LoadData() {
        options=new  FirebaseRecyclerOptions.Builder<Member>().setQuery(reference,Member.class).build();
        adapter=new FirebaseRecyclerAdapter<Member, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Member model) {
                holder.textView.setText(model.getTitle());
                Picasso.get().load(model.getImage()).into(holder.imageView);

            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.image,parent,false);

                return new ViewHolder(v);


            }
        };
        adapter.startListening();
        mrecyclerViewv.setAdapter(adapter);
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mrecyclerViewv.setLayoutManager(staggeredGridLayoutManager);
    }

    @Override
    protected void onStop() {
        unregisterReceiver(checkNetworkListner);
        super.onStop();
    }


}