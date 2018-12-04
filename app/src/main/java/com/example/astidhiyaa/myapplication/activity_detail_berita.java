package com.example.astidhiyaa.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class activity_detail_berita extends AppCompatActivity {
    private ImageView iv_foto, exit;
    private TextView judul, tgl, deskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);

        iv_foto = findViewById(R.id.iv_berita);
        judul = findViewById(R.id.tv_judul_berita);
        tgl = findViewById(R.id.tv_tanggal_berita);
        deskripsi = findViewById(R.id.tv_deskripsi_berita);
        exit = findViewById(R.id.back_detail_berita);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final Berita post = (Berita) getIntent().getSerializableExtra("data");

        judul.setText(post.getJudul());
        deskripsi.setText(post.getDeskripsi());
        Picasso.with(getApplicationContext()).load(post.getFoto()).into(iv_foto);

    }
}
