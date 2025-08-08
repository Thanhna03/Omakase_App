package com.example.register;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    //khai báo
    private EditText emailedit, passedit;
    private Button btnlogin, btnregister;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth= FirebaseAuth.getInstance();

        emailedit=findViewById(R.id.email);
        passedit=findViewById(R.id.password);
        btnlogin=findViewById(R.id.btnlogin);
        btnregister=findViewById(R.id.btnregister);

        //sự kiện
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void register() {
        Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(i);
    }
    private void login() {
        //doc nhung bien user dien vao edit text
        String email, pass;
        email= emailedit.getText().toString();
        pass=passedit.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Vui long nhap email!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Vui long nhap password!", Toast.LENGTH_SHORT).show();
            return;
        }
        //dang nhap bang firbase
         mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {
                 if (task.isSuccessful()) {
                     Toast.makeText(getApplicationContext(),"Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                     Intent intent= new Intent(LoginActivity.this, MainActivity.class);
                     startActivity(intent);

                 }else{
                     Toast.makeText(getApplicationContext(),"Dang nhap khong thanh cong", Toast.LENGTH_SHORT).show();
                 }
             }
         });

    }

}
