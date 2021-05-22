package com.myur.testup;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;

import static android.widget.Toast.LENGTH_SHORT;

public class CheckNetworkListner extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {


        if (!Common.isConnectedToInternet(context)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View layout_dialog = LayoutInflater.from(context).inflate(R.layout.check_internet_dialog, null);
            builder.setView(layout_dialog);
            Toast.makeText(context, "Your Offline...", LENGTH_SHORT).show();

            AppCompatButton btnRetry = layout_dialog.findViewById(R.id.btnRetry);

            //show Dialog
            AlertDialog dialog = builder.create();
            dialog.show();
            dialog.setCancelable(false);

            dialog.getWindow().setGravity(Gravity.CENTER);

            btnRetry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    onReceive(context, intent);

                    if (Common.isConnectedToInternet(context)) {
                        Toast.makeText(context, "Your Online...", LENGTH_SHORT).show();
                    }

                }

            });

        }

    }
}
