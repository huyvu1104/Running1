package com.example.running1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
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

public class RunningActivity1 extends AppCompatActivity implements ValueEventListener {


    private TextView sobuocchaynhieunhat, sobuochientai, sobuocconlai;
    BarChart chart;
    DatabaseReference dataRef;
    User u;
    FirebaseUser current;
    String timeStamp;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_running1);
        timeStamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
        dataRef = FirebaseDatabase.getInstance().getReference();
        current = FirebaseAuth.getInstance().getCurrentUser();
        dataRef.child("users").child(current.getUid()).addListenerForSingleValueEvent(this);
        sobuocchaynhieunhat = (TextView) findViewById (R.id.sobuocchaynhieunhat);
        sobuochientai = (TextView) findViewById (R.id.buocchayhientaicuaban);
        sobuocconlai = (TextView) findViewById (R.id.sobuocconlai);
        chart = (BarChart) findViewById(R.id.barchart);

        ArrayList NoOfEmp = new ArrayList();
        NoOfEmp.add(new BarEntry(1040f, 1));
        NoOfEmp.add(new BarEntry(1133f, 2));
        NoOfEmp.add(new BarEntry(1240f, 3));
        NoOfEmp.add(new BarEntry(1369f, 4));
        NoOfEmp.add(new BarEntry(1487f, 5));
        NoOfEmp.add(new BarEntry(1501f, 6));
        NoOfEmp.add(new BarEntry(1645f, 7));
        BarDataSet bardataset = new BarDataSet(NoOfEmp, "Buoc chan");
        chart.animateY(5000);
        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(bardataset);
        BarData data;
        data = new BarData(dataSets);
        chart.setData(data);



    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        u = dataSnapshot.getValue(User.class);
        sobuocchaynhieunhat.setText (""+u.getTotal());
        sobuochientai.setText (""+u.daily.get(timeStamp) );
        sobuocconlai.setText (""+(1000-u.daily.get(timeStamp)));
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
