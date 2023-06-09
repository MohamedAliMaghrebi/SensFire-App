package com.example.iot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SecondActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private TextView mHumidityTextView;
    private TextView mTemperatureTextView;
    private TextView VH,VT ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Initialise Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Référence aux TextView
        mHumidityTextView = findViewById(R.id.humidityTextView);
        mTemperatureTextView = findViewById(R.id.temperatureTextView);
        VH = findViewById(R.id.humidityValueTextView);
        VT = findViewById(R.id.temperatureValueTextView);
        // Récupérer les données en temps réel
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Récupérer les données de l'utilisateur
                int humidity = dataSnapshot.child("humidity").getValue(Integer.class);
                int temperature = dataSnapshot.child("temperature").getValue(Integer.class);

                // Mettre à jour les TextView avec les données
                mHumidityTextView.setText("Humidité: ");
                VH.setText(humidity+ "%");
                mTemperatureTextView.setText("Température: ");
                VT.setText(temperature+ "°C");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Gérer les erreurs de base de données
            }
        });
    }
}
