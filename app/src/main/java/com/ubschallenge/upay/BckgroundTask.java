package com.ubschallenge.upay;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by rushabh on 18/2/18.
 */

public class BckgroundTask extends AsyncTask<String, Void, String> {

    Context ctx;

    BckgroundTask(Context ctx){
        this.ctx=ctx;
    }

    @Override
    protected String doInBackground(String... voids) {

        String reg_url="http://103.96.43.156:8080/performsignup";
        String method=voids[0];
            String name=voids[1];
            String email=voids[2];
            String passwd=voids[3];
            String cotact=voids[4];

            int responseCode = 0;
        try {

            URL url = new URL(reg_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept","application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            JSONObject jsonParam = new JSONObject();
            jsonParam.put("name", name);
            jsonParam.put("aadhaar", email);
            jsonParam.put("email", email);
            jsonParam.put("phone", cotact);
            jsonParam.put("password", passwd);
            jsonParam.put("dob", passwd);

            Log.i("JSON", jsonParam.toString());
            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
            os.writeBytes(jsonParam.toString());


            os.flush();
            os.close();

            conn.connect();

            responseCode=conn.getResponseCode();

//            Log.i("STATUS", String.valueOf(conn.getResponseCode()));
//            Log.i("MSG" , "abcd");

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }




            return "" + responseCode;//name,aadhaar,email,phone,password,dob
    }

    public BckgroundTask() {
        super();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(ctx,"result"+result,Toast.LENGTH_SHORT).show();

            /*    Intent intentnew=new Intent(ctx,MainActivity.class);
        ctx.startActivity(intentnew);*/
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}
