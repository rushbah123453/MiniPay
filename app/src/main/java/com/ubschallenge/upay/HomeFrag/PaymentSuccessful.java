package com.ubschallenge.upay.HomeFrag;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ubschallenge.upay.NetworkCall.AsyncResponse;
import com.ubschallenge.upay.R;

@SuppressLint("ValidFragment")
public class PaymentSuccessful extends Fragment  {
    private String amt,type,number;

    public PaymentSuccessful(String amt,String type,String number)
    {
        this.amt = amt;
        this.type = type;
        this.number = number;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment_success, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView s = (TextView)getView().findViewById(R.id.amt_transfered);
        s.setText("₹ "+amt);

        TextView text = (TextView)getView().findViewById(R.id.success_text);
        if(type.equals("add"))
            text.setText("Money added to wallet successfully!");
        else if(type.equals("pay"))
        {
            text.setText("Transaction to "+number+" is successfully done!");
        }

    }


}
