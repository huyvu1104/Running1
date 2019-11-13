package com.example.running1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowInfoActivity extends AppCompatActivity implements View.OnClickListener{
    private DatabaseReference DBref;
    private TextView ageTV;
    private TextView heightTV;
    private TextView weightTV;
    private TextView tokenTV;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        DBref = FirebaseDatabase.getInstance().getReference();
        Button inputBtn = findViewById(R.id.inputData);
        inputBtn.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.inputData:

                Intent transform = new Intent(this,InputInfoActivity.class);
                startActivity(transform);
                break;
        }
    }
}
