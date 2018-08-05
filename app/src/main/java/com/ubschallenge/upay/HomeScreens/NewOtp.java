package com.ubschallenge.upay.HomeScreens;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserAttributes;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserCodeDeliveryDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.ForgotPasswordContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.MultiFactorAuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.NewPasswordContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GenericHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.SignUpHandler;
import com.amazonaws.regions.Regions;
import com.raycoarana.codeinputview.CodeInputView;
import com.raycoarana.codeinputview.OnCodeCompleteListener;
import com.ubschallenge.upay.R;

import java.util.UUID;

public class NewOtp extends AppCompatActivity {


    private final String TAG="MainActivity";
    Context context;
    String userPoolId="us-east-2_iCjYtx4wl";
    String clientId="69fkvkdff6cgq1tufomf9ljd5s";
    String ClientSecret="1bhab4vlp6ur9i6ipeo374f4icb8fvp73ffl5r4882l0u61ncb84";
    private AlertDialog userDialog;
    private ProgressDialog waitDialog;
    private static final Regions cognitoRegion = Regions.US_EAST_2;
    CognitoUserPool cognitoUserPool;
    private EditText inUsername;
    private EditText inPassword;

    public String uniqueID;
    private String username;
    private String password;

    public String mobileno1;

    public TextView textView;

    private MultiFactorAuthenticationContinuation multiFactorAuthenticationContinuation;
    private ForgotPasswordContinuation forgotPasswordContinuation;
    private NewPasswordContinuation newPasswordContinuation;

public Button button;
String otp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_otp);
        uniqueID = UUID.randomUUID().toString();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        /*button=(Button)findViewById(R.id.buttonLogIn);
        inUsername=(EditText) findViewById(R.id.textViewUserIdLabel);
        inPassword=(EditText) findViewById(R.id.textViewUserPasswordLabel);
        otp=(EditText)findViewById(R.id.otp);*/

    /*   mobileno1=getIntent().getExtras().getString("mobileno");*/


     textView=(TextView)findViewById(R.id.verify);

    mobileno1="+91"+ getIntent().getExtras().getString("mobileno");
        Toast.makeText(getApplicationContext(),mobileno1,Toast.LENGTH_SHORT).show();
        final CodeInputView codeInputView=(CodeInputView)findViewById(R.id.otp_CodeInput);

        codeInputView.addOnCompleteListener(new OnCodeCompleteListener() {
            @Override
            public void onCompleted(String code) {


                 otp=codeInputView.getCode();

                Toast.makeText(NewOtp.this,"OTP:"+otp,Toast.LENGTH_SHORT).show();

                sendConfCode();

                /*Intent intent=new Intent(getApplicationContext(),MyBloodGroup.class);
                startActivity(intent);*/
            }
        });

        Bundle extras = getIntent().getExtras();
      /*  if (extras != null) {
            // get back to main screen
            String value = extras.getString("TODO");
            if (value.equals("exit")) {
                onBackPressed();
            }
        }*/

       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_Register);
        //toolbar.setTitle("");
        //setSupportActionBar(toolbar);

       // TextView main_title = (TextView) findViewById(R.id.signUp_toolbar_title);
        //main_title.setText("Sign up");

      /*  toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });*/


        context=getApplicationContext();
         cognitoUserPool=new CognitoUserPool(context ,userPoolId,clientId,ClientSecret,cognitoRegion);
        CognitoUserAttributes cognitoUserAttributes=new CognitoUserAttributes();
        cognitoUserAttributes.addAttribute("phone_number",mobileno1);
        init();


       /* cognitoUserPool.signUpInBackground("FromMyApp","Abcgf234vunu$",cognitoUserAttributes,null,signUpHandler);

        GenericHandler genericHandler=new GenericHandler() {
            @Override
            public void onSuccess() {
                Toast.makeText(getApplicationContext(),"OTP sent",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Exception exception) {

            }
        };*/



        Toast.makeText(getApplicationContext(),"here",Toast.LENGTH_SHORT).show();

       /* button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendConfCode();
            }
        });*/


    }



    private void sendConfCode() {
        String userName = "+919552388824";
        String confirmCode = otp;



        cognitoUserPool.getUser(uniqueID).confirmSignUpInBackground(confirmCode, true, confHandler);
    }

    GenericHandler confHandler = new GenericHandler() {
        @Override
        public void onSuccess() {
            Toast.makeText(getApplicationContext(),"OTP user successfull",Toast.LENGTH_SHORT).show();
          //  Intent intent=new Intent(getApplicationContext(),MyBloodGroup.class);
            //startActivity(intent);
        }

        @Override
        public void onFailure(Exception exception) {
           /* TextView label = (TextView) findViewById(R.id.textViewConfirmUserIdMessage);
            label.setText("Confirmation failed!");
            *//*username.setBackground(getDrawable(R.drawable.text_border_error));*//*

            label = (TextView) findViewById(R.id.textViewConfirmCodeMessage);
            label.setText("Confirmation failed!");
            *//*confCode.setBackground(getDrawable(R.drawable.text_border_error));*//*

            showDialogMessage("Confirmation failed", AppHelper.formatException(exception), false);*/

            Toast.makeText(getApplicationContext(),"User failed signup",Toast.LENGTH_SHORT).show();
        }
    };


    private void init(){

        String givenName="+919552388824";

        String userInput = givenName.toString();

        CognitoUserAttributes userAttributes = new CognitoUserAttributes();
        if (userInput != null) {
            if (userInput.length() > 0) {
                userAttributes.addAttribute("given_name", userInput);
            }
        }

        userInput = "rushabh1234d53@gmail.com";
        if (userInput != null) {
            if (userInput.length() > 0) {
                userAttributes.addAttribute("email", userInput);
            }
        }

        userInput = mobileno1;
        if (userInput != null) {
            if (userInput.length() > 0) {
                userAttributes.addAttribute("phone_number", userInput);
            }
        }

      //  showWaitDialog("Signing up...");

        cognitoUserPool.signUpInBackground(uniqueID, "Aprddvvnuru434@", userAttributes, null, signUpHandler);

    }
    SignUpHandler signUpHandler = new SignUpHandler() {
        @Override
        public void onSuccess(CognitoUser user, boolean signUpConfirmationState,
                              CognitoUserCodeDeliveryDetails cognitoUserCodeDeliveryDetails) {
            // Check signUpConfirmationState to see if the user is already confirmed
          //  closeWaitDialog();
            Boolean regState = signUpConfirmationState;
            if (signUpConfirmationState) {
                // User is already confirmed
                Toast.makeText(getApplicationContext(),"signup success",Toast.LENGTH_SHORT).show();
               // Intent intent=new Intent(getApplicationContext(),MyBloodGroup.class);
             //   startActivity(intent);
            }
            else {
                // User is not confirmed
                //confirmSignUp(cognitoUserCodeDeliveryDetails);
            }
        }

        @Override
        public void onFailure(Exception exception) {
            //closeWaitDialog();
        /*    TextView label = (TextView) findViewById(R.id.textViewRegUserIdMessage);
            label.setText("Sign up failed");
            username.setBackground(getDrawable(R.drawable.text_border_error));
            showDialogMessage("Sign up failed",AppHelper.formatException(exception),false);*/

            Toast.makeText(getApplicationContext(),"signup failed"+exception.getMessage(),Toast.LENGTH_LONG).show();
            textView.setText(exception.getMessage());
        }
    };



}
