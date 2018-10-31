package com.example.astidhiyaa.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



import com.example.astidhiyaa.myapplication.Post;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

class AdapterArtikelRecyclerView extends RecyclerView.Adapter<AdapterArtikelRecyclerView.ViewHolder> {

    private ArrayList<Post> dftrArtikel;
    private Context context;
    //FirebaseDataListener listener;

    DelDat dd;


    public AdapterArtikelRecyclerView(ArrayList<Post> artikel, Context ctx) {

        dftrArtikel = artikel;
        context = ctx;
        dd = (activity_listArtikel) ctx;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvDesc;


        ViewHolder(View v) {
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.tvJudul);
            tvDesc = (TextView) v.findViewById(R.id.tvDeskripsi);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_artikel, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final String name = dftrArtikel.get(position).getJudul();
        final String desk = dftrArtikel.get(position).getDeskripsi();
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.tvTitle.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_del_up);
                dialog.setTitle("Pilih Aksi");
                dialog.show();

                Button delBtn = (Button) dialog.findViewById(R.id.btnDelete);

                delBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();
                        dd.hapusData(dftrArtikel.get(position),position);

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
        return dftrArtikel.size();
    }

    public interface DelDat{
        void hapusData(Post post, int position);
    }



}