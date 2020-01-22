package com.thesis.pdm.hallergen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class ManageAccountActivity extends AppCompatActivity {

    private EditText etFName, etMName, etLName, etUsername, etPassword, etEmail;
    public static SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_account);
        pref = this.getSharedPreferences(String.valueOf(R.string.pref_Account), MODE_PRIVATE);


        TextView tvTitle = findViewById(R.id.toolbar_title);
        tvTitle.setText(R.string.createaccount);

        etFName = findViewById(R.id.etFName);
        etMName = findViewById(R.id.etMName);
        etLName = findViewById(R.id.etLName);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail);

    }

    public void OnClick_ToolbarBack(View view) {
        finish();
    }

    public void OnClick_SubmitAccount(View view) {

        if (!Utility.isOnline(getSystemService(Context.CONNECTIVITY_SERVICE))) {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();

        } else {


            view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));

            String strFname = etFName.getText().toString();
            String strMname = etMName.getText().toString();
            String strLname = etLName.getText().toString();
            String strUser = etUsername.getText().toString();
            String strPass = etPassword.getText().toString();
            String strEmail = etEmail.getText().toString();

            if (strFname.equals("") || strMname.equals("") || strLname.equals("") || strUser.equals("") || strPass.equals("")) {
                Toast.makeText(getApplicationContext(), "Fill Required Infromations", Toast.LENGTH_SHORT).show();
                return;
            }



            ModelsUser newUser = new ModelsUser();
            newUser.setUserUID(getRandomNumber(1000, 10000) + "");
            newUser.setFirstName(strFname);
            newUser.setMiddleName(strMname);
            newUser.setLastName(strLname);
            newUser.setUsername(strUser);
            newUser.setPassword(strPass);
            newUser.setEmail(strEmail);

            String rCode = getRandomNumber(1000, 10000) + "";
            Email.Send(getApplicationContext(),rCode, newUser.getEmail());
            Utility.setRegistrationUserDataToPref(pref, newUser, rCode);
            startActivity(new Intent(this, RegistrationVerificationActivity.class));

        }
    }


    public static String getVerificationCode() {
        Variable.rCode = pref.getString(String.valueOf(R.string.pref_code), "");
        return Variable.rCode;
    }


    private int getRandomNumber(int min, int max) {
        return (new Random()).nextInt((max - min) + 1) + min;
    }


    public static ModelsUser getRegistrationUserData() {
        return Utility.getRegistrationUserDataFromPref(pref);
    }



}
