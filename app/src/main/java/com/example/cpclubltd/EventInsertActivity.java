package com.example.cpclubltd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import view.fragment.ongoing;

public class EventInsertActivity extends AppCompatActivity {

    EditText nameET,wingET,detailsET;
    TextView titleTV,dateTV;
    Button insertButton,  viewListButton;;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_insert);

        nameET=findViewById(R.id.nameEditId);
        wingET=findViewById(R.id.wingEditId);
        detailsET=findViewById(R.id.detailsETId);
        titleTV=findViewById(R.id.titleTVId);
        dateTV=findViewById(R.id.dateTVId);
        insertButton=findViewById(R.id.insertBtnId);
        viewListButton=findViewById(R.id.listBtnId);


        dbHelper= new DbHelper(EventInsertActivity.this);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        viewListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new ongoing();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.eventInsertActivity, fragment).commit();
            }
        });


        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = nameET.getText().toString();
                String wing = wingET.getText().toString();
                String date = dateTV.getText().toString();
                String details = detailsET.getText().toString();

                OngoingInfo ongoingInfo=new OngoingInfo(name,wing,date,details);
                long value = dbHelper.insertOngoingInfo(ongoingInfo);
                if (value != -1) {
                    Toast.makeText(EventInsertActivity.this, "Data is Inserted!", Toast.LENGTH_SHORT).show();
                    nameET.getText().clear();
                    wingET.getText().clear();
                    detailsET.getText().clear();
                }

            }
        });


        dateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePicker datePicker = new DatePicker(EventInsertActivity.this);

                DatePickerDialog dialog = new DatePickerDialog(EventInsertActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        dateTV.setText(i+"-"+(i1+1)+"-"+i2);
                    }
                }, datePicker.getYear(), datePicker.getMonth(),datePicker.getDayOfMonth());

                dialog.show();
            }
        });


    }



}