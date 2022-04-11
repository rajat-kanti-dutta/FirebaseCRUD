package com.rajat.firebasecrud;


import android.app.Dialog;
import android.preference.EditTextPreference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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

public class MyPetAdapter extends FirebaseRecyclerAdapter<MyPetModel, MyPetAdapter.MyPetViewHolder> {

    public MyPetAdapter(@NonNull FirebaseRecyclerOptions<MyPetModel> options) {
        super(options);
    }

    @Override
    public void onBindViewHolder(@NonNull MyPetViewHolder holder, int position, @NonNull MyPetModel model) {
        holder.petName.setText(model.getName());
        holder.petAge.setText(model.getAge() + "Yrs.");
        //Log.e("RECYCLERFIREBASE",model.getPurl());
        //my url for image not working,.... so tried with a fixed one
        Glide.with(holder.petImgView.getContext())
                .load(model.getPurl())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .circleCrop()
                .error(R.drawable.mylogo)
                .into(holder.petImgView);
        //
        //setting OnClickListener of Edit Button
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus myDialogPlus = DialogPlus.newDialog(holder.petImgView.getContext())
                        .setContentHolder(new ViewHolder(R.layout.edit_layout_dialog))
                        .setExpanded(true,1400)
                        .create();
                View myEditView = myDialogPlus.getHolderView();
                 EditText myEditPetName = myEditView.findViewById(R.id.editPetName);
                EditText myEditPetAge = myEditView.findViewById(R.id.editPetAge1);
                EditText myEditPetImgUrl = myEditView.findViewById(R.id.editPetImageURL);
                Button editSubmit = (Button) myEditView.findViewById(R.id.butEditSave);
                Button editCancel = (Button) myEditView.findViewById(R.id.butEditBack);
                myEditPetName.setText(model.getName());
               myEditPetAge.setText(model.getAge()+"");
                myEditPetImgUrl.setText(model.getPurl());
                myDialogPlus.show();


            }
        });

    }

    //
    @NonNull
    @Override
    public MyPetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row, parent, false);
        return new MyPetViewHolder(view);
    }

    //ViewHolder
    class MyPetViewHolder extends RecyclerView.ViewHolder {
        CircleImageView petImgView;
        TextView petName, petAge;
        ImageView editButton, deleteButton;

        public MyPetViewHolder(@NonNull View itemView) {
            super(itemView);
            petImgView = (CircleImageView) itemView.findViewById(R.id.img1);
            petName = (TextView) itemView.findViewById(R.id.petName);
            petAge = (TextView) itemView.findViewById(R.id.age);
            editButton=(ImageView) itemView.findViewById(R.id.editPet);
            deleteButton = (ImageView) itemView.findViewById(R.id.deletePet);


        }

    }
}