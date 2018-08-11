package com.ubschallenge.upay.SignUp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ubschallenge.upay.R;


public class Signup extends AppCompatActivity {

    EditText name, mobile, passwd, adhar, email;
    Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        name = (EditText) findViewById(R.id.username);
        mobile = (EditText) findViewById(R.id.mobile);
        passwd = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        signUp = (Button) findViewById(R.id.signup1);
        adhar= (EditText) findViewById(R.id.adhar);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(Signup.this, "SignUp clicked", Toast.LENGTH_SHORT).show();


                if (mobile.getText().toString().matches("")) {
                    Toast.makeText(Signup.this, "Please enter mobile number", Toast.LENGTH_SHORT).show();
                    mobile.setError("Please enter mobile number");
                } else {
                    Intent intent = new Intent(getApplicationContext(), Otp.class);
                    intent.putExtra("mobileno", mobile.getText().toString());
                    intent.putExtra("name", name.getText().toString());
                    intent.putExtra("passwd", passwd.getText().toString());
                    intent.putExtra("email", email.getText().toString());
                    intent.putExtra("aadhar", adhar.getText().toString());
                    startActivity(intent);


                }
              /*  BckgroundTask bckgroundTask=new BckgroundTask(Signup.this);
                bckgroundTask.execute("signup",name.getText().toString(),email.getText().toString(),passwd.getText().toString(),mobile.getText().toString());

*/
            }
        });


    }
}
