package com.bappy.austblooddonationapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private Button editButton;
    private String Name = "", BloodGroup = "", Divison = "", District = "", Phone = "", Date = "", Available = "";

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
        editButton = findViewById(R.id.edit_profile_button);


        available.setClickable(false);

        FirebaseUser rUser = mAuth.getCurrentUser();
        String userId = rUser.getUid();

        databaseReference = FirebaseDatabase.getInstance("https://aust-blood-donation-app-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("users").child(userId);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                userProfileData userProfileData = snapshot.getValue(DataModels.userProfileData.class);


                Name = userProfileData.getName();
                BloodGroup = userProfileData.getBloodGroup();
                Divison = userProfileData.getDivison();
                District = userProfileData.getDistrict();
                Phone = userProfileData.getPhone();
                Date = userProfileData.getDate();
                Available = userProfileData.getAvailability();

                name.setText("Name: "+Name);
                bloodGroup.setText("Blood Group: "+BloodGroup);
                divison.setText("Divison: "+Divison);
                district.setText("District: "+District);
                phone.setText("Contact No: "+Phone);
                date.setText(Date);
                if(Available.equals("true"))
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

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), editProfile.class);
                intent.putExtra("name", Name);
                intent.putExtra("blood", BloodGroup);
                intent.putExtra("divison", Divison);
                intent.putExtra("district", District);
                intent.putExtra("phone", Phone);
                intent.putExtra("date", Date);
                intent.putExtra("available", Available);

                startActivity(intent);
                finish();

            }
        });


    }
}