package com.myur.testup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class BuyActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    String uid, Store;
    Button confirm;
    EditText store;
    TextView order_list, mailid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        order_list = findViewById(R.id.order_list);
        mailid = findViewById(R.id.userMail);
        confirm = findViewById(R.id.confirm_button);
        store = findViewById(R.id.store_name);

        auth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        String text = intent.getStringExtra(Intent.EXTRA_TEXT);


        order_list.setText(text);

        loadUserInfo();
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Store=store.getText().toString()+'\n'+order_list.getText().toString();

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, Store);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

            }
        });


    }

    private void loadUserInfo() {
        FirebaseUser user = auth.getCurrentUser();

        String userMail = user.getEmail();

        if (user.getEmail() != null) {
            mailid.setText(userMail);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (auth.getCurrentUser() == null) {
            finish();
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }
    }
}