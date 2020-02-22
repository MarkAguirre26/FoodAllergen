package com.thesis.pdm.hallergen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.thesis.pdm.hallergen.Variable.logUser;

public class CheckNutrientIntakeActivity extends AppCompatActivity {

    Button btnIntakeAndUndo;
    boolean isbtnIntakeAndUndoAlreadyClicked;
    TextView
            txtNoFamily,
            txtNoFamilyMessage,
            txtNameIntake,
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

    ScrollView mainPage, prohibitedPage;

    ListView listViewProhibited;

    Button btnProceed;

    private DatabaseAdapter db;

    ModelsFamily fam = new ModelsFamily();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_availability);

        db = new DatabaseAdapter(getApplicationContext());
        initComponents();
        initData();

    }

    private void initData() {
        isbtnIntakeAndUndoAlreadyClicked = false;

        if (getFamilies().size() <= 0) {
            //HIDE The PAGE since family list is empty
            prohibitedPage.setVisibility(View.GONE);
            mainPage.setVisibility(View.GONE);
            btnProceed.setVisibility(View.GONE);
            txtNoFamily.setVisibility(View.VISIBLE);
            txtNoFamilyMessage.setVisibility(View.VISIBLE);

            return;
        }//ELSE VISIBLE

        txtNoFamily.setVisibility(View.GONE);
        txtNoFamilyMessage.setVisibility(View.GONE);
        prohibitedPage.setVisibility(View.GONE); //set GONE for logic
        btnProceed.setVisibility(View.GONE); //set GONE for logic
        mainPage.setVisibility(View.VISIBLE);

        txtNameIntake.setText(getFamilies().get(0));

        //ELSE
        findViewById(R.id.scrollPage).startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_trans_bot_norm));

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        txtDateIntake.setText((mDay + 1) + "/" + mMonth + "/" + mYear);

        //get first record of family list
        setData(0);

    }

    private void setData(int indexData) {
        List<ModelsFamily> families = logUser.getFamily();


        fam = families.get(indexData);

        //Family member data
        Party party = new Party();
        party.setName(fam.getName());
        party.setAge(Integer.valueOf(Utility.getAge(fam.getBirthday())));
        if (fam.isGender()) {
            party.setGender(Constant.MALE);
        } else {
            party.setGender(Constant.FEMALE);
        }
        party.setWeight(fam.getWeight());
        if (party.getAge() >= 1 && party.getAge() <= 18) {
            party.setLifeStageAgeGroup(Constant.CHILDREN);
        } else if (party.getAge() >= 19) {
            party.setLifeStageAgeGroup(Constant.ADULT);
        }
        txtAgeIntake.setText("Age: " + party.getAge());
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
        txtProteinScannedIntake.setText(calories);
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
        txtProteinDailyIntake.setText(String.valueOf(amdr.getProteinMin()));
        txtTotalFatDailyIntake.setText(String.valueOf(amdr.getTotalFatMin()));
        txtCarbohydrateDailyIntake.setText(String.valueOf(amdr.getCarbohydtrateMin()));
        txtEssentialFattyDailyIntake.setText(String.valueOf(rmi.getaLinolenicAcid()));
        txtDietaryFiberDailyIntake.setText(String.valueOf(rmi.getDiataryFiberMin()));
        txtWaterDailyIntake.setText(String.valueOf(rmi.getWaterMin()));

        //Allowance Left  --TO DO
        getAllowanceIntake(fam.getFamilyUID());
    }

    private void getAllowanceIntake(String family_guid) {

        //RECOMMENDED
        double EnergyDailyIntake = txtEnergyDailyIntake.getText().toString().equals("") ? 0.00 : Double.valueOf(txtEnergyDailyIntake.getText().toString());
        double ProteinDailyIntake = txtProteinDailyIntake.getText().toString().equals("") ? 0.00 : Double.valueOf(txtProteinDailyIntake.getText().toString());
        double TotalFatDailyIntake = txtTotalFatDailyIntake.getText().toString().equals("") ? 0.00 : Double.valueOf(txtTotalFatDailyIntake.getText().toString());
        double CarbohydrateDailyIntake = txtCarbohydrateDailyIntake.getText().toString().equals("") ? 0.00 : Double.valueOf(txtCarbohydrateDailyIntake.getText().toString());
        double EssentialFattyDailyIntake = txtEssentialFattyDailyIntake.getText().toString().equals("") ? 0.00 : Double.valueOf(txtEssentialFattyDailyIntake.getText().toString());
        double DietaryFiberDailyIntake = txtDietaryFiberDailyIntake.getText().toString().equals("") ? 0.00 : Double.valueOf(txtDietaryFiberDailyIntake.getText().toString());
        double WaterDailyIntake = txtWaterDailyIntake.getText().toString().equals("") ? 0.00 : Double.valueOf(txtWaterDailyIntake.getText().toString());

        //scanned
        double EnergyScannedIntake = txtEnergyScannedIntake.getText().toString().equals("") ? 0.00 : Double.valueOf(txtEnergyScannedIntake.getText().toString());
        double ProteinScannedIntake = txtProteinScannedIntake.getText().toString().equals("") ? 0.00 : Double.valueOf(txtProteinScannedIntake.getText().toString());
        double TotalFatScannedIntake = txtTotalFatScannedIntake.getText().toString().equals("") ? 0.00 : Double.valueOf(txtTotalFatScannedIntake.getText().toString());
        double CarbohydrateScannedIntake = txtCarbohydrateScannedIntake.getText().toString().equals("") ? 0.00 : Double.valueOf(txtCarbohydrateScannedIntake.getText().toString());
        double EssentialFattyScannedIntake = txtEssentialFattyScannedIntake.getText().toString().equals("") ? 0.00 : Double.valueOf(txtEssentialFattyScannedIntake.getText().toString());
        double DietaryFiberScannedIntake = txtDietaryFiberScannedIntake.getText().toString().equals("") ? 0.00 : Double.valueOf(txtDietaryFiberScannedIntake.getText().toString());
        double WaterScannedIntake = txtWaterScannedIntake.getText().toString().equals("") ? 0.00 : Double.valueOf(txtWaterScannedIntake.getText().toString());


        DatabaseAdapter da = new DatabaseAdapter(this);
        ModelIntake modelIntake = da.getIntakeData(family_guid);
        txtEnergyAllowanceLeftIntake.setText(String.valueOf(EnergyDailyIntake - Double.valueOf(modelIntake.getEnergy()) - EnergyScannedIntake));
        txtProteinAllowanceLeftIntake.setText(String.valueOf(ProteinDailyIntake - Double.valueOf(modelIntake.getProtein()) - ProteinScannedIntake));
        txtTotalFatAllowanceLeftIntake.setText(String.valueOf(TotalFatDailyIntake - Double.valueOf(modelIntake.getTotal_fat()) - TotalFatScannedIntake));
        txtCarbohydrateAllowanceLeftIntake.setText(String.valueOf(CarbohydrateDailyIntake - Double.valueOf(modelIntake.getCarbohydtrate()) - CarbohydrateScannedIntake));
        txtEssentialFattyAllowanceLeftIntake.setText(String.valueOf(EssentialFattyDailyIntake - Double.valueOf(modelIntake.getEssentialFattyAcid()) - EssentialFattyScannedIntake));
        txtDietaryFiberAllowanceLeftIntake.setText(String.valueOf(DietaryFiberDailyIntake - Double.valueOf(modelIntake.getDietaryFiber()) - DietaryFiberScannedIntake));
        txtWaterAllowanceLeftIntake.setText(String.valueOf(WaterDailyIntake - Double.valueOf(modelIntake.getWater()) - WaterScannedIntake));

    }

    private void initComponents() {
        btnIntakeAndUndo = findViewById(R.id.btnIntakeAndUndo);
        listViewProhibited = findViewById(R.id.listViewProhibited);
        mainPage = findViewById(R.id.scrollPage);
        prohibitedPage = findViewById(R.id.scrollProhiitedPage);
        txtNoFamily = findViewById(R.id.txtNoFamily);
        txtNoFamilyMessage = findViewById(R.id.txtNoFamilyMessage);
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
        btnProceed = findViewById(R.id.btnProceed);

        toolbar_title_checkAvailability.setText("Check Availability");
    }

    public void undoClicked(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));

    }

    public void intakeClicked(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_pulse_out));


        if (btnIntakeAndUndo.getText().toString().toLowerCase().contains("intake")) {
            btnIntakeAndUndo.setText("Undo");
            btnIntakeAndUndo.setBackgroundResource(R.drawable.btn_rounded_red);
            ModelIntake modelIntake = new ModelIntake();
            modelIntake.setId("0");
            modelIntake.setAccountID(logUser.getUserUID());
            modelIntake.setFamId(fam.getFamilyUID());
            modelIntake.setEnergy(txtEnergyAllowanceLeftIntake.getText().toString());
            modelIntake.setProtein(txtProteinAllowanceLeftIntake.getText().toString());
            modelIntake.setTotal_fat(txtTotalFatAllowanceLeftIntake.getText().toString());
            modelIntake.setCarbohydtrate(txtCarbohydrateAllowanceLeftIntake.getText().toString());
            modelIntake.setEssentialFattyAcid(txtEssentialFattyAllowanceLeftIntake.getText().toString());
            modelIntake.setDietaryFiber(txtDietaryFiberAllowanceLeftIntake.getText().toString());
            modelIntake.setWater(txtWaterAllowanceLeftIntake.getText().toString());
            DatabaseAdapter databaseAdapter = new DatabaseAdapter(this);
            databaseAdapter.insertIntakeData(modelIntake);


        } else {
            btnIntakeAndUndo.setText("Intake");
            btnIntakeAndUndo.setBackgroundResource(R.drawable.btn_rounded);
            //TO DO DELETE
            db.deleteLastIntake();
            getAllowanceIntake(fam.getFamilyUID());
        }


        //RE FRESH THE LIST
//        getAllowanceIntake(fam.getFamilyUID());
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

        List<String> famName = getFamilies();

        final CharSequence[] items = famName.toArray(new CharSequence[famName.size()]);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Make your selection");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // Do something with the selection
                txtNameIntake.setText(items[item]);
                setData(item);

            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }


    private List<String> getFamilies() {

        logUser.setFamily(db.getFamilyData(logUser));
        List<ModelsFamily> families = logUser.getFamily();
        List<String> names = new ArrayList<>();
        for (ModelsFamily s : families) {
//            txtNameIntake.setText(s.getName());
//            txtAgeIntake.setText(s.getBirthday());

            names.add(s.getName());

        }
        return names;
    }

    public void btnProceedProhibited(View view) {
        prohibitedPage.setVisibility(View.GONE);
        btnProceed.setVisibility(View.GONE);
        mainPage.setVisibility(View.VISIBLE);

    }
}
