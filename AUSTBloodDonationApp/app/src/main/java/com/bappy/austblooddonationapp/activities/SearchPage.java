package com.bappy.austblooddonationapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.bappy.austblooddonationapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class SearchPage extends AppCompatActivity {


    private AutoCompleteTextView bloodDropdown, divisonDropdown, districtDropdown;
    private String[] bloodList;
    private String[] divisonList;
    private String[] districtList;
    private String divison = "",bloodGroup = "",district = "";
    private Button searchButton;
    private Toolbar menuItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);


        bloodDropdown = findViewById(R.id.BloodGroupDropDown);
        divisonDropdown = findViewById(R.id.DivisonDropDown);
        districtDropdown = findViewById(R.id.DistrictDropDown);
        searchButton = findViewById(R.id.search_button);
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


        bloodList = getResources().getStringArray(R.array.blood_group);
        ArrayAdapter<String> arrayAdapter_blood = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_item, bloodList);

        bloodDropdown.setAdapter(arrayAdapter_blood);

        divisonList = getResources().getStringArray(R.array.divison);
        ArrayAdapter<String> arrayAdapter_divison = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_item, divisonList);

        divisonDropdown.setAdapter(arrayAdapter_divison);


        bloodDropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                bloodGroup = (String)parent.getItemAtPosition(position);
            }
        });


        divisonDropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                divison = (String)parent.getItemAtPosition(position);

                if(divison.equals("Dhaka")){
                    districtList = getResources().getStringArray(R.array.dhakaDistrict);
                    ArrayAdapter<String> arrayAdapter_district = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_item, districtList);

                    districtDropdown.setAdapter(arrayAdapter_district);
                }

                else if(divison.equals("Chattogram")){
                    districtList = getResources().getStringArray(R.array.chattogramDistrict);
                    ArrayAdapter<String> arrayAdapter_district = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_item, districtList);

                    districtDropdown.setAdapter(arrayAdapter_district);
                }

                else if(divison.equals("Rajshahi")){
                    districtList = getResources().getStringArray(R.array.rajshahiDistrict);
                    ArrayAdapter<String> arrayAdapter_district = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_item, districtList);

                    districtDropdown.setAdapter(arrayAdapter_district);
                }

                else if(divison.equals("Barishal")){
                    districtList = getResources().getStringArray(R.array.barihsalDistrict);
                    ArrayAdapter<String> arrayAdapter_district = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_item, districtList);

                    districtDropdown.setAdapter(arrayAdapter_district);
                }

                else if(divison.equals("Khulna")){
                    districtList = getResources().getStringArray(R.array.khulnaDistrict);
                    ArrayAdapter<String> arrayAdapter_district = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_item, districtList);

                    districtDropdown.setAdapter(arrayAdapter_district);
                }

                else if(divison.equals("Rangpur")){
                    districtList = getResources().getStringArray(R.array.rangpurDistrict);
                    ArrayAdapter<String> arrayAdapter_district = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_item, districtList);

                    districtDropdown.setAdapter(arrayAdapter_district);
                }

                else if(divison.equals("Sylhet")){
                    districtList = getResources().getStringArray(R.array.sylhetDistrict);
                    ArrayAdapter<String> arrayAdapter_district = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_item, districtList);

                    districtDropdown.setAdapter(arrayAdapter_district);
                }

                else if(divison.equals("Mymensingh")){
                    districtList = getResources().getStringArray(R.array.mymensinghDistrict);
                    ArrayAdapter<String> arrayAdapter_district = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_item, districtList);

                    districtDropdown.setAdapter(arrayAdapter_district);
                }

            }
        });

        districtDropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                district = (String)parent.getItemAtPosition(position);
            }
        });


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bloodGroup.equals("") && divison.equals("") && district.equals("")) {
                    bloodDropdown.requestFocus();
                    Toast.makeText(SearchPage.this, "Please Enter something to search!", Toast.LENGTH_SHORT).show();
                }
                else if(!divison.equals("") && district.equals("")) {
                    districtDropdown.requestFocus();
                    Toast.makeText(SearchPage.this, "Enter district for location search!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), showSearchResult.class);
                    intent.putExtra("Divison",divison);
                    intent.putExtra("District", district);
                    intent.putExtra("Blood Group",bloodGroup);
                    startActivity(intent);
                    finish();

                }

            }
        });


    }
}