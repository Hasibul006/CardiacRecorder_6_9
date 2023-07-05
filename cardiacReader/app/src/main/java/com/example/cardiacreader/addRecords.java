package com.example.cardiacreader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class addRecords extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    EditText etDiastolic, etSystolic, etHeartRate, etDate, etTime, etComment;
    FirebaseFirestore database;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_records);

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();
        String currentUserUid = firebaseAuth.getCurrentUser().getUid();

        etDiastolic = findViewById(R.id.etDiastolic);
        etSystolic = findViewById(R.id.etSystolic);
        etTime = findViewById(R.id.etTime);
        etDate = findViewById(R.id.etDate);
        etComment = findViewById(R.id.etComment);
        etHeartRate = findViewById(R.id.etHeartRate);
        btnSave = findViewById(R.id.btnSave);

        etDate.setEnabled(false);
        etTime.setEnabled(false);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String date = dateFormat.format(calendar.getTime());
        String time = timeFormat.format(calendar.getTime());

        etDate.setText(date);
        etTime.setText(time);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String diastolic = etDiastolic.getText().toString();
                String systolic = etSystolic.getText().toString();
                String heartRate = etHeartRate.getText().toString();
                String comment = etComment.getText().toString();

                int diastolicValue = Integer.parseInt(diastolic);
                int systolicValue = Integer.parseInt(systolic);
                int heartRateValue = Integer.parseInt(heartRate);


                Records records = new Records(systolicValue, diastolicValue, date, time, heartRateValue, comment);

                CollectionReference cRef = database.collection("users").document(currentUserUid).collection("records");
                DocumentReference docRef = cRef.document();

                String measurementId = docRef.getId();
                records.setId(measurementId);

                docRef.set(records)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(addRecords.this, "Added Successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(addRecords.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(addRecords.this, "Data insertion error!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }
}