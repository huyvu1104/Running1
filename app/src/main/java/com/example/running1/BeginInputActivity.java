package com.example.running1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BeginInputActivity extends AppCompatActivity implements View.OnClickListener {
    public User u;
    FirebaseUser current;
    EditText age;
    EditText height;
    EditText weight;
    DatabaseReference DBref;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin_input);
        Button acceptBtn = findViewById(R.id.begin_accept);
        acceptBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        age = findViewById(R.id.begin_input_age);
        height = findViewById(R.id.begin_input_height);
        weight = findViewById(R.id.begin_input_weight);
        switch (view.getId()) {
            case R.id.begin_accept:

                current = FirebaseAuth.getInstance().getCurrentUser();
                if (current == null) return;
                u = new User();

                String strAge = age.getText().toString();
                u.setAge(Integer.parseInt(strAge));
                String strHeight = height.getText().toString();
                u.setHeight(Integer.parseInt(strHeight));
                String strWeight = weight.getText().toString();
                u.setWeight(Integer.parseInt(strWeight));
                u.setToken(0);
                u.setStep(0);
                DBref = FirebaseDatabase.getInstance().getReference();
                DBref.child("users").child(current.getUid()).setValue(u);

                Intent transform = new Intent(this, LoginActivity.class);
                startActivity(transform);
        }
    }
}