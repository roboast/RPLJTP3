package com.example.astidhiyaa.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class activity_detail_wahana extends AppCompatActivity {

    private TextView nama, deskripsi;
    private ImageView foto, exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_wahana);

        nama = findViewById(R.id.detail_nama_wahana);
        deskripsi = findViewById(R.id.deskripsi_detail_wahana);
        foto = findViewById(R.id.foto_detail_wahana);
        exit = findViewById(R.id.back_detail_wahana);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        final Wahana wahana = (Wahana) getIntent().getSerializableExtra("data");

        nama.setText(wahana.getNama());
        deskripsi.setText(wahana.getDeskripsi());
        Picasso.with(getApplicationContext()).load(wahana.getFoto()).into(foto);
    }
}
