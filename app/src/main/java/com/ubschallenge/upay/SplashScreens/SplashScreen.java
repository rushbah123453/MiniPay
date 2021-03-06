package com.ubschallenge.upay.SplashScreens;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ubschallenge.upay.HomeScreens.HomeBottomNav;
import com.ubschallenge.upay.MainActivity;
import com.ubschallenge.upay.R;

public class SplashScreen extends AppCompatActivity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
               /* Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);*/


                SharedPreferences sharedPreferences=SplashScreen.this.getSharedPreferences("pref", Context.MODE_PRIVATE);


                final String loginStatus=sharedPreferences.getString("loginStatus", "default value");

                if (loginStatus.equals("true")){
                    Intent i = new Intent(SplashScreen.this, HomeBottomNav.class);
                    startActivity(i);

                }
                else {
                    Intent i = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(i);
                }





                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);






    }
}
