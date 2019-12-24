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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class BeginInputActivity extends AppCompatActivity implements View.OnClickListener {
    public User u;
    FirebaseUser current;
    EditText age;
    EditText height;
    EditText weight;
    EditText id;
    String timeStamp;
    DatabaseReference DBref;
    HashMap<String,Integer> daily;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        timeStamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
        setContentView(R.layout.activity_begin_input);
        Button acceptBtn = findViewById(R.id.begin_accept);
        acceptBtn.setOnClickListener(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View view) {
        age = findViewById(R.id.begin_input_age);
        height = findViewById(R.id.begin_input_height);
        weight = findViewById(R.id.begin_input_weight);
        id = findViewById(R.id.begin_input_id);
        switch (view.getId()) {
            case R.id.begin_accept:

                current = FirebaseAuth.getInstance().getCurrentUser();

                if (current == null) return;
                u = new User();
                String strId=id.getText().toString();
                u.setId(strId);
                u.setAge(Integer.parseInt(""+age.getText()));
                String strHeight = height.getText().toString();
                u.setHeight(Integer.parseInt(strHeight));
                String strWeight = weight.getText().toString();
                u.setWeight(Integer.parseInt(strWeight));
                u.setTotal(0);
                daily=new HashMap<String,Integer>();
                daily.put(timeStamp,0);
                u.setDaily(daily);
                DBref = FirebaseDatabase.getInstance().getReference();
                DBref.child("users").child(current.getUid()).setValue(u);
                Intent transform = new Intent(this, LoginActivity.class);
                startActivity(transform);
        }
    }

}