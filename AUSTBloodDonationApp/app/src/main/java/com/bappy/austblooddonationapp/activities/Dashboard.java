package com.bappy.austblooddonationapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.bappy.austblooddonationapp.R;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {

    private CardView allRequestButton, makeRequestButton, allDonorButton, searchDonorButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        allRequestButton = findViewById(R.id.all_blood_request);
        makeRequestButton = findViewById(R.id.make_request);
        allDonorButton = findViewById(R.id.all_donor);
        searchDonorButton = findViewById(R.id.search_donor);


        allRequestButton.setOnClickListener(this);
        makeRequestButton.setOnClickListener(this);
        allDonorButton.setOnClickListener(this);
        searchDonorButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.all_blood_request) {

            Intent intent = new Intent(getApplicationContext(), showRequest.class);
            startActivity(intent);

        }

        if (v.getId() == R.id.make_request) {

            Intent intent = new Intent(getApplicationContext(), MakeRequestActivity.class);
            startActivity(intent);

        }

        if (v.getId() == R.id.all_donor) {

        }

        if (v.getId() == R.id.search_donor) {

            Intent intent = new Intent(getApplicationContext(), SearchPage.class);
            startActivity(intent);

        }
    }

}