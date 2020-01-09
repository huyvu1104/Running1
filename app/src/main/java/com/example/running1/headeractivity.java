package com.example.running1;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class headeractivity extends AppCompatActivity  {

   private TextView User;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_header);

        mAuth = FirebaseAuth.getInstance();
        User  = (TextView) findViewById (R.id.GmailUser);
        Log.d ("user", mAuth.getCurrentUser ().getEmail ());
        User.setText (mAuth.getCurrentUser ().getEmail ()+" ");





    }}

