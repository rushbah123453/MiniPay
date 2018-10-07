package com.ubschallenge.upay;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ubschallenge.upay.HomeScreens.HomeBottomNav;
import com.ubschallenge.upay.NetworkCall.BckgroundTask;
import com.ubschallenge.upay.SignUp.Otp;
import com.ubschallenge.upay.SignUp.Signup;

public class MainActivity extends AppCompatActivity {



    TextView signUp;
    Button signIn;
    ImageView icon;
    LinearLayout linearLayout;
    EditText phoneno,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;

        signUp=(TextView)findViewById(R.id.signup);
        signIn=(Button)findViewById(R.id.signin);

        phoneno=(EditText)findViewById(R.id.signinPhone) ;
        password=(EditText)findViewById(R.id.signinPassword);

    /*  icon = (ImageView) findViewById(R.id.image_icon);

        linearLayout = (LinearLayout) findViewById(R.id.linearuserpass);
        icon.setAlpha(0.5f);
        icon.setScaleX(1.5f);
        icon.setScaleY(1.5f);

        icon.animate().translationY(-(height )).scaleX(1.f).scaleY(1f).setDuration(5000).alpha(1f).setUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            int flag = 0;

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                Log.e("value", "" + animation.getAnimatedValue());
                if (((float) animation.getAnimatedValue()) > 0.5f && flag == 0) {
                    linearLayout.animate().alpha(1f).setDuration(1000);
                    flag = 1;
                }

            }
        });*/



        final SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor=sharedPreferences.edit();



        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                editor.putString("sharedPhoneno",phoneno.getText().toString());
             //   editor.putString("phone",password.getText().toString());
                editor.commit();

                BckgroundTask bckgroundTask1=new BckgroundTask(MainActivity.this);
                bckgroundTask1.execute("signin","91"+phoneno.getText().toString(),password.getText().toString());







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
