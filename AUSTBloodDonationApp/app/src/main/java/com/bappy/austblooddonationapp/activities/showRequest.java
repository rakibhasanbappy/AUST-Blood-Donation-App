package com.bappy.austblooddonationapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bappy.austblooddonationapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Adapters.showPostAdapter;
import DataModels.bloodPostData;

public class showRequest extends AppCompatActivity {

    private ListView listView;
    private List<bloodPostData> bloodPostDataList;
    private showPostAdapter showpostAdapter;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private Toolbar menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_request);

        databaseReference = FirebaseDatabase.getInstance("https://aust-blood-donation-app-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("bloodPost");

        bloodPostDataList = new ArrayList<>();
        showpostAdapter = new showPostAdapter(showRequest.this, bloodPostDataList);

        listView= findViewById(R.id.listView);
        menuItem = findViewById(R.id.toolbar);


        menuItem.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if(item.getItemId() == R.id.my_profile){
                    Intent intent = new Intent(getApplicationContext(), userProfile.class);
                    startActivity(intent);
                    return true;
                }

                if(item.getItemId() == R.id.my_request){
                    Intent intent = new Intent(getApplicationContext(), myRequest.class);
                    startActivity(intent);
                    return true;
                }

                if(item.getItemId() == R.id.about_us){
                    Intent intent = new Intent(getApplicationContext(), aboutUs.class);
                    startActivity(intent);
                    return true;
                }

                if(item.getItemId() == R.id.logout){
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(getApplicationContext(), SignInScreen.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finishAffinity();
                    return true;
                }

                return false;

            }
        });


    }

    @Override
    protected void onStart() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                bloodPostDataList.clear();
                for(DataSnapshot dataSnapshot1 : snapshot.getChildren()){
                    bloodPostData bloodPostData = dataSnapshot1.getValue(DataModels.bloodPostData.class);
                    bloodPostDataList.add(bloodPostData);
                }


                listView.setAdapter(showpostAdapter);


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        view.setSelected(true);
                        String postID = showpostAdapter.getItem(position).getId().toString();
                        int views = showpostAdapter.getItem(position).getViews();
                        String uid = showpostAdapter.getItem(position).getUid();
                        FirebaseUser rUser = mAuth.getCurrentUser();
                        String userId = rUser.getUid();
                        if(!uid.equals(userId))
                            views++;

                        Intent intent = new Intent(getApplicationContext(), showPostDetails.class);
                        intent.putExtra("postID",postID);
                        intent.putExtra("views",views);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(showRequest.this, "Can't Read Data! Check internet connection.", Toast.LENGTH_SHORT).show();
            }
        });

        super.onStart();
    }

}