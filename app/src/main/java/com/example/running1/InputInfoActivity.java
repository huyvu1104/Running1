package com.example.running1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;

public class InputInfoActivity extends AppCompatActivity implements View.OnClickListener, ValueEventListener, OnSuccessListener<Void> {
    public User u;
    FirebaseUser current;
    EditText age;
    EditText height;
    EditText weight;
    DatabaseReference DBref;

    void updateUI() {
        DBref = FirebaseDatabase.getInstance().getReference();
        current = FirebaseAuth.getInstance().getCurrentUser();
        age = findViewById(R.id.inputAge);
        height = findViewById(R.id.inputHeight);
        weight = findViewById(R.id.inputWeight);
        current = FirebaseAuth.getInstance().getCurrentUser();

        DBref.child("users").child(current.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                u = (User) dataSnapshot.getValue(User.class);
                age.setText(""+u.getAge());
                weight.setText(""+u.getWeight());
                height.setText(""+u.getHeight());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        Button acceptBtn = findViewById(R.id.accept);
        acceptBtn.setOnClickListener(this);
        updateUI();
    }

    @Override
    public void onClick(View view) {
        age = findViewById(R.id.inputAge);
        height = findViewById(R.id.inputHeight);
        weight = findViewById(R.id.inputWeight);
        switch (view.getId()) {
            case R.id.accept:
                current = FirebaseAuth.getInstance().getCurrentUser();
                if (current == null) return;
                DBref.child("users").child(current.getUid()).addListenerForSingleValueEvent(this);

                String strAge;
                String strHeight;
                String strWeight;

                    strAge = age.getText().toString();
                    u.setAge(Integer.parseInt(strAge));
                    strHeight = height.getText().toString();
                    u.setHeight(Integer.parseInt(strHeight));
                    strWeight = weight.getText().toString();
                    u.setWeight(Integer.parseInt(strWeight));
                    u.setToken(u.getToken());

                DBref.child("users").child(current.getUid()).setValue(u).addOnSuccessListener(this);

                break;
        }
    }


    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        u = (User) dataSnapshot.getValue(User.class);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        DBref.removeEventListener(this);
    }

    @Override
    public void onSuccess(Void aVoid) {
        Intent transform = new Intent(this, ShowInfoActivity.class);
        startActivity(transform);
        Log.d("INPUTINFO", "successs ");
    }
}
