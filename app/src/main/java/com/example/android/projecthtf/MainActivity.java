package com.example.android.projecthtf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.projecthtf.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button btnInsert,btnView,btnSortByTemp,btnSortBySpo2;
    EditText name,phone,age,gender,bp,bedNo,spo2,temperature,bloodGroup,previousIllness,covidStatus;
    DatabaseReference databaseUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Main Menu");
        btnInsert=findViewById(R.id.btnInsert);
        btnView=findViewById(R.id.btnView);
        name=findViewById(R.id.edtname);
        phone=findViewById(R.id.phone);
        age=findViewById(R.id.age);
        gender=findViewById(R.id.gender);
        bp=findViewById(R.id.bp);
        bedNo=findViewById(R.id.bedNo);
        spo2=findViewById(R.id.spo2);
        btnSortByTemp=findViewById(R.id.btnSortByTemp);
        btnSortBySpo2=findViewById(R.id.btnSortBySpo2);
        temperature=findViewById(R.id.temperature);
        bloodGroup=findViewById(R.id.bloodGroup);
        previousIllness=findViewById(R.id.previousIllness);
        covidStatus=findViewById(R.id.covidStatus);
        databaseUsers= FirebaseDatabase.getInstance().getReference();

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertData();
            }
        });
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Userlist.class));
                finish();
            }
        });
        btnSortByTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, com.example.android.projecthtf.tempActivity.class));
                finish();
            }
        });
        btnSortBySpo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, com.example.android.projecthtf.spo2Activity.class));
                finish();
            }
        });

    }
    private void InsertData(){
        String patientName=name.getText().toString();
        String patientPhone=phone.getText().toString();
        String patientGender=gender.getText().toString();
        int patientAge= Integer.parseInt(age.getText().toString());
        int patientBP= Integer.parseInt(bp.getText().toString());
        int patientBedNo= Integer.parseInt(bedNo.getText().toString());
        int patientSPO2= Integer.parseInt(spo2.getText().toString());
        String patientBloodGroup= bloodGroup.getText().toString();
        String patientPreviousIllness= previousIllness.getText().toString();
        String patientCovidStatus= covidStatus.getText().toString();


        int patientTemperature= Integer.parseInt(temperature.getText().toString());

        String id=databaseUsers.push().getKey();
        if(!patientName.isEmpty()||!patientGender.isEmpty()||!patientPhone.isEmpty()) {
            User user = new User(patientName,patientGender,patientPhone,patientAge,patientBP,patientBedNo,patientSPO2,patientTemperature,patientBloodGroup,patientPreviousIllness,patientCovidStatus);
            databaseUsers.child("users").child(id).setValue(user)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "User details inserted", Toast.LENGTH_SHORT).show();
                                name.setText("");
                                phone.setText("");
                                age.setText("");
                                gender.setText("");
                                bp.setText("");
                                temperature.setText("");
                                bedNo.setText("");
                                spo2.setText("");
                                bloodGroup.setText("");
                                previousIllness.setText("");
                                covidStatus.setText("");
                            }
                        }
                    });
        }
        else{
            Toast.makeText(this, "Please Enter all the details", Toast.LENGTH_SHORT).show();
        }
    }
}