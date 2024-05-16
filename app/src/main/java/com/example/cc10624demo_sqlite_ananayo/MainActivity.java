package com.example.cc10624demo_sqlite_ananayo;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText txtName, txtLocation, txtCourse;
    Button btnSave, btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtName = findViewById(R.id.txtName);
        txtLocation = findViewById(R.id.txtLocation);
        txtCourse = findViewById(R.id.txtCourse);
        btnSave = findViewById(R.id.btnSave);
        btnView = findViewById(R.id.btnView);


        btnSave.setOnClickListener(v -> {
            String name = txtName.getText().toString();
            String location = txtLocation.getText().toString();
            String course = txtCourse.getText().toString();
            DBHandler dbHandler = new DBHandler(this);
            if (name.isEmpty()) {
                toastMessage("Name is required");
                return;
            }

            if (location.isEmpty()) {
                toastMessage("Location is required");
                return;
            }

            if (course.isEmpty()) {
                toastMessage("Course is required");
                return;
            } else {
                // add new student
                Student newStudent = new Student(name, location, course);
                dbHandler.addStudent(newStudent);
                toastMessage("Student added successfully");
            }
            Intent intent = new Intent(MainActivity.this, StudentList.class);
            startActivity(intent);
        });

        btnView.setOnClickListener(v -> {
            startActivity(StudentList.class);
        });


    }

    public void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void startActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }
}