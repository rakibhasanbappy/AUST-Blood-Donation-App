package com.bappy.austblooddonationapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.bappy.austblooddonationapp.R;

import java.util.ArrayList;
import java.util.List;

import Adapters.RequestAdapter;
import DataModels.RequestDataModel;

public class Dashboard extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<RequestDataModel> requestDataModelList;
    private RequestAdapter requestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        requestDataModelList = new ArrayList<>();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if(item.getItemId() == R.id.search_button){

                    startActivity(new Intent(Dashboard.this, SearchPage.class));

                }

                return false;
            }
        });

        recyclerView = findViewById(R.id.recyclerView);

        LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);

        recyclerView.setLayoutManager(layoutManager);
        requestAdapter = new RequestAdapter(requestDataModelList, this);
        recyclerView.setAdapter(requestAdapter);
        populateDashboard();

    }

    private void populateDashboard(){
        RequestDataModel requestDataModel = new RequestDataModel("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.");

        requestDataModelList.add(requestDataModel);
        requestDataModelList.add(requestDataModel);
        requestDataModelList.add(requestDataModel);
        requestDataModelList.add(requestDataModel);
        requestDataModelList.add(requestDataModel);
        requestDataModelList.add(requestDataModel);
        requestDataModelList.add(requestDataModel);

        requestAdapter.notifyDataSetChanged();
    }

}