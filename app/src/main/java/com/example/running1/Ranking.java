package com.example.running1;

import android.content.Context;
import android.os.Bundle;
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

public class Ranking extends AppCompatActivity implements ValueEventListener {
    ListView ListRanking;
    DatabaseReference dataRef;
    User u;
    RankingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        dataRef = FirebaseDatabase.getInstance().getReference().child("users").getRef();
        dataRef.addValueEventListener(this);

        ListView listviewRanking = findViewById(R.id.list_ranking);
        adapter = new RankingAdapter();
        adapter.setContext(this);
        listviewRanking.setAdapter(adapter);

    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        List<User> data = new ArrayList<>();
        for (DataSnapshot d : dataSnapshot.getChildren()) {
            data.add(d.getValue(User.class));
        }
        adapter.setData(data);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}

class RankingAdapter extends BaseAdapter {
    Context context;
    List<User> data;

    RankingAdapter(){
        data = new ArrayList<>();
    }
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        notifyDataSetChanged();
        this.data = data;
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
        if(view!=null){
            viewHolder = (ViewHolder) view.getTag();
        } else {
            view = View.inflate(getContext(),R.layout.item_rank,null);
            viewHolder.user = view.findViewById(R.id.rank_item_id);
            viewHolder.total = view.findViewById(R.id.rank_item_total);
            view.setTag(viewHolder);
        }

        viewHolder.user.setText(data.get(i).getId());
        viewHolder.total.setText(data.get(i).getTotal() + "");

        return null;
    }

    class ViewHolder {
        TextView user;
        TextView total;

        public TextView getUser() {
            return user;
        }

        public void setUser(TextView user) {
            this.user = user;
        }

        public TextView getTotal() {
            return total;
        }

        public void setTotal(TextView total) {
            this.total = total;
        }
    }

}