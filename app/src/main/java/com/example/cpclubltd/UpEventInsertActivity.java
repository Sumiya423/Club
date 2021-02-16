package com.example.cpclubltd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import view.fragment.ongoing;

public class UpEventInsertActivity extends AppCompatActivity {

    EditText up_nameET,up_wingET,up_detailsET;
    TextView up_titleTV,up_dateTV;
    Button up_insertButton,  up_viewListButton;;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_event_insert);

        up_nameET=findViewById(R.id.up_nameEditId);
        up_wingET=findViewById(R.id.up_wingEditId);
        up_detailsET=findViewById(R.id.up_detailsETId);
        up_titleTV=findViewById(R.id.up_titleTVId);
        up_dateTV=findViewById(R.id.up_dateTVId);
        up_insertButton=findViewById(R.id.up_insertBtnId);
        up_viewListButton=findViewById(R.id.up_listBtnId);

        dbHelper= new DbHelper(UpEventInsertActivity.this);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        up_viewListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new ongoing();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.upcomingFragment, fragment).commit();
            }
        });

        up_insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = up_nameET.getText().toString();
                String wing = up_wingET.getText().toString();
                String date = up_dateTV.getText().toString();
                String details = up_detailsET.getText().toString();

                UpcomingInfo upcomingInfo=new UpcomingInfo(name,wing,date,details);
                long value = dbHelper.insertUpcomingInfo(upcomingInfo);
                if (value != -1) {
                    Toast.makeText(UpEventInsertActivity.this, "Data is Inserted!", Toast.LENGTH_SHORT).show();
                    up_nameET.getText().clear();
                    up_wingET.getText().clear();
                    up_detailsET.getText().clear();
                }

            }
        });

        up_dateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePicker datePicker = new DatePicker(UpEventInsertActivity.this);

                DatePickerDialog dialog = new DatePickerDialog(UpEventInsertActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        up_dateTV.setText(i+"-"+(i1+1)+"-"+i2);
                    }
                }, datePicker.getYear(), datePicker.getMonth(),datePicker.getDayOfMonth());

                dialog.show();
            }
        });
    }
}