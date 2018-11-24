package com.example.astidhiyaa.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView tmbh_artikel, tmbh_wahana;
    private ImageView list_artikel, list_wahana;

    private ImageView nyoba,coba,lol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tmbh_artikel = findViewById(R.id.btn_tambah_artikel);
        tmbh_wahana = findViewById(R.id.btn_tambah_wahana);
        list_artikel = findViewById(R.id.btn_artikel);
        list_wahana = findViewById(R.id.btn_wahana);

        nyoba = findViewById(R.id.btn_tambah_lost);
        coba = findViewById(R.id.btn_lost);
        lol = findViewById(R.id.btn_cek);

        coba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nyoba = new Intent(MainActivity.this,activity_wahana_guest.class);
                startActivity(nyoba);
            }
        });

        nyoba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nyoba = new Intent(MainActivity.this,activity_home_guest.class);
                startActivity(nyoba);
            }
        });
        lol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ya = new Intent(MainActivity.this,activity_berita_guest.class);
                startActivity(ya);
            }
        });

        tmbh_artikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent postArtikel = new Intent(MainActivity.this, activity_post.class);
                startActivity(postArtikel);
            }
        });

        tmbh_wahana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent postWahana = new Intent(MainActivity.this,activity_wahana.class);
                startActivity(postWahana);
            }
        });

        list_artikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lihat = new Intent(MainActivity.this,activity_listArtikel.class);
                startActivity(lihat);
            }
        });

        list_wahana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lihatWahana = new Intent(MainActivity.this,activity_listwahana.class);
                startActivity(lihatWahana);
            }
        });
    }
}
