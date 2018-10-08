package com.ubschallenge.upay.Passbook;
import com.ubschallenge.upay.HomeFrag.AddMoneyFrag;
import com.ubschallenge.upay.HomeFrag.HomeFrag;
import com.ubschallenge.upay.MainActivity;
import com.ubschallenge.upay.NetworkCall.AsyncResponse;
import com.ubschallenge.upay.NetworkCall.BckgroundTask;
import com.ubschallenge.upay.R;
import com.ubschallenge.upay.SignUp.Signup;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SyncStatusObserver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;


public class Passbook extends AppCompatActivity implements AsyncResponse {


    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList data;
    private static Map<String, Object> myMap;
    private static String phone_number = "1234";
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private static TextView balance;
    //private static Button proceed_add;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passbook);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
            balance=(TextView)findViewById(R.id.balanceValue);
            SharedPreferences sharedPreferences=this.getSharedPreferences("pref", Context.MODE_PRIVATE);
            BckgroundTask bckgroundTask=new BckgroundTask(Passbook.this);
            String phonenumber=sharedPreferences.getString("sharedPhoneno", "default value");

            //Toast.makeText(this,"shared phone"+phonenumber,Toast.LENGTH_SHORT).show();
            bckgroundTask.output=Passbook.this;
            bckgroundTask.execute("getBalance",phonenumber);
            BckgroundTask bckgroundTask1=new BckgroundTask(Passbook.this);
           bckgroundTask1.output=Passbook.this;
        bckgroundTask1.execute("getpassbook", phonenumber);
        /*proceed_add =(Button) findViewById(R.id.proceed_add2);
            proceed_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fragment=new AddMoneyFrag();
                    loadFragment(fragment);
                }
            });*/


        }

   /* private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }*/

    @Override
    public void AsyncFinnished(String output) {
            if(output.startsWith("getBalance"))
            {
                output = output.replace("getBalance","");
                balance.setText(output+" ₹");
            }

            Log.i("Passbook Output :" ,output);
            try {

                JSONObject Jobject = new JSONObject(output);
                myMap =  JSONUtil.toMap(Jobject);
                Log.i("Mapped Passbook val",myMap.toString());
                data = new ArrayList<DataModel>();
                ArrayList l = new ArrayList(myMap.keySet());
                System.out.println("LIST>>>"+l);
                ArrayList keyList = new ArrayList();
                for(int i = 0;i<l.size();i++)
                {
                    keyList.add(Integer.parseInt(l.get(i).toString()));
                }
                Collections.sort(keyList);

                System.out.println("FINAL>>"+keyList);

                for (int i = keyList.size()-1 ; i >= 1; i--) {
                //for (Map.Entry<String, Object> entry : myMap.entrySet()) {
                    String key = ""+keyList.get(i);
                    System.out.println("CHECK >>> "+key + "/" + myMap.get(key));

                    String temp = myMap.get(key).toString().replace("[","");
                    temp = temp.replace("]","");
                    System.out.println(key + "/" + temp);
                    String val[] = temp.split(",");
                    String search_for="";
                    String amt ="";
                    String name = null;
                    String time = "";
                    String name_received = val[4];
                    //Log.i("STring check","a"+val[1].trim()+"a  a"+phone_number+"a "+val[1].trim().equals(phone_number) );
                    if(val[1].trim().equals(phone_number) )
                    {
                        search_for = val[0];

                        amt = "+ ₹ "+val[2];
                    }
                    else
                    {
                        search_for = val[1];
                        amt = "- ₹ "+val[2];

                    }
                   // name = showContacts(search_for,this);
                    if (name == null)
                    {
                        name = name_received;
                    }
                    time = getTime(val[3].toString());


                    Log.i("Before adapter",val[0]+" "+name+" "+amt+" "+time);
                    data.add(new DataModel(
                            Integer.parseInt(key),
                            val[0], //from
                            name, //to
                           amt, //amt
                           time  //time
                    ));
                }

                //Toast.makeText(this,"Data "+data,Toast.LENGTH_SHORT).show();

                adapter = new Adapter(data);
                recyclerView.setAdapter(adapter);

                adapter.notifyDataSetChanged();



            }
            catch(Exception e)
            {
                Log.i("Error in Passbook",e.getMessage());
            }
        }

    private String getTime(String s) {
            String ret_val = "";
            try
            {
                //Log.i("Date Input",s);
                SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date=formatter.parse(s);
                formatter = new SimpleDateFormat("dd MMM yyyy    HH:mm:ss");
                ret_val = formatter.format(date);
                Log.i("Date converted",ret_val);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }


            return  ret_val;
    }

    private String showContacts(String phone_number,Context con) {
            String number = null;
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        } else {
            // Android version is lesser than 6.0 or the permission is already granted.

            number = getContactName(phone_number,con);

        }
        return number;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                showContacts(phone_number,this);
            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public String getContactName(String phoneNumber,Context context)
    {
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));
        Cursor cursor = cr.query(uri, new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME}, null, null, null);
        if (cursor == null) {
            return null;
        }
        String contactName = null;
        if(cursor.moveToFirst()) {
            contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
        }

        if(cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return contactName;
    }

}
