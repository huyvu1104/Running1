package com.example.running1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Navigation_header extends AppCompatActivity {


    FirebaseUser current;
    TextView gmail;
    FirebaseAuth auth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.navigation_header);
        current = FirebaseAuth.getInstance().getCurrentUser();

        gmail = (TextView) findViewById (R.id.GmailUser);
        gmail.setText (current.getEmail() +" ");
     //   Log.d ("1111", current.getEmail () +"");
    }
}
