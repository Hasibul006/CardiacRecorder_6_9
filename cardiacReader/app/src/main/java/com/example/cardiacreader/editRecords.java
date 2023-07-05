package com.example.cardiacreader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class editRecords extends AppCompatActivity {

    EditText etSystolic,etDiastolic,etHeartRate,etComment;
    Button btnSave;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_records);
    }



}