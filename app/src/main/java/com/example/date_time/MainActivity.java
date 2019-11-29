package com.example.date_time;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.squareup.timessquare.CalendarPickerView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calendar calender = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calender.getTime());
        TextView textViewDate = findViewById(R.id.text_view_date);
        textViewDate.setText(currentDate);
        Button timePickerButton = findViewById(R.id.button_time_picker);
        timePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
        Button buttonDatePicker = findViewById(R.id.button_date_picker);
        buttonDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
        Date today = new Date();
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);
        CalendarPickerView datePicker = findViewById(R.id.calender_picker);
        datePicker.init(today, nextYear.getTime())
                .inMode(CalendarPickerView.SelectionMode.RANGE)
                .withSelectedDate(today);

        datePicker.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
//                String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(date);
                Calendar calSelected = Calendar.getInstance();
                calSelected.setTime(date);
                String selectedDate = "" + calSelected.get(Calendar.DAY_OF_MONTH) + " " + (calSelected.get(Calendar.MONTH) + 1) + " " + calSelected.get(Calendar.YEAR);
                Toast.makeText(MainActivity.this, selectedDate, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDateUnselected(Date date) {
                String unSelectedDate;
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView textView = findViewById(R.id.text_view_time);
        textView.setText("Hour: " + hourOfDay + " Minute: " + minute);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        TextView textView = findViewById(R.id.text_view_date_picker);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        textView.setText(currentDateString);
    }
}
