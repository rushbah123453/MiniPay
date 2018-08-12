package com.ubschallenge.upay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ubschallenge.upay.HomeScreens.HomeBottomNav;
import com.ubschallenge.upay.NetworkCall.BckgroundTask;
import com.ubschallenge.upay.SignUp.Otp;
import com.ubschallenge.upay.SignUp.Signup;

public class MainActivity extends AppCompatActivity {



    TextView signUp;
    Button signIn;


    EditText phoneno,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signUp=(TextView)findViewById(R.id.signup);
        signIn=(Button)findViewById(R.id.signin);

        phoneno=(EditText)findViewById(R.id.signinPhone) ;
        password=(EditText)findViewById(R.id.signinPassword);






        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                BckgroundTask bckgroundTask1=new BckgroundTask(MainActivity.this);
                bckgroundTask1.execute("signin",phoneno.getText().toString(),password.getText().toString());







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
