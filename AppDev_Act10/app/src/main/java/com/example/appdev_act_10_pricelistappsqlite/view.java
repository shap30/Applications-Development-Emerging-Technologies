package com.example.appdev_act_10_pricelistappsqlite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class view extends AppCompatActivity {
    private ListView viewList;
    private final List<String> productList = new ArrayList<>();
    private ArrayAdapter<String> prodListAdapter;

    @SuppressLint("Recycle")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        SQLiteDatabase db = openOrCreateDatabase("dbaseprod", Context.MODE_PRIVATE, null);

        viewList = findViewById(R.id.list_id);

        Intent intent = getIntent();
        String category = intent.getStringExtra("category");

        String query = (category != null) ?
                "SELECT * FROM tblproduct WHERE f_category = ? ORDER BY f_prodname" :
                "SELECT * FROM tblproduct ORDER BY f_prodname";

        Cursor tmpTable = db.rawQuery(query, (category != null) ? new String[]{category} : null);
        int idIndex = tmpTable.getColumnIndex("id");
        int prodNameIndex = tmpTable.getColumnIndex("f_prodname");
        int prodPriceIndex = tmpTable.getColumnIndex("f_prodprice");
        productList.clear();

        prodListAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, productList);
        viewList.setAdapter(prodListAdapter);

        List<product> productDetails = new ArrayList<>();

        if (tmpTable.moveToFirst()) {
            do {
                product product = new product();
                product.setRefId(tmpTable.getString(idIndex));
                product.setProductName(tmpTable.getString(prodNameIndex));
                product.setProductPrice(tmpTable.getString(prodPriceIndex));
                productDetails.add(product);

                productList.add(tmpTable.getString(idIndex) + ". " + tmpTable.getString(prodNameIndex) + "\t\t\t\t\t₱" + tmpTable.getString(prodPriceIndex));
            } while (tmpTable.moveToNext());

            prodListAdapter.notifyDataSetChanged();
            viewList.invalidateViews();
        }

        viewList.setOnItemClickListener((adapterView, view, i, l) -> {
            product product = productDetails.get(i);
            Intent intentEdit = new Intent(getApplicationContext(), edit.class);

            intentEdit.putExtra("id", product.getRefId());
            intentEdit.putExtra("prod", product.getProductName());
            intentEdit.putExtra("price", product.getProductPrice());
            finish();

            startActivity(intentEdit);
        });
    }
}
