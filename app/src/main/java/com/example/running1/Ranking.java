package com.example.running1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Ranking extends AppCompatActivity implements ValueEventListener {
    ListView ListRanking;
    DatabaseReference dataRef;
    User u;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_ranking);
      //  List<User> image_details = getListData ();
       // ListRanking = (ListView)  findViewById (R.id.listRanking);// ListRanking.setAdapter(new ListDataRanking(this, image_details));
//        ArrayList<User> Username  = new ArrayList<User> ();
//        Username.add (new User ("tranvande",1000));
//        Username.add (new User ("phamvanthanh",1200));

        dataRef = FirebaseDatabase.getInstance().getReference().child("users").getRef();
        dataRef.child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(this);
//        ArrayAdapter<String> list = new ArrayAdapter<String> (this,android.R.layout.simple_list_item_1,Username);
//        ListRanking.setAdapter (list);
        Query queryRef = dataRef.orderByChild("users").limitToLast(100);
        queryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    u=postSnapshot.getValue(User.class);
                    Log.d("test"," values is " + u.getId()  + " " + u.getTotal());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
