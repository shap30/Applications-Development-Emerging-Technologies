package com.example.appdev_act11;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter extends FirebaseRecyclerAdapter <MainModel, MainAdapter.myViewHolder>{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull MainModel model) {

        holder.name.setText(model.getB_name());
        holder.song.setText(model.getC_song());
        holder.playlist.setText(model.getD_playlist());

        Glide.with(holder.img.getContext())
                .load(model.getE_imageurl())
                .placeholder(com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus diaglogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.show_update))
                        .setExpanded(true, 1500)
                        .create();

                View view = diaglogPlus.getHolderView();
                EditText name = view.findViewById(R.id.edit_name);
                EditText song = view.findViewById(R.id.edit_song);
                EditText playlist = view.findViewById(R.id.edit_playlist);
                EditText imgurl = view.findViewById(R.id.edit_image_url);

                Button btnUpdate = view.findViewById(R.id.btn_id_update);

                name.setText(model.getB_name());
                song.setText(model.getC_song());
                playlist.setText(model.getD_playlist());
                imgurl.setText(model.getE_imageurl());

                diaglogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String updatedName = name.getText().toString();
                        String updatedSong = song.getText().toString();
                        String updatedPlaylist = playlist.getText().toString();
                        String updatedImageUrl = imgurl.getText().toString();

                        // Check if there are no changes
                        if (updatedName.equals(model.getB_name()) &&
                                updatedSong.equals(model.getC_song()) &&
                                updatedPlaylist.equals(model.getD_playlist()) &&
                                updatedImageUrl.equals(model.getE_imageurl())) {

                            Toast.makeText(holder.name.getContext(), "NOTHING HAVE CHANGED", Toast.LENGTH_SHORT).show();
                            diaglogPlus.dismiss();
                        } else {
                            // Check if any field is empty
                            if (updatedName.isEmpty()) {
                                Toast.makeText(holder.name.getContext(), "FILL IN THE NAME", Toast.LENGTH_SHORT).show();
                            } else if (updatedSong.isEmpty()) {
                                Toast.makeText(holder.name.getContext(), "FILL IN THE SONG", Toast.LENGTH_SHORT).show();
                            } else if (updatedPlaylist.isEmpty()) {
                                Toast.makeText(holder.name.getContext(), "FILL IN THE PLAYLIST", Toast.LENGTH_SHORT).show();
                            } else if (updatedImageUrl.isEmpty()) {
                                Toast.makeText(holder.name.getContext(), "FILL IN THE IMAGE URL", Toast.LENGTH_SHORT).show();
                            } else {
                                // Perform the update operation
                                Map<String, Object> map = new HashMap<>();
                                map.put("b_name", updatedName);
                                map.put("c_song", updatedSong);
                                map.put("d_playlist", updatedPlaylist);
                                map.put("e_imageurl", updatedImageUrl);

                                FirebaseDatabase.getInstance().getReference().child("artist")
                                        .child(getRef(position).getKey()).updateChildren(map)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(holder.name.getContext(), "Name, song, playlist, and image updated successfully", Toast.LENGTH_SHORT).show();
                                                diaglogPlus.dismiss();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(holder.name.getContext(), "Error updating record", Toast.LENGTH_SHORT).show();
                                                diaglogPlus.dismiss();
                                            }
                                        });
                            }
                        }
                    }
                });

            }
        });


        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("ATTENTION");
                builder.setMessage("ARE YOU SURE WANT TO DELETE THIS RECORD?");

                builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("artist")
                                .child(getRef(position).getKey()).removeValue()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.name.getContext(), "RECORD DELETED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.name.getContext(), "ERROR DELETING RECORD", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.name.getContext(), "CANCELLED...", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();
            }
        });



    }

    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_artist,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView name,song,playlist;

        Button btnEdit, btnDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img1_id);
            name = itemView.findViewById(R.id.name_artist_id);
            song = itemView.findViewById(R.id.name_song_id);
            playlist = itemView.findViewById(R.id.name_playlist_id);

            btnEdit = (Button)itemView.findViewById(R.id.btnEdit);
            btnDelete = (Button)itemView.findViewById(R.id.btnDelete);





        }
    }
}
