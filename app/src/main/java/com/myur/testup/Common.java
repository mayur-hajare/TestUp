package com.myur.testup;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class Common {
    private Context context;
    private boolean isconnected = false;
    private ConnectivityManager connectivityManager;

    public Common(Context context) {
        this.context = context;


    }

    public boolean isInternetConnected() {
        try {
            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            isconnected = networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
            return isconnected;

        } catch (Exception ex) {
            Log.e("Error", ex.getLocalizedMessage());


        }
        return isconnected;


    }

}
