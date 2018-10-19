package com.example.mostafa.selection_task;

import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class login extends AppCompatActivity {
    private int x=0;
    private FirebaseAuth mAuth;
    private EditText password;
    private EditText email;
    private Button button_register;
    private Button button_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.text_email);
        password = (EditText) findViewById(R.id.text_password);
        button_login = (Button) findViewById(R.id.btn_login);
        button_register = (Button) findViewById(R.id.btn_register);
        final String Email = email.getText().toString().trim();
        final String Password = password.getText().toString().trim();

    }


    public void Register(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
    public void login(View view) {
        final String Email = email.getText().toString().trim();
        final String Password = password.getText().toString().trim();
        mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(getApplicationContext(),
                            MainActivity.class));

                    FirebaseUser user = mAuth.getCurrentUser();

                } else {

                    // If sign in fails, display a message to the user.
                    Toast.makeText(login.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        }

}
