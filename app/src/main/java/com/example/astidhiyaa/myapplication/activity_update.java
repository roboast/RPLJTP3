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

public class activity_update extends AppCompatActivity {

    private Button btnUpdate;
    private EditText etJudul, etDeskripsi;
    DatabaseReference db = FirebaseDatabase.getInstance().getReference("artikel");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        etJudul = (EditText) findViewById(R.id.textJudul);
        etDeskripsi = (EditText) findViewById(R.id.textDesc);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateArtikel();
            }
        });
    }

    private void updateArtikel(){
        String artikel = db.push().getKey();
        Post post = new Post(etJudul.getText().toString(), etDeskripsi.getText().toString());
        db.child("id").push().setValue(post).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Snackbar.make(findViewById(R.id.btnSubmit), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
