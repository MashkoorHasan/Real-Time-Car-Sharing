package com.realtime.car.sharing.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.realtime.car.sharing.MainActivity;
import com.realtime.car.sharing.Models.UserData;
import com.realtime.car.sharing.R;

public class SignUpActivity extends AppCompatActivity {
    private EditText nameEt,emailEt, passwordEt1, passwordEt2, mobileEt;
    private Button SignUpButton;
    private TextView SignInTv;
    private RadioButton radioMale, radioFemale;
    String Gender="";

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference UserDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebaseAuth = FirebaseAuth.getInstance();
        radioMale = findViewById(R.id.male);
        radioFemale = findViewById(R.id.female);
        nameEt = findViewById(R.id.name);
        emailEt = findViewById(R.id.email);
        passwordEt1 = findViewById(R.id.password1);
        passwordEt2 = findViewById(R.id.password2);
        mobileEt = findViewById(R.id.mobile);
        SignUpButton = findViewById(R.id.signup);
        UserDbRef = FirebaseDatabase.getInstance().getReference("UserDATA");
        progressDialog = new ProgressDialog(this);
        SignInTv = findViewById(R.id.signinTv);

        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();

            }
        });


        SignInTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);

            }
        });

    }






    private void Register() {
        final String name = nameEt.getText().toString();
        final String email = emailEt.getText().toString();
        final String password1 = passwordEt1.getText().toString();


        if(radioMale.isChecked()){
            Gender = "Male";
        }
        if (radioFemale.isChecked()){
            Gender = "Female";
        }
        String password2 = passwordEt2.getText().toString();
        final String mobile = mobileEt.getText().toString();



        if (TextUtils.isEmpty(name)) {
            nameEt.setError("Enter your name.");
            return;
        }
        else if (TextUtils.isEmpty(email)) {
            emailEt.setError("Enter your email.");
            return;
        }

        else if (TextUtils.isEmpty(password1)) {
            passwordEt1.setError("Enter your password.");
            return;
        } else if (TextUtils.isEmpty(password2)) {
            passwordEt2.setError("Confirm your password.");
            return;
        }
        else if (TextUtils.isEmpty(mobile)) {
            mobileEt.setError("Enter your mobile number.");
            return;
        }
        if (!password1.equals(password2)) {
            passwordEt2.setError("Password doesn't match! Please match the password !");
            return;
        }
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);



        firebaseAuth.createUserWithEmailAndPassword(email,password1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    UserData userData = new UserData(name, email, password1, Gender, mobile);
                    UserDbRef.push().setValue(userData);

                    firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SignUpActivity.this, "Succussfully Registered !!.. Please verify your EMAIL ID.!", Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(SignUpActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(SignUpActivity.this, "Sign Up failed..!!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
                else{
                    Toast.makeText(SignUpActivity.this, "Sign Up failed..!!", Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });

    }


}

