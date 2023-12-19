package com.example.appdev_act9;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView resultText;
    private Button playAgainButton;
    private Button exitButton;
    private int score;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultText = findViewById(R.id.txtview_id_result);
        playAgainButton = findViewById(R.id.btn_id_play);
        exitButton = findViewById(R.id.btn_id_exit);

        Intent intentResult = getIntent();
        score = intentResult.getIntExtra("score", 0);
        resultText.setText(String.valueOf(score));

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMain = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intentMain);
                finish();
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
