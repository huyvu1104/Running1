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
import android.util.Log;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
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

public class MainActivity2 extends AppCompatActivity implements SensorEventListener, ValueEventListener {
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    TextView tv_steps;
    SensorManager sensorManager;
    boolean running=false;
    ProgressBar progressBar;
    DatabaseReference dataRef;
    User u;
    String timeStamp;
    int change = 0;
    int old = -1;
    HashMap daily;
    FirebaseUser current;

    protected void onCreate(Bundle savedInstanceState) {
        timeStamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
        dataRef = FirebaseDatabase.getInstance().getReference().child("users").getRef();
        dataRef.child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_steps= findViewById(R.id.buocchay);
        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        progressBar= (ProgressBar) findViewById (R.id.progressBar);

        tv_steps = findViewById(R.id.tv_steps);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
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
                        finish();
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
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
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

        if (u == null) return;
        if (u!=null) {
            if (old == -1) {
                old = (int) sensorEvent.values[0];
            }
            change = (int) sensorEvent.values[0] - old;
            old = (int) sensorEvent.values[0];

            if (u.getDaily().keySet().contains(timeStamp)) {
                Integer x = u.getDaily().get(timeStamp);
                x += change;
                u.getDaily().replace(timeStamp, x);
            } else {
                u.getDaily().put(timeStamp, 0);
            }
            u.total = 0;
            for (Integer value:u.getDaily().values()) {
                u.total += value;
            }
            tv_steps.setText((int) u.total + "");
            Log.d("COUNT_STEP", "onSensorChanged: sensorValue " + sensorEvent.values[0] + "\n" +
                    u.toString()
            );
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        dataRef.child(FirebaseAuth.getInstance().getUid()).setValue(u);
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        u = dataSnapshot.getValue(User.class);
        if (u == null) {
            u = new User();
            u.setTotal(0);
            daily=new HashMap<String,Integer>();
            daily.put(timeStamp,0);
            u.setDaily(daily);
            u.setAge(0);
            u.setHeight(0);
            u.setId("");
            u.setWeight(0);
        }
        // set UI
        tv_steps.setText((int) u.daily.get(timeStamp) + "");
    }


    // useless
    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}

/*
    currnetData.total : tong buoc chan
    currentData.target: muc tieu
    currentData.daily.get("20191224") : du lieu ngay 24/12/2019


 */
