package com.example.running1;

import android.app.ListActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListDataRanking  extends BaseAdapter {
    private List<User> listUsername;
    private LayoutInflater layoutInflater;
    private Context context;

    public ListDataRanking(List<User> listUsername, LayoutInflater layoutInflater, Context context) {
        this.listUsername = listUsername;
        this.layoutInflater = layoutInflater;
        this.context = context;
    }

    public ListDataRanking(Context aContext, List<User> listUsername) {
        this.context = aContext;
        this.listUsername = listUsername;
        layoutInflater = LayoutInflater.from (aContext);

    }

    @Override
    public int getCount() {
        return  listUsername.size ();
    }

    @Override
    public Object getItem(int position) {
        return listUsername.get (position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = layoutInflater.inflate (R.layout.layout_ranking,null);
            holder = new ViewHolder ();
            holder.Username = (TextView) convertView.findViewById (R.id.Username);
            holder.Buocchay =(TextView) convertView.findViewById (R.id.buocchay);
            convertView.setTag (holder);

        }else{
            holder = (ViewHolder) convertView.getTag ();
        }
        User user = this.listUsername.get (position);
        holder.Username.setText (user.getUsename ());
        String buocchay = Integer.toString (user.getRunning ());
        holder.Buocchay.setText (buocchay+"");
        return convertView;
    }
    static  class ViewHolder{
        TextView Username,Buocchay;
    }
}
