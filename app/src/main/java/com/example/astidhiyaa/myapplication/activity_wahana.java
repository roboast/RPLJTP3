package com.example.astidhiyaa.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

public class activity_wahana extends AppCompatActivity {

    private DatabaseReference db;

    private EditText etNama;
    private EditText etDeskripsi;
    private Button btnwahana, btn, btnU;
    private AdapterArtikelRecyclerView adapter;

    private FirebaseStorage storage;
    private StorageReference storageReference;
    private static final int PICK_IMAGE_REQUEST = 234;

    private Uri filePath;
    private ImageView imageView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wahana);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();


        db = FirebaseDatabase.getInstance().getReference("wahana");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReferenceFromUrl("gs://blog-4f92e.appspot.com");


        etNama = (EditText) findViewById(R.id.textNama);
        etDeskripsi = (EditText) findViewById(R.id.textDesc);
        btnwahana = (Button) findViewById(R.id.btnSubmit);
        btn = (Button) findViewById(R.id.btn_choose);

        final Wahana wahana = (Wahana) getIntent().getSerializableExtra("data");

        imageView = findViewById(R.id.imageView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });



        if (wahana != null){
            etNama.setText(wahana.getNama());
            etDeskripsi.setText(wahana.getDeskripsi());
            Picasso.with(getApplicationContext()).load(wahana.getFoto()).into(imageView);


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

        if (filePath != null){
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading..");
            progressDialog.show();

            final StorageReference ref = storageReference.child("images/"+UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Uri downloadUrl = uri;
                                    String wahana_id = db.push().getKey();
                                    String date_now = DateFormat.getDateTimeInstance().format(new Date());

                                    Wahana wahana = new Wahana(etNama.getText().toString(), etDeskripsi.getText().toString(),date_now,downloadUrl.toString());

                                    db.child("id").push().setValue(wahana).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            etNama.setText("");
                                            etDeskripsi.setText("");
                                            Snackbar.make(findViewById(R.id.btnSubmit), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
                                        }
                                    });
                                }
                            });
                            Toast.makeText(activity_wahana.this,"Uploaded",Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(activity_wahana.this,"Failed"+e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploaded.."+(int)progress+"%");
                        }
                    });

        }
    }

    public void updateWahana(Wahana wahana){
        db.child("id").child(wahana.getId()).setValue(wahana).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Snackbar.make(findViewById(R.id.btnSubmit), "Data berhasil diupdate", Snackbar.LENGTH_LONG).show();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
}
