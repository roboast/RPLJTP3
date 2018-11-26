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

 class AdapterArtikelRecyclerView extends RecyclerView.Adapter<AdapterArtikelRecyclerView.ViewHolder> {

    private ArrayList<Post> dftrArtikel;
    private Context context;
    activity_listArtikel lA;




    public AdapterArtikelRecyclerView(ArrayList<Post> artikel, Context context) {

        dftrArtikel = artikel;
        this.context = context;
        lA = (activity_listArtikel) context;


    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvDesc, tvTanggal;
        ImageView ivFoto;


        ViewHolder(View v) {
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.tvJudul);
            tvDesc = (TextView) v.findViewById(R.id.tvDeskripsi);
            tvTanggal = (TextView) v.findViewById(R.id.tvTanggalArtikel);
            ivFoto = (ImageView) v.findViewById(R.id.img_view);

        }
    }

    @Override
    public AdapterArtikelRecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artikel, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final String name = dftrArtikel.get(position).getJudul();
        final String desk = dftrArtikel.get(position).getDeskripsi();
        final String tgl = dftrArtikel.get(position).getTanggal();
        final String foto = dftrArtikel.get(position).getFoto();

        holder.tvTitle.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_del_up);
                dialog.show();

                Button delBtn = (Button) dialog.findViewById(R.id.btnDelete);
                Button updBtn = (Button) dialog.findViewById(R.id.btnUpdate);


                delBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();
                        lA.hapusData(dftrArtikel.get(position),position);

                    }
                });

                updBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        Intent intent = new Intent(context, activity_post.class);
                        intent.putExtra("data",dftrArtikel.get(position));
                        ((Activity)context).startActivity(intent);

                    }
                });

                return true;
            }
        });
        holder.tvTitle.setText(name);
        holder.tvDesc.setText(desk);
        holder.tvTanggal.setText(tgl);
        Picasso.with(context).load(foto).into(holder.ivFoto);
    }



    @Override
    public int getItemCount() {
        return (dftrArtikel == null) ? 0 : dftrArtikel.size();
    }






}