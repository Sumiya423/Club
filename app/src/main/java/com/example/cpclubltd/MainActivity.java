package com.example.cpclubltd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import view.fragment.Applications;
import view.fragment.ongoing;
import view.fragment.upcoming;
import view.fragment.wings;

public class MainActivity extends AppCompatActivity {

    ChipNavigationBar chipNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chipNavigationBar = findViewById(R.id.bottomNav);
        chipNavigationBar.setItemSelected(R.id.bottomNav,true);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new wings()).commit();
        bottomMenu();
    }

    private void bottomMenu() {


        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i) {
                    case R.id.menu_bt_wings:
                        fragment= new wings();
                    case R.id.menu_bt_application:
                        fragment = new Applications();
                        break;
                    case R.id.menu_bt_ongoing:
                        fragment = new ongoing();
                        break;
                    case R.id.menu_bt_upcoming:
                        fragment = new upcoming();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            }
        });


    }
}