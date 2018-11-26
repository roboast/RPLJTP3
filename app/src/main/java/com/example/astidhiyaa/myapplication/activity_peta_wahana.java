package com.example.astidhiyaa.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.chrisbanes.photoview.PhotoView;

public class activity_peta_wahana extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peta_wahana);

        PhotoView peta_wahana = (PhotoView) findViewById(R.id.peta_dino);
        peta_wahana.setImageResource(R.drawable.peta_zona);

    }
}
