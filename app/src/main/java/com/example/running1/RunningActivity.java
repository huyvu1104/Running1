package com.example.running1;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;


public class RunningActivity extends AppCompatActivity  {
    private BarChart barChart;
    private TextView Nguoichaycungban, sobuocchaynhieunhat, sobuochientai, sobuocconlai;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView (R.layout.activity_running);
        barChart = (BarChart) findViewById (R.id.barchar);
        ArrayList<BarEntry> barEntries = new ArrayList<> ();
        barEntries.add (new BarEntry (40f, 0));
        barEntries.add (new BarEntry (45f, 1));
        barEntries.add (new BarEntry (46f, 2));
        barEntries.add (new BarEntry (30f, 3));
        barEntries.add (new BarEntry (020f, 4));
        barEntries.add (new BarEntry (060f, 5));
        barEntries.add (new BarEntry (060f, 6));
        BarDataSet barDataSet = new BarDataSet (barEntries, "Dates");

        ArrayList<String> theDates = new ArrayList<String> ();
        theDates.add ("1");
        theDates.add ("2");
        theDates.add ("3");
        theDates.add ("4");
        theDates.add ("5");
        theDates.add ("6");
        theDates.add ("7");

        BarData theData = new BarData ((IBarDataSet) theDates, barDataSet);
        barChart.setData (theData);

        barChart.setTouchEnabled (true);
        barChart.setDragEnabled (true);
        barChart.setScaleEnabled (true);

        Nguoichaycungban = (TextView) findViewById (R.id.songuoidangchaycungban);
        sobuocchaynhieunhat = (TextView) findViewById (R.id.songuoicobuocchaynhieunhat);
        sobuochientai = (TextView) findViewById (R.id.buocchayhientaicuaban);
        sobuocconlai = (TextView) findViewById (R.id.sobuocconlai);
        Nguoichaycungban.setText ("6" );
        sobuocchaynhieunhat.setText ("10000");
        sobuochientai.setText ("2000" );
        sobuochientai.setText ("6070");
    }}


