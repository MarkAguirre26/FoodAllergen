package com.thesis.pdm.hallergen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.thesis.pdm.hallergen.Variable.logUser;

public class CheckAvailabilityActivity extends AppCompatActivity {

    TextView txtNameIntake,
            txtAgeIntake,
            txtDateIntake,
            txtEnergyDailyIntake,
            txtEnergyScannedIntake,
            txtEnergyAllowanceLeftIntake,
            txtProteinDailyIntake,
            txtProteinScannedIntake,
            txtProteinAllowanceLeftIntake,
            txtTotalFatDailyIntake,
            txtTotalFatScannedIntake,
            txtTotalFatAllowanceLeftIntake,
            txtCarbohydrateDailyIntake,
            txtCarbohydrateScannedIntake,
            txtCarbohydrateAllowanceLeftIntake,
            txtEssentialFattyDailyIntake,
            txtEssentialFattyScannedIntake,
            txtEssentialFattyAllowanceLeftIntake,
            txtDietaryFiberDailyIntake,
            txtDietaryFiberScannedIntake,
            txtDietaryFiberAllowanceLeftIntake,
            txtWaterDailyIntake,
            txtWaterScannedIntake,
            txtWaterAllowanceLeftIntake,
            toolbar_title_checkAvailability;

    ScrollView scrollPage;

    private DatabaseAdapter db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_availability);

        db = new DatabaseAdapter(getApplicationContext());
        initComponents();
        initData();

    }

    private void initData() {

        if (getGamilies().size() <= 0) {
            scrollPage.setVisibility(View.GONE);
            return;
        }//ELSE VISIBLE

        txtNameIntake.setText(getGamilies().get(0));

        //ELSE
        findViewById(R.id.scrollPage).startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_trans_bot_norm));

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        txtDateIntake.setText((mDay + 1) + "/" + mMonth + "/" + mYear);

        Party party = new Party();
        party.setName("Markoi");
        party.setAge(30);
        party.setGender(Constant.MALE);
        party.setWeight(80);

        //Extract values from captured
        StringManager stringManager = new StringManager(Variable.outputText);
        String calories = stringManager.getValueFromString("Calories");
        String protein = stringManager.getValueFromString("Protein");
        String totalFat = stringManager.getValueFromString("Total Fat");
        String carbohydrate = stringManager.getValueFromString("Carbohydrate");
        String essentialFattyAcid = stringManager.getValueFromString("Essential Fatty Acid");
        String dietaryFiber = stringManager.getValueFromString("Dietary Fiber");
        String water = stringManager.getValueFromString("Water");


        //scanned
        txtEnergyScannedIntake.setText(protein);
        txtEnergyScannedIntake.setText(calories);
        txtTotalFatScannedIntake.setText(totalFat);
        txtCarbohydrateScannedIntake.setText(carbohydrate);
        txtEssentialFattyScannedIntake.setText(essentialFattyAcid);
        txtDietaryFiberScannedIntake.setText(dietaryFiber);
        txtWaterScannedIntake.setText(water);

        //Recommended Intake
        RecommendedEnergyIntakesPerDay reipd = new RecommendedEnergyIntakesPerDay(party);
        RecommendedMacronutrientsIntakesPerDay rmi = new RecommendedMacronutrientsIntakesPerDay(party);
        AcceptableMacronutrientDistributionRanges amdr = new AcceptableMacronutrientDistributionRanges(party);
        txtEnergyDailyIntake.setText(String.valueOf(reipd.getEnergy()));
        txtProteinDailyIntake.setText(amdr.getProteinMin() + "-" + amdr.getProteinMax());
        txtTotalFatDailyIntake.setText(amdr.getTotalFatMin() + "-" + amdr.getTotalFatMax());
        txtCarbohydrateDailyIntake.setText(amdr.getCarbohydtrateMin() + "-" + amdr.getCarbohydtrateMax());
        txtEssentialFattyDailyIntake.setText(rmi.getaLinolenicAcid() + "/" + rmi.getLinolenicAcid());
        txtDietaryFiberDailyIntake.setText(rmi.getDiataryFiberMin() + "/" + rmi.getDiataryFiberMax());
        txtWaterDailyIntake.setText(rmi.getWaterMin());

        //Allowance Left
        txtEnergyAllowanceLeftIntake.setText(String.valueOf(reipd.getEnergy() - 600));

    }

    private void initComponents() {
        scrollPage = findViewById(R.id.scrollPage);
        txtNameIntake = findViewById(R.id.txtNameIntake);
        txtAgeIntake = findViewById(R.id.txtAgeIntake);
        txtDateIntake = findViewById(R.id.txtDateIntake);
        txtEnergyDailyIntake = findViewById(R.id.txtEnergyDailyIntake);
        txtEnergyScannedIntake = findViewById(R.id.txtEnergyScannedIntake);
        txtEnergyAllowanceLeftIntake = findViewById(R.id.txtEnergyAllowanceLeftIntake);
        txtProteinDailyIntake = findViewById(R.id.txtProteinDailyIntake);
        txtProteinScannedIntake = findViewById(R.id.txtProteinScannedIntake);
        txtProteinAllowanceLeftIntake = findViewById(R.id.txtProteinAllowanceLeftIntake);
        txtTotalFatDailyIntake = findViewById(R.id.txtTotalFatDailyIntake);
        txtTotalFatScannedIntake = findViewById(R.id.txtTotalFatScannedIntake);
        txtTotalFatAllowanceLeftIntake = findViewById(R.id.txtTotalFatAllowanceLeftIntake);
        txtCarbohydrateDailyIntake = findViewById(R.id.txtCarbohydrateDailyIntake);
        txtCarbohydrateScannedIntake = findViewById(R.id.txtCarbohydrateScannedIntake);
        txtCarbohydrateAllowanceLeftIntake = findViewById(R.id.txtCarbohydrateAllowanceLeftIntake);
        txtEssentialFattyDailyIntake = findViewById(R.id.txtEssentialFattyDailyIntake);
        txtEssentialFattyScannedIntake = findViewById(R.id.txtEssentialFattyScannedIntake);
        txtEssentialFattyAllowanceLeftIntake = findViewById(R.id.txtEssentialFattyAllowanceLeftIntake);
        txtDietaryFiberDailyIntake = findViewById(R.id.txtDietaryFiberDailyIntake);
        txtDietaryFiberScannedIntake = findViewById(R.id.txtDietaryFiberScannedIntake);
        txtDietaryFiberAllowanceLeftIntake = findViewById(R.id.txtDietaryFiberAllowanceLeftIntake);
        txtWaterDailyIntake = findViewById(R.id.txtWaterDailyIntake);
        txtWaterScannedIntake = findViewById(R.id.txtWaterScannedIntake);
        txtWaterAllowanceLeftIntake = findViewById(R.id.txtWaterAllowanceLeftIntake);
        toolbar_title_checkAvailability = findViewById(R.id.toolbar_title_checkAvailability);
        txtDateIntake = findViewById(R.id.txtDateIntake);

        toolbar_title_checkAvailability.setText("Check Availability");
    }

    public void undoClicked(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));
    }

    public void intakeClicked(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));
    }

    public void backClicked(View view) {
        finish();
    }

    public void homeClicked(View view) {

        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));
        startActivity(new Intent(this, MainActivity.class));
        finishAffinity();
    }

    public void selectDateClicked(View view) {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        txtDateIntake.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void familyMemberSelectCLicked(View view) {

        List<String> famName = getGamilies();

        final CharSequence[] items = famName.toArray(new CharSequence[famName.size()]);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Make your selection");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // Do something with the selection
                txtNameIntake.setText(items[item]);
            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }


    private List<String> getGamilies() {
        logUser.setFamily(db.getFamilyData(logUser));
        List<ModelsFamily> families = logUser.getFamily();


        List<String> names = new ArrayList<>();
        for (ModelsFamily s : families) {
            txtNameIntake.setText(s.getName());
            txtAgeIntake.setText(s.getBirthday());

            names.add(s.getName());

        }
        return names;
    }

}
