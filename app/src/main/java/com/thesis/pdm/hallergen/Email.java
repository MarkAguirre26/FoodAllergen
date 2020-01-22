package com.thesis.pdm.hallergen;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Email {

    public static void Send(Context ctx, String rCode, String to) {
// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(ctx);
        String url = "http://remmplus.com/sendmail.php?to=" + to + "&code=" + rCode;

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
//                        textView.setText("Response is: " + response.substring(0, 500));
                        Log.d("responseHere", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);


    }


}
