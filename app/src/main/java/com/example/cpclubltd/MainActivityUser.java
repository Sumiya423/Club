package com.example.cpclubltd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import view.fragment.CommitteeFragment;
import view.fragment.HomeFragment;
import view.fragment.UserOngoingFragment;
import view.fragment.ongoing;
import view.fragment.upcoming;
import view.fragment.wings;

public class MainActivityUser extends AppCompatActivity {

    private Toolbar toolbar;
    private FrameLayout user_frameLayout;
    ChipNavigationBar chipNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

        toolbar = findViewById(R.id.user_home_toolbar);
        user_frameLayout = findViewById(R.id.user_home_frame_layout);
        chipNavigationBar = findViewById(R.id.user_bottomNav);
        chipNavigationBar.setItemSelected(R.id.user_bottomNav,true);
        getSupportFragmentManager().beginTransaction().replace(R.id.user_fragment_container,new HomeFragment()).commit();
        bottomMenu();

    }

    private void bottomMenu() {


        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i) {
                    case R.id.menu_bt_wings_user:
                        fragment= new wings();
                        break;
                    case R.id.menu_bt_committee_user:
                        fragment = new CommitteeFragment();
                        break;
                    case R.id.menu_bt_ongoing:
                        fragment = new UserOngoingFragment();
                        break;
                    case R.id.menu_bt_upcoming:
                        fragment = new upcoming();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.user_fragment_container,fragment).commit();
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
                new SharedPrefs(this).logout();
                finish();
                Intent intent = new Intent(MainActivityUser.this,LoginActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}