package com.pabi.my_app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Admin on 1/24/2018.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    Context context;
    ArrayList<String> nameList;
    ArrayList<String> descrList;
    ArrayList<String> imgList;


    class SearchViewHolder extends RecyclerView.ViewHolder{

        TextView name,description;
        ImageView image;
        Button directionsBtn;

        public SearchViewHolder(View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.club_name);
            description = (TextView)itemView.findViewById(R.id.club_descr);
            image = (ImageView) itemView.findViewById(R.id.club_Img);
            directionsBtn = (Button)itemView.findViewById(R.id.BtnDirections);
        }
    }

        public SearchAdapter(Context context, ArrayList<String> nameList, ArrayList<String> descrList, ArrayList<String> imgList) {
        this.context = context;
        this.nameList = nameList;
        this.descrList = descrList;
        this.imgList = imgList;
    }


    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_layout,parent,false);
        return new SearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        holder.name.setText(nameList.get(position));
        holder.description.setText(descrList.get(position));
        Glide.with(context).load(imgList.get(position)).asBitmap().placeholder(R.drawable.placeholder).into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, GalleryActivity.class);
                context.startActivity(i);
            }
        });

        holder.directionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent map = new Intent(context, MapsActivity2.class);
               context.startActivity(map);
            }
        });

    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }
}

