package com.thesis.pdm.hallergen;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class UtilityNetworkConnectivity {

    public static boolean checkNetworkConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Toast.makeText(context, "Great Connected", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(context, "No internet connections", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}

