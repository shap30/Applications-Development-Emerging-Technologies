package com.example.appdev_act_10_pricelistappsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private ProgressBar pb;
    private int counter = 0;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_splash_screen);

        pb = findViewById(R.id.pb);
        handler = new Handler();

        prog();
    }

    private void prog() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                counter++;
                pb.setProgress(counter);

                if (counter < 100) {
                    prog(); // Schedule the next iteration
                } else {
                    // Start the next activity (MainActivity)
                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Optional: Finish the splash screen activity if you don't want to go back to it
                }
            }
        }, 100); // Delay for 100 milliseconds between each iteration
    }
}
