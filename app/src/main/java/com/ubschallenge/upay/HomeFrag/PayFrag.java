package com.ubschallenge.upay.HomeFrag;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ubschallenge.upay.NetworkCall.AsyncResponse;
import com.ubschallenge.upay.NetworkCall.BckgroundTask;
import com.ubschallenge.upay.R;
import com.ubschallenge.upay.SignUp.Signup;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class PayFrag extends Fragment implements AsyncResponse,View.OnClickListener {

    private Button amt100;
    private Button amt200;
    private Button amt500;
    EditText payRecipient,amt;
    Button payNow;
    View view;
    String amt_send,recip_send;
    String pay_to;
    String hardCodedAmount="5";
    String hardCodedFromPhoneNumber="1234";
    String phonenumber;

    @SuppressLint("ValidFragment")
    public PayFrag(String pay_to) {
        // Required empty public constructor
        this.pay_to = pay_to;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pay, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("Inside oncreate", "VIEW");
        amt100 = (Button) getView().findViewById(R.id.add100);
        amt100.setOnClickListener(this);

        amt200 = (Button) getView().findViewById(R.id.add200);
        amt200.setOnClickListener(this);

        amt500 = (Button) getView().findViewById(R.id.add500);
        amt500.setOnClickListener(this);


        payRecipient=(EditText)view.findViewById(R.id.payToMobileNumber);
        amt=(EditText)view.findViewById(R.id.amount);
        payNow=(Button)view.findViewById(R.id.payMoney);

        SharedPreferences sharedPreferences=this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);


        phonenumber=sharedPreferences.getString("sharedPhoneno", "default value");
        if(!pay_to.equals(""))
        {
            payRecipient.setText(pay_to);
        }



        payNow.setOnClickListener(this);



    }


    @Override
    public void onClick(View view) {
        String temp="0";
        switch (view.getId()) {

            case R.id.payMoney:

                String temp_amt =  amt.getText().toString();
                if(temp_amt.equals("") || temp_amt.equals("0"))
                {
                    Toast.makeText(getContext(),"Please enter valid amount",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(),temp_amt,Toast.LENGTH_SHORT).show();
                    BckgroundTask bckgroundTask1=new BckgroundTask(getContext());
                    bckgroundTask1.output=this;
                    bckgroundTask1.execute("payMoney",phonenumber,payRecipient.getText().toString(),amt.getText().toString());
                    this.amt_send = temp_amt;
                    this.recip_send = payRecipient.getText().toString();

                }

                break;

            case R.id.add100:
                // do your code
                temp = amt.getText().toString();
                if(!temp.isEmpty() && android.text.TextUtils.isDigitsOnly(temp))
                    amt.setText(Integer.parseInt(temp)+100+"");
                else if(temp.isEmpty())
                    amt.setText("100");
                break;

            case R.id.add200:
                // do your code
                temp = amt.getText().toString();
                if(!temp.isEmpty() && android.text.TextUtils.isDigitsOnly(temp))
                    amt.setText(Integer.parseInt(temp)+200+"");
                else if(temp.isEmpty())
                    amt.setText("200");

                break;

            case R.id.add500:
                // do your code
                temp = amt.getText().toString();
                if(!temp.isEmpty() && android.text.TextUtils.isDigitsOnly(temp))
                    amt.setText(Integer.parseInt(temp)+500+"");
                else if(temp.isEmpty())
                    amt.setText("500");
                break;


            default:
                break;
        }

    }

    @Override
    public void AsyncFinnished(String output) {
        Toast.makeText(getContext(),"Output add money"+output,Toast.LENGTH_SHORT).show();
        if(output.trim().equals("1")) {
            Fragment fragment2 = new PaymentSuccessful(amt_send,"pay",recip_send);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_container, fragment2);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        else
        {
            Toast.makeText(getContext(),"Error occured while paying money!"+output,Toast.LENGTH_SHORT).show();
        }

    }





}
