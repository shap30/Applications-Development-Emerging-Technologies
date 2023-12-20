package com.example.appdev_lab3_components;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    ImageView background, motorImg;

    TextView titleTxt, motorPrice;
    TextView makeInp, modelInp, dateInp, transInp;
    CheckBox checkBox, checkBox2, checkBox3, checkBox4;
    CheckBox checkBox5, checkBox6, checkBox7, checkBox8;
    RadioButton theme1, theme2, theme3;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        background = findViewById(R.id.background);
        theme1 = findViewById(R.id.theme1);
        theme2 = findViewById(R.id.theme2);
        theme3 = findViewById(R.id.theme3);

        theme1.setOnClickListener(new View . OnClickListener () {


            public void onClick(View view) {
                background.setImageResource(R.drawable.bg1);
                theme2.setSelected(false);
                theme3.setSelected(false);
            }
        });

        theme2.setOnClickListener(new View . OnClickListener () {


            public void onClick(View view) {
                background.setImageResource(R.drawable.bg2);
                theme1.setSelected(false);
                theme3.setSelected(false);
            }
        });

        theme3.setOnClickListener(new View . OnClickListener () {


            public void onClick(View view) {
                background.setImageResource(R.drawable.bg3);
                theme1.setSelected(false);
                theme2.setSelected(false);
            }
        });

        checkBox = findViewById(R.id.CheckBox);
        checkBox2 = findViewById(R.id.CheckBox2);
        checkBox3 = findViewById(R.id.CheckBox3);
        checkBox4 = findViewById(R.id.CheckBox4);
        checkBox5 = findViewById(R.id.CheckBox5);
        checkBox6 = findViewById(R.id.CheckBox6);
        checkBox7 = findViewById(R.id.CheckBox7);
        checkBox8 = findViewById(R.id.CheckBox8);

        motorImg = findViewById(R.id.motorImg);
        motorPrice = findViewById(R.id.motorPrice);
        makeInp = findViewById(R.id.makeInp);
        modelInp = findViewById(R.id.modelInp);
        dateInp = findViewById(R.id.dateInp);
        transInp = findViewById(R.id.transInp);

        checkBox.setOnClickListener(new View . OnClickListener () {


            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    motorImg.setImageResource(R.drawable.k1);
                    motorPrice.setText("SRP: P 279,000");
                    makeInp.setText("KYMCO - THE THRILLS OF TOURING");
                    modelInp.setText("KYMCO DT X360 - 350 (SK64DB)");
                    dateInp.setText("2022");
                    transInp.setText("Automatic");

                } else {
                    motorImg.setImageResource(R.drawable.placeholder);
                    motorPrice.setText("");
                    makeInp.setText("");
                    modelInp.setText("");
                    dateInp.setText("");
                    transInp.setText("");
                }

                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                checkBox5.setChecked(false);
                checkBox6.setChecked(false);
                checkBox7.setChecked(false);
                checkBox8.setChecked(false);

            }
        });

        checkBox2.setOnClickListener(new View . OnClickListener () {


            public void onClick(View view) {
                if (checkBox2.isChecked()) {
                    motorImg.setImageResource(R.drawable.k1);
                    motorPrice.setText("SRP: P 279,000");
                    makeInp.setText("KYMCO - THE THRILLS OF TOURING");
                    modelInp.setText("KYMCO DT X360 - 350 (SK64DB)");
                    dateInp.setText("2022");
                    transInp.setText("AUTOMATIC");

                } else {
                    motorImg.setImageResource(R.drawable.placeholder);
                    motorPrice.setText("");
                    makeInp.setText("");
                    modelInp.setText("");
                    dateInp.setText("");
                    transInp.setText("");
                }

                checkBox.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                checkBox5.setChecked(false);
                checkBox6.setChecked(false);
                checkBox7.setChecked(false);
                checkBox8.setChecked(false);
            }
        });

        checkBox2.setOnClickListener(new View . OnClickListener () {


            public void onClick(View view) {
                if (checkBox2.isChecked()) {
                    motorImg.setImageResource(R.drawable.r3);
                    motorPrice.setText("SRP: P 290,000");
                    makeInp.setText("YAMAHA - REVS YOUR HEART");
                    modelInp.setText("YZF R3 (BVJD)");
                    dateInp.setText("2021");
                    transInp.setText("MANUAL");

                } else {
                    motorImg.setImageResource(R.drawable.placeholder);
                    motorPrice.setText("");
                    makeInp.setText("");
                    modelInp.setText("");
                    dateInp.setText("");
                    transInp.setText("");
                }

                checkBox.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                checkBox5.setChecked(false);
                checkBox6.setChecked(false);
                checkBox7.setChecked(false);
                checkBox8.setChecked(false);
            }
        });

        checkBox3.setOnClickListener(new View . OnClickListener () {


            public void onClick(View view) {
                if (checkBox3.isChecked()) {
                    motorImg.setImageResource(R.drawable.xmax);
                    motorPrice.setText("SRP: P 274,000");
                    makeInp.setText("YAMAHA - REVS YOUR HEART");
                    modelInp.setText("YAMAHA XMAX (XMAX B5XE)");
                    dateInp.setText("2022");
                    transInp.setText("Automatic");

                } else {
                    motorImg.setImageResource(R.drawable.placeholder);
                    motorPrice.setText("");
                    makeInp.setText("");
                    modelInp.setText("");
                    dateInp.setText("");
                    transInp.setText("");
                }

                checkBox.setChecked(false);
                checkBox2.setChecked(false);
                checkBox4.setChecked(false);
                checkBox5.setChecked(false);
                checkBox6.setChecked(false);
                checkBox7.setChecked(false);
                checkBox8.setChecked(false);
            }
        });

        checkBox4.setOnClickListener(new View . OnClickListener () {


            public void onClick(View view) {
                if (checkBox4.isChecked()) {
                    motorImg.setImageResource(R.drawable.ninja);
                    motorPrice.setText("SRP: P 718,000");
                    makeInp.setText("KAWASAKI - TOP SELLING BIG BIKE");
                    modelInp.setText("KWASAKI NINJA 1000SX");
                    dateInp.setText("2022");
                    transInp.setText("MANUAL");

                } else {
                    motorImg.setImageResource(R.drawable.placeholder);
                    motorPrice.setText("");
                    makeInp.setText("");
                    modelInp.setText("");
                    dateInp.setText("");
                    transInp.setText("");
                }

                checkBox.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox5.setChecked(false);
                checkBox6.setChecked(false);
                checkBox7.setChecked(false);
                checkBox8.setChecked(false);

            }
        });

        checkBox5.setOnClickListener(new View . OnClickListener () {


            public void onClick(View view) {
                if (checkBox5.isChecked()) {
                    motorImg.setImageResource(R.drawable.h2);
                    motorPrice.setText("SRP: P 920,000");
                    makeInp.setText("KAWASAKI - TOP SELLING BIG BIKE");
                    modelInp.setText("KAWASAKI Z H2");
                    dateInp.setText("2021");
                    transInp.setText("6-SPEED");

                } else {
                    motorImg.setImageResource(R.drawable.placeholder);
                    motorPrice.setText("");
                    makeInp.setText("");
                    modelInp.setText("");
                    dateInp.setText("");
                    transInp.setText("");
                }

                checkBox.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                checkBox6.setChecked(false);
                checkBox7.setChecked(false);
                checkBox8.setChecked(false);

            }
        });

        checkBox6.setOnClickListener(new View . OnClickListener () {


            public void onClick(View view) {
                if (checkBox6.isChecked()) {
                    motorImg.setImageResource(R.drawable.r1m);
                    motorPrice.setText("SRP: P 1,689,001");
                    makeInp.setText("YAMAHA - REVSYOUR HEART");
                    modelInp.setText("YAMAHA YZF-R1");
                    dateInp.setText("2020");
                    transInp.setText("CONSTANT MESH, 6-SPEED");

                } else {
                    motorImg.setImageResource(R.drawable.placeholder);
                    motorPrice.setText("");
                    makeInp.setText("");
                    modelInp.setText("");
                    dateInp.setText("");
                    transInp.setText("");
                }

                checkBox.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                checkBox5.setChecked(false);
                checkBox7.setChecked(false);
                checkBox8.setChecked(false);

            }
        });

        checkBox7.setOnClickListener(new View . OnClickListener () {


            public void onClick(View view) {
                if (checkBox7.isChecked()) {
                    motorImg.setImageResource(R.drawable.honda_africa);
                    motorPrice.setText("SRP: P 1,170,000");
                    makeInp.setText("HONDA - THE POWER OF DREAMS");
                    modelInp.setText("HONDA AFRICAN TWIN ADVENTURE");
                    dateInp.setText("2019");
                    transInp.setText("6-SPEED");

                } else {
                    motorImg.setImageResource(R.drawable.placeholder);
                    motorPrice.setText("");
                    makeInp.setText("");
                    modelInp.setText("");
                    dateInp.setText("");
                    transInp.setText("");
                }

                checkBox.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                checkBox5.setChecked(false);
                checkBox6.setChecked(false);
                checkBox8.setChecked(false);

            }
        });

        checkBox8.setOnClickListener(new View . OnClickListener () {
            @Override
            public void onClick(View view) {
                if (checkBox8.isChecked()) {
                    motorImg.setImageResource(R.drawable.honda);
                    motorPrice.setText("SRP: P 1,600,000");
                    makeInp.setText("HONDA - THE POWER OF DREAMS");
                    modelInp.setText("HONDA CBR1000RR 2019");
                    dateInp.setText("2019");
                    transInp.setText("6-SPEED WITH QUICK SHIFTER");

                } else {
                    motorImg.setImageResource(R.drawable.placeholder);
                    motorPrice.setText("");
                    makeInp.setText("");
                    modelInp.setText("");
                    dateInp.setText("");
                    transInp.setText("");
                }

                checkBox.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                checkBox5.setChecked(false);
                checkBox6.setChecked(false);
                checkBox7.setChecked(false);

            }
        });

    }
}

