package com.ubschallenge.upay.HomeScreens;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.ubschallenge.upay.R;

public class HomeBottomNav extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_bottom_nav);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }




    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_shop:
                   /* toolbar.setTitle("Shop");*/
                    /*fragment=new Home();
                    loadFragment(fragment);*/


                    Toast.makeText(HomeBottomNav.this,"Home clicked",Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_gifts:
                   /* toolbar.setTitle("My Gifts");*/
                  /*  fragment=new Donate();
                    loadFragment(fragment);*/
                    Toast.makeText(HomeBottomNav.this,"Barcode clicked",Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_cart:
                   /* toolbar.setTitle("Cart");*/
                    /*fragment=new EligibilityCriteria();
                    loadFragment(fragment);*/
                    Toast.makeText(HomeBottomNav.this,"Passbook clicked",Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_profile:
                    /*toolbar.setTitle("Profile");*/
                  /*  fragment=new AboutUs();
                    loadFragment(fragment);*/
                    Toast.makeText(HomeBottomNav.this,"Profile clicked",Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
