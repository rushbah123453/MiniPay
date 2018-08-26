package com.ubschallenge.upay.HomeFrag;


import android.content.Context;
import android.content.SharedPreferences;
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

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFrag extends Fragment implements AsyncResponse {


    View view;
TextView balance;
    SharedPreferences pref;

    public HomeFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);

        balance=(TextView)view.findViewById(R.id.balance);
        SharedPreferences sharedPreferences=this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);

       /* SharedPreferences prefs = getSharedPreferences("pref", MODE_PRIVATE);
        String restoredText = prefs.getString("text", null);
        if (restoredText != null) {
            String name = prefs.getString("name", "No name defined");//"No name defined" is the default value.
            int idName = prefs.getInt("idName", 0); //0 is the default value.
        }*/

        String phonenumber=sharedPreferences.getString("sharedPhoneno", "default value");

        Toast.makeText(getContext(),"shared phone"+phonenumber,Toast.LENGTH_SHORT).show();
        BckgroundTask bckgroundTask=new BckgroundTask(getContext());
        bckgroundTask.output=HomeFrag.this;
        bckgroundTask.execute("getBalance",phonenumber);










        return view;
    }

    @Override
    public void AsyncFinnished(String output) {
        balance.setText(output);
        Toast.makeText(getContext(), "Balance"+output, Toast.LENGTH_SHORT).show();
    }
}
