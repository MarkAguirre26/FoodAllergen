package com.thesis.pdm.hallergen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.thesis.pdm.hallergen.Variable.logUser;

public class EditAccountActivity extends AppCompatActivity {


    private EditText etFName, etMName, etLName, etUsername, etPassword, etEmail, etPassword_retype;
    DatabaseAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);


        db = new DatabaseAdapter(this);

        TextView tvTitle = findViewById(R.id.toolbar_title);
        tvTitle.setText(R.string.manage_account);

        etFName = findViewById(R.id.etFName_edit);
        etMName = findViewById(R.id.etMName_edit);
        etLName = findViewById(R.id.etLName_edit);
        etUsername = findViewById(R.id.etUsername_edit);
        etPassword = findViewById(R.id.etPassword_edit);
        etPassword_retype = findViewById(R.id.etPassword_retype_edit);
        etEmail = findViewById(R.id.etEmail_edit);



        etFName.setText(logUser.getFirstName());
        etMName.setText(logUser.getMiddleName());
        etLName.setText(logUser.getLastName());
        etUsername.setText(logUser.getUsername());
        etPassword.setText(logUser.getPassword());
        etEmail.setText(logUser.getEmail());


    }


    public void OnClick_ToolbarBack(View view) {
      close();
    }
    public void OnClick_SubmitAccount(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));

        String strFname = etFName.getText().toString();
        String strMname = etMName.getText().toString();
        String strLname = etLName.getText().toString();
        String strUser = etUsername.getText().toString();
        String strPass = etPassword.getText().toString();
        String strPassRetype = etPassword_retype.getText().toString();
        String strEmail = etEmail.getText().toString();

        if (strFname.equals("") || strMname.equals("") || strLname.equals("") || strUser.equals("") || strPass.equals("")) {
            Toast.makeText(getApplicationContext(), "Fill Required Infromations", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!strPass.equals(strPassRetype)) {
            Toast.makeText(getApplicationContext(), "Password not mismatch", Toast.LENGTH_SHORT).show();
            return;
        }

        ModelsUser user = logUser;
        user.setFirstName(strFname);
        user.setMiddleName(strMname);
        user.setLastName(strLname);
        user.setUsername(strUser);
        user.setPassword(strPass);
        user.setEmail(strEmail);

        logUser = user;

        db.updateUserData(user);
       close();
    }

    @Override
    public void onBackPressed() {

        close();
    }

    void close() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));//Jump to main LoginActivity
        finishAffinity();
    }

}
