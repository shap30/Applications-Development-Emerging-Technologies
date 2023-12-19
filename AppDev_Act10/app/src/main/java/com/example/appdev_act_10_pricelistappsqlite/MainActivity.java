package com.example.appdev_act_10_pricelistappsqlite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText prodname;
    private EditText prodprice;
    private Button btnadd;
    private Button btnview;
    private RadioButton radioFoodBtn;
    private RadioButton radioDrinkBtn;
    private RadioButton radioOtherBtn;
    private Button btnFood;
    private Button btnDrink;
    private Button btnOther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prodname = findViewById(R.id.product_id);
        prodprice = findViewById(R.id.price_id);
        btnadd = findViewById(R.id.btn_edit_id);
        btnview = findViewById(R.id.btn_delete_id);

        radioFoodBtn = findViewById(R.id.radioFoodBtn);
        radioDrinkBtn = findViewById(R.id.radioDrinkBtn);
        radioOtherBtn = findViewById(R.id.radioOtherBtn);

        btnFood = findViewById(R.id.btn_food_id);
        btnDrink = findViewById(R.id.btn_drink_id);
        btnOther = findViewById(R.id.btn_other_id);

        radioFoodBtn.setOnClickListener(v -> {
            radioDrinkBtn.setChecked(false);
            radioOtherBtn.setChecked(false);
        });

        radioDrinkBtn.setOnClickListener(v -> {
            radioFoodBtn.setChecked(false);
            radioOtherBtn.setChecked(false);
        });

        radioOtherBtn.setOnClickListener(v -> {
            radioFoodBtn.setChecked(false);
            radioDrinkBtn.setChecked(false);
        });

        btnadd.setOnClickListener(v -> {
            String nameofprods = prodname.getText().toString();
            String priceofprods = prodprice.getText().toString();

            if (nameofprods.trim().isEmpty() || priceofprods.trim().isEmpty()) {
                Toast.makeText(getApplicationContext(), "PLEASE FILL UP ALL FIELDS.", Toast.LENGTH_SHORT).show();
            } else if (!radioFoodBtn.isChecked() && !radioDrinkBtn.isChecked() && !radioOtherBtn.isChecked()) {
                Toast.makeText(getApplicationContext(), "PLEASE CHOOSE THE CATEGORY", Toast.LENGTH_SHORT).show();
            } else {
                String category = "";
                if (radioFoodBtn.isChecked()) {
                    category = "Food";
                } else if (radioDrinkBtn.isChecked()) {
                    category = "Drink";
                } else if (radioOtherBtn.isChecked()) {
                    category = "Other";
                }

                addRec(category);
                radioFoodBtn.setChecked(false);
                radioDrinkBtn.setChecked(false);
                radioOtherBtn.setChecked(false);
            }
        });




        btnFood.setOnClickListener(v -> viewCategory("Food"));

        btnDrink.setOnClickListener(v -> viewCategory("Drink"));

        btnOther.setOnClickListener(v -> viewCategory("Other"));

        btnview.setOnClickListener(v -> {
            Intent intentview = new Intent(getApplicationContext(), view.class);
            startActivity(intentview);
        });
    }

    private void addRec(String category) {
        String nameofprods = prodname.getText().toString();
        String priceofprods = prodprice.getText().toString();

        if (!nameofprods.isEmpty()) {
            if (!priceofprods.isEmpty()) {
                if (!category.isEmpty()) {
                    try {
                        SQLiteDatabase db = openOrCreateDatabase("dbaseprod", Context.MODE_PRIVATE, null);
                        db.execSQL("CREATE TABLE IF NOT EXISTS tblproduct(id INTEGER PRIMARY KEY AUTOINCREMENT, f_prodname VARCHAR, f_prodprice VARCHAR, f_category VARCHAR)");

                        String mysql = "INSERT INTO tblproduct(f_prodname, f_prodprice, f_category) VALUES(?, ?, ?)";

                        if (priceofprods.trim().length() > 0) {  // Check if price is not blank
                            SQLiteStatement statement = db.compileStatement(mysql);
                            statement.bindString(1, nameofprods);
                            statement.bindString(2, priceofprods);
                            statement.bindString(3, category);
                            statement.execute();

                            Toast.makeText(getApplicationContext(), "RECORD ADDED SUCCESSFULLY.", Toast.LENGTH_SHORT).show();
                            prodname.getText().clear();
                            prodprice.getText().clear();
                            prodname.requestFocus();
                        } else {
                            Toast.makeText(getApplicationContext(), "PLEASE FILL UP THE PRICE.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception exception) {
                        Toast.makeText(getApplicationContext(), "ERROR OCCURRED WHILE ADDING THE RECORD.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "PLEASE SELECT THE CATEGORY.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "PLEASE INSERT THE PRICE.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "PLEASE INSERT THE PRODUCT NAME.", Toast.LENGTH_SHORT).show();
        }
    }



    private void viewCategory(String category) {
        Intent intent = new Intent(getApplicationContext(), view.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }
}
