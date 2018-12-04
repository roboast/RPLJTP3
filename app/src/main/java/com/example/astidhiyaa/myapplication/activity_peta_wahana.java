package com.example.astidhiyaa.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;

public class activity_peta_wahana extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private ImageView exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peta_wahana);

        exit = findViewById(R.id.back_peta);

        PhotoView peta_wahana = (PhotoView) findViewById(R.id.peta_dino);
        peta_wahana.setImageResource(R.drawable.peta_zona);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
}
