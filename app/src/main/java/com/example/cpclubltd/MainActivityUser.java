package com.example.cpclubltd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import view.fragment.CommitteeFragment;
import view.fragment.HomeFragment;
import view.fragment.UserOngoingFragment;
import view.fragment.UserUpcomingFragment;
import view.fragment.ongoing;
import view.fragment.upcoming;
import view.fragment.wings;

public class MainActivityUser extends AppCompatActivity {

    private static final String TAG =MainActivityUser.class.getSimpleName() ;
    private Toolbar toolbar;
    private FrameLayout user_frameLayout;
    ChipNavigationBar chipNavigationBarUser;

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

        toolbar = findViewById(R.id.user_home_toolbar);
        chipNavigationBarUser = findViewById(R.id.user_bottomNav);
        chipNavigationBarUser.setItemSelected(R.id.user_bottomNav,true);
        getSupportFragmentManager().beginTransaction().replace(R.id.user_fragment_container,new HomeFragment()).commit();
        bottomMenuUser();

    }

    private void bottomMenuUser() {


        chipNavigationBarUser.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment1=null;
                switch (i) {
                    case R.id.menu_bt_wings_user:
                        fragment1= new wings();
                        setTitle("CPC Wings");
                        break;
                    case R.id.menu_bt_committee_user:
                        fragment1 = new CommitteeFragment();
                        setTitle("CPC Committee");
                        break;
                    case R.id.menu_bt_ongoing_user:
                        fragment1 = new UserOngoingFragment();
                        setTitle("CPC Ongoing Events");
                        break;
                    case R.id.menu_bt_upcoming_user:
                        fragment1 = new UserUpcomingFragment();
                        setTitle("CPC Upcoming Events");
                        break;
                }
                if(fragment1!=null){
                    fragmentManager= getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.user_fragment_container,fragment1).commit();
                }
                else {
                    Log.e(TAG,"Error in Creating Fragment");
                }
            }
        });


    }

    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.top_menu_nav_user,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.menu_top_Developer:
                startActivity(new Intent(MainActivityUser.this, DeveloperActivity.class));
                break;

            case R.id.menu_top_CPC:
                startActivity(new Intent(MainActivityUser.this, AboutAppActivity.class));
                break;
        }
        return true;
    }

}