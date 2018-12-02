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
        //bottomNavigationView = findViewById(R.id.nav_bottom_peta);
        exit = findViewById(R.id.back_peta);

        PhotoView peta_wahana = (PhotoView) findViewById(R.id.peta_dino);
        peta_wahana.setImageResource(R.drawable.peta_zona);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

      /**  bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.goHome:
                        Intent peta = new Intent(activity_peta_wahana.this,activity_home_guest.class);
                        startActivity(peta);
                        break;
                    case R.id.goMaps:
                        break;
                    case R.id.goBerita:
                        Intent berita = new Intent(activity_peta_wahana.this,activity_berita_guest.class);
                        startActivity(berita);
                        break;
                    case R.id.goWahana:
                        Intent wahana = new Intent(activity_peta_wahana.this,activity_wahana_guest.class);
                        startActivity(wahana);
                        break;
                }
                return false;
            }
        });
*/

    }
}
