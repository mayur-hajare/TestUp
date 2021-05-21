package com.myur.testup;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {

    View view;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);

    view=itemView;



    }
    public  void setdetails(Context context,String title,String image){

        TextView mtitlev=view.findViewById(R.id.rTextView);
        ImageView mimagev=view.findViewById(R.id.rImageView);

        mtitlev.setText(title);
        Picasso.get().load(image).into(mimagev);

    }
    public  void setnewdetails(Context context,String image){


        ImageView mimagev=view.findViewById(R.id.rImageView2);


        Picasso.get().load(image).into(mimagev);

    }

}
