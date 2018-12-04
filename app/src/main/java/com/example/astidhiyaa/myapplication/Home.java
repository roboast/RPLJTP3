package com.example.astidhiyaa.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    private DatabaseReference database;
    private TextView tvJudul, tvDesk;
    Berita post;
    ArrayList dftrArtikel;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tvJudul = findViewById(R.id.tvJudulBaru);
        tvDesk = findViewById(R.id.tvDeskripsiBaru);


        tampilBeritaTerbaru();

    }

    void tampilBeritaTerbaru(){

        //Post post = new Post();
     FirebaseDatabase.getInstance().getReference("artikel").child("id").limitToFirst(1).addValueEventListener(new ValueEventListener() {

         //Post post = new Post();
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             //dftrArtikel = new ArrayList<>();
             //DataSnapshot noteDataSnapshot = (DataSnapshot) dataSnapshot.getChildren();


                 tvJudul.setText(dataSnapshot.getValue(Berita.class).getJudul());
                 tvDesk.setText(dataSnapshot.getValue(Berita.class).getDeskripsi());


                 //dftrArtikel.add(post);


             }


         //}

         @Override
         public void onCancelled(DatabaseError databaseError) {
             System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
         }
     });

    }
}

