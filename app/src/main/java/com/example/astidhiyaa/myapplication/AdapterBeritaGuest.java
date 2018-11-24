package com.example.astidhiyaa.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class AdapterBeritaGuest extends RecyclerView.Adapter<AdapterBeritaGuest.ViewHolder> {

    private ArrayList<Post> dftrBerita;
    private Context context;
    activity_berita_guest lW;




    public AdapterBeritaGuest(ArrayList<Post> post, Context context) {

        dftrBerita = post;
        this.context = context;
        lW = (activity_berita_guest) context;


    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_judul, tv_tanggal;
        ImageView iv_foto;


        ViewHolder(View v) {
            super(v);
            tv_judul = v.findViewById(R.id.tv_judul_berita);
            tv_tanggal = v.findViewById(R.id.tv_tanggal_berita);
            iv_foto = v.findViewById(R.id.foto_berita);
        }
    }

    @Override
    public AdapterBeritaGuest.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_berita_guest, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {



        final  String judul = dftrBerita.get(position).getJudul();
        final String tanggal = dftrBerita.get(position).getTanggal();

        holder.tv_judul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        holder.tv_judul.setText(judul);
        holder.tv_tanggal.setText(tanggal);

    }



    @Override
    public int getItemCount() {
        return (dftrBerita == null) ? 0 : dftrBerita.size();
    }



}