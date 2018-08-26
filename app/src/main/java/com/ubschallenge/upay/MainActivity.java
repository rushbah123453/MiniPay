package com.ubschallenge.upay;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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


        final SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor=sharedPreferences.edit();



        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                editor.putString("sharedPhoneno",phoneno.getText().toString());
             //   editor.putString("phone",password.getText().toString());
                editor.commit();

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
