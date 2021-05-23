package com.myur.testup;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {

    ImageView imageView;
    TextView textView,des;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Products");

        imageView = findViewById(R.id.rImageView);
        textView = findViewById(R.id.title);
        des = findViewById(R.id.rDes);

        String ItemKey = getIntent().getStringExtra("ItemKey");

        databaseReference.child(ItemKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    String title = snapshot.child("title").getValue().toString();
                    String image = snapshot.child("image").getValue().toString();
                    String descrip = snapshot.child("des").getValue().toString();

                    Picasso.get().load(image).into(imageView);
                    textView.setText(title);
                    des.setText(descrip);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}