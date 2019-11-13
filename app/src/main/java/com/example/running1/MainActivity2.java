package com.example.running1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity2 extends AppCompatActivity {
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                        Toast.makeText(MainActivity2.this, "OK", Toast.LENGTH_SHORT).show();

                        break;
                    }
                    case R.id.ranking: {
                        Toast.makeText(MainActivity2.this, "OK", Toast.LENGTH_SHORT).show();

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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return t.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

}
