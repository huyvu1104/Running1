package com.example.running1;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Ranking extends AppCompatActivity {
    ListView ListRanking;
    DatabaseReference dataRef;
    User u;
    RankingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        ListRanking = findViewById(R.id.list_ranking);
        adapter = new RankingAdapter();
        adapter.setContext(this);
        ListRanking.setAdapter(adapter);

    }


}

class RankingAdapter extends BaseAdapter implements ValueEventListener {
    Context context;
    List<User> data;

    DatabaseReference dataRef;

    RankingAdapter() {
        data = new ArrayList<>();
        dataRef = FirebaseDatabase.getInstance().getReference().child("users").getRef();
        dataRef.addValueEventListener(this);
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder();
        if (view != null) {
            viewHolder = (ViewHolder) view.getTag();
        } else {
            view = View.inflate(context, R.layout.item_rank, null);
            viewHolder.user = view.findViewById(R.id.rank_item_id);
            viewHolder.total = view.findViewById(R.id.rank_item_total);
            viewHolder.stt = view.findViewById(R.id.rank_item_stt);
            view.setTag(viewHolder);
        }

        viewHolder.user.setText(data.get(i).getId());
        viewHolder.total.setText(data.get(i).getTotal() + "");
        viewHolder.stt.setText("" + (i+1));

        return view;
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        List<User> temp = new ArrayList<>();
        for (DataSnapshot d : dataSnapshot.getChildren()) {
            temp.add(d.getValue(User.class));
            Log.d("data: ", "onDataChange: " + temp.toString());

        }
        data = temp;
        notifyDataSetChanged();
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    class ViewHolder {

        TextView stt;
        TextView user;
        TextView total;

    }

}