package com.example.appdev_act_10_pricelistappsqlite;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class edit extends AppCompatActivity {
    private TextView txtRecNo;
    private EditText produkto;
    private EditText presyo;
    private Button editButton;
    private Button deleteButton;
    private String originalName = "";
    private String originalPrice = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        txtRecNo = findViewById(R.id.record_id);
        produkto = findViewById(R.id.product_id);
        presyo = findViewById(R.id.price_id);
        editButton = findViewById(R.id.btn_edit_id);
        deleteButton = findViewById(R.id.btn_delete_id);

        Intent intent = getIntent();
        String col1 = intent.getStringExtra("id");
        String col2 = intent.getStringExtra("prod");
        String col3 = intent.getStringExtra("price");

        txtRecNo.setText(col1);
        produkto.setText(col2);
        presyo.setText(col3);
        originalName = produkto.getText().toString();
        originalPrice = presyo.getText().toString();

        editButton.setOnClickListener(v -> editRec());

        deleteButton.setOnClickListener(v -> deleteRec());
    }

    private void editRec() {
        String newName = produkto.getText().toString();
        String newPrice = presyo.getText().toString();

        if (!newName.equals(originalName) || !newPrice.equals(originalPrice)) {
            if (!newName.isEmpty() && !newPrice.isEmpty()) {
                try {
                    String recNum = txtRecNo.getText().toString();
                    SQLiteDatabase db = openOrCreateDatabase("dbaseprod", Context.MODE_PRIVATE, null);

                    String sql = "UPDATE tblproduct SET f_prodname = ?, f_prodprice = ? WHERE id = ?";
                    SQLiteStatement statement = db.compileStatement(sql);
                    statement.bindString(1, newName);
                    statement.bindString(2, newPrice);
                    statement.bindString(3, recNum);
                    statement.execute();

                    Toast.makeText(getApplicationContext(), "RECORD SAVED!!", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent intentBack = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intentBack);

                } catch (Exception exception) {
                    Toast.makeText(getApplicationContext(), "RECORD SAVED!!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "PLEASE INPUT ALL FIELD WANT TO EDIT", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void deleteRec() {
        try {
            String recNum = txtRecNo.getText().toString();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("DELETE RECORD");
            builder.setMessage("YOU WANT TO DELETE THIS RECORD?");

            builder.setPositiveButton("Yes", (dialog, which) -> {
                try {
                    SQLiteDatabase db = openOrCreateDatabase("dbaseprod", Context.MODE_PRIVATE, null);

                    String sql = "DELETE FROM tblproduct WHERE id = ?";
                    SQLiteStatement statement = db.compileStatement(sql);
                    statement.bindString(1, recNum);
                    statement.execute();

                    Toast.makeText(getApplicationContext(), "RECORD DELETED!!", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent intentBack = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intentBack);
                } catch (Exception exception) {
                    Toast.makeText(getApplicationContext(), "RECORD DELETED!", Toast.LENGTH_SHORT).show();
                }
            });

            builder.setNegativeButton("No", (dialog, which) -> {
                dialog.dismiss();
                // Navigate back to MainActivity
                Intent intentBack = new Intent(edit.this, MainActivity.class);
                startActivity(intentBack);
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        } catch (Exception exception) {
            Toast.makeText(getApplicationContext(), "ERROR!!", Toast.LENGTH_SHORT).show();
        }
    }
}

