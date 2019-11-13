package com.example.running1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InputInfoActivity extends AppCompatActivity implements View.OnClickListener {
    public User u;
    FirebaseUser current;
    EditText age;
    EditText height;
    EditText weight;
    void updateUI() {
        age = findViewById(R.id.inputAge);
        height=findViewById(R.id.inputHeight);
        weight=findViewById(R.id.inputWeight);
        current = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseUser current = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference DBref = FirebaseDatabase.getInstance().getReference();

        DBref.child("users").child(current.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                u = (User) dataSnapshot.getValue(User.class);
                age.setText(u.getAge());
                weight.setText(u.getWeight());
                height.setText(u.getHeight());
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
        acceptBtn.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View view) {
        age = findViewById(R.id.inputAge);
        height=findViewById(R.id.inputHeight);
        weight=findViewById(R.id.inputWeight);
        switch (view.getId()) {
            case R.id.accept:
                FirebaseUser current = FirebaseAuth.getInstance().getCurrentUser();
                if(current==null) return;
                u = new User();
                u.setUserID(current.getUid());
                String strAge = age.getText().toString();
                u.setAge(Integer.parseInt(strAge));
                String strHeight=height.getText().toString();
                u.setHeight(Integer.parseInt(strHeight));
                String strWeight=weight.getText().toString();
                u.setWeight(Integer.parseInt(strWeight));

                DatabaseReference DBref = FirebaseDatabase.getInstance().getReference();
                DBref.child("users").child(current.getUid()).setValue(u);
                Intent transform = new Intent(this, ShowInfoActivity.class);
                startActivity(transform);
                break;
        }
    }
}
