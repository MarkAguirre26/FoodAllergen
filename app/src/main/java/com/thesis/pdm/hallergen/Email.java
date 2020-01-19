package com.thesis.pdm.hallergen;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

public class Email {

    public static void Send(String rCode,String to) {
        AndroidNetworking.post("http://remmplus.com/sendmail.php")
                .addBodyParameter("to", to)
                .addBodyParameter("code", rCode)
                .setTag("VirificationCode")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }


}
