package com.example.astidhiyaa.myapplication;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.example.astidhiyaa.myapplication.Post;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_post extends AppCompatActivity {

    private DatabaseReference db;

    private EditText etJudul;
    private EditText etDeskripsi;
    private Button btnPost, btnUpdate;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        db = FirebaseDatabase.getInstance().getReference("artikel");

        etJudul = (EditText) findViewById(R.id.textJudul);
        etDeskripsi = (EditText) findViewById(R.id.textDesc);
        btnPost = (Button) findViewById(R.id.btnSubmit);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitArtikel();
            }
        });



    }

    public void submitArtikel() {
        String artikel = db.push().getKey();
        Post post = new Post(etJudul.getText().toString(), etDeskripsi.getText().toString());
        db.child("id").push().setValue(post).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                etJudul.setText("");
                etDeskripsi.setText("");
                Snackbar.make(findViewById(R.id.btnSubmit), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
            }
        });
    }



}
