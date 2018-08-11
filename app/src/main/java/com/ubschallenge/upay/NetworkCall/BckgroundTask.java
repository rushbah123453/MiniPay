package com.ubschallenge.upay.NetworkCall;

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

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by rushabh on 18/2/18.
 */

public class BckgroundTask extends AsyncTask<String, Void, String> {

    Context ctx;
    static MediaType JSON = null;


    public BckgroundTask(Context ctx){
        this.ctx=ctx;
    }

    @Override
    protected String doInBackground(String... voids) {

        String reg_url="http://18.219.109.118:8080/performsignup";
        String method=voids[0];
            String name=voids[1];
            String email=voids[2];
            String passwd=voids[3];
            String cotact=voids[4];
        String aadhar=voids[5];
        Response response=null;

            int responseCode = 0;
        try {

            JSON = MediaType.parse("application/json; charset=utf-8");
            OkHttpClient client = new OkHttpClient();


            JSONObject jsonParam = new JSONObject();
            jsonParam.put("name", name);
            jsonParam.put("aadhaar", aadhar);
            jsonParam.put("email", email);
            jsonParam.put("phone", cotact);
            jsonParam.put("password", passwd);
            jsonParam.put("dob", passwd);

            RequestBody body = RequestBody.create(JSON, jsonParam.toString());




            Request request = new Request.Builder()
                    .url(reg_url)
                    .post(body)
                    .build();

             response = client.newCall(request).execute();
            System.out.print("abc:"+response.message().toString());

           // Toast.makeText(ctx,"Response " + response.toString(),Toast.LENGTH_SHORT).show();

         /*   URL url = new URL(reg_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept","application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            JSONObject jsonParam = new JSONObject();
            jsonParam.put("name", name);
            jsonParam.put("aadhaar", aadhar);
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

            responseCode=conn.getResponseCode();*/


            /*Request request*/



//            Log.i("STATUS", String.valueOf(conn.getResponseCode()));
//            Log.i("MSG" , "abcd");

           // conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
//            Toast.makeText(ctx,"exception"+e.toString(),Toast.LENGTH_SHORT).show();
            System.out.print(e.getMessage().toString());
            String err = String.format("{\"result\":\"false\",\"error\":\"%s\"}", e.getMessage());
            System.out.print(err);

        }



      //  Toast.makeText(ctx,"result"+client.newCall(request).execute();,Toast.LENGTH_SHORT).show();
        try {
            return response.body().string();//name,aadhaar,email,phone,password,dob
        }
        catch(IOException ioe) {
            return "" + ioe.getStackTrace();
        }
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
