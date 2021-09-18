package com.bappy.austblooddonationapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import Adapters.showPostAdapter;
import DataModels.bloodPostData;

public class showSearchResult extends AppCompatActivity {


    private ListView listView;
    private List<bloodPostData> bloodPostDataList;
    private showPostAdapter showpostAdapter;
    DatabaseReference databaseReference;

    String divison, district, bloodGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_request);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();


        if(extras != null){

            divison = extras.getString("Divison");
            district = extras.getString("District");
            bloodGroup = extras.getString("Blood Group");
        }

        databaseReference = FirebaseDatabase.getInstance("https://aust-blood-donation-app-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("bloodPost");

        bloodPostDataList = new ArrayList<>();

        showpostAdapter = new showPostAdapter(showSearchResult.this, bloodPostDataList);

        listView= findViewById(R.id.listView);

    }


    @Override
    protected void onStart() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                bloodPostDataList.clear();
                for(DataSnapshot dataSnapshot1 : snapshot.getChildren()){
                    bloodPostData bloodPostData = dataSnapshot1.getValue(DataModels.bloodPostData.class);
                    if(!divison.equals("") && !district.equals("") && !bloodGroup.equals("")){
                        if(divison.equals(bloodPostData.getDivison().toString()) && district.equals(bloodPostData.getDistrict().toString()) && bloodGroup.equals(bloodPostData.getBloodGroup().toString()))
                            bloodPostDataList.add(bloodPostData);
                    }
                    else if(!bloodGroup.equals("")){
                        if(bloodGroup.equals(bloodPostData.getBloodGroup().toString()))
                            bloodPostDataList.add(bloodPostData);
                    }
                    else{
                        if(divison.equals(bloodPostData.getDivison().toString()) && district.equals(bloodPostData.getDistrict().toString()))
                            bloodPostDataList.add(bloodPostData);
                    }
                }


                listView.setAdapter(showpostAdapter);


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        view.setSelected(true);
                        String postID = showpostAdapter.getItem(position).getId().toString();
                        Intent intent = new Intent(getApplicationContext(), showPostDetails.class);
                        intent.putExtra("postID",postID);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(showSearchResult.this, "Can't Read Data! Check internet connection.", Toast.LENGTH_SHORT).show();
            }
        });

        super.onStart();
    }


}