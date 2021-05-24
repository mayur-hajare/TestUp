package com.myur.testup;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.InputDevice;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Details extends AppCompatActivity {

    ImageView imageView;
    TextView textView,des,details;
    EditText strip,packet;
    DatabaseReference databaseReference;
    ImageButton cartButton;
    private static final String orders = "example.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Products");

        imageView = findViewById(R.id.rImageView);
        textView = findViewById(R.id.title);
        des = findViewById(R.id.rDes);
        cartButton = findViewById(R.id.cartButton);
        strip = findViewById(R.id.editText3);
        packet = findViewById(R.id.editText4);
        details = findViewById(R.id.details);



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

                    cartButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String strips=strip.getText().toString();
                            String packets=packet.getText().toString();
                            String titles=textView.getText().toString();
                            String detail=titles+'\n'+strips+'\n'+packets;
                            String info="";
                            info=info+'\n'+detail;
                            details.setText(detail);
                          /*  Intent intent = new Intent(Details.this, DisplayImagesActivity.class);
                            intent.putExtra(Intent.EXTRA_TEXT, info);
                            startActivity(intent);*/

                            //Changes Done for save file
                            FileOutputStream fos = null;
                            try {
                                fos = openFileOutput(orders, MODE_APPEND);
                                fos.write(info.getBytes());

                                //file Open
                              /*  String path;
                                path="/data/user/0/com.myur.testup/files/example.txt";
                                File yourFile = new File( path );*/
                                Context context = getApplicationContext();
                                FileInputStream fis = context.openFileInput("example.txt");
                                InputStreamReader isr = new InputStreamReader(fis);
                                BufferedReader bufferedReader = new BufferedReader(isr);
                                StringBuilder sb = new StringBuilder();
                                String line;
                                while ((line = bufferedReader.readLine()) != null) {
                                    sb.append('\n'+line);
                                }
                                Toast.makeText(Details.this, "Cart Item"+'\n'+sb, Toast.LENGTH_LONG).show();


                                Toast.makeText(Details.this, "Saved to " + getFilesDir() + "/" + orders,
                                        Toast.LENGTH_LONG).show();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                if (fos != null) {
                                    try {
                                        fos.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }



                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }

}