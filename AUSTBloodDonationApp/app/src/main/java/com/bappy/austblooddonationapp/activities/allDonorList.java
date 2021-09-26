package com.bappy.austblooddonationapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.bappy.austblooddonationapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Adapters.showUsersAdapter;
import DataModels.userProfileData;

public class allDonorList extends AppCompatActivity {


    private ListView listView;
    private List<userProfileData> usersList;
    private showUsersAdapter showusersAdapter;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_request);


        databaseReference = FirebaseDatabase.getInstance("https://aust-blood-donation-app-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("users");

        usersList = new ArrayList<>();
        showusersAdapter = new showUsersAdapter(allDonorList.this, usersList);

        listView= findViewById(R.id.listView);

    }


    @Override
    protected void onStart() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                usersList.clear();
                for(DataSnapshot dataSnapshot1 : snapshot.getChildren()){
                    userProfileData userProfileData = dataSnapshot1.getValue(DataModels.userProfileData.class);
                    usersList.add(userProfileData);
                }


                listView.setAdapter(showusersAdapter);


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        view.setSelected(true);
                        String phone = showusersAdapter.getItem(position).getPhone();
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        String phoneNo = "tel:" + phone;
                        intent.setData(Uri.parse(phoneNo));
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(allDonorList.this, "Can't Read Data! Check internet connection.", Toast.LENGTH_SHORT).show();
            }
        });

        super.onStart();
    }


}