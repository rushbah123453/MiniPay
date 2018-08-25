package com.ubschallenge.upay.HomeFrag;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ubschallenge.upay.NetworkCall.BckgroundTask;
import com.ubschallenge.upay.R;
import com.ubschallenge.upay.SignUp.Signup;

/**
 * A simple {@link Fragment} subclass.
 */
public class PayFrag extends Fragment {


    EditText payRecipient;
    Button payNow;
    View view;
    String toPayNumber;
    String hardCodedAmount="555";
    String hardCodedFromPhoneNumber="+919552388824";

    public PayFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         view=inflater.inflate(R.layout.fragment_pay, container, false);


        payRecipient=(EditText)view.findViewById(R.id.payToMobile);
        payNow=(Button)view.findViewById(R.id.payMoney);

        toPayNumber=payRecipient.getText().toString();



        payNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                BckgroundTask bckgroundTask=new BckgroundTask(getContext());
                // bckgroundTask.output=Signup.this;
                bckgroundTask.execute("payMoney",hardCodedFromPhoneNumber,toPayNumber,hardCodedAmount);

            }
        });











        return view;
    }






}
