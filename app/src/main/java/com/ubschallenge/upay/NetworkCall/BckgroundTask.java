package com.ubschallenge.upay.NetworkCall;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.ubschallenge.upay.HomeScreens.HomeBottomNav;
import com.ubschallenge.upay.MainActivity;
import com.ubschallenge.upay.SignUp.Otp;
import com.ubschallenge.upay.SignUp.Signup;

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
import okhttp3.ResponseBody;

/**
 * Created by rushabh on 18/2/18.
 */

public class BckgroundTask  extends AsyncTask<String, Void, String>  {

    Context ctx;
    static MediaType JSON = null;
    Response responseGlobal;
    String methodType=null;
    ResponseBody responseBody;
    String finalResult;
    public AsyncResponse output=null;

    public BckgroundTask(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected String doInBackground(String... voids) {

        String reg_url = "http://18.219.106.142:8080/performsignup";
        String login_url="http://18.219.106.142:8080/performlogin";
        String validSignup="http://18.219.106.142:8080/validatesignup";
        String getPassbook = "http://18.219.106.142:8080/getpassbook";
        String addmoney = "http://18.219.106.142:8080/addmoney";

        String method = voids[0];

        Response response = null;

        //int responseCode = 0;
        try {

            JSON = MediaType.parse("application/json; charset=utf-8");
            OkHttpClient client = new OkHttpClient();





            if (method.equals("signup")) {
                String name = voids[1];
                String email = voids[2];
                String passwd = voids[3];
                String cotact = voids[4];
                String aadhar = voids[5];
                methodType="signup";
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
              //  System.out.print("Response:" + response.body());
            } else if (method.equals("signin")) {
                System.out.print("in  else block");

                String phone = voids[1];
                String password = voids[2];
                methodType="signin";
                JSONObject jsonParam1 = new JSONObject();
                jsonParam1.put("phone", phone);
                jsonParam1.put("password", password);

                RequestBody body = RequestBody.create(JSON, jsonParam1.toString());
                Request request = new Request.Builder()
                        .url(login_url)
                        .post(body)
                        .build();

                response = client.newCall(request).execute();
//                System.out.print("Response:" + response.body());


            }
            else if(method.equals("validateAlreadyExistingUser")){

                String phone = voids[1];
                methodType="validateAlreadyExistingUser";
                JSONObject jsonParam2 = new JSONObject();
                jsonParam2.put("phone", phone);

                RequestBody body = RequestBody.create(JSON, jsonParam2.toString());
                Request request = new Request.Builder()
                        .url(validSignup)
                        .post(body)
                        .build();

                response = client.newCall(request).execute();

            }
            else if(method.equals("getpassbook"))
            {
                System.out.print("In get Passbook block");
                String phone = voids[1];
                methodType="getpassbook";
                JSONObject jsonParam1 = new JSONObject();
                jsonParam1.put("phone", phone);
                RequestBody body = RequestBody.create(JSON, jsonParam1.toString());
                Request request = new Request.Builder()
                        .url(getPassbook)
                        .post(body)
                        .build();
                response = client.newCall(request).execute();
//                System.out.print("Response:" + response.body());
            }
            else if(method.equals("addmoney"))
            {
                System.out.print("In Add Money block");
                String phone = voids[1];
                String amt = voids[2];
                methodType="addmoney";
                JSONObject jsonParam1 = new JSONObject();
                jsonParam1.put("tophone", phone);
                jsonParam1.put("amount", amt);
                RequestBody body = RequestBody.create(JSON, jsonParam1.toString());
                Request request = new Request.Builder()
                        .url(addmoney)
                        .post(body)
                        .build();
                response = client.newCall(request).execute();
//                System.out.print("Response:" + response.body());
            }





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
          //  String err = String.format("{\"result\":\"false\",\"error\":\"%s\"}", e.getMessage());
            //System.out.print(err);

        }

       // responseGlobal = response;

      //  Toast.makeText(ctx,"result"+client.newCall(request).execute(),Toast.LENGTH_SHORT).show();
      /*  try {
           Log.e("in try",response.body().string());
            return  response.body().string();       //name,aadhaar,email,phone,password,dob
          //  Log.e("Resonse",response.body().string());




        } catch (IOException ioe) {
            return "" + ioe.getStackTrace();

        }*/
        String content = null;



          try {

                  responseBody = response.body();
              content = responseBody.string();
          } catch (Exception e) {
              e.printStackTrace();
              content="Server_error";
          }

            /*  ResponseBody responseBodyCopy = response.peekBody(Long.MAX_VALUE);
            String content= responseBodyCopy.string();*/


        return content;


       // return "abc";


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



        finalResult=result;
            /*    Intent intentnew=new Intent(ctx,MainActivity.class);
        ctx.startActivity(intentnew);*/


            Toast.makeText(ctx,"this is response "+result,Toast.LENGTH_SHORT).show();

            if(result.equals("1") && methodType.equals("signin"))
            {
                Toast.makeText(ctx,"Succesfull login  "+result,Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(ctx,HomeBottomNav.class);
                ctx.startActivity(intent);
            }
            else if (result.equals("Server_error")){
                Toast.makeText(ctx,"In Dialog Box",Toast.LENGTH_SHORT).show();

                AlertDialog.Builder alBuilder=new AlertDialog.Builder(ctx);

                alBuilder.setTitle("Server Error").setMessage("Please try after sometime");
                alBuilder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(ctx,"Dialog Box "+"Retry Clicked",Toast.LENGTH_SHORT).show();
                    }
                });

                alBuilder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(ctx,"Dialog Box "+"Back Clicked",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(ctx, Signup.class);
                        ctx.startActivity(intent);
                    }
                });

                alBuilder.show();

            }
            else if (result.equals("1062") && methodType.equals("signup")){
                AlertDialog.Builder alBuilder=new AlertDialog.Builder(ctx);

                alBuilder.setTitle("User Already Exist!").setMessage("Please Add  different number to same email id");


                alBuilder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(ctx,"Dialog Box "+"Okay Clicked",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(ctx, Signup.class);
                        ctx.startActivity(intent);
                    }
                });

                alBuilder.show();
            }


            else if(result.equals("false") && methodType.equals("validateAlreadyExistingUser")){

                Toast.makeText(ctx,"User Already Existing",Toast.LENGTH_SHORT).show();
                output.AsyncFinnished(result);
            }

            else if(result.equals("true") && methodType.equals("validateAlreadyExistingUser")){
                Toast.makeText(ctx,"User Not Existing",Toast.LENGTH_SHORT).show();
                output.AsyncFinnished(result);



            }
            else if(methodType.equals("getpassbook"))
            {
                Toast.makeText(ctx,"Passbook details fetched",Toast.LENGTH_SHORT).show();
                output.AsyncFinnished(result);
            }
            else if(methodType.equals("addmoney"))
            {
                Toast.makeText(ctx,"Money Added",Toast.LENGTH_SHORT).show();
                output.AsyncFinnished(result);
            }



    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }





}
