package com.example.appdev_act8;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText searchBox, prodName, priceInp;
    Button saveBtn;
    ListView listView;

    ArrayList<String> itemList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    private View add;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBox = findViewById(R.id.searchBox);
        prodName = findViewById(R.id.prodName);
        priceInp = findViewById(R.id.priceInp);
        saveBtn = findViewById(R.id.saveBtn);
        listView = findViewById(R.id.listView);

        itemList = PriceHelper.readData(this);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, itemList);

        listView.setAdapter(arrayAdapter);

        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Filter the list based on the user's input
                arrayAdapter.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String prod = prodName.getText().toString();
                String price = priceInp.getText().toString();

                // Check if both product name and price are not empty
                if (!prod.isEmpty() && !price.isEmpty()) {
                    // Concatenate the product name and price
                    String result = prod + " ₱" + price;

                    // Add the result to the item list
                    itemList.add(result);

                    // Clear the input fields
                    prodName.setText("");
                    priceInp.setText("");

                    // Write the updated list to the file
                    PriceHelper.writeData(itemList, getApplicationContext());

                    // Notify the adapter that the data has changed
                    arrayAdapter.notifyDataSetChanged();
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("REMOVE");
                alert.setMessage("DO YOU WANT REMOVE THIS ITEM TO OUR LIST?");
                alert.setCancelable(false);
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        itemList.remove(position);
                        arrayAdapter.notifyDataSetChanged();
                        PriceHelper.writeData(itemList, getApplicationContext());
                    }
                });

                AlertDialog alertDialog = alert.create();
                alertDialog.show();
            }
        });
    }
}