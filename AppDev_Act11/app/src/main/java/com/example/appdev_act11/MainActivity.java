package com.example.appdev_act11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton v_floating_action_btn;
    RecyclerView v_recyclerView;
    MainAdapter v_mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        v_recyclerView = findViewById(R.id.rv_id);
        v_recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("artist"), MainModel.class)
                        .build();


        v_mainAdapter = new MainAdapter(options);
        v_recyclerView.setAdapter(v_mainAdapter);


        v_floating_action_btn = findViewById(R.id.floatAbtn_id);

        v_floating_action_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddNewRecord.class));

            }

        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        v_mainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        v_mainAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);

    }

    private void txtSearch(String str) {
        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("artist").orderByChild("b_name").startAt(str).endAt(str + "\uf8ff"), MainModel.class)
                        .build();

        v_mainAdapter = new MainAdapter(options);
        v_mainAdapter.startListening();
        v_recyclerView.setAdapter(v_mainAdapter);
    }


}
