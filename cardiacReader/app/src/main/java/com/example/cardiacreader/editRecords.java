package com.example.cardiacreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
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

        String id = getIntent().getStringExtra("Id");

        etSystolic = findViewById(R.id.etSystolic);
        etDiastolic = findViewById(R.id.etDiastolic);
        etHeartRate = findViewById(R.id.etHeartRate);
        etComment = findViewById(R.id.etComment);
        btnSave = findViewById(R.id.btnSave);

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();
        String currentUserUid = firebaseAuth.getCurrentUser().getUid();
        CollectionReference measurementsRef = database.collection("users").document(currentUserUid).collection("records");

        measurementsRef.document(id).get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                Records records = documentSnapshot.toObject(Records.class);

                // Set the retrieved values to the EditText fields
                etSystolic.setText(String.valueOf(records.getSystolic()));
                etDiastolic.setText(String.valueOf(records.getDiastolic()));
                etHeartRate.setText(String.valueOf(records.getHeartRate()));
                etComment.setText(records.getComment());

                // Handle the button click event for saving changes
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Update the measurement object with the new values from EditText fields
                        records.setSystolic(Integer.parseInt(etSystolic.getText().toString()));
                        records.setDiastolic(Integer.parseInt(etDiastolic.getText().toString()));
                        records.setHeartRate(Integer.parseInt(etHeartRate.getText().toString()));
                        records.setComment(etComment.getText().toString());

                        // Save the updated measurement object back to the database
                        measurementsRef.document(id).set(records)
                                .addOnSuccessListener(aVoid -> {
                                    Toast.makeText(editRecords.this, "Item Edited", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(editRecords.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                })
                                .addOnFailureListener(e -> {
                                    Toast.makeText(editRecords.this, "Edit Failed", Toast.LENGTH_SHORT).show();

                                });
                    }
                });
            }
        });

    }
}