package com.ubschallenge.upay.HomeFrag;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ubschallenge.upay.HomeScreens.HomeBottomNav;
import com.ubschallenge.upay.MainActivity;
import com.ubschallenge.upay.NetworkCall.AsyncResponse;
import com.ubschallenge.upay.NetworkCall.BckgroundTask;
import com.ubschallenge.upay.Passbook.Passbook;
import com.ubschallenge.upay.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddMoneyFrag extends Fragment implements AsyncResponse, View.OnClickListener  {
    private Button amt100;
    private Button amt200;
    private Button amt500;
    private TextView amt;
    private Button add;
    private String toPhone = "1234";
    private String amt_send;
    String phonenumber;
    SharedPreferences sharedPreferences;
    public AddMoneyFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment


        sharedPreferences=this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        phonenumber=sharedPreferences.getString("sharedPhoneno", "default value");


        return inflater.inflate(R.layout.fragment_add_money, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("Inside oncreate","VIEW");

        amt100 = (Button) getView().findViewById(R.id.add100);
        amt100.setOnClickListener(this);

        amt200 = (Button) getView().findViewById(R.id.add200);
        amt200.setOnClickListener(this);

        amt500 = (Button) getView().findViewById(R.id.add500);
        amt500.setOnClickListener(this);

        add = (Button) getView().findViewById(R.id.proceed_add);
        add.setOnClickListener(this);

        amt = (TextView) getView().findViewById(R.id.amt);



    }


    @Override
    public void onClick(View view) {
        String temp="0";
        switch (view.getId()) {

            case R.id.proceed_add:

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
                    bckgroundTask1.execute("addmoney", phonenumber, temp_amt);
                    this.amt_send = temp_amt;

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
            Fragment fragment2 = new PaymentSuccessful(amt_send,"add","");
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_container, fragment2);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        else
        {
            Toast.makeText(getContext(),"Error occured while adding money!"+output,Toast.LENGTH_SHORT).show();
        }

    }
}
