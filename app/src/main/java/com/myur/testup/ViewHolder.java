package com.myur.testup;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView textView;
    TextView des;
    View view;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        view=itemView;

        imageView=itemView.findViewById(R.id.rImageView);
        textView=itemView.findViewById(R.id.rTextView);
        des=itemView.findViewById(R.id.rDes);




    }

}
