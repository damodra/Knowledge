package com.example.knowledge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    TextView tv_main_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       init();
        tv_main_page=findViewById(R.id.tv_main_page);
        tv_main_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calendar = Calendar.getInstance();
                currentMinute = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog=new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        tv_main_page.setText(String.format("%02d:%02d", hourOfDay, minutes));
                    }
                },0,0,false);
                timePickerDialog.show();
            }
        });

    }

    private void init() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView tvStudentMain=findViewById(R.id.tv_student_main);
        TextView tvTeacherMain=findViewById(R.id.tv_teacher_main);
        TextView tvMainPage=findViewById(R.id.tv_main_page);
        tvMainPage.setPaintFlags(tvMainPage.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        setSupportActionBar(toolbar);
        tvStudentMain.setOnClickListener(v-> startActivity(new Intent(MainActivity.this,StudLoginActivity.class)));
        tvTeacherMain.setOnClickListener(v-> startActivity(new Intent(MainActivity.this,TechLoginActivity.class)));
    }
}
