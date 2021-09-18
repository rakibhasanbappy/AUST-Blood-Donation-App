package com.bappy.austblooddonationapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bappy.austblooddonationapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import DataModels.bloodPostData;

public class showPostDetails extends AppCompatActivity {

    String id, phone;
    DatabaseReference databaseReference;
    TextView showName, showBloodGroup, showDivison, showDistrict, showAddress, showContact, showMessage;
    ImageButton callButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_post_details);

        showName = findViewById(R.id.showName);
        showBloodGroup = findViewById(R.id.showBloodGroup);
        showDivison = findViewById(R.id.showDivison);
        showDistrict = findViewById(R.id.showDistrict);
        showAddress = findViewById(R.id.showAddress);
        showContact = findViewById(R.id.showContact);
        showMessage = findViewById(R.id.showMessage);
        callButton = findViewById(R.id.callButton);

        Bundle extra = getIntent().getExtras();
        if(extra != null){
            id = extra.getString("postID");
        }

        databaseReference = FirebaseDatabase.getInstance("https://aust-blood-donation-app-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("bloodPost").child(id);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                bloodPostData showPost = snapshot.getValue(DataModels.bloodPostData.class);


                showName.setText("Name: "+ showPost.getName());
                showBloodGroup.setText("Blood Group: "+ showPost.getBloodGroup());
                showDivison.setText("Divison: "+ showPost.getDivison());
                showDistrict.setText("District: "+ showPost.getDistrict());
                showAddress.setText("Full Address: " + showPost.getAddress());
                showContact.setText("Contact No: "+ showPost.getPhone());
                showMessage.setText("Message: "+ showPost.getMessage());
                phone = showPost.getPhone();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(showPostDetails.this, "Can't Read Data! Check internet connection.", Toast.LENGTH_SHORT).show();
            }
        });

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                String phoneNo = "tel:" + phone;
                intent.setData(Uri.parse(phoneNo));
                startActivity(intent);

            }
        });

    }


}