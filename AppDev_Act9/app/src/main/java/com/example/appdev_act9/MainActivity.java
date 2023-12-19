package com.example.appdev_act9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnAddition;
    private Button btnSubtraction;
    private Button btnMultiplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddition = findViewById(R.id.btn1);
        btnSubtraction = findViewById(R.id.btn2);
        btnMultiplication = findViewById(R.id.btn3);

        btnAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGameActivity("addition");
            }
        });

        btnSubtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGameActivity("subtraction");
            }
        });

        btnMultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGameActivity("multiplication");
            }
        });
    }

    private void startGameActivity(String operation) {
        Intent intentGame = new Intent(this, game.class);
        intentGame.putExtra("operation", operation);
        startActivity(intentGame);
        finish();
    }
}