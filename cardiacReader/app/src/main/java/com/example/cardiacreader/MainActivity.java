package com.example.cardiacreader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    private TextView txtView;
    private FirebaseAuth firebaseAuth;
    private Button btnLogout,btnAdd;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<Records> recordsList;
    private FirebaseFirestore database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();
        String currentUserUid = firebaseAuth.getCurrentUser().getUid();


        btnLogout = findViewById(R.id.log);
        btnAdd = findViewById(R.id.add);
        recyclerView = findViewById(R.id.recyclerView);

        // Set the current user's display name
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            String name = currentUser.getDisplayName();

        }

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recordsList = new ArrayList<>();
        adapter = new MyAdapter(recordsList);
        recyclerView.setAdapter(adapter);

        // Retrieve measurement data from Firestore
        CollectionReference measurementsRef = database.collection("users").document(currentUserUid).collection("records");

        Query query = measurementsRef
                .orderBy("date", Query.Direction.ASCENDING);
        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                recordsList.clear();
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    Records measurement = documentSnapshot.toObject(Records.class);
                    recordsList.add(measurement);
                }

                Collections.sort(recordsList, new Comparator<Records>() {
                    @Override
                    public int compare(Records o1, Records o2) {
                        return o1.getTime().compareTo(o2.getTime());
                    }
                });

                adapter.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Failed to retrieve measurements", Toast.LENGTH_SHORT).show();
            }
        });


        // Set up click listeners



        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        Button add = findViewById(R.id.add),
                logout= findViewById(R.id.log);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                Intent intent = new Intent(MainActivity.this, Signin.class);
                startActivity(intent);
                finish();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, addRecords.class);
                startActivity(intent);
            }
        });
    }
}