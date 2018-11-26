package com.example.astidhiyaa.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class activity_home_guest extends AppCompatActivity {
    private DatabaseReference database;
    private TextView judul_berita_terkini;
    private ImageView btn_tiket, foto_berita_terkini;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_guest);

        judul_berita_terkini = findViewById(R.id.tv_judul_berita_terkini);

        btn_tiket = findViewById(R.id.btn_tiket);

        foto_berita_terkini = findViewById(R.id.foto_berita_terkini);

        database = FirebaseDatabase.getInstance().getReference();
        tampilBeritaTerkini();

        btn_tiket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://book.jtp.id/jtpotp/";
                Intent jtp = new Intent(Intent.ACTION_VIEW);
                jtp.setData(Uri.parse(url));
                startActivity(jtp);
            }
        });
    }

    public void tampilBeritaTerkini(){
        database.child("artikel").child("id").limitToLast(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()){
                    Post post = noteDataSnapshot.getValue(Post.class);
                    judul_berita_terkini.setText(post.getJudul());
                    Picasso.with(getApplicationContext()).load(post.getFoto()).into(foto_berita_terkini);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });

    }
}
