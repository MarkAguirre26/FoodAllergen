package com.thesis.pdm.hallergen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.thesis.pdm.hallergen.Variable.rCode;

public class RegistrationVerificationActivity extends AppCompatActivity {

    DatabaseAdapter db;


    EditText etVerificationCode;
    TextView rCodeMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_verification);
        etVerificationCode = findViewById(R.id.etVerificationCode);
        rCodeMessage = findViewById(R.id.rCodeMessage);
        rCodeMessage.setVisibility(View.GONE);


        db = new DatabaseAdapter(this);


    }

    public void verify_Clicked(View view) {

        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));
        rCodeMessage.setVisibility(View.GONE);

        if (ManageAccountActivity.getVerificationCode().equals(etVerificationCode.getText().toString())) {
            db.insertUserData(ManageAccountActivity.getRegistrationUserData());//Save to sqllite
            startActivity(new Intent(getApplicationContext(), RegistrationSuccessActivity.class));//Jump to main activity

        } else {
            rCodeMessage.setVisibility(View.VISIBLE);
//            Toast.makeText(getApplicationContext(), "Invalid Verification Code:" + etVerificationCode.getText().toString(), Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), ManageAccountActivity.class));
        finish();
    }

    public void reSend_Clicked(View view) {
        String email = ManageAccountActivity.getRegistrationUserData().getEmail();
        Email.Send(rCode, email);
        Toast.makeText(getApplicationContext(), "Verification Code Send to " + email, Toast.LENGTH_SHORT).show();
    }
}
