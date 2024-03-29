package com.example.gradproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;


import com.example.gradproject.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    ActivityMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavigationView navView=findViewById(R.id.drawer_nav_view);
        navView.setNavigationItemSelectedListener(this);

        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer =(DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        drawer.addDrawerListener(toggle);
        toggle.syncState();




        replaceFragment(new MapFragment());
        binding.navView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.NavMap:
                    replaceFragment(new MapFragment());
                    break;
                case R.id.NavCamera:
                    replaceFragment(new CameraFragment());
                    break;
                case R.id.NavAnalysis:
                    replaceFragment(new AnalysisFragment());
                    break;
            }
            return true;
        });

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.UserSettings:
                Intent intent = new Intent(this, UserSettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.DashboardItem:
                Intent intent1 = new Intent(this, DashboardActivity.class);
                startActivity(intent1);
                break;
            case R.id.LogoutItem:
                Intent intent2 = new Intent(this,LoginActivity.class);
                startActivity(intent2);
                finish();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_frag,fragment);
        fragmentTransaction.commit();
    }
    @Override
    public void onStop() {
        super.onStop();



    }
}