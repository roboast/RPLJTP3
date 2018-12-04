package com.example.astidhiyaa.myapplication;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class activity_listArtikel extends AppCompatActivity  {
    private DatabaseReference database;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Berita> dftrArtikel;
    private FirebaseStorage storage;
    private StorageReference storageReference;


    public activity_listArtikel(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listartikel);

        rvView = (RecyclerView) findViewById(R.id.rv_main);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);
        database = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance().getReference();
        storage = FirebaseStorage.getInstance();


        tampilData();


    }

    public void tampilData(){

        database.child("artikel").child("id").orderByChild("tanggal").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                dftrArtikel = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {

                    Berita post = noteDataSnapshot.getValue(Berita.class);
                    post.setId(noteDataSnapshot.getKey());


                    dftrArtikel.add(post);
                }

                adapter = new AdapterArtikelRecyclerView(dftrArtikel, activity_listArtikel.this);
                rvView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
    }




    public void hapusData(final Berita post, final int position) {
        storageReference = storage.getReferenceFromUrl(post.getFoto());
        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(activity_listArtikel.this,"Gambar Berhasil terhapus",Toast.LENGTH_SHORT);
                database.child("artikel").child("id").child(post.getId()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(activity_listArtikel.this,"Berhasil terhapus",Toast.LENGTH_SHORT);
                    }
                });
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(activity_listArtikel.this,"Gagal terhapus",Toast.LENGTH_SHORT);
                    }
                });
    }


    }

