package com.bappy.austblooddonationapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bappy.austblooddonationapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import DataModels.bloodPostData;

public class MakeRequestActivity extends AppCompatActivity {

    EditText requestMessageText;
    EditText detailAddress;
    Button request_submit_button;
    AutoCompleteTextView bloodDropdown, divisonDropdown, districtDropdown;
    String[] bloodList;
    String[] divisonList;
    String[] districtList;
    DatabaseReference databaseReference;

    boolean valid = true;
    String divison,bloodGroup,district,address,message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_request);

        requestMessageText = findViewById(R.id.requestMessage);
        request_submit_button = findViewById(R.id.submit_request_button);
        bloodDropdown = findViewById(R.id.BloodGroupDropDown);
        divisonDropdown = findViewById(R.id.DivisonDropDown);
        districtDropdown = findViewById(R.id.DistrictDropDown);
        detailAddress = findViewById(R.id.detailAdress);

        databaseReference = FirebaseDatabase.getInstance("https://aust-blood-donation-app-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("bloodPost");


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



        request_submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if (isValid()) {
                    saveData();
                //}
            }
        });
    }


    private boolean isValid(){

        bloodDropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                bloodGroup = (String)parent.getItemAtPosition(position);
                if(bloodGroup.equals("")){
                    showMessage("Select a blood group");
                    valid = false;
                }
            }
        });


        divisonDropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                divison = (String)parent.getItemAtPosition(position);
                if(divison.equals("")) {
                    valid = false;
                    showMessage("Select a divison");
                }
            }
        });



        districtDropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                district = (String)parent.getItemAtPosition(position);
                if(district.equals("")){
                    showMessage("Select a district");
                    valid = false;
                }
            }
        });

        if(detailAddress.getText().toString().equals("")){
            showMessage("Enter your detail address");
            valid = false;
        }

        return valid;

    }


    private void showMessage(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void saveData(){

        address = detailAddress.getText().toString();

        message = requestMessageText.getText().toString();

        Toast.makeText(this, bloodGroup, Toast.LENGTH_SHORT).show();

        Toast.makeText(this, district, Toast.LENGTH_SHORT).show();

        String key = databaseReference.push().getKey();

        bloodPostData bloodPostData = new bloodPostData(bloodGroup, divison, district, address);
        
        databaseReference.child(key).setValue(bloodPostData);

        Toast.makeText(this, "Post Updated!", Toast.LENGTH_SHORT).show();

    }

}