package com.realtime.car.sharing.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.realtime.car.sharing.MainActivity;
import com.realtime.car.sharing.R;

public class ForgetPasswordActivity extends AppCompatActivity {
    private EditText PasswordEmail;
    private Button ResetButton, BackButton;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        PasswordEmail = findViewById(R.id.forgetTv);
        ResetButton = findViewById(R.id.forgetemailButton);
        BackButton = findViewById(R.id.backButton);
        firebaseAuth =FirebaseAuth.getInstance();
        ResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = PasswordEmail.getText().toString().trim();
                if(email.equals("")){
                    Toast.makeText(ForgetPasswordActivity.this,"Please enter your regitered email !", Toast.LENGTH_SHORT).show();
                }
                else {
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ForgetPasswordActivity.this, "Password reset link sent to your email id !", Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(ForgetPasswordActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(ForgetPasswordActivity.this, "Error in sending email.. Or the email is not registered!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ForgetPasswordActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
