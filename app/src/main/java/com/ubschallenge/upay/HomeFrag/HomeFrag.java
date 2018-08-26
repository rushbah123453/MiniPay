package com.ubschallenge.upay.HomeFrag;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ubschallenge.upay.NetworkCall.AsyncResponse;
import com.ubschallenge.upay.NetworkCall.BckgroundTask;
import com.ubschallenge.upay.R;
import com.ubschallenge.upay.SignUp.Signup;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFrag extends Fragment implements AsyncResponse {


    View view;
TextView balance;

    public HomeFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);

        balance=(TextView)view.findViewById(R.id.balance);


        BckgroundTask bckgroundTask=new BckgroundTask(getContext());
        bckgroundTask.output=HomeFrag.this;
        bckgroundTask.execute("getBalance","1234");










        return view;
    }

    @Override
    public void AsyncFinnished(String output) {
        balance.setText(output);
        Toast.makeText(getContext(), "Balance"+output, Toast.LENGTH_SHORT).show();
    }
}
