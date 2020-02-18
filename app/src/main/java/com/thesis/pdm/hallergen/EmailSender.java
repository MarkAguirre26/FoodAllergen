package com.thesis.pdm.hallergen;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class EmailSender {

    public static String Send(Context ctx, final String rCode, final String to) {

        RequestQueue MyRequestQueue = Volley.newRequestQueue(ctx);
        String url = "http://remmplus.com/sendmail.php?";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("responseHEre", response);
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VolleyError", error.getMessage());
                //This code is executed if there is an error.
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("to", to); //Add the data you'd like to send to the server.
                MyData.put("code", rCode); //Add the data you'd like to send to the server.
                return MyData;
            }
        };


        MyRequestQueue.add(MyStringRequest);

        return "ok";
    }


}
