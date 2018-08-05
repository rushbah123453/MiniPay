package com.ubschallenge.upay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ubschallenge.upay.HomeScreens.HomeBottomNav;

public class MainActivity extends AppCompatActivity {



    TextView signUp;
    Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signUp=(TextView)findViewById(R.id.signup);
        signIn=(Button)findViewById(R.id.signin);



        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(MainActivity.this,HomeBottomNav.class);
                startActivity(intent);


            }
        });




        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Signup.class);
                startActivity(intent);
            }
        });



    }
}
