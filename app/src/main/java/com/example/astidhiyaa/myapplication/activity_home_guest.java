package com.example.astidhiyaa.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class activity_home_guest extends AppCompatActivity {
    private DatabaseReference database;
    private TextView judul_berita_terkini, deskripsi_berita_terkini;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_guest);

        judul_berita_terkini = findViewById(R.id.tv_judul_berita_terkini);
        deskripsi_berita_terkini = findViewById(R.id.tv_deskripsi_berita_terkini);

        database = FirebaseDatabase.getInstance().getReference();
        tampilBeritaTerkini();
    }

    public void tampilBeritaTerkini(){
        database.child("artikel").child("id").limitToLast(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()){
                    Post post = noteDataSnapshot.getValue(Post.class);
                    judul_berita_terkini.setText(post.getJudul());
                    deskripsi_berita_terkini.setText(post.getDeskripsi());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });

    }
}
