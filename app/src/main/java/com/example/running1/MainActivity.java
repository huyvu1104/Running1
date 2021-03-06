package com.example.running1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    protected void onCreate(Bundle savedInstanceState) {
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
//        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
//            Intent intent = new Intent(this, MainActivity2.class);
//            startActivity(intent);
//            //finish();
//        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginview);
        Button loginBtn = findViewById(R.id.login_transfer);
        Button signupBtn = findViewById(R.id.signup_transfer);

        loginBtn.setOnClickListener(this);
        signupBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_transfer:
                loginTransferClick();
                break;
            case R.id.signup_transfer:
                SignUpTransferClick();
                break;
        }
    }

    public void loginTransferClick() {
        Intent transform = new Intent(this, LoginActivity.class);
        startActivity(transform);
    }

    public void SignUpTransferClick() {
        Intent transform = new Intent(this, RegistrationActivity.class);
        startActivity(transform);
    }
}