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

class AdapterWahanaGuest extends RecyclerView.Adapter<AdapterWahanaGuest.ViewHolder> {

    private ArrayList<Wahana> dftrWahana;
    private Context context;
    activity_wahana_guest lW;




    public AdapterWahanaGuest(ArrayList<Wahana> wahana, Context context) {

        dftrWahana = wahana;
        this.context = context;
        lW = (activity_wahana_guest) context;


    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_nama;
        ImageView iv_foto;


        ViewHolder(View v) {
            super(v);
            tv_nama = (TextView) v.findViewById(R.id.nama_wahana);
            iv_foto = (ImageView) v.findViewById(R.id.foto_wahana);
        }
    }

    @Override
    public AdapterWahanaGuest.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wahana_guest, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {



        final  String nama = dftrWahana.get(position).getNama();
        final String foto = dftrWahana.get(position).getFoto();

        holder.iv_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, activity_detail_wahana.class);
                intent.putExtra("data",dftrWahana.get(position));
                ((Activity)context).startActivity(intent);
            }
        });

        holder.tv_nama.setText(nama);
        Picasso.with(context).load(foto).into(holder.iv_foto);
    }



    @Override
    public int getItemCount() {
        return (dftrWahana == null) ? 0 : dftrWahana.size();
    }



}