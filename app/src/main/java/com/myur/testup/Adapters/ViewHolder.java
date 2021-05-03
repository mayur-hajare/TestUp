package com.myur.testup.Adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myur.testup.R;
import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder{

    View view;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        view=itemView;



    }
    public  void setdetails(Context context,String title,String image){
        TextView mtitle=view.findViewById(R.id.rTitleView);
        ImageView mimage=view.findViewById(R.id.rImageView);

        mtitle.setText(title);
        Picasso.get().load(image).into(mimage);
    }
}
