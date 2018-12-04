package com.example.astidhiyaa.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class activity_wahana_guest extends AppCompatActivity {
    private DatabaseReference database;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Wahana> dftrWahana;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private ImageView exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wahana_guest);

        rvView = (RecyclerView) findViewById(R.id.rvNumbers);
        int jlh_kolom = 2;
        rvView.setLayoutManager(new GridLayoutManager(this,jlh_kolom));
        database = FirebaseDatabase.getInstance().getReference();
        storage = FirebaseStorage.getInstance();
        exit = findViewById(R.id.back_wahana);

        tampilData();

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    public void tampilData(){
        database.child("wahana").child("id").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                dftrWahana = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {

                    Wahana wahana = noteDataSnapshot.getValue(Wahana.class);
                    wahana.setId(noteDataSnapshot.getKey());

                    dftrWahana.add(wahana);
                }

                adapter = new AdapterWahanaGuest(dftrWahana, activity_wahana_guest.this);
                rvView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
    }
}
