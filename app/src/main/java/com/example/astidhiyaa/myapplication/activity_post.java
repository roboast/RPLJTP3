package com.example.astidhiyaa.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.Intent;

import com.example.astidhiyaa.myapplication.Post;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

public class activity_post extends AppCompatActivity {

    private DatabaseReference db;
    private EditText etJudul;
    private EditText etDeskripsi;
    private Button btnPost, btnUpdate,btnSelect;
    private AdapterArtikelRecyclerView adapter;

    private FirebaseStorage storage;
    private StorageReference storageReference;
    private static final int PICK_IMAGE_REQUEST = 234;

    private Uri filePath;
    private ImageView imageView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        db = FirebaseDatabase.getInstance().getReference("artikel");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReferenceFromUrl("gs://blog-4f92e.appspot.com");

        etJudul = (EditText) findViewById(R.id.textJudul);
        etDeskripsi = (EditText) findViewById(R.id.textDesc);
        btnPost = (Button) findViewById(R.id.btnSubmit);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        final Post post = (Post) getIntent().getSerializableExtra("data");

        imageView = findViewById(R.id.iv_artikel);
        btnSelect = findViewById(R.id.btn_pilih);

        if (post != null){
            etJudul.setText(post.getJudul());
            etDeskripsi.setText(post.getDeskripsi());

            btnPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    post.setJudul(etJudul.getText().toString());
                    post.setDeskripsi(etDeskripsi.getText().toString());
                    updateArtikel(post);
                }
            });
        }
        else{
            btnPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    submitArtikel();
                }
            });
        }


        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });

    }

    public void submitArtikel() {
        if (filePath != null){
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading..");
            progressDialog.show();

            final StorageReference ref = storageReference.child("artikel/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Uri downloadUrl = uri;

                                    String artikel = db.push().getKey();
                                    String date_now = DateFormat.getDateTimeInstance().format(new Date());
                                    Post post = new Post(etJudul.getText().toString(), etDeskripsi.getText().toString(),date_now,downloadUrl.toString());

                                    db.child("id").push().setValue(post).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            etJudul.setText("");
                                            etDeskripsi.setText("");
                                            Snackbar.make(findViewById(R.id.btnSubmit), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
                                        }
                                    });
                                }
                            });
                            Toast.makeText(activity_post.this,"Uploaded",Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(activity_post.this,"Failed"+e.getMessage(),Toast.LENGTH_SHORT).show();
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

    public void updateArtikel(final Post post){
        storageReference = storage.getReferenceFromUrl(post.getFoto());
        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                StorageReference storageRefo = storage.getReferenceFromUrl("gs://blog-4f92e.appspot.com");
                final StorageReference refo = storageRefo.child("artikel/"+UUID.randomUUID().toString());
                refo.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        refo.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Uri downloadUrl = uri;
                                Post pst = new Post(post.getJudul(),post.getDeskripsi(),post.getTanggal(),downloadUrl.toString());
                                db.child("id").child(post.getId()).setValue(pst).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Snackbar.make(findViewById(R.id.btnSubmit), "Data berhasil diupdate", Snackbar.LENGTH_LONG).show();
                                    }
                                });
                            }
                        });
                    }
                });
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
