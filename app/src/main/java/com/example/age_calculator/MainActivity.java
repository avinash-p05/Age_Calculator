package com.example.age_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private EditText t;
    private ImageButton birthDateTextView;
    private TextView ageTextView, Tn;
    private Button calculateButton;
    private Calendar birthDateCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Tn = findViewById(R.id.editTextNumber);
        birthDateTextView = findViewById(R.id.imageButton3);
        ageTextView = findViewById(R.id.editTextDate);
        calculateButton = findViewById(R.id.button3);

        birthDateCalendar = Calendar.getInstance();

        birthDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAge();
            }
        });
    }

    private void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        birthDateCalendar.set(year, month, dayOfMonth);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        Tn.setText(dateFormat.format(birthDateCalendar.getTime()));
                    }
                },
                birthDateCalendar.get(Calendar.YEAR),
                birthDateCalendar.get(Calendar.MONTH),
                birthDateCalendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void calculateAge() {
        Calendar currentDate = Calendar.getInstance();

        int years = currentDate.get(Calendar.YEAR) - birthDateCalendar.get(Calendar.YEAR);
        int months = currentDate.get(Calendar.MONTH) - birthDateCalendar.get(Calendar.MONTH);
        int days = currentDate.get(Calendar.DAY_OF_MONTH) - birthDateCalendar.get(Calendar.DAY_OF_MONTH);

        if (days < 0) {
            months--;
            Calendar lastMonthCalendar = (Calendar) birthDateCalendar.clone();
            lastMonthCalendar.add(Calendar.MONTH, 1);
            days = lastMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDateCalendar.get(Calendar.DAY_OF_MONTH) + currentDate.get(Calendar.DAY_OF_MONTH);
        }

        if (months < 0) {
            years--;
            months += 12;
        }

        ageTextView.setText("" + years + " years, " + months + " months, " + days + " days.");
    }
}
