package com.bappy.austblooddonationapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
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

public class myRequest extends AppCompatActivity {

    private ListView listView;
    private List<bloodPostData> bloodPostDataList;
    private showPostAdapter showpostAdapter;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private String userId;
    private Toolbar menuItem;
    private TextView noDataText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_request);


        FirebaseUser rUser = mAuth.getCurrentUser();
        userId = rUser.getUid();

        databaseReference = FirebaseDatabase.getInstance("https://aust-blood-donation-app-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("bloodPost");

        bloodPostDataList = new ArrayList<>();
        showpostAdapter = new showPostAdapter(myRequest.this, bloodPostDataList);

        listView= findViewById(R.id.listView);

        menuItem = findViewById(R.id.toolbar);

        noDataText = findViewById(R.id.noText);


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


                if(item.getItemId() == R.id.share){
                    String details = "Download AUST Blood Donation App: https://drive.google.com/drive/folders/1lhIfbIdxVcELHFWTv_fxtVqB61D3ULkO?usp=sharing";
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, details);
                    startActivity(Intent.createChooser(sharingIntent, "Share via"));
                    return true;
                }

                if(item.getItemId() == R.id.report){
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:austblooddonationapp@gmail.com")); // only email apps should handle this
                    intent.putExtra(Intent.EXTRA_EMAIL, "austblooddonationapp@gmail.com");
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
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

                    if(bloodPostData.getUid().equals(userId))
                        bloodPostDataList.add(bloodPostData);
                }

                if(bloodPostDataList.isEmpty()){
                    listView.setVisibility(View.GONE);
                    noDataText.setVisibility(View.VISIBLE);
                }


                listView.setAdapter(showpostAdapter);


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        view.setSelected(true);
                        String postID = showpostAdapter.getItem(position).getId().toString();
                        int views = showpostAdapter.getItem(position).getViews();
                        Intent intent = new Intent(getApplicationContext(), showPostDetails.class);
                        intent.putExtra("postID",postID);
                        intent.putExtra("views",views);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(myRequest.this, "Can't Read Data! Check internet connection.", Toast.LENGTH_SHORT).show();
            }
        });

        super.onStart();
    }


}