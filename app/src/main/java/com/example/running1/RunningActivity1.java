package com.example.running1;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class RunningActivity1 extends AppCompatActivity {


    private TextView Nguoichaycungban, sobuocchaynhieunhat, sobuochientai, sobuocconlai;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_running1);
        Nguoichaycungban = (TextView) findViewById (R.id.songuoidangchaycungban);
        sobuocchaynhieunhat = (TextView) findViewById (R.id.songuoicobuocchaynhieunhat);
        sobuochientai = (TextView) findViewById (R.id.buocchayhientaicuaban);
        sobuocconlai = (TextView) findViewById (R.id.sobuocconlai);
        Nguoichaycungban.setText ("6" );
        sobuocchaynhieunhat.setText ("10000");
        sobuochientai.setText ("2000" );
        sobuocconlai.setText ("6070");
        BarChart chart = (BarChart) findViewById(R.id.barchart);

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
}
