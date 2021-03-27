package com.realtime.car.sharing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.realtime.car.sharing.Activities.SignInActivity;

public class MainActivity extends AppCompatActivity {

    Button LogOutBTN, btnView;
    TextView TVname,TVemail,TVmob;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //database retrieve:-

        TVname = findViewById(R.id.tvname);
        TVemail = findViewById(R.id.tvemail);
        TVmob = findViewById(R.id.tvmob);
        btnView = findViewById(R.id.viewprofile);


        LogOutBTN = findViewById(R.id.logout);
        LogOutBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(MainActivity.this,"You're Logged out",Toast.LENGTH_SHORT).show();

            }
        });




        /* btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference = FirebaseDatabase.getInstance().getReference().child("UserDATA").child("MTPbIBR0taxNGlK1VLX");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String name = snapshot.child("name").getValue().toString();
                        String email = snapshot.child("email").getValue().toString();
                        String mobile = snapshot.child("Mobile").getValue().toString();
                        TVname.setText(name);
                        TVemail.setText(email);
                        TVmob.setText(mobile);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });*/
    }
}
