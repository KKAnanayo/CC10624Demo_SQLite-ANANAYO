package com.example.cc10624demo_sqlite_ananayo;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class StudentEdit extends AppCompatActivity {

    EditText txtNameUpdate, txtLocationUpdate, txtCourseUpdate;
    Button btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_edit);

        txtNameUpdate = findViewById(R.id.txtNameUpdate);
        txtLocationUpdate = findViewById(R.id.txtLocationUpdate);
        txtCourseUpdate = findViewById(R.id.txtCourseUpdate);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        Intent intent = getIntent();
        Student student = (Student) intent.getSerializableExtra("Student");

        btnDelete.setOnClickListener(v -> {
            if (student != null) {
                DBHandler dbHandler = new DBHandler(this);
                dbHandler.deleteStudent(student.getId());
                toastMessage("Student deleted successfully");

                Intent delete = new Intent(StudentEdit.this, StudentList.class);
                startActivity(delete);
            } else {
                toastMessage("No student to delete");
            }
        });


        btnUpdate.setOnClickListener(v -> {
            String name = txtNameUpdate.getText().toString();
            String location = txtLocationUpdate.getText().toString();
            String course = txtCourseUpdate.getText().toString();
            DBHandler dbHandler = new DBHandler(this);

            if (student != null) {
                // update existing student
                student.setName(name);
                student.setLocation(location);
                student.setCourse(course);
                dbHandler.updateStudent(student);
                toastMessage("Student updated successfully");
            }

            else if(name.isEmpty()){
                toastMessage("Name is required");
                return;
            }

            else if(location.isEmpty()){
                toastMessage("Location is required");
                return;
            }

            else if(course.isEmpty()){
                toastMessage("Course is required");
                return;
            }

            Intent update = new Intent(StudentEdit.this, StudentList.class);
            startActivity(update);

        });


        if (student != null) {
            // populate your input fields with the student's information
            txtNameUpdate.setText(student.getName());
            txtLocationUpdate.setText(student.getLocation());
            txtCourseUpdate.setText(student.getCourse());
        }
    }



    public void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void startActivity(Class<?> cls){
        startActivity(new Intent(this, cls));
    }
}