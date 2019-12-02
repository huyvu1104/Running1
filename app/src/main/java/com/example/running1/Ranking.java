package com.example.running1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Ranking extends AppCompatActivity {
    ListView ListRanking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_ranking);
        List<User> image_details = getListData ();
        ListRanking = (ListView)  findViewById (R.id.listRanking);
        ListRanking.setAdapter(new ListDataRanking(this, image_details));
//        ArrayList<User> Username  = new ArrayList<User> ();
//        Username.add (new User ("tranvande",1000));
//        Username.add (new User ("phamvanthanh",1200));


//        ArrayAdapter<String> list = new ArrayAdapter<String> (this,android.R.layout.simple_list_item_1,Username);
//        ListRanking.setAdapter (list);

    }
    public List<User> getListData(){
        List<User> users = new ArrayList<User> ();
        User tranvande = new User ("tranvande",1200);
        User tranvande1 = new User ("phamvanthanh",1200);
        User tranvande2 = new User ("nguyenhuyphuc",1200);
        users.add (tranvande);
        users.add (tranvande2);
        users.add (tranvande1);
        return  users;
    }
}
