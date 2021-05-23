package com.myur.testup;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class DisplayImagesActivity extends AppCompatActivity {

    Common common;
    boolean isconnected = false;
    RecyclerView mrecyclerView, mrecyclerViewv;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference, referencenew;


    FirebaseRecyclerOptions<Member> options;
    FirebaseRecyclerOptions<Member2> option;
    FirebaseRecyclerAdapter<Member, ViewHolder> adapter;
    FirebaseRecyclerAdapter<Member2, ViewHolder> adapterNew;


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        common = new Common(DisplayImagesActivity.this);
        isconnected = common.isInternetConnected();
        if (!isconnected) {
            AlertDialog alertDialog = new AlertDialog.Builder(DisplayImagesActivity.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setCancelable(true);
            alertDialog.setMessage("Check Your Internet Connection..!");
            alertDialog.setIcon(R.drawable.ic_baseline_error);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(DisplayImagesActivity.this,"Make sure Your Connection On...",Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }


        setContentView(R.layout.activity_display_images);
        mrecyclerViewv = findViewById(R.id.recyclerViewv);
        mrecyclerView = findViewById(R.id.recyclerViewh);
        mrecyclerViewv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mrecyclerViewv.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mrecyclerView.setHasFixedSize(true);
        reference = FirebaseDatabase.getInstance().getReference().child("Products");
        referencenew = FirebaseDatabase.getInstance().getReference().child("NewData");
        LoadData();
        LoadNewData();

    }


    private void LoadNewData() {
        option = new FirebaseRecyclerOptions.Builder<Member2>().setQuery(referencenew, Member2.class).build();
        adapterNew = new FirebaseRecyclerAdapter<Member2, ViewHolder>(option) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Member2 model) {
                holder.textView.setText(model.getTitle());
                Picasso.get().load(model.getImage()).into(holder.imageView);
            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image, parent, false);

                return new ViewHolder(v);

            }
        };
        adapterNew.startListening();
        mrecyclerView.setAdapter(adapterNew);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mrecyclerView.setLayoutManager(layoutManager);

    }

    private void LoadData() {
        options = new FirebaseRecyclerOptions.Builder<Member>().setQuery(reference, Member.class).build();
        adapter = new FirebaseRecyclerAdapter<Member, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Member model) {
                holder.textView.setText(model.getTitle());
                Picasso.get().load(model.getImage()).into(holder.imageView);

            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image, parent, false);

                return new ViewHolder(v);


            }
        };
        adapter.startListening();
        mrecyclerViewv.setAdapter(adapter);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mrecyclerViewv.setLayoutManager(staggeredGridLayoutManager);
    }

    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    protected void onStop() {

        super.onStop();
    }


}