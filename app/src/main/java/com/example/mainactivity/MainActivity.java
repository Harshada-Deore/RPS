package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button start_game;
    EditText enter_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseUI();
        start_game.setOnClickListener(view->{
            String username = enter_name.getText().toString();
            FirebaseFirestore users = FirebaseFirestore.getInstance();
            Map<String,Object> users_data = new HashMap<>();
            users_data.put("Name",username);
            users.collection("users").document("User "+username).set(users_data).addOnCompleteListener(task ->{
                if(task.isSuccessful()){

                    startActivity(new Intent(MainActivity.this,RulesActivity.class));
                }
            }).addOnFailureListener(e -> {

                e.printStackTrace();
            });
        });
    }
    private void initialiseUI() {
        //initializes the ui of main screen at the start...
        start_game = findViewById(R.id.start_btn);
        enter_name = findViewById(R.id.name_enter);
    }
}