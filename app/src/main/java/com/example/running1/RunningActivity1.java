package com.example.running1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class RunningActivity1 extends AppCompatActivity implements ValueEventListener {


    private TextView  sobuochientai, sobuocconlai;
    LineChart mChart;
    DatabaseReference dataRef;
    User u;
    FirebaseUser current;
    String timeStamp;
    ArrayList<Entry> values;
    HashMap daily;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_running1);
        timeStamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
        dataRef = FirebaseDatabase.getInstance().getReference();
        current = FirebaseAuth.getInstance().getCurrentUser();
        dataRef.child("users").child(current.getUid()).addListenerForSingleValueEvent(this);

        sobuochientai = (TextView) findViewById (R.id.buocchayhientaicuaban);
        sobuocconlai = (TextView) findViewById (R.id.sobuocconlai);
        mChart = (LineChart) findViewById(R.id.chart);

        mChart.setTouchEnabled(true);
        mChart.setPinchZoom(true);
        values = new ArrayList<>();
        values.add(new Entry(1, 105));
        values.add(new Entry(2, 100));
        values.add(new Entry(3, 150));
        values.add(new Entry(4, 60));
        values.add(new Entry(5, 660));
        values.add(new Entry(6, 6000));
        values.add(new Entry(7, 1000));
//         values = new ArrayList<>();

        LineDataSet set1;
        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new LineDataSet(values, " Data");
            set1.setDrawIcons(false);
            set1.enableDashedLine(10f, 5f, 0f);
            set1.enableDashedHighlightLine(10f, 5f, 0f);
            set1.setColor(Color.DKGRAY);
            set1.setCircleColor(Color.DKGRAY);
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(9f);
            set1.setDrawFilled(true);
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect (new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);
            if (Utils.getSDKInt() >= 18) {
                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.common_google_signin_btn_icon_dark);
                set1.setFillDrawable(drawable);
            } else {
                set1.setFillColor(Color.DKGRAY);
            }
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            LineData data = new LineData(dataSets);
            mChart.setData(data);
        }






    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        u = dataSnapshot.getValue(User.class);
        if (u == null) {
            u = new User();
            u.setTotal(0);
            daily=new HashMap<String,Integer> ();
            daily.put(timeStamp,0);
            u.setDaily(daily);
            u.setAge(0);
            u.setHeight(0);
            u.setId("");
            u.setWeight(0);
        }
        if(u.daily.get(timeStamp)==null){
            u.daily.put(timeStamp,0);
        }

        sobuochientai.setText (""+u.daily.get(timeStamp) );
        sobuocconlai.setText (""+(10000-u.daily.get(timeStamp)));
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
