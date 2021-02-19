package com.example.cpclubltd;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    EditText stuName,stuID,eventID,stuEmail,stuDetails;
    Button submitBtn;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        stuName=findViewById(R.id.stuNameET);
        stuID=findViewById(R.id.stuIdET);
        stuEmail=findViewById(R.id.stuEmailET);
        stuDetails=findViewById(R.id.stuDetailsET);
        eventID=findViewById(R.id.eventID_ET);
        submitBtn=findViewById(R.id.submitBtnET);

        dbHelper = new DbHelper(RegistrationActivity.this);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameS = stuName.getText().toString();
                String idS = stuID.getText().toString();
                String emailS = stuEmail.getText().toString();
                String detailsS = stuDetails.getText().toString();
                String eventIdS = eventID.getText().toString();

                ApplInfo applicationInfo=new ApplInfo(idS,nameS,eventIdS,emailS,detailsS);
                long value = dbHelper.insertApplication(applicationInfo);
                if(value!=-1){
                    Toast.makeText(RegistrationActivity.this, "Submitted Successfully for the Event !", Toast.LENGTH_SHORT).show();
                    stuName.getText().clear();
                    stuDetails.getText().clear();
                    stuEmail.getText().clear();
                    stuID.getText().clear();
                    eventID.getText().clear();
                }
            }
        });


    }
}