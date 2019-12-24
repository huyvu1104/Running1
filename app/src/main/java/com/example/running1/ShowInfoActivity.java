package com.example.running1;

import android.content.Intent;
import android.os.Bundle;
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

public class ShowInfoActivity extends AppCompatActivity implements View.OnClickListener, ValueEventListener{
    private TextView ageTV;
    private TextView heightTV;
    private TextView weightTV;
    private TextView idTV;
    private DatabaseReference dataRef;
    User u;
    FirebaseUser current;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Button inputBtn = findViewById(R.id.inputData);
        inputBtn.setOnClickListener((View.OnClickListener) this);

        heightTV = findViewById(R.id.showHeight);
        weightTV = findViewById(R.id.showWeight);
        ageTV = findViewById(R.id.showAge);
        idTV = findViewById(R.id.showId);
        dataRef = FirebaseDatabase.getInstance().getReference();
        current = FirebaseAuth.getInstance().getCurrentUser();
        dataRef.child("users").child(current.getUid()).addListenerForSingleValueEvent(this);
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

    @Override
    protected void onStop() {
        super.onStop();
        dataRef.removeEventListener(this);
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        u = dataSnapshot.getValue(User.class);
        ageTV.setText(""+u.getAge());
        weightTV.setText(""+u.getWeight());
        heightTV.setText(""+u.getHeight());
        idTV.setText(""+u.getId());

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
