package com.example.myfirebaseapps;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Splash extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_act);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mUser != null) {
                    intent = new Intent(Splash.this, StudentArea.class);
                    intent.putExtra("action", " ");
                    startActivity(intent);
                } else {
                    intent = new Intent(Splash.this, MainActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        }, 1000);
    }
}