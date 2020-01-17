package com.example.running1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class RunningActivity1 extends AppCompatActivity implements ValueEventListener {


    private TextView sobuochientai, sobuocconlai;
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
        timeStamp = new SimpleDateFormat ("yyyyMMdd").format (new Date ());
        dataRef = FirebaseDatabase.getInstance ().getReference ();
        current = FirebaseAuth.getInstance ().getCurrentUser ();
        dataRef.child ("users").child (current.getUid ()).addListenerForSingleValueEvent (this);

        sobuochientai = (TextView) findViewById (R.id.buocchayhientaicuaban);
        sobuocconlai = (TextView) findViewById (R.id.sobuocconlai);
        mChart = (LineChart) findViewById (R.id.chart);

        mChart.setTouchEnabled (true);
        mChart.setPinchZoom (true);
        values = new ArrayList<> ();
        values.add (new Entry (1, 0));
        values.add (new Entry (2, 50));
        values.add (new Entry (3, 60));
        values.add (new Entry (4, 70));
        values.add (new Entry (5, 90));
        values.add (new Entry (6, 80));
        values.add (new Entry (7, 90));


        LineDataSet set1;
        if (mChart.getData () != null &&
                mChart.getData ().getDataSetCount () > 0) {
            set1 = (LineDataSet) mChart.getData ().getDataSetByIndex (0);
            set1.setValues (values);
            mChart.getData ().notifyDataChanged ();
            mChart.notifyDataSetChanged ();
        } else {
            set1 = new LineDataSet (values, " Data");
            set1.setDrawIcons (false);
            set1.enableDashedLine (10f, 5f, 0f);
            set1.enableDashedHighlightLine (10f, 5f, 0f);
            set1.setColor (Color.DKGRAY);
            set1.setCircleColor (Color.DKGRAY);
            set1.setLineWidth (1f);
            set1.setCircleRadius (3f);
            set1.setDrawCircleHole (false);
            set1.setValueTextSize (9f);
            set1.setDrawFilled (true);
            set1.setFormLineWidth (1f);
            set1.setFormLineDashEffect (new DashPathEffect (new float[]{10f, 5f}, 0f));
            set1.setFormSize (15.f);
            if (Utils.getSDKInt () >= 18) {
                Drawable drawable = ContextCompat.getDrawable (this, R.drawable.common_google_signin_btn_icon_dark);
                set1.setFillDrawable (drawable);
            } else {
                set1.setFillColor (Color.DKGRAY);
            }
            ArrayList<ILineDataSet> dataSets = new ArrayList<> ();
            dataSets.add (set1);
            LineData data = new LineData (dataSets);
            mChart.setData (data);
        }


    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        u = dataSnapshot.getValue (User.class);


        sobuochientai.setText ("" + u.daily.get (timeStamp));
        sobuocconlai.setText ("" + (10000 - u.daily.get (timeStamp)));
//        int day1, day2, day3, day4, day5, day6;
//        DateFormat dateFormat = new SimpleDateFormat ("yyyyMMdd");
//        Calendar calendar = Calendar.getInstance ();
//        calendar.add (Calendar.DATE, -6);
//        Date todate6 = calendar.getTime ();
//        calendar.add (Calendar.DATE, -5);
//        Date todate5 = calendar.getTime ();
//        calendar.add (Calendar.DATE, -4);
//        Date todate4 = calendar.getTime ();
//        calendar.add (Calendar.DATE, -3);
//        Date todate3 = calendar.getTime ();
//        calendar.add (Calendar.DATE, -2);
//        Date todate2 = calendar.getTime ();
//        calendar.add (Calendar.DATE, -1);
//        Date todate1 = calendar.getTime ();
//        if (dateFormat.format (todate1) == null || dateFormat.format (todate2) == null || dateFormat.format (todate3) == null ||
//                dateFormat.format (todate4) == null || dateFormat.format (todate5) == null || dateFormat.format (todate6) == null) {
//            day1 = day2 = day3 = day4 = day5 = day6 = 0;
//        } else {
//            day6 = Integer.parseInt (dateFormat.format (todate6));
//            day5 = Integer.parseInt (dateFormat.format (todate5));
//            day4 = Integer.parseInt (dateFormat.format (todate4));
//            day3 = Integer.parseInt (dateFormat.format (todate3));
//            day2 = Integer.parseInt (dateFormat.format (todate2));
//            day1 = Integer.parseInt (dateFormat.format (todate1));
//        }
        Date date = new Date ();
        DateFormat dateFormat  = new SimpleDateFormat ("yyyyMMdd");
        int date6 = Integer.parseInt (dateFormat.format (date.getTime ()-6*24*3600*1000)) ;
        int date5 = Integer.parseInt (dateFormat.format (date.getTime ()-5*24*3600*1000)) ;
        int date4 = Integer.parseInt (dateFormat.format (date.getTime ()-4*24*3600*1000)) ;
        int date3 = Integer.parseInt (dateFormat.format (date.getTime ()-3*24*3600*1000)) ;
        int date2 = Integer.parseInt (dateFormat.format (date.getTime ()-2*24*3600*1000)) ;
        int date1 = Integer.parseInt (dateFormat.format (date.getTime ()-1*24*3600*1000)) ;



        values.set (0, new Entry (1,date6 ));
        values.set (1, new Entry (2, date5));
        values.set (2, new Entry (3, date4));
        values.set (3, new Entry (4, date3));
        values.set (4, new Entry (5, date2));
        values.set (5, new Entry (6, date1));
        values.set (6, new Entry (7,u.daily.get (timeStamp)));





    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
