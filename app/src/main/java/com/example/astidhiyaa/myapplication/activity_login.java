package com.example.astidhiyaa.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.astidhiyaa.myapplication.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class activity_login extends AppCompatActivity {

    private Button btn_login;
    private EditText user,pass;
    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.btnLogin);
        user = findViewById(R.id.textUser);
        pass = findViewById(R.id.textPass);
        db = FirebaseDatabase.getInstance().getReference("User");

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(user.getText().toString(),pass.getText().toString());
            }
        });

    }
    public void signIn(final String nama, final String pass){
    db.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            if (dataSnapshot.child(nama).exists()){
                if (!nama.isEmpty()){
                    User login = dataSnapshot.child(nama).getValue(User.class);
                    if (login.getPass().equals(pass)){
                        Toast.makeText(activity_login.this,"Login sukses",Toast.LENGTH_SHORT).show();
                        Intent home_utama = new Intent(activity_login.this,MainActivity.class);
                        startActivity(home_utama);
                    }
                    else {
                        Toast.makeText(activity_login.this,"Login gagal",Toast.LENGTH_SHORT).show();
                    }
                }
            }
            else {
                Toast.makeText(activity_login.this,"Login gagal",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Toast.makeText(activity_login.this,"Login gagal"+databaseError.getMessage(),Toast.LENGTH_SHORT).show();
        }
    });
    }
}
