package com.ubschallenge.upay.SignIn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ubschallenge.upay.MainActivity;
import com.ubschallenge.upay.R;

public class Sign extends AppCompatActivity {

    TextView signIN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        signIN=(TextView)findViewById(R.id.signin);


        signIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Sign.this,MainActivity.class);
                startActivity(intent);
            }
        });



    }
}
