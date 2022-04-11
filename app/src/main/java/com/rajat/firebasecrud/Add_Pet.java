package com.rajat.firebasecrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Add_Pet extends AppCompatActivity {
    EditText petName,petAge,petImageURL;
    Button backBtn, saveBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet2);
        petName=(EditText)findViewById(R.id.petNameAdd);
        petAge = (EditText) findViewById(R.id.petAgeAdd);
        petImageURL = (EditText) findViewById(R.id.petImgURLAdd);
        backBtn=(Button)findViewById(R.id.backBtnAdd);
        saveBtn = (Button)findViewById(R.id.btnSubmitAdd);
        //
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                 performInsert();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                startActivity(new Intent(Add_Pet.this,MainActivity.class));
                finish();
            }
        });


    }

    private void performInsert() {
        Map<String,Object> map = new HashMap<>();
        map.put("name",petName.getText().toString());
        map.put("age",Integer.parseInt(petAge.getText().toString()));
        map.put("purl",petImageURL.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("pets").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        petName.setText("");
                        petAge.setText("0");
                        petImageURL.setText("");
                        Toast.makeText(getApplicationContext(),"Succcessfully Added",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("FIREBASECRUD",e.getMessage());
                        Toast.makeText(getApplicationContext(),"Insert Failed!!!!!!",Toast.LENGTH_LONG).show();
                    }
                });


    }
}