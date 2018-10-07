package com.ubschallenge.upay.HomeFrag;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.FragmentTransaction;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.ubschallenge.upay.HomeScreens.HomeBottomNav;
import com.ubschallenge.upay.MainActivity;
import com.ubschallenge.upay.NetworkCall.AsyncResponse;
import com.ubschallenge.upay.NetworkCall.BckgroundTask;
import com.ubschallenge.upay.Passbook.Passbook;
import com.ubschallenge.upay.R;
import com.ubschallenge.upay.NetworkCall.BckgroundTask;


import org.json.JSONException;
import org.json.JSONObject;
import com.ubschallenge.upay.SignUp.Signup;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFrag extends Fragment implements AsyncResponse {


    View view;
TextView balance,addmoney,passbook;
    SharedPreferences pref;
    CardView card_view,card_view1,card_view3;
    LinearLayout pay_home;
    LinearLayout scan_home;
    private IntentIntegrator qrScan;


    public HomeFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);

        //addmoney=(TextView)view.findViewById(R.id.addmoney) ;
        //passbook=(TextView)view.findViewById(R.id.passbookhome);

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

        card_view = (CardView) view.findViewById(R.id.card2); // creating a CardView and assigning a value.

        card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do whatever you want to do on click (to launch any fragment or activity you need to put intent here.)
                Intent intent=new Intent(getContext(),Passbook.class);
                startActivity(intent);
            }
        });


        card_view3 = (CardView) view.findViewById(R.id.card3); // creating a CardView and assigning a value.

        card_view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do whatever you want to do on click (to launch any fragment or activity you need to put intent here.)
                Intent intent = new Intent("android.intent.action.VIEW");

                /** creates an sms uri */
                Uri data = Uri.parse("sms:");

                /** Setting sms uri to the intent */
                intent.setData(data);

                /** Initiates the SMS compose screen, because the activity contain ACTION_VIEW and sms uri */
                startActivity(intent);
            }
        });

        card_view1 = (CardView) view.findViewById(R.id.card1); // creating a CardView and assigning a value.

        card_view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do whatever you want to do on click (to launch any fragment or activity you need to put intent here.)
                Fragment fragment=new AddMoneyFrag();
                loadFragment(fragment);
                Toast.makeText(getContext(),"Add Money clicked",Toast.LENGTH_SHORT).show();

            }
        });




        pay_home = (LinearLayout )view.findViewById(R.id.pay_home);
        scan_home = (LinearLayout )view.findViewById(R.id.scan_home);

        pay_home.setOnClickListener(new View.OnClickListener() {

                     @Override
                     public void onClick(View view) {

                         Fragment fragment=new PayFrag("");
                         loadFragment(fragment);
                         Toast.makeText(getContext(),"Pay Money clicked",Toast.LENGTH_SHORT).show();

                     }
                 }
        );


        scan_home.setOnClickListener(new View.OnClickListener() {

                 @Override
                 public void onClick(View view) {
                     //Intent intent=new Intent(MainActivity.this,ScanAndPay.class);
                     // startActivity(intent);

                     //qrScan.setOrientationLocked(false);
                     //qrScan.initiateScan();

                     IntentIntegrator.forSupportFragment(HomeFrag.this).initiateScan();
                 }
             }
        );






        return view;
    }

    

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(getContext(), "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                try {
                    //converting the data to json
                    JSONObject obj = new JSONObject(result.getContents());
                    //setting values to textviews
                    String result_final = "Result is " + obj.getString("phone") + " " + obj.getString("emailid");
                    Toast.makeText(getContext(), result_final, Toast.LENGTH_LONG).show();
                    String pay_to = obj.getString("phone");
                    Fragment fragment=new PayFrag(pay_to);
                    loadFragment(fragment);
                    Toast.makeText(getContext(),"Pay Money clicked",Toast.LENGTH_SHORT).show();



                } catch (JSONException e) {
                    e.printStackTrace();
                    //if control comes here
                    //that means the encoded format not matches
                    //in this case you can display whatever data is available on the qrcode
                    //to a toast
                    Toast.makeText(getContext(), result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    @Override
    public void AsyncFinnished(String output) {
        output = output.replace("getBalance","");
        balance.setText(output);
        Toast.makeText(getContext(), "Balance"+output, Toast.LENGTH_SHORT).show();
    }
}
