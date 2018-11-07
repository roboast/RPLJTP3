package com.example.astidhiyaa.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnTambah, btnLihat, btnHome;
    private Button btnTmbhWhn, btnLihatWhn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTambah = findViewById(R.id.btnTambah);
        btnLihat = findViewById(R.id.btnLihat);


        btnTmbhWhn = findViewById(R.id.btn_tmbhWahana);
        btnLihatWhn = findViewById(R.id.btn_lihatWahana);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent postArtikel = new Intent(MainActivity.this, activity_post.class);
                startActivity(postArtikel);
            }
        });


        btnLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lihat = new Intent(MainActivity.this,activity_listArtikel.class);
                startActivity(lihat);
            }
        });

        btnTmbhWhn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent postWahana = new Intent(MainActivity.this,activity_wahana.class);
                startActivity(postWahana);
            }
        });
        btnLihatWhn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lihatWahana = new Intent(MainActivity.this,activity_listwahana.class);
                startActivity(lihatWahana);
            }
        });
    }}
