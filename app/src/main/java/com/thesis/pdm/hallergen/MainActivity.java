package com.thesis.pdm.hallergen;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.thesis.pdm.hallergen.Variable.logUser;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener,
        AdapterRecyclerFamMember.OnClickFamilyMemberListener, AdapterRecyclerFamMember.OnLongClickFamilyMemberListener {

    private CheckBox cbKeepLogin, cbNotification;
    //Allergies
    private CheckBox cba1, cba1_1, cba1_2, cba1_3, cba1_4, cba1_5, cba1_6, cba1_7,
            cba2, cba2_1, cba2_2,
            cba3, cba3_1, cba3_2, cba3_3, cba3_4, cba3_5,
            cba4, cba4_1, cba4_2, cba4_3, cba4_4,
            cba5, cba5_1, cba5_2, cba5_3, cba5_4,
            cba6, cba6_1, cba6_2, cba6_3, cba6_4, cba6_5, cba6_6, cba6_7, cba6_8, cba6_9,
            cba7, cba7_1, cba7_2, cba7_3, cba7_4, cba7_5, cba7_6, cba7_7,
            cba8, cba8_1, cba8_2, cba8_3, cba8_4, cba8_5, cba8_6, cba8_7, cba8_8;
    private TextView tvCheckAllergy;

    private LinearLayout conSettings;
    private LinearLayout conUserAccount;
    //Add Family Member
    private EditText etFamName, etFamRelation, etFamBirthday, etFamAge, etFamHeight, etFamWeight;
    private DatePicker picker;
    private RadioButton rbMale, rbFemale, rbNotPregnant, rbPregnant;
    private RadioGroup rgGender, rgPregnant;
    private TextView tvUsername, tvName, tvEmail;
    //View Family Member
    private TextView tvFamName, tvFamRelation, tvFamBirthday, tvFamHeight, tvFamWeight, tvFamGender, tvFamPregnant, tvFamAllergies;

    private SharedPreferences pref;
    private SharedPreferences.Editor prefEdit;

    private DatabaseReference refDB = FirebaseDatabase.getInstance().getReference();

    private AdapterRecyclerFamMember adapter;
    private RecyclerView recyclerView;

    private ModelsFamily selectedFamMember = new ModelsFamily();
    private ArrayList<ModelsAllergy> selectedAllergies = new ArrayList<>();
    private ArrayList<CheckBox> allergyList = new ArrayList<>();
    private static ArrayList<ModelsUser> _userList = new ArrayList<>();
    private static ArrayList<ModelsFamily> _familyList = new ArrayList<>();


    private DatabaseAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAnimation();
        setContentView(R.layout.activity_main);
        pref = this.getSharedPreferences(String.valueOf(R.string.pref_Account), MODE_PRIVATE);
        prefEdit = pref.edit();
        db = new DatabaseAdapter(getApplicationContext());
        initID();

        if (!UtilityNetworkConnectivity.checkNetworkConnection(getApplicationContext())) {
            Toast.makeText(getApplicationContext(), "Offline Mode", Toast.LENGTH_SHORT).show();
//         return;
        }

        initData();
        checkPermissions();

        picker.setMaxDate(new Date().getTime());
        etFamBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.con_datepicker).setVisibility(View.VISIBLE);
            }
        });

        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                rbPregnant.setEnabled(checkedId == R.id.rbFemale);
                rbNotPregnant.setEnabled(checkedId == R.id.rbFemale);
                if (checkedId == R.id.rbMale) {
                    rbNotPregnant.setChecked(true);
                }
            }
        });
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


    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Connect to Network")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //TO DO
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }


    private void initID() {
        conSettings = findViewById(R.id.con_settings);
        conUserAccount = findViewById(R.id.con_user_account);
        cbKeepLogin = findViewById(R.id.cbKeepLogin);
        cbNotification = findViewById(R.id.cbNotification);
        etFamName = findViewById(R.id.etName);
        etFamRelation = findViewById(R.id.etRelation);
        etFamBirthday = findViewById(R.id.etBirthday);
        etFamAge = findViewById(R.id.etAge);
        etFamHeight = findViewById(R.id.etHeight);
        etFamWeight = findViewById(R.id.etWeight);
        picker = findViewById(R.id.datePicker);
        rgGender = findViewById(R.id.rgGender);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        rgPregnant = findViewById(R.id.rgPregnant);
        rbNotPregnant = findViewById(R.id.rbNotPregnant);
        rbPregnant = findViewById(R.id.rbPregnant);
        tvFamName = findViewById(R.id.tvName);
        tvFamRelation = findViewById(R.id.tvRelation);
        tvFamBirthday = findViewById(R.id.tvBirthday);
        tvFamHeight = findViewById(R.id.tvHeight);
        tvFamWeight = findViewById(R.id.tvWeight);
        tvFamGender = findViewById(R.id.tvGender);
        tvFamPregnant = findViewById(R.id.tvPregnant);
        tvFamAllergies = findViewById(R.id.tvListOfAllergy);
        tvUsername = findViewById(R.id.tvLoginUsername);
        tvName = findViewById(R.id.tvLoginName);
        tvEmail = findViewById(R.id.tvLoginEmail);

        //Allergies Checkbox
        cba1 = findViewById(R.id.cbAllergy_CowMilk);
        allergyList.add(cba1);
        cba1.setOnCheckedChangeListener(this);
        cba1_1 = findViewById(R.id.cbAllergy_CowMilk_1);
        allergyList.add(cba1_1);
        cba1_1.setOnCheckedChangeListener(this);
        cba1_2 = findViewById(R.id.cbAllergy_CowMilk_2);
        allergyList.add(cba1_2);
        cba1_2.setOnCheckedChangeListener(this);
        cba1_3 = findViewById(R.id.cbAllergy_CowMilk_3);
        allergyList.add(cba1_3);
        cba1_3.setOnCheckedChangeListener(this);
        cba1_4 = findViewById(R.id.cbAllergy_CowMilk_4);
        allergyList.add(cba1_4);
        cba1_4.setOnCheckedChangeListener(this);
        cba1_5 = findViewById(R.id.cbAllergy_CowMilk_5);
        allergyList.add(cba1_5);
        cba1_5.setOnCheckedChangeListener(this);
        cba1_6 = findViewById(R.id.cbAllergy_CowMilk_6);
        allergyList.add(cba1_6);
        cba1_6.setOnCheckedChangeListener(this);
        cba1_7 = findViewById(R.id.cbAllergy_CowMilk_7);
        allergyList.add(cba1_7);
        cba1_7.setOnCheckedChangeListener(this);
        cba2 = findViewById(R.id.cbAllergy_Eggs);
        allergyList.add(cba2);
        cba2.setOnCheckedChangeListener(this);
        cba2_1 = findViewById(R.id.cbAllergy_Eggs_1);
        allergyList.add(cba2_1);
        cba2_1.setOnCheckedChangeListener(this);
        cba2_2 = findViewById(R.id.cbAllergy_Eggs_2);
        allergyList.add(cba2_2);
        cba2_2.setOnCheckedChangeListener(this);
        cba3 = findViewById(R.id.cbAllergy_Fish);
        allergyList.add(cba3);
        cba3.setOnCheckedChangeListener(this);
        cba3_1 = findViewById(R.id.cbAllergy_Fish_1);
        allergyList.add(cba3_1);
        cba3_1.setOnCheckedChangeListener(this);
        cba3_2 = findViewById(R.id.cbAllergy_Fish_2);
        allergyList.add(cba3_2);
        cba3_2.setOnCheckedChangeListener(this);
        cba3_3 = findViewById(R.id.cbAllergy_Fish_3);
        allergyList.add(cba3_3);
        cba3_3.setOnCheckedChangeListener(this);
        cba3_4 = findViewById(R.id.cbAllergy_Fish_4);
        allergyList.add(cba3_4);
        cba3_4.setOnCheckedChangeListener(this);
        cba3_5 = findViewById(R.id.cbAllergy_Fish_5);
        allergyList.add(cba3_5);
        cba3_5.setOnCheckedChangeListener(this);
        cba4 = findViewById(R.id.cbAllergy_Peanuts);
        allergyList.add(cba4);
        cba4.setOnCheckedChangeListener(this);
        cba4_1 = findViewById(R.id.cbAllergy_Peanuts_1);
        allergyList.add(cba4_1);
        cba4_1.setOnCheckedChangeListener(this);
        cba4_2 = findViewById(R.id.cbAllergy_Peanuts_2);
        allergyList.add(cba4_2);
        cba4_2.setOnCheckedChangeListener(this);
        cba4_3 = findViewById(R.id.cbAllergy_Peanuts_3);
        allergyList.add(cba4_3);
        cba4_3.setOnCheckedChangeListener(this);
        cba4_4 = findViewById(R.id.cbAllergy_Peanuts_4);
        allergyList.add(cba4_4);
        cba4_4.setOnCheckedChangeListener(this);
        cba5 = findViewById(R.id.cbAllergy_Shellfish);
        allergyList.add(cba5);
        cba5.setOnCheckedChangeListener(this);
        cba5_1 = findViewById(R.id.cbAllergy_Shellfish_1);
        allergyList.add(cba5_1);
        cba5_1.setOnCheckedChangeListener(this);
        cba5_2 = findViewById(R.id.cbAllergy_Shellfish_2);
        allergyList.add(cba5_2);
        cba5_2.setOnCheckedChangeListener(this);
        cba5_3 = findViewById(R.id.cbAllergy_Shellfish_3);
        allergyList.add(cba5_3);
        cba5_3.setOnCheckedChangeListener(this);
        cba5_4 = findViewById(R.id.cbAllergy_Shellfish_4);
        allergyList.add(cba5_4);
        cba5_4.setOnCheckedChangeListener(this);
        cba6 = findViewById(R.id.cbAllergy_Soy);
        allergyList.add(cba6);
        cba6.setOnCheckedChangeListener(this);
        cba6_1 = findViewById(R.id.cbAllergy_Soy_1);
        allergyList.add(cba6_1);
        cba6_1.setOnCheckedChangeListener(this);
        cba6_2 = findViewById(R.id.cbAllergy_Soy_2);
        allergyList.add(cba6_2);
        cba6_2.setOnCheckedChangeListener(this);
        cba6_3 = findViewById(R.id.cbAllergy_Soy_3);
        allergyList.add(cba6_3);
        cba6_3.setOnCheckedChangeListener(this);
        cba6_4 = findViewById(R.id.cbAllergy_Soy_4);
        allergyList.add(cba6_4);
        cba6_4.setOnCheckedChangeListener(this);
        cba6_5 = findViewById(R.id.cbAllergy_Soy_5);
        allergyList.add(cba6_5);
        cba6_5.setOnCheckedChangeListener(this);
        cba6_6 = findViewById(R.id.cbAllergy_Soy_6);
        allergyList.add(cba6_6);
        cba6_6.setOnCheckedChangeListener(this);
        cba6_7 = findViewById(R.id.cbAllergy_Soy_7);
        allergyList.add(cba6_7);
        cba6_7.setOnCheckedChangeListener(this);
        cba6_8 = findViewById(R.id.cbAllergy_Soy_8);
        allergyList.add(cba6_8);
        cba6_8.setOnCheckedChangeListener(this);
        cba6_9 = findViewById(R.id.cbAllergy_Soy_9);
        allergyList.add(cba6_9);
        cba6_9.setOnCheckedChangeListener(this);
        cba7 = findViewById(R.id.cbAllergy_Treenuts);
        allergyList.add(cba7);
        cba7.setOnCheckedChangeListener(this);
        cba7_1 = findViewById(R.id.cbAllergy_Treenuts_1);
        allergyList.add(cba7_1);
        cba7_1.setOnCheckedChangeListener(this);
        cba7_2 = findViewById(R.id.cbAllergy_Treenuts_2);
        allergyList.add(cba7_2);
        cba7_2.setOnCheckedChangeListener(this);
        cba7_3 = findViewById(R.id.cbAllergy_Treenuts_3);
        allergyList.add(cba7_3);
        cba7_3.setOnCheckedChangeListener(this);
        cba7_4 = findViewById(R.id.cbAllergy_Treenuts_4);
        allergyList.add(cba7_4);
        cba7_4.setOnCheckedChangeListener(this);
        cba7_5 = findViewById(R.id.cbAllergy_Treenuts_5);
        allergyList.add(cba7_5);
        cba7_5.setOnCheckedChangeListener(this);
        cba7_6 = findViewById(R.id.cbAllergy_Treenuts_6);
        allergyList.add(cba7_6);
        cba7_6.setOnCheckedChangeListener(this);
        cba7_7 = findViewById(R.id.cbAllergy_Treenuts_7);
        allergyList.add(cba7_7);
        cba7_7.setOnCheckedChangeListener(this);
        cba8 = findViewById(R.id.cbAllergy_Wheat);
        allergyList.add(cba8);
        cba8.setOnCheckedChangeListener(this);
        cba8_1 = findViewById(R.id.cbAllergy_Wheat_1);
        allergyList.add(cba8_1);
        cba8_1.setOnCheckedChangeListener(this);
        cba8_2 = findViewById(R.id.cbAllergy_Wheat_2);
        allergyList.add(cba8_2);
        cba8_2.setOnCheckedChangeListener(this);
        cba8_3 = findViewById(R.id.cbAllergy_Wheat_3);
        allergyList.add(cba8_3);
        cba8_3.setOnCheckedChangeListener(this);
        cba8_4 = findViewById(R.id.cbAllergy_Wheat_4);
        allergyList.add(cba8_4);
        cba8_4.setOnCheckedChangeListener(this);
        cba8_5 = findViewById(R.id.cbAllergy_Wheat_5);
        allergyList.add(cba8_5);
        cba8_5.setOnCheckedChangeListener(this);
        cba8_6 = findViewById(R.id.cbAllergy_Wheat_6);
        allergyList.add(cba8_6);
        cba8_6.setOnCheckedChangeListener(this);
        cba8_7 = findViewById(R.id.cbAllergy_Wheat_7);
        allergyList.add(cba8_7);
        cba8_7.setOnCheckedChangeListener(this);
        cba8_8 = findViewById(R.id.cbAllergy_Wheat_8);
        allergyList.add(cba8_8);
        cba8_8.setOnCheckedChangeListener(this);
        tvCheckAllergy = findViewById(R.id.tvCheckAllergy);
    } // get ID from Front End

    @Override
    public void onBackPressed() {
        //super.onBackPressed();


        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finishAffinity();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void checkPermissions() {
        String per[] = {
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.CAMERA,
                Manifest.permission.INTERNET,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String s : per) {
            if (ContextCompat.checkSelfPermission(this, s) == PackageManager.PERMISSION_GRANTED) {
                continue;
            }
            listPermissionsNeeded.add(s);
        }
        if (listPermissionsNeeded.isEmpty()) {
            return;
        }
        ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 1);
    }

    private void initData() {
        // Get All user if new Account
        //Get Login etBirthdayUser Data
        tvUsername.setText(logUser.getUsername());
        tvName.setText(String.format("%s, %s %s",
                logUser.getLastName(),
                logUser.getFirstName(),
                logUser.getMiddleName()));
        tvEmail.setText(logUser.getEmail());
        //Settings
        boolean isKeepLogin = pref.getBoolean(String.valueOf(R.string.pref_KeepMeLogin), false);
        cbKeepLogin.setChecked(isKeepLogin);
        cbNotification.setChecked(pref.getBoolean(String.valueOf(R.string.pref_Notification), false));

        if (isKeepLogin) {
            logUser = Utility.getLogUserDataFromPref(pref);
        }

        //Get Family Member to DB
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView = findViewById(R.id.recyclerFamMember);
        recyclerView.setLayoutManager(layoutManager);
        refreshList();


        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);


    }

    private void refreshList() {


        logUser.setFamily(db.getFamilyData(logUser));
        List<ModelsFamily> families = logUser.getFamily();
        ArrayList<ModelsFamily> familyArrayList = new ArrayList<>();
        familyArrayList.addAll(families);

        adapter = new AdapterRecyclerFamMember(this, familyArrayList, this, this);
        recyclerView.setAdapter(adapter);
    }

    private void ClearFamInfo() {
        etFamName.setText(null);
        etFamRelation.setText(null);
        etFamBirthday.setText(null);
        etFamAge.setText(null);
        etFamHeight.setText(null);
        etFamWeight.setText(null);
        rbMale.setChecked(true);
        tvCheckAllergy.setText(null);
        selectedAllergies = new ArrayList<>();
    }

    private ArrayList<ModelsAllergy> getCheckedAllergies() {
        ArrayList<ModelsAllergy> allergies = new ArrayList<>();

        for (CheckBox cb : allergyList) {
            ModelsAllergy modelsAllergy = new ModelsAllergy();
            if (cb.isChecked()) {
                // add allergy name to allergies if checkbox is checked
                modelsAllergy.setAllergyName(cb.getText().toString());

                allergies.add(modelsAllergy);
            }
        }
        return allergies;
    }

    private void setCheckedAllergies(ArrayList<ModelsAllergy> allergies) {
        if (allergies == null || allergies.size() == 0) {
            return;
        }
        //Reset checkbox all to false
        for (CheckBox cb : allergyList) {
            cb.setChecked(false);
        }
        //check selected allergy
        for (ModelsAllergy str : allergies) {
            for (CheckBox cb : allergyList) {

                if (str.getAllergyName().equals(cb.getText().toString())) {
                    cb.setChecked(true);
                    break;
                }
            }
        }
    }

    // OnClick Listeners ///////////////////////////////////////////////////////////////
    public void none(View view) {
    }

    public void OnClick_AddFamilyGroup(View view) {


        if (UtilityNetworkConnectivity.checkNetworkConnection(getApplicationContext())) {
            view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));
            findViewById(R.id.con_add_family).setVisibility(View.VISIBLE);
            findViewById(R.id.btnSaveFamily).setTag("New");
            ClearFamInfo();
        } else {
//            TO DO
            openNoInternetConnectionActivity();
        }
    }

    private void openNoInternetConnectionActivity() {

        Intent i = new Intent(this, NoInternetActivity.class);
        if (Build.VERSION.SDK_INT > 20) {
            ActivityOptions options =
                    ActivityOptions.makeSceneTransitionAnimation(this);
            startActivity(i, options.toBundle());
        } else {
            startActivity(i);
        }

        finish();


    }


    public void OnClick_DonePickDate(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));
        findViewById(R.id.con_datepicker).setVisibility(View.GONE);
        etFamBirthday.setText(String.format("%s/%s/%s", picker.getMonth() + 1, picker.getDayOfMonth(), picker.getYear()));

        etFamAge.setText(Utility.getAge(picker.getDayOfMonth() + "/" + (picker.getMonth() + 1) + "/" + picker.getYear()));
    }

    public void OnCLick_CheckAllergies(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));
        setCheckedAllergies(selectedFamMember.getAllergylist());
        findViewById(R.id.con_check_allergy).setVisibility(View.VISIBLE);
    }

    public void OnClick_DoneCheckAllergies(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));
        selectedAllergies = getCheckedAllergies();

        for (ModelsAllergy allergy : selectedAllergies) {
            Log.e("ditoNameAlergy", allergy.getAllergyName());
        }
        tvCheckAllergy.setText(selectedAllergies.size() == 0 ? "" : String.format("No. of Food Allergy: %d", selectedAllergies.size()));
        findViewById(R.id.con_check_allergy).setVisibility(View.GONE);
    }

    public void OnCLick_CancelAddFamily(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));
        findViewById(R.id.con_add_family).setVisibility(View.GONE);
    }

    public void OnCLick_SaveFamily(View view) {
        // input validation
        if (etFamName.getText().toString().equals("") ||
                etFamRelation.getText().toString().equals("") ||
                etFamBirthday.getText().toString().equals("") ||
                etFamHeight.getText().toString().equals("") ||
                etFamWeight.getText().toString().equals("")) {
            return;
        }
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));
        ModelsFamily newFamilyMember = new ModelsFamily();
        newFamilyMember.setAccountUID(logUser.getUserUID());//UID of logUser
        newFamilyMember.setName(etFamName.getText().toString());
        newFamilyMember.setFamily_GUID(Utility.getGUID());
        newFamilyMember.setImageResourcesId(R.mipmap.icon_user_info);
        newFamilyMember.setRelation(etFamRelation.getText().toString());
        newFamilyMember.setBirthday(etFamBirthday.getText().toString());
        newFamilyMember.setHeight(Double.parseDouble(etFamHeight.getText().toString()));
        newFamilyMember.setWeight(Double.parseDouble(etFamWeight.getText().toString()));
        newFamilyMember.setGender(rgGender.getCheckedRadioButtonId() == rbMale.getId());
        newFamilyMember.setPregnant(rgPregnant.getCheckedRadioButtonId() == rbPregnant.getId());
        newFamilyMember.setAllergylist(selectedAllergies);


//        String userUID = pref.getString(String.valueOf(R.string.pref_UID), "");
//        assert userUID != null;

        if (view.getTag().equals("New")) {
            db.insertFamilyData(newFamilyMember);
            List<ModelsAllergy> allergies = newFamilyMember.getAllergylist();

            for (ModelsAllergy allergy : allergies) {
                allergy.setFamilyGUID(newFamilyMember.getFamily_GUID());
                db.insertAllergy(allergy);
            }
        }// Add New Family Member
        else {

            db.deleteFamilyData(selectedFamMember);
            db.deleteAllergyData(selectedFamMember);


            db.insertFamilyData(newFamilyMember);
            List<ModelsAllergy> allergies = newFamilyMember.getAllergylist();

            for (ModelsAllergy allergy : allergies) {
                allergy.setFamilyGUID(newFamilyMember.getFamily_GUID());
                db.insertAllergy(allergy);
            }


//            _familyList.set(Integer.parseInt((String) view.getTag()), newFamilyMember);
        }// Edit Selected Member

        refreshList();
        findViewById(R.id.con_add_family).setVisibility(View.GONE);
    }


    @Override
    public void OnClickFamilyMemberListener(int position) {
//        selectedFamMember = _familyList.get(position);
        selectedFamMember = logUser.getFamily().get(position);
        tvFamName.setText(selectedFamMember.getName());
        tvFamRelation.setText(selectedFamMember.getRelation());
        tvFamBirthday.setText(selectedFamMember.getBirthday());
        tvFamHeight.setText(String.valueOf(selectedFamMember.getHeight()));
        tvFamWeight.setText(String.valueOf(selectedFamMember.getWeight()));
        tvFamGender.setText(selectedFamMember.isGender() ? "Male" : "Female");
        tvFamPregnant.setVisibility(selectedFamMember.isGender() ? View.GONE : View.VISIBLE);
        tvFamPregnant.setText(selectedFamMember.isPregnant() ? "Pregnant" : "Not Pregnant");

        String checkedAllergy = "";
        for (ModelsAllergy allergy : db.getAllergyData(selectedFamMember)) {
            checkedAllergy += allergy.getAllergyName() + "\n";
        }
        tvFamAllergies.setText(checkedAllergy);

        findViewById(R.id.con_view_family).setVisibility(View.VISIBLE);
    }

    @Override
    public void OnLongClickFamilyMemberListener(int position) {
        selectedFamMember = logUser.getFamily().get(position);
        TextView tvname = findViewById(R.id.tvEditDelete);
        tvname.setText(selectedFamMember.getName());
        findViewById(R.id.con_edit_delete).setVisibility(View.VISIBLE);
        findViewById(R.id.btnSaveFamily).setTag(String.valueOf(position));
    }

    public void OnCLick_Edit(View view) {
        findViewById(R.id.btnSaveFamily).setTag("Edit");
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));
        etFamName.setText(selectedFamMember.getName());
        etFamRelation.setText(selectedFamMember.getRelation());
        etFamBirthday.setText(selectedFamMember.getBirthday());
        String[] date = selectedFamMember.getBirthday().split("/");
        etFamAge.setText(Utility.getAge(Integer.parseInt(date[1]) + "/" + Integer.parseInt(date[0]) + "/" + Integer.parseInt(date[2])));
        etFamHeight.setText(String.valueOf(selectedFamMember.getHeight()));
        etFamWeight.setText(String.valueOf(selectedFamMember.getWeight()));
        rbMale.setChecked(selectedFamMember.isGender());
        rbFemale.setChecked(!selectedFamMember.isGender());
        rbPregnant.setChecked(selectedFamMember.isPregnant());
        rbNotPregnant.setChecked(!selectedFamMember.isPregnant());
        selectedAllergies = selectedFamMember.getAllergylist();
        String checkedAllergy = "";
        for (ModelsAllergy allergy : selectedAllergies) {
            checkedAllergy += allergy + "\n";
        }
        tvFamAllergies.setText(checkedAllergy);

        findViewById(R.id.con_add_family).setVisibility(View.VISIBLE);
        findViewById(R.id.con_edit_delete).setVisibility(View.GONE);
    }

    public void OnCLick_Delete(View view) {
        db.deleteFamilyData(selectedFamMember);
        refreshList();

        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));
        findViewById(R.id.con_edit_delete).setVisibility(View.GONE);
    }

    public void OnCLick_HideViewFamInfo(View view) {
        findViewById(R.id.con_view_family).setVisibility(View.GONE);
    }

    public void OnClick_Contact(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));
        startActivity(new Intent(this, ContactsActivity.class));
    }

    public void OnClick_Camera(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));
        startActivity(new Intent(this, CaptureActivity.class));
    }

    public void OnClick_Ingredients(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));
        //startActivity(new Intent(this,IngredientsActivity.class));
    }

    public void OnClick_Pharmacy(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));
        //startActivity(new Intent(this,PharmacyActivity.class));
    }

    public void OnClick_Settings(View view) {
        boolean status = Utility.RotateToggle(this, view);
        conSettings.setVisibility(status ? View.GONE : View.VISIBLE);
        conSettings.startAnimation(AnimationUtils.loadAnimation(this, status ? R.anim.anim_trans_norm_right : R.anim.anim_trans_right_norm));
    }

    public void OnClick_ViewAccount(View view) {
        boolean status = Utility.RotateToggle(this, view);
        conUserAccount.setVisibility(status ? View.GONE : View.VISIBLE);
        conUserAccount.startAnimation(AnimationUtils.loadAnimation(this, status ? R.anim.anim_trans_norm_right : R.anim.anim_trans_right_norm));
    }

    public void OnClick_KeepSignIn(View view) {
        prefEdit.putBoolean(String.valueOf(R.string.pref_KeepMeLogin), cbKeepLogin.isChecked()).apply();
    }

    public void OnClick_Notification(View view) {
        prefEdit.putBoolean(String.valueOf(R.string.pref_Notification), cbNotification.isChecked()).apply();
    }

    public void OnClick_UploadData(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));
    }

    public void OnClick_About(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));
        findViewById(R.id.con_about_dialog).startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_trans_right_norm));
        findViewById(R.id.con_sett_about).setVisibility(View.VISIBLE);
    }

    public void OnClick_Help(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));
        findViewById(R.id.con_help_dialog).startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_trans_right_norm));
        findViewById(R.id.con_sett_help).setVisibility(View.VISIBLE);
    }

    public void OnClick_EditAccount(View view) {
//        findViewById(R.id.btnUpdateAccount).setTag("Edit");
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));
        startActivity(new Intent(this, EditAccountActivity.class));
        finish();

    }

    public void OnClick_Logout(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));
        prefEdit.putBoolean(String.valueOf(R.string.pref_KeepMeLogin), false).apply();
        prefEdit.putString(String.valueOf(R.string.pref_Username), "").apply();
        prefEdit.putString(String.valueOf(R.string.pref_Password), "").apply();
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void OnCLick_Close(View view) {
        if (view.getId() == R.id.btnCloseAbout) {
            findViewById(R.id.con_about_dialog).startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_trans_norm_right));
            findViewById(R.id.con_sett_about).setVisibility(View.GONE);
            // Get Open Help if new Account
            if (!pref.getString(String.valueOf(R.string.pref_UID), "").equals("")) {
                return;
            }
            Utility.AppSleep(2000);
            findViewById(R.id.con_help_dialog).startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_bounce));
            findViewById(R.id.con_sett_help).setVisibility(View.VISIBLE);
            return;
        }
        if (view.getId() == R.id.btnCloseHelp) {
            findViewById(R.id.con_help_dialog).startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_trans_norm_right));
            findViewById(R.id.con_sett_help).setVisibility(View.GONE);
            // Get User UID if new User
            if (!pref.getString(String.valueOf(R.string.pref_UID), "").equals("")) {
                return;
            }
//            String username = pref.getString(String.valueOf(R.string.pref_Username), "");
//            String password = pref.getString(String.valueOf(R.string.pref_Password), "");
//            ModelsUser user = Utility.FindUser(username, password, _userList);
//            assert user != null;
            prefEdit.putString(String.valueOf(R.string.pref_UID).toLowerCase(), logUser.getUserUID()).apply();
            return;
        }
        if (view.getId() == R.id.btnCloseEditDelete) {
            findViewById(R.id.con_edit_delete).setVisibility(View.GONE);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();
        //Check/Uncheck specefic by general
        if (id == cba1.getId()) {
            cba1_1.setChecked(isChecked);
            cba1_2.setChecked(isChecked);
            cba1_3.setChecked(isChecked);
            cba1_4.setChecked(isChecked);
            cba1_5.setChecked(isChecked);
            cba1_6.setChecked(isChecked);
            cba1_7.setChecked(isChecked);
            return;
        } // 7
        if (id == cba2.getId()) {
            cba2_1.setChecked(isChecked);
            cba2_2.setChecked(isChecked);
            return;
        } // 2
        if (id == cba3.getId()) {
            cba3_1.setChecked(isChecked);
            cba3_2.setChecked(isChecked);
            cba3_3.setChecked(isChecked);
            cba3_4.setChecked(isChecked);
            cba3_5.setChecked(isChecked);
            return;
        } // 5
        if (id == cba4.getId()) {
            cba4_1.setChecked(isChecked);
            cba4_2.setChecked(isChecked);
            cba4_3.setChecked(isChecked);
            cba4_4.setChecked(isChecked);
            return;
        } // 4
        if (id == cba5.getId()) {
            cba5_1.setChecked(isChecked);
            cba5_2.setChecked(isChecked);
            cba5_3.setChecked(isChecked);
            cba5_4.setChecked(isChecked);
            return;
        } // 4
        if (id == cba6.getId()) {
            cba6_1.setChecked(isChecked);
            cba6_2.setChecked(isChecked);
            cba6_3.setChecked(isChecked);
            cba6_4.setChecked(isChecked);
            cba6_5.setChecked(isChecked);
            cba6_6.setChecked(isChecked);
            cba6_7.setChecked(isChecked);
            cba6_8.setChecked(isChecked);
            cba6_9.setChecked(isChecked);
            return;
        } // 9
        if (id == cba7.getId()) {
            cba7_1.setChecked(isChecked);
            cba7_2.setChecked(isChecked);
            cba7_3.setChecked(isChecked);
            cba7_4.setChecked(isChecked);
            cba7_5.setChecked(isChecked);
            cba7_6.setChecked(isChecked);
            cba7_7.setChecked(isChecked);
            return;
        } // 7
        if (id == cba8.getId()) {
            cba8_1.setChecked(isChecked);
            cba8_2.setChecked(isChecked);
            cba8_3.setChecked(isChecked);
            cba8_4.setChecked(isChecked);
            cba8_5.setChecked(isChecked);
            cba8_6.setChecked(isChecked);
            cba8_7.setChecked(isChecked);
            cba8_8.setChecked(isChecked);
            return;
        } // 8
    }

}
