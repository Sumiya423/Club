package com.example.cpclubltd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ApplicationDetails extends AppCompatActivity {

    TextView stuName,stuID,eveId,stuEmail,stuDetails;
    Button emailBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_details);
        setTitle("Application Details");

        final ApplInfo stuDetail = (ApplInfo) getIntent().getSerializableExtra("appDetails");

        stuName=findViewById(R.id.stuNameTV);
        stuID=findViewById(R.id.studentIdTV);
        eveId=findViewById(R.id.eveIdTV);
        stuEmail=findViewById(R.id.stuEmailTV);
        stuDetails=findViewById(R.id.stuDetailsTV);

        emailBtn=findViewById(R.id.emailBtnId);

        stuName.setText("Student name - "+stuDetail.getsName());
        stuID.setText("Student ID - "+stuDetail.getsId());
        eveId.setText("Event ID - "+stuDetail.getEvID());
        stuEmail.setText("Student Email - "+stuDetail.getsEmail());
        stuDetails.setText("Reason for applying the event - "+stuDetail.getsDetails());


        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sentMail();
            }
        });



    }

    private void sentMail() {
        String toMail = stuEmail.getText().toString();
        String[] makeTo = toMail.split(",");
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,makeTo);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"Choose an email client"));
    }
}