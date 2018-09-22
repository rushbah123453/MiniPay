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
import com.ubschallenge.upay.R;
import com.ubschallenge.upay.SplashScreens.SplashScreen;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFrag extends Fragment {

    TextView logout;
    View view;


    public ProfileFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_profile, container, false);


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




        return view;
    }

}
