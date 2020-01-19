package com.thesis.pdm.hallergen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;

public class RegistrationSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_success);
    }

    public void done_Clicked(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));
       close();
    }

    @Override
    public void onBackPressed() {
       close();
    }

    void close(){
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));//Jump to main LoginActivity
        finishAffinity();
    }
}
