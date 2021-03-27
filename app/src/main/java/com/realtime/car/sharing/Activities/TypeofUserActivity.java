package com.realtime.car.sharing.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.realtime.car.sharing.MainActivity;
import com.realtime.car.sharing.R;

public class TypeofUserActivity extends AppCompatActivity {
    private Button PassengerButton, BackButton, gotoDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typeof_user);
        PassengerButton=findViewById(R.id.passengerBTN);
        BackButton = findViewById(R.id.backButton);
        gotoDashboard = findViewById(R.id.GoToDashboard);
        PassengerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(TypeofUserActivity.this, PassengerActivity.class);
                startActivity(intent);

            }
        });
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TypeofUserActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
        gotoDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TypeofUserActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
