package com.myur.testup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myur.testup.Models.Users;

public class SignUpActivity extends AppCompatActivity {

    EditText etname,etpass,etmail,etphone;
    Button SignUP;
    ProgressDialog progressDialog;
    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etmail=findViewById(R.id.Email);
        etname=findViewById(R.id.Name);
        etpass=findViewById(R.id.Password);
        etphone=findViewById(R.id.PhoneNumber);
        SignUP=findViewById(R.id.signup);

        progressDialog=new ProgressDialog(SignUpActivity.this);
        progressDialog.setTitle("Creating Your Account");
        progressDialog.setMessage("We Are Creating Your Account");

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        myRef.setValue("All Is We ll!");

        // Read from the database
        ValueEventListener valueEventListener = myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);

                Toast.makeText(SignUpActivity.this,value,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
                Toast.makeText(SignUpActivity.this,"Failed to read value.",Toast.LENGTH_LONG).show();
            }
        });

        SignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                mAuth.createUserWithEmailAndPassword(etmail.getText().toString(),etpass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                        if(task.isSuccessful()){
                            Users user=new Users(etmail.getText().toString(),etpass.getText().toString(),etphone.getText().toString());
                            String id=task.getResult().getUser().getUid();
                            database.getReference().child("Users").child(id).setValue(user);

                            Toast.makeText(SignUpActivity.this,"Successful",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(SignUpActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }

                    }

                });

            }
        });



        TextView SignIn=findViewById(R.id.SignIn);
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}