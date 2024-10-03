package com.rehman.netenergymeetring;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    NavigationView navMenu;
    ActionBarDrawerToggle toggle;
    DrawerLayout drayerLayout;

    Fragment temp=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);

        navMenu=findViewById(R.id.navMenu);
        drayerLayout=findViewById(R.id.drawerlayout);

        //    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new Fragment_MainDashboard_cat()).commit();


        toggle=new ActionBarDrawerToggle(this,drayerLayout,toolbar,R.string.app_name,R.string.app_name);
        drayerLayout.addDrawerListener(toggle);
        toggle.syncState();


        navMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {


                    case R.id.menu_wapda:
                        Intent wapdaIntent=new Intent(MainActivity.this,WapdaActivity.class);
                        startActivity(wapdaIntent);
                        drayerLayout.closeDrawer(GravityCompat.START);
                        break;



                    case R.id.menu_homeLight:
                        Intent homeIntent=new Intent(MainActivity.this, HomeLightActivity.class);
                        startActivity(homeIntent);
                        drayerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menuHomeFan:
                        Intent fanIntent=new Intent(MainActivity.this, FanInfoActivity.class);
                        startActivity(fanIntent);
                        drayerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_voltage:
                        Intent voltageIntent=new Intent(MainActivity.this, VoltageActivity.class);
                        startActivity(voltageIntent);
                        drayerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_meterImage:
                        Intent meterIntent=new Intent(MainActivity.this, MeterImageActivity.class);
                        startActivity(meterIntent);
                        drayerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_lightControl:
                        Intent lightConIntent=new Intent(MainActivity.this, LightControlActivity.class);
                        startActivity(lightConIntent);
                        drayerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_fanControl:
                        Intent fanConIntent=new Intent(MainActivity.this, FanControlAcitivty.class);
                        startActivity(fanConIntent);
                        drayerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_meterControl:
                        Intent meterConIntent=new Intent(MainActivity.this, MeterControlActivity.class);
                        startActivity(meterConIntent);
                        drayerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_history:
                        Intent historyntent=new Intent(MainActivity.this, HistoryActivity.class);
                        startActivity(historyntent);
                        drayerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_deleteHistory:
                        Intent deleteIntent=new Intent(MainActivity.this, HistoryDeleteActivity.class);
                        startActivity(deleteIntent);
                        drayerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_logout:

                        Intent logIntent=new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(logIntent);
                        finish();
                        Toast.makeText(MainActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                        drayerLayout.closeDrawer(GravityCompat.START);
                        break;

                }

                return false;
            }
        });
    }
}