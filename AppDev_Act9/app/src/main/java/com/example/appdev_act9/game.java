package com.example.appdev_act9;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class game extends AppCompatActivity {

    private TextView score;
    private TextView life;
    private TextView time;
    private TextView question;
    private EditText answer;
    private Button ok;
    private Button next;

    private final Random randomnum = new Random();
    private int num1 = 0;
    private int num2 = 0;
    private int userAnswer = 0;
    private int correctAnswer = 0;
    private int userScore = 0;
    private int userLife = 3;

    private CountDownTimer timer;
    private final long START_TIMER_IN_MILLIS = 21000;
    private boolean timerRunning = false;
    private long timeLeftInMillis = START_TIMER_IN_MILLIS;

    private boolean okButtonClicked = false;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        score = findViewById(R.id.txt_id_Score);
        life = findViewById(R.id.txt_id_Life);
        time = findViewById(R.id.txt_id_Time);
        question = findViewById(R.id.txt_id_Question);
        answer = findViewById(R.id.txt_id_Answer);
        ok = findViewById(R.id.btn_id_ok);
        next = findViewById(R.id.btn_id_next);

        gameContinue();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!okButtonClicked) {
                    String userAnswerText = answer.getText().toString().trim();

                    if (userAnswerText.isEmpty()) {
                        displayMessage("PLEASE ENTER YOUR ANSWER.");
                    } else {
                        userAnswer = Integer.parseInt(userAnswerText);
                        pauseTimer();

                        if (userAnswer == correctAnswer) {
                            userScore++;
                            score.setText(String.valueOf(userScore));
                            question.setText("CORRECT!");
                            displayMessage("CORRECT!");
                        } else {
                            userLife--;
                            life.setText(String.valueOf(userLife));
                            question.setText("WRONG!");
                            displayMessage("WRONG!");
                        }

                        okButtonClicked = true;
                    }
                } else {
                    displayMessage("KID, YOU ALREADY ANSWERED!");
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userAnswerText = answer.getText().toString().trim();
                if (userAnswerText.isEmpty()) {
                    displayMessage("ENTER YOUR ANSWER FIRST, AND PROCEED TO NEXT QUESTION.");
                } else {
                    answer.getText().clear();
                    resetTimer();
                    okButtonClicked = false;

                    if (userLife <= 0) {
                        Toast.makeText(getApplicationContext(), "GAME-OVER!!", Toast.LENGTH_SHORT).show();
                        Intent intentResult = new Intent(game.this, ResultActivity.class);
                        intentResult.putExtra("score", userScore);
                        startActivity(intentResult);
                        finish();
                    } else {
                        gameContinue();
                    }
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void gameContinue() {
        num1 = randomnum.nextInt(100);
        num2 = randomnum.nextInt(100);
        String operation = getIntent().getStringExtra("operation");

        if ("subtraction".equals(operation)) {
            num1 = randomnum.nextInt(100);
            num2 = randomnum.nextInt(num1 + 1);
        } else {
            num1 = randomnum.nextInt(100);
            num2 = randomnum.nextInt(100);
        }

        String operatorSymbol = "";
        switch (operation) {
            case "addition":
                operatorSymbol = "+";
                break;
            case "subtraction":
                operatorSymbol = "-";
                break;
            case "multiplication":
                operatorSymbol = "*";
                break;
        }

        switch (operation) {
            case "addition":
                correctAnswer = num1 + num2;
                break;
            case "subtraction":
                correctAnswer = num1 - num2;
                break;
            case "multiplication":
                correctAnswer = num1 * num2;
                break;
            default:
                correctAnswer = 0;
                break;
        }

        question.setText(num1 + " " + operatorSymbol + " " + num2);
        answer.getText().clear();
        startTimer();
    }

    private void startTimer() {
        timer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateText();
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                timerRunning = false;
                pauseTimer();
                userLife--;
                life.setText(String.valueOf(userLife));
                if (userLife <= 0) {
                    question.setText("GAME-OVER!!!");
                    Toast.makeText(getApplicationContext(), "GAME-OVER!!!", Toast.LENGTH_SHORT).show();
                    Intent intentResult = new Intent(game.this, ResultActivity.class);
                    intentResult.putExtra("score", userScore);
                    startActivity(intentResult);
                    finish();
                } else {
                    question.setText("TIME UPS!!!");
                    resetTimer();
                }
            }
        }.start();

        timerRunning = true;
    }

    private void pauseTimer() {
        timer.cancel();
        timerRunning = false;
    }

    private void resetTimer() {
        timeLeftInMillis = START_TIMER_IN_MILLIS;
        updateText();
    }

    private void updateText() {
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeLeft = String.format("%02d", seconds);
        time.setText(timeLeft);
    }

    private void displayMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}