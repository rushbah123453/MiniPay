package com.ubschallenge.upay.HomeFrag;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ubschallenge.upay.MainActivity;
import com.ubschallenge.upay.NetworkCall.AsyncResponse;
import com.ubschallenge.upay.NetworkCall.BckgroundTask;
import com.ubschallenge.upay.R;
import com.ubschallenge.upay.SplashScreens.SplashScreen;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFrag extends Fragment implements AsyncResponse{

    TextView logout,balance,email,phone,adhar,name;
    View view;


    public ProfileFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_profile, container, false);

        balance=(TextView)view.findViewById(R.id.balanceprofile);
        phone=(TextView)view.findViewById(R.id.phoneprofile);
        email=(TextView)view.findViewById(R.id.emailprofile);
        adhar=(TextView)view.findViewById(R.id.adharprofile);
        name=(TextView)view.findViewById(R.id.nameprofile);


        logout=(TextView)view.findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"logout",Toast.LENGTH_SHORT).show();
                final SharedPreferences sharedPreferences=getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
                final SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("loginStatus","false");
                editor.commit();

                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);

            }
        });



        SharedPreferences sharedPreferences=this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        String phonenumber=sharedPreferences.getString("sharedPhoneno", "default value");




        Toast.makeText(getContext(),"shared phone"+phonenumber,Toast.LENGTH_SHORT).show();
        BckgroundTask bckgroundTask=new BckgroundTask(getContext());
        bckgroundTask.output=  ProfileFrag.this;
        bckgroundTask.execute("getprofile",phonenumber);










        return view;
    }

    @Override
    public void AsyncFinnished(String output) throws JSONException {
        Toast.makeText(getContext(),"Profile"+output,Toast.LENGTH_SHORT).show();
        JSONObject jsonObject=new JSONObject(output);
        JSONObject response_data=jsonObject.getJSONObject("response_data");
        String name_response=response_data.getString("u_name");
        String balance_response=response_data.getString("balance");
        String phone_response=response_data.getString("phone");
        String aadhaar_response=response_data.getString("aadhaar");
        String email_response=response_data.getString("email");


        balance.setText(balance_response);
        phone.setText(phone_response);
        email.setText(email_response);
        adhar.setText(aadhaar_response);
        name.setText(name_response);


       // Toast.makeText(getContext(),"name"+name,Toast.LENGTH_SHORT).show();

    }
}
