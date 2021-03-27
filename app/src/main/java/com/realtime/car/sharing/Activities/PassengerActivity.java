package com.realtime.car.sharing.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.realtime.car.sharing.MainActivity;
import com.realtime.car.sharing.R;

import java.util.ArrayList;
import java.util.Calendar;

public class PassengerActivity extends AppCompatActivity {
    EditText sLocation, eLocation, dateTE, TimeTE, seats;
    Button Passengerbutton;
    private Spinner spDriver, spPassenger;
    private Spinner n1,n2,n3,n4;
    String Seat_No = "";
    String Rider_type = "";
    int hour, minute;
    DatePickerDialog.OnDateSetListener setListener;
    DatabaseReference PassenegerDbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger);
        //seats = findViewById(R.id.Seats);
        spDriver = findViewById(R.id.spinner_rider_type);
        spPassenger = findViewById(R.id.spinner_rider_type);
        n1 = findViewById(R.id.spinner_seat_no);
        n2 = findViewById(R.id.spinner_seat_no);
        n3 = findViewById(R.id.spinner_seat_no);
        n4 = findViewById(R.id.spinner_seat_no);
        sLocation = findViewById(R.id.StartLocation);
        eLocation = findViewById(R.id.EndLocation);
        dateTE = findViewById(R.id.date);
        TimeTE = findViewById(R.id.time);
        Passengerbutton=findViewById(R.id.SubmitPassengerButton);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        //}

        Spinner spinner1 = findViewById(R.id.spinner_seat_no);
        ArrayList<String> arrayList1 = new ArrayList<>();
        arrayList1.add("1");
        arrayList1.add("2");
        arrayList1.add("3");
        arrayList1.add("4");
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList1);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(arrayAdapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String seatno = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + seatno, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        Spinner spinner = findViewById(R.id.spinner_rider_type);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Driver");
        arrayList.add("Passenger");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String gender = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + gender, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //});

        dateTE.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(PassengerActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.GRAY));
                datePickerDialog.show();


            }
        });
        TimeTE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(PassengerActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minuteOfDay) {
                        hour = hourOfDay;
                        minute = minuteOfDay;
                        Calendar calendar1 = Calendar.getInstance();
                        calendar1.set(0,0,0,hour, month);
                        TimeTE.setText(DateFormat.format("hh:mm aa",calendar1));
                    }
                },12,0,false);
                timePickerDialog.updateTime(hour,minute);
                timePickerDialog.show();
            }
        });



        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = day+"/"+month+"/"+year;
                dateTE.setText(date);



            }
        };




        PassenegerDbRef = FirebaseDatabase.getInstance().getReference().child("RIDE_DETAILS");


        Passengerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertPassengerData();
            }
        });

    }
    private void insertPassengerData(){
        final String Slocation=sLocation.getText().toString();
        final String Elocation=eLocation.getText().toString();
        final String Date=dateTE.getText().toString();
        final String Time = TimeTE.getText().toString();
        //String nSeats = seats.getText().toString();

        if (spDriver.isSelected()) {
            Rider_type = "Driver";
        }
        if (spPassenger.isSelected()){
            Rider_type = "Passenger";
        }

        if (n1.isSelected()) {
            Seat_No = "1";
        }
        if (n2.isSelected()){
            Seat_No = "2";
        }

        if (n3.isSelected()) {
            Seat_No = "3";
        }
        if (n4.isSelected()){
            Seat_No = "4";
        }
        Passengers passengers = new Passengers(Slocation,Elocation,Date,Time, Seat_No, Rider_type);
        PassenegerDbRef.push().setValue(passengers);
        Toast.makeText(PassengerActivity.this, "Data submitted!", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(PassengerActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
