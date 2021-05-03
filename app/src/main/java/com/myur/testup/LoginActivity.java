package com.myur.testup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.myur.testup.Models.Users;

public class LoginActivity extends AppCompatActivity {

    Button SignIn;
    EditText etmail,etpass,etphone;
    ProgressDialog progressDialog;
    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etmail=findViewById(R.id.etEmail);
        etpass=findViewById(R.id.etPassword);

        SignIn=findViewById(R.id.SignIn);

        progressDialog=new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Login.....");
        progressDialog.setMessage("Just a minute...");

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                mAuth.signInWithEmailAndPassword(etmail.getText().toString(),etpass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        Intent intent=new Intent(LoginActivity.this,DashboardActivity.class);
                        startActivity(intent);

                    }
                });
            }
        });


                TextView SignUP=findViewById(R.id.SignUP);
                SignUP.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(LoginActivity.this,SignUpActivity.class);
                        startActivity(intent);

                    }
                });

    }
}