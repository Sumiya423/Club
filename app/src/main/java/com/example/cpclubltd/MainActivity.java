package com.example.cpclubltd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import view.fragment.ApplicationFragment;
import view.fragment.CommitteeFragment;
import view.fragment.HomeFragment;
import view.fragment.ongoing;
import view.fragment.upcoming;
import view.fragment.wings;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FrameLayout frameLayout;
    ChipNavigationBar chipNavigationBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.home_toolbar);
        frameLayout = findViewById(R.id.home_frame_layout);
        chipNavigationBar = findViewById(R.id.bottomNav);
        chipNavigationBar.setItemSelected(R.id.bottomNav,true);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
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
                        setTitle("CPC Wings");
                        break;
                    case R.id.menu_bt_committee:
                        fragment = new CommitteeFragment();
                        setTitle("CPC Committee");
                        break;
                    case R.id.menu_bt_ongoing:
                        fragment = new ongoing();
                        setTitle("CPC Ongoing Events");
                        break;
                    case R.id.menu_bt_upcoming:
                        fragment = new upcoming();
                        setTitle("CPC Upcoming Events");
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            }
        });



    }

    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.top_menu_nav,menu);

        if(new SharedPrefs(this).isLoggedIn()){
            menu.getItem(2).setVisible(true);
        } else {
            menu.getItem(2).setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()){
            case R.id.menu_top_Application_dev:
                Fragment fragment2= new ApplicationFragment();
                FragmentManager fragmentManager=getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container,fragment2).commit();
                setTitle("Event Application");
                break;

            case R.id.menu_top_Developer_app:
                startActivity(new Intent(MainActivity.this, DeveloperActivity.class));
                break;

            case R.id.menu_top_logout_app:
                new SharedPrefs(this).logout();
                finish();
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
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