package com.example.astidhiyaa.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Main2Activity extends AppCompatActivity {
    private Button guest,admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        guest = findViewById(R.id.btn_guest);
        admin  = findViewById(R.id.btn_admin);

        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nyoba = new Intent(Main2Activity.this,activity_home_guest.class);
                startActivity(nyoba);
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nyoba = new Intent(Main2Activity.this,activity_login.class);
                startActivity(nyoba);
            }
        });


    }
}
