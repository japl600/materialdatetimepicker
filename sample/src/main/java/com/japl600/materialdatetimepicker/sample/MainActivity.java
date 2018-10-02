package com.japl600.materialdatetimepicker.sample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.japl600.materialdatetimepicker.date.DatePickerDialog;
import com.japl600.materialdatetimepicker.multidate.MultiDatePickerDialog;
import com.japl600.materialdatetimepicker.time.RadialPickerLayout;
import com.japl600.materialdatetimepicker.time.TimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by aprada on 9/26/18.
 */
public class MainActivity extends AppCompatActivity implements
        TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener,
        MultiDatePickerDialog.OnDateSetListener,View.OnClickListener{

    private static final String TIMEPICKER = "TimePickerDialog",
            DATEPICKER = "DatePickerDialog", MULTIDATEPICKER = "MultiDatePickerDialog";

    private CheckBox mode24Hours, modeDarkTime, modeDarkDate;
    private TextView timeTextView, dateTextView, multiDateTextView;
    private Button timeButton, dateButton, multiDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        handleClicks();
    }

    private void initializeViews() {
        timeTextView = findViewById(R.id.time_textview);
        dateTextView = findViewById(R.id.date_textview);
        multiDateTextView = findViewById(R.id.multi_date_textview);
        timeButton = findViewById(R.id.time_button);
        dateButton = findViewById(R.id.date_button);
        multiDataButton = findViewById(R.id.multi_date_button);
        mode24Hours = findViewById(R.id.mode_24_hours);
        modeDarkTime = findViewById(R.id.mode_dark_time);
        modeDarkDate = findViewById(R.id.mode_dark_date);
    }

    private void handleClicks() {
        timeButton.setOnClickListener(this);
        dateButton.setOnClickListener(this);
        multiDataButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String fontName = "lato_italic";
        switch (view.getId()) {
            case R.id.time_button: {
                Calendar now = Calendar.getInstance();
                TimePickerDialog tpd = TimePickerDialog.newInstance(
                        MainActivity.this,
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE),
                        mode24Hours.isChecked()
                );
                tpd.setThemeDark(modeDarkTime.isChecked());
                tpd.setTypeface(fontName);
                tpd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        Log.d(TIMEPICKER, "Dialog was cancelled");
                    }
                });
                tpd.show(getFragmentManager(), TIMEPICKER);
                break;
            }
            case R.id.date_button: {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        MainActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.setThemeDark(modeDarkDate.isChecked());
                dpd.setTypeface(fontName);
                dpd.show(getFragmentManager(), DATEPICKER);
                break;
            }
            case R.id.multi_date_button:
                MultiDatePickerDialog mdpd = MultiDatePickerDialog.newInstance(MainActivity.this, null);
                Calendar[] pc = new Calendar[30];
                for (int i = 0; i < pc.length; i++) {
                    pc[i] = Calendar.getInstance();
                    pc[i].add(Calendar.DAY_OF_YEAR, i);
                }
                mdpd.setMinDate(pc[0]);
                mdpd.setMaxDate(pc[29]);
                //mdpd.setSelectableDays(pc);
                mdpd.setThemeDark(modeDarkDate.isChecked());
                mdpd.setTypeface(fontName);
                mdpd.show(getFragmentManager(), MULTIDATEPICKER);
                break;
            default:
                break;
        }
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        String hourString = hourOfDay < 10 ? "0" + hourOfDay : "" + hourOfDay;
        String minuteString = minute < 10 ? "0" + minute : "" + minute;
        String time = "You picked the following time: " + hourString + ":" + minuteString;
        timeTextView.setText(time);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        // Note: monthOfYear is 0-indexed
        String date = "You picked the following date: " + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
        dateTextView.setText(date);
    }

    @Override
    public void onDateSet(MultiDatePickerDialog view, ArrayList<Calendar> selectedDays) {
        StringBuilder date = new StringBuilder("You picked the following dates:\n\t");
        for (Calendar calendar : selectedDays) {
            date.append(calendar.get(Calendar.DAY_OF_MONTH)).append("/")
                    .append(calendar.get(Calendar.MONTH) + 1).append("/")
                    .append(calendar.get(Calendar.YEAR)).append("\n\t");
        }
        multiDateTextView.setText(date.toString());
    }
}
