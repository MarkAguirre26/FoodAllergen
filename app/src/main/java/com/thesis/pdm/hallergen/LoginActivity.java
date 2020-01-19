package com.thesis.pdm.hallergen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.thesis.pdm.hallergen.Variable.logUser;

public class LoginActivity extends AppCompatActivity {

    // Declairation
    private Button btnLogin, btnCreate;
    private EditText etUsername, etPassword;
    private CheckBox cbKeepLogin;

    private SharedPreferences pref;
    private SharedPreferences.Editor prefEdit;

    private DatabaseReference refDB;// = FirebaseDatabase.getInstance().getReference();

    //    private ModelsUser logUser = new ModelsUser();
    private List<ModelsUser> UserList = new ArrayList<>();

    private DatabaseAdapter db;// = new DatabaseAdapter(getApplicationContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAnimation();
        setContentView(R.layout.activity_login);
        pref = this.getSharedPreferences(String.valueOf(R.string.pref_Account), MODE_PRIVATE);
        prefEdit = pref.edit();
        btnLogin = findViewById(R.id.btnLogin);
        btnCreate = findViewById(R.id.btnCreate);
        cbKeepLogin = findViewById(R.id.cbKeepLogin);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        etUsername.setText("mark");
        etPassword.setText("aaaaaaaaa");

        db = new DatabaseAdapter(getApplicationContext());
//
//        if (UtilityNetworkConnectivity.checkNetworkConnection(getApplicationContext())) {
//            refDB = FirebaseDatabase.getInstance().getReference();
//            UserList = Utility.GetAllUsersToFirebase(refDB);
//        } else {
//           // Toast.makeText(getApplicationContext(),"Offline",Toast.LENGTH_SHORT).show();
////            TO DO
////           openNoOfflineDataActivity();
//        }


        // if keep me login is checked move main activity
        if (pref.getBoolean(String.valueOf(R.string.pref_KeepMeLogin), false)) {
            startActivity(new Intent(this, MainActivity.class));
        }

        // show/hide password
        etPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Utility.ChangePasswordInputType(event, etPassword, etPassword.getTransformationMethod() == HideReturnsTransformationMethod.getInstance());
                return false;
            }
        });
    }


    private void openMainActivity() {

        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();


    }


    //Your Slide animation
    public void setAnimation() {
        if (Build.VERSION.SDK_INT > 20) {
            Slide slide = new Slide();
            slide.setSlideEdge(Gravity.LEFT);
            slide.setDuration(400);
            slide.setInterpolator(new DecelerateInterpolator());
            getWindow().setExitTransition(slide);
            getWindow().setEnterTransition(slide);
        }
    }


    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    private void UserLogin(String username, String password) {


        //input validation
        if (username.equals("") || password.equals("")) {
            Toast.makeText(getApplicationContext(), "Enter your username and password.", Toast.LENGTH_SHORT).show();
            return;
        }
//        if (!Utility.isOnline(getSystemService(Context.CONNECTIVITY_SERVICE))) {
//            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
//            return;
//        }

        // Find input username password if exist
        ArrayList<ModelsUser> userList = new ArrayList<>(UserList.size());
        userList.addAll(UserList);
        logUser = Utility.FindUser(username, password, userList);


        //not found
        if (logUser == null) {
            Toast.makeText(getApplicationContext(), "Account not found", Toast.LENGTH_SHORT).show();
            return;
        }

        //Save all users to sqlite
//        for (ModelsUser mUser : UserList) {
//            db.insertUserData(mUser);
//            if (mUser.getFamily().size() > 0) {
//                for (ModelsFamily family : mUser.getFamily()) {
//                    db.insertFamilyData(family);
//                }
//            }
//
//
//        }


        // set log user in cache
        Utility.setLogUserDataToPref(prefEdit, logUser);
        // move to main activity
        openMainActivity();
    }

    // OnClick Listeners
    public void OnClick_CreateAccount(View view) {
//        findViewById(R.id.btnSubmit).setTag("New");
        Variable.isNew = "New";
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));
        startActivity(new Intent(this, ManageAccountActivity.class));
    }

    public void OnClick_KeepLogin(View view) {
        prefEdit.putBoolean(String.valueOf(R.string.pref_KeepMeLogin), cbKeepLogin.isChecked()).apply();
    }

    public void OnCLick_Login(View view) {


//        if (!UtilityNetworkConnectivity.checkNetworkConnection(getApplicationContext())) {
        UserList = db.getUserData();
//        }


        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));
        UserLogin(etUsername.getText().toString(), etPassword.getText().toString());
//
//        for (ModelsUser u : UserList) {
//            if (u.getFamily().size() > 0) {
//                Log.d("Family", u.getFamily().get(0).getName());
//            }
//
//        }

    }
}
