package com.example.cpclubltd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UpcomingDetailsActivity extends AppCompatActivity {

    TextView uName,uID,uWing,uDate,uDetails;
    Button registrationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_details);
        setTitle("Upcoming Event Details");

        final UpcomingInfo detailInfo = (UpcomingInfo) getIntent().getSerializableExtra("details");

        uName=findViewById(R.id.uName);
        uID=findViewById(R.id.uID);
        uWing=findViewById(R.id.uWing);
        uDate=findViewById(R.id.uDate);
        uDetails=findViewById(R.id.uDetails);
        registrationBtn=findViewById(R.id.registerBtnId);

        uName.setText("Event name -"+detailInfo.getUp_name());
        uID.setText("Event ID -"+detailInfo.getUp_id());
        uWing.setText("Event name -"+detailInfo.getUp_wing());
        uDate.setText("Event name -"+detailInfo.getUp_date());
        uDetails.setText("Event name -"+detailInfo.getUp_details());

        registrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpcomingDetailsActivity.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}