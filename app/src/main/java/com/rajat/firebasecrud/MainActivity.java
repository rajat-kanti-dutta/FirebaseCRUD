package com.rajat.firebasecrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    RecyclerView recView;
    MyPetAdapter myPetAdapter;
    FloatingActionButton fButton;
    ImageView editButton, deleteButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recView = (RecyclerView) findViewById(R.id.recView);
        recView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<MyPetModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("pets"),MyPetModel.class)
                .build();
        myPetAdapter = new MyPetAdapter(options);
        recView.setAdapter(myPetAdapter);
        fButton = (FloatingActionButton) findViewById(R.id.floatAdd);
        fButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Add_Pet.class));
            }
        });
        //
        editButton=(ImageView) findViewById(R.id.editPet);
        deleteButton = (ImageView) findViewById(R.id.deletePet);

    }
    @Override
    protected void onStart(){
        super.onStart();
        myPetAdapter.startListening();
    }
    @Override
    protected void onStop(){
        super.onStop();
        myPetAdapter.stopListening();
    }
}