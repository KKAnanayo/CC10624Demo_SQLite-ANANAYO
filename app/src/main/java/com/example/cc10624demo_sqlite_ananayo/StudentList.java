package com.example.cc10624demo_sqlite_ananayo;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class StudentList extends AppCompatActivity{

    private ListView lvStudentList;
    private Button btnBack;
    private List<Student> students;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        lvStudentList = findViewById(R.id.lvStudentList);
        btnBack = findViewById(R.id.btnBack);
        dbHandler = new DBHandler(this);

        students = dbHandler.getAllStudents();

        List<String> studentStrings = new ArrayList<>();
        for (Student student : students) {
            studentStrings.add(student.toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentStrings);
        lvStudentList.setAdapter(adapter);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentList.this, MainActivity.class);
                startActivity(intent);
            }
        });

        lvStudentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student clickedStudent = students.get(position);

                Intent intent = new Intent(StudentList.this, StudentEdit.class);
                intent.putExtra("Student", clickedStudent);
                startActivity(intent);
            }
        });
    }

}