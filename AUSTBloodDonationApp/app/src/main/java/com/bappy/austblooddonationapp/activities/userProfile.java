package com.bappy.austblooddonationapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
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

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import DataModels.bloodPostData;
import DataModels.userProfileData;

public class userProfile extends AppCompatActivity {

    private TextView datePicker, name, bloodGroup, divison, district, phone, date;
    private SwitchCompat available;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        name = findViewById(R.id.name);
        bloodGroup = findViewById(R.id.bloodGroup);
        divison = findViewById(R.id.divison);
        district = findViewById(R.id.district);
        phone = findViewById(R.id.phone);
        date = findViewById(R.id.date_picker);
        available = findViewById(R.id.available);

        FirebaseUser rUser = mAuth.getCurrentUser();
        String userId = rUser.getUid();

        databaseReference = FirebaseDatabase.getInstance("https://aust-blood-donation-app-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("users").child(userId);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                userProfileData userProfileData = snapshot.getValue(DataModels.userProfileData.class);


                name.setText("Name: "+userProfileData.getName());
                bloodGroup.setText("Blood Group: "+userProfileData.getBloodGroup());
                divison.setText("Divison: "+userProfileData.getDivison());
                district.setText("District: "+userProfileData.getDistrict());
                phone.setText("Contact No: "+userProfileData.getPhone());
                date.setText(userProfileData.getDate());
                if(userProfileData.getAvailability().equals("true"))
                    available.setChecked(true);
                else
                    available.setChecked(false);

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {
                Toast.makeText(userProfile.this, "Can't Read Data! Check internet connection.", Toast.LENGTH_SHORT).show();
            }
        });


        /*datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(userProfile.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {

                month = month +1;
                String date = day + "/" + month + "/" + year;
                datePicker.setText(date);

            }
        };*/

    }
}