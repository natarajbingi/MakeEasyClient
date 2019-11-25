package com.makein.client.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.makein.client.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SignUpActivity extends AppCompatActivity {
    Button prev,next;
    TextView date, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        login = findViewById(R.id.login_text);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

       /* prev = findViewById(R.id.prev);
        next = findViewById(R.id.next);
        date = findViewById(R.id.textdate);

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance(Locale.UK);
                c.set(Calendar.DAY_OF_WEEK,  c.getFirstDayOfWeek());
              SimpleDateFormat  daydate = new SimpleDateFormat("dd.MM.yyyy");

                List previous = new ArrayList();
                for (int i = 0; i < 7; i++) {
                    c.add(Calendar.DATE, -1);
                   String start = daydate.format(c.getTime());
                    previous.add(start);
                }
                date.setText("" + previous.get(6) + "-" + previous.get(2));
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance(Locale.UK);
                c.set(Calendar.DAY_OF_WEEK,  c.getFirstDayOfWeek());
                SimpleDateFormat  daydate = new SimpleDateFormat("dd.MM.yyyy");

                List next = new ArrayList();
                for (int i = 0; i < 7; i++) {
                    c.add(Calendar.DATE, 1);
                  String  start = daydate.format(c.getTime());
                    next.add(start);
                }
                date.setText("" + next.get(0) + "-" + next.get(4));
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c1 = Calendar.getInstance();
                int mYear = c1.get(Calendar.YEAR);
                int mMonth = c1.get(Calendar.MONTH);
                int mDay = c1.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(SignUpActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                c1.set(year, monthOfYear, dayOfMonth);
                                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                                SimpleDateFormat dateFormatter1 = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

                                 date.setText( dateFormatter1.format(c1.getTime()));
                                c1.set(Calendar.DAY_OF_WEEK, 2);

                                int year1 = c1.get(Calendar.YEAR);
                               // int month1 = c1.get(Calendar.MONTH);
                                int month1 = monthOfYear+1;
                                final int day1 = c1.get(Calendar.DAY_OF_MONTH);
                               // int day1 = dayOfMonth;

                                //last day of week
                                c1.set(Calendar.DAY_OF_WEEK, 6);

                                int year7 = c1.get(Calendar.YEAR);
                                //int month7 = c1.get(Calendar.MONTH) + 1;
                                int month7 = monthOfYear +1;
                                 int day7 =  c1.get(Calendar.DAY_OF_MONTH);

                                String firtdate = c1.get(Calendar.YEAR) + "-" + month1 + "-" + String.valueOf(day1);
                                String lastdate = c1.get(Calendar.YEAR) + "-" + month7 + "-" + String.valueOf(day7);

                                date.setText(firtdate + " to " + lastdate);


                            }
                        },mYear,mMonth,mDay);



                // datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());

                // mYears = mYear;
                // mMonths = mMonth;

                datePickerDialog.show();

            }

        });*/
    }
    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }
}
