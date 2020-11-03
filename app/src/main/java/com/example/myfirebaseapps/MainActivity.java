package com.example.myfirebaseapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button c, lct, l, r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);

        //findViewById
        lct = findViewById(R.id.bttn_lct);
        c = findViewById(R.id.bttn_c);
        r = findViewById(R.id.bttn_r);
        l = findViewById(R.id.bttn_l);
    }
    public void button_lecturer_clicked(View view) {
        Intent intent = new Intent(MainActivity.this, AddLecturer.class);
        intent.putExtra("action", "add");
        startActivity(intent);
        finish();
    }
    public void button_course_clicked(View view) {
        Intent intent = new Intent(MainActivity.this, AddCourse.class);
        intent.putExtra("action", "add");
        startActivity(intent);
        finish();
    }
    public void button_student_clicked(View view) {
        Intent intent = new Intent(MainActivity.this, AddStudent.class);
        intent.putExtra("action", "add");
        startActivity(intent);
        finish();
    }
    public void button_login_clicked(View view) {
        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
        finish();
    }
}