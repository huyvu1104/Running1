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

public class ShowInfoActivity extends AppCompatActivity implements View.OnClickListener, ValueEventListener{
    private DatabaseReference DBref;
    private TextView ageTV;
    private TextView heightTV;
    private TextView weightTV;
    private TextView tokenTV;
    User u;
    FirebaseUser current;
    FirebaseAuth auth;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        DBref = FirebaseDatabase.getInstance().getReference();
        Button inputBtn = findViewById(R.id.inputData);
        inputBtn.setOnClickListener((View.OnClickListener) this);

        heightTV = findViewById(R.id.showHeight);
        weightTV = findViewById(R.id.showWeight);
        ageTV = findViewById(R.id.showAge);
        tokenTV = findViewById(R.id.showToken);
        current = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseUser current = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference DBref = FirebaseDatabase.getInstance().getReference();

        DBref.child("users").child(current.getUid()).addListenerForSingleValueEvent(this);
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
        DBref.removeEventListener(this);

    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        u = (User) dataSnapshot.getValue(User.class);
        ageTV.setText(""+u.getAge());
        weightTV.setText(""+u.getWeight());
        heightTV.setText(""+u.getHeight());
        tokenTV.setText(""+u.getToken());
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
