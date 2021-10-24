package com.example.lab211;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.app.ActionBar;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button timeChanger;
    private int hourOfDay, minute;
    TextView textView;
    ConstraintLayout layout;
    boolean viewExist=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeChanger = findViewById(R.id.timeBut);
        layout = (ConstraintLayout) findViewById(R.id.constraintL);
        timeChanger.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(viewExist){
            layout.removeView(textView);
        }

        createTextView();
        setTime();


    }

    public void setTime() {
        TimePickerDialog dialog = new TimePickerDialog(this, t, hourOfDay, minute, true);
        dialog.show();
    }

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


            String time = hourOfDay + ":" + minute;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            try {
                Date data = simpleDateFormat.parse(time);
                textView.setText(simpleDateFormat.format(data));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    };

    public void createTextView() {
        textView = new TextView(this);
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(CoordinatorLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.startToStart = ConstraintSet.PARENT_ID;
        layoutParams.bottomToBottom = ConstraintSet.PARENT_ID;
        layoutParams.endToEnd = ConstraintSet.PARENT_ID;
        layoutParams.topToBottom = R.id.timeBut;
        textView.setLayoutParams(layoutParams);
        textView.setTextSize(32f);
        viewExist=true;
        layout.addView(textView);
        //textView.setText("Hello");
    }
}
