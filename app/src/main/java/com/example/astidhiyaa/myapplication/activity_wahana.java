package com.example.astidhiyaa.myapplication;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_wahana extends AppCompatActivity {

    private DatabaseReference db;
    private EditText etNama;
    private EditText etDeskripsi;
    private Button btnwahana;
    private AdapterArtikelRecyclerView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wahana);

        db = FirebaseDatabase.getInstance().getReference("wahana");

        etNama = (EditText) findViewById(R.id.textNama);
        etDeskripsi = (EditText) findViewById(R.id.textDesc);
        btnwahana = (Button) findViewById(R.id.btnSubmit);
        final Wahana wahana = (Wahana) getIntent().getSerializableExtra("data");

        if (wahana != null){
            etNama.setText(wahana.getNama());
            etDeskripsi.setText(wahana.getDeskripsi());

            btnwahana.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wahana.setNama(etNama.getText().toString());
                    wahana.setDeskripsi(etDeskripsi.getText().toString());
                    updateWahana(wahana);
                }
            });
        }
        else{
            btnwahana.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    submitWahana();
                }
            });
        }
    }

    public void submitWahana() {
        String wahana_id = db.push().getKey();
        Wahana wahana = new Wahana(etNama.getText().toString(), etDeskripsi.getText().toString());
        db.child("id").push().setValue(wahana).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                etNama.setText("");
                etDeskripsi.setText("");
                Snackbar.make(findViewById(R.id.btnSubmit), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public void updateWahana(Wahana wahana){
        db.child("id").child(wahana.getId()).setValue(wahana).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Snackbar.make(findViewById(R.id.btnSubmit), "Data berhasil diupdate", Snackbar.LENGTH_LONG).show();
            }
        });
    }

}
