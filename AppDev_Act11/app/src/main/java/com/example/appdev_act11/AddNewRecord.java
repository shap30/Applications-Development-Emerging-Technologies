package com.example.appdev_act11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class AddNewRecord extends AppCompatActivity {

    EditText v_name, v_songs, v_playlist, v_imgurl;
    Button v_save, v_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_record);

        v_name = findViewById(R.id.txt_id_name);
        v_songs = findViewById(R.id.txt_id_best_songs);
        v_playlist = findViewById(R.id.txt_id_best_playlist);
        v_imgurl = findViewById(R.id.txt_id_imgurl);

        v_save = findViewById(R.id.btn_id_save);
        v_cancel = findViewById(R.id.btn_id_cancel);


        v_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name_is = v_name.getText().toString();
                String songs_is = v_songs.getText().toString();
                String playlist_is = v_playlist.getText().toString();
                String images_is = v_imgurl.getText().toString();


                if (name_is.isEmpty()) {
                    f_validate_msg("NAME");
                }
                else if (songs_is.isEmpty()) {
                    f_validate_msg("SONG");
                }
                else if (playlist_is.isEmpty()) {
                    f_validate_msg("PLAYLIST");
                }
                else if (images_is.isEmpty()) {
                    f_validate_msg("PICTURE");
                }
                else{

                    f_insert_record(name_is,songs_is,playlist_is,images_is);

                }


            }
        });


        v_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

    }

    private void f_insert_record(String name_is,String songs_is,String playlist_is,String images_is) {

        // create hashmap

        HashMap<String, Object>data__hashmap = new HashMap<>();

        data__hashmap.put("b_name", name_is);
        data__hashmap.put("c_song", songs_is);
        data__hashmap.put("d_playlist", playlist_is);
        data__hashmap.put("e_imageurl", images_is);


        // instantiate firebase database connection:

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference tbl_reference = database.getReference("artist");

        String idkey = tbl_reference.push().getKey();
        data__hashmap.put("a_idno", idkey);

        tbl_reference.child(idkey).setValue(data__hashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(AddNewRecord.this,"RECORD INSERTED SUCCESSFFULY", Toast.LENGTH_SHORT).show();

                v_name.getText().clear();
                v_songs.getText().clear();
                v_playlist.getText().clear();
                v_imgurl.getText().clear();

                Intent intent = new Intent(AddNewRecord.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void f_validate_msg(String info) {
        Toast.makeText(AddNewRecord.this,"PLEASE ENTER NAME OF ARTIST " + info, Toast.LENGTH_SHORT).show();
    }

}

