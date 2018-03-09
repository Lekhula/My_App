package com.pabi.my_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class BottomNav_Activity  extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth auth;

    Button logout;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener ( ){

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

                case R.id.navigation_home:
                   // Intent intent1 = new Intent(BottomNav_Activity.this, Lineup_Fragment.class);//ACTIVITY_NUM = 0
                   // startActivity(intent1);
                   return true;
                   // break;

                case R.id.navigation_dashboard:
                   // Intent intent2  = new Intent(BottomNav_Activity.this, FindMe_Fragment.class);//ACTIVITY_NUM = 1
                    //startActivity(intent2);
                    //break;
                    return true;

                case R.id.navigation_notifications:
                    //Intent intent3 = new Intent(BottomNav_Activity.this, Bookings_Fragment.class);//ACTIVITY_NUM = 2
                   //startActivity(intent3);
                    //break;
                    return true;
            }

            return false;
        }
    };


            @Override
            public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_bottom_nav_);

                auth = FirebaseAuth.getInstance();
                if(auth.getCurrentUser() == null){
                    finish();
                    startActivity(new Intent(this, MainActivity.class));

                }
                FirebaseUser user = auth.getCurrentUser();

                logout = (Button)findViewById(R.id.logoutBtn);
                logout.setOnClickListener(this);


                BottomNavigationView navigation = (BottomNavigationView)findViewById(R.id.navigation);
                navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = new Fragment();
                        switch (item.getItemId()){
                            case R.id.navigation_home:
                                 Intent intent1 = new Intent(BottomNav_Activity.this, Lineup_Fragment.class);//ACTIVITY_NUM = 0
                                 startActivity(intent1);
                                break;

                            case R.id.navigation_dashboard:
                                Intent intent2  = new Intent(BottomNav_Activity.this, MainActivity2.class);//ACTIVITY_NUM = 1
                                startActivity(intent2);
                                break;

                            case R.id.navigation_notifications:
                                Intent intent3 = new Intent(BottomNav_Activity.this, Bookings_Fragment.class);//ACTIVITY_NUM = 2
                                startActivity(intent3);
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.contentLayout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
               // transaction.replace(R.id.contentLayout, Lineup_Fragment.newInstance());
                transaction.commit();

    }


    @Override
    public void onClick(View v) {
        if(v == logout){
            auth.signOut();
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

    }
}

/**Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_bottom_navigation );

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
        findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
        (new BottomNavigationView.OnNavigationItemSelectedListener() {
@Override
public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;
        switch (item.getItemId()) {
        case R.id.navigation_home:
        selectedFragment = HomeFragment.newInstance();
        break;
        case R.id.navigation_jobs:
        selectedFragment = JobsFragment.newInstance();
        break;
        case R.id.navigation_links:
        selectedFragment = LinksFragment.newInstance();
        break;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, selectedFragment);
        transaction.commit();
        return true;
        }
        });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, HomeFragment.newInstance());
        transaction.commit();
        }**/
