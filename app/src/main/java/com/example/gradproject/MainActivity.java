package com.example.gradproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.gradproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_frag,fragment);
        fragmentTransaction.commit();
    }
}