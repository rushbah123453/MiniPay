package com.ubschallenge.upay.HomeFrag;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ubschallenge.upay.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddMoneyFrag extends Fragment {


    public AddMoneyFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_money, container, false);
    }

}
