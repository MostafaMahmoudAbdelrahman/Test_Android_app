package com.example.mostafa.selection_task;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;
import android.text.TextUtils;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText password;
    private EditText Cpassword;
    private EditText email;
    private Button button_register;
    private Button button_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        email =(EditText) findViewById(R.id.text_email);
        password =(EditText) findViewById(R.id.text_password);
        Cpassword =(EditText) findViewById(R.id.text_password_confirm);
        button_login =(Button) findViewById(R.id.btn_login);
        button_register =(Button) findViewById(R.id.btn_register);





    }
    public void login(View view) {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }

    public void RegisterUser(View view){
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();
        String CPassword = Cpassword.getText().toString().trim();
if (!(Objects.equals(Password,CPassword))){
    Toast.makeText(this, "A Password and confirm password don't match", Toast.LENGTH_SHORT).show();
    return;

}
        if (TextUtils.isEmpty(Email)){
            Toast.makeText(this, "A Field is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Password)){
            Toast.makeText(this, "A Field is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        try {
                            //check if successful
                            if (task.isSuccessful()) {
                                //User is successfully registered and logged in
                                //start Profile Activity here
                                Toast.makeText(Register.this, "registration successful",
                                        Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(getApplicationContext(), login.class));
                            }else{
                                Toast.makeText(Register.this, "Couldn't register, try again",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
    }

}

