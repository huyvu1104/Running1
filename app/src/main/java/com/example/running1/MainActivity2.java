package com.example.running1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity2 extends AppCompatActivity implements SensorEventListener {
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    TextView tv_steps;
    SensorManager sensorManager;
    boolean running=false;
    ProgressBar progressBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_steps= findViewById(R.id.buocchay);
        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        progressBar= (ProgressBar) findViewById (R.id.progressBar);

        dl = findViewById(R.id.dl);
        t = new ActionBarDrawerToggle(this, dl, R.string.app_name, R.string.app_name);
        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
              //  Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
                switch (id) {
                    case R.id.info: {
                        Intent i = new Intent(MainActivity2.this, ShowInfoActivity.class);
                        startActivity(i);
                        break;
                    }
                    case R.id.running: {
                        Intent a = new Intent(MainActivity2.this, RunningActivity1.class);
                        startActivity(a);
                        break;
//                        Intent i = new Intent(MainActivity2.this, ShowInfoActivity.class);
//                        startActivity(i);
//                        break;
                    }
                    case R.id.ranking: {
                        Intent a = new Intent(MainActivity2.this, Ranking.class);
                        startActivity(a);
                        break;
                    }
                    case R.id.mlogout: {
                        Intent i = new Intent(MainActivity2.this, MainActivity.class);
                        startActivity(i);
                        break;
                    }


                }
                return true;

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor!=null){
            sensorManager.registerListener(this,countSensor,SensorManager.SENSOR_DELAY_UI);
        } else {
            tv_steps.setText("May khong co cam bien dem buoc chan");
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return t.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(running){
            final int buocchay = (int) sensorEvent.values[0];
            tv_steps.setText(buocchay +"bước");

        Thread t = new Thread (){
            @Override
            public void run() {
                while (progressBar.getProgress ()<100){
                    progressBar.setProgress ((buocchay/8432)/100);
                }

                super.run ();
            }
        };

    }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
