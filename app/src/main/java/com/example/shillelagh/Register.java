package com.example.shillelagh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
EditText name,email,password,contact,em_contact;
Button register;
FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        contact = (EditText)findViewById(R.id.contact);
        em_contact = (EditText)findViewById(R.id.emergency_contact);
        register = (Button)findViewById(R.id.signup);

        fAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final String mEmail = email.getText().toString().trim();
                final String mpass = password.getText().toString().trim();
                final String mname = name.getText().toString().trim();
                final String mcontact = contact.getText().toString().trim();
                final String emerg_contact = em_contact.getText().toString().trim();

                if(TextUtils.isEmpty(mEmail)){
                    email.setError("Email is required");
                    email.requestFocus();
                    return ;
                }
                if(TextUtils.isEmpty(mpass)){
                    password.setError("Password is required");
                    password.requestFocus();
                    return ;
                }
                if(mpass.length() < 6){
                    password.setError("Minimum length should be 6 characters");
                    password.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(mname)){
                    name.setError("Name is required");
                    name.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(mcontact)){
                    contact.setError("Contact is required");
                    contact.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(emerg_contact)){
                    em_contact.setError("Field is required");
                    em_contact.requestFocus();
                    return;
                }

                fAuth.createUserWithEmailAndPassword(mEmail,mpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(mname,mEmail,mcontact,emerg_contact);

                            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Registered Successfully..!", Toast.LENGTH_LONG).show();
                                        Intent i =  new Intent(getApplicationContext(),Dash.class);
                                        startActivity(i);

                                    } else {
                                        Toast.makeText(getApplicationContext(), "Failed to register! Try Again", Toast.LENGTH_LONG).show();
                                    }

                                }
                            });
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Failed to register! Try Again", Toast.LENGTH_LONG).show();

                        }

                    }

                });

            }
        });


    }
}