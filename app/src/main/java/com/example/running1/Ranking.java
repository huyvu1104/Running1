package com.example.running1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Ranking extends AppCompatActivity {
    ListView ListRanking;
    DatabaseReference dataRef;
    Data currentData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_ranking);
        List<User> image_details = getListData ();
        ListRanking = (ListView)  findViewById (R.id.listRanking);
        ListRanking.setAdapter(new ListDataRanking(this, image_details));
        dataRef = FirebaseDatabase.getInstance().getReference();
    }
    public List<User> getListData(){
        List<User> users = new ArrayList<User> ();
        //dien vao 2 cai day a
        User tranvande = new User ("tranvande",1200);
        User tranvande1 = new User ("phamvanthanh",1300);
        User tranvande2 = new User ("nguyenhuyphuc",1500);
        users.add (tranvande);
        users.add (tranvande2);
        users.add (tranvande1);
        return  users;
    }
    private void writeNewUser( String name, int step) {


//       name = dataRef.child("users").child(name).setValue(user);
    }

}
