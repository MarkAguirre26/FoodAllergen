package com.thesis.pdm.hallergen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class TableActivity extends AppCompatActivity {

    TextView txtNameIntake, txtAgeIntake, txtDateIntake, txtEnergyDailyIntake, txtEnergyScannedIntake, txtEnergyAllowanceLeftIntake, txtProteinDailyIntake, txtProteinScannedIntake, txtProteinAllowanceLeftIntake, txtTotalFatDailyIntake, txtTotalFatScannedIntake, txtTotalFatAllowanceLeftIntake, txtCarbohydrateDailyIntake, txtCarbohydrateScannedIntake, txtCarbohydrateAllowanceLeftIntake, txtEssentialFattyDailyIntake, txtEssentialFattyScannedIntake, txtEssentialFattyAllowanceLeftIntake, txtDietaryFiberDailyIntake, txtDietaryFiberScannedIntake, txtDietaryFiberAllowanceLeftIntake, txtWaterDailyIntake, txtWaterScannedIntake, txtWaterAllowanceLeftIntake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        initComponents();
        initData();

    }

    private void initData() {


        Party party = new Party();
        party.setName("Markoi");
        party.setAge(30);
        party.setGender(Constant.MALE);
        party.setWeight(80);

        RecommendedEnergyIntakesPerDay reipd = new RecommendedEnergyIntakesPerDay(party);
        txtEnergyDailyIntake.setText(String.valueOf(reipd.getEnergy()));
        txtEnergyScannedIntake.setText(String.valueOf(600));
        txtEnergyAllowanceLeftIntake.setText(String.valueOf(reipd.getEnergy() - 600));
    }

    private void initComponents() {
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
    }

    public void undoClicked(View view) {
    }

    public void intakeClicked(View view) {
    }

    public void backClicked(View view) {
        finish();
    }

    public void homeClicked(View view) {

        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));
        startActivity(new Intent(this, MainActivity.class));
        finishAffinity();
    }
}
