package com.example.astidhiyaa.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



import com.example.astidhiyaa.myapplication.Post;
import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;
import java.util.ArrayList;

class AdapterWahanaRecyclerView extends RecyclerView.Adapter<AdapterWahanaRecyclerView.ViewHolder> {

    private ArrayList<Wahana> dftrWahana;
    private Context context;
    activity_listwahana lW;




    public AdapterWahanaRecyclerView(ArrayList<Wahana> wahana, Context context) {

        dftrWahana = wahana;
        this.context = context;
        lW = (activity_listwahana) context;


    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvDesc;


        ViewHolder(View v) {
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.tvNamaWahana);
            tvDesc = (TextView) v.findViewById(R.id.tvDeskripsiWahana);

        }
    }

    @Override
    public AdapterWahanaRecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wahana, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final String name = dftrWahana.get(position).getNama();
        final String desk = dftrWahana.get(position).getDeskripsi();

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
                        lW.hapusData(dftrWahana.get(position),position);

                    }
                });

                updBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        Intent intent = new Intent(context, activity_wahana.class);
                        intent.putExtra("data",dftrWahana.get(position));
                        ((Activity)context).startActivity(intent);

                    }
                });

                return true;
            }
        });
        holder.tvTitle.setText(name);
        holder.tvDesc.setText(desk);
    }



    @Override
    public int getItemCount() {
        return (dftrWahana == null) ? 0 : dftrWahana.size();
    }






}