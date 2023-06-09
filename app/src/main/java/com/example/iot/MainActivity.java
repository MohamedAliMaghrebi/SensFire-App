package com.example.iot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iot.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button b ;
    EditText El,EP ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b =  findViewById(R.id.btn);
        El = findViewById(R.id.edtL);
        EP = findViewById(R.id.edtP);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userloginStr = El.getText().toString();
                String passwordStr = EP.getText().toString();

                if (userloginStr.equals("DHT11") && passwordStr.equals("DHT11")) {
                    Intent i = new Intent(MainActivity.this, SecondActivity.class);
                    i.putExtra("Login",El.getText().toString());

                    startActivity(i);
                }

                else {
                    Toast.makeText(MainActivity.this, "Login ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}