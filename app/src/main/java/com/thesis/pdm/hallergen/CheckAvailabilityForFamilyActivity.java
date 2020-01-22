package com.thesis.pdm.hallergen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class CheckAvailabilityForFamilyActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_availability_for_family);

        init();
    }

    private void init() {


        final ArrayList<ItemData> list = new ArrayList<>();
        list.add(new ItemData("Khr", R.drawable.ic_group_black_24dp));
        list.add(new ItemData("Usd", R.drawable.ic_group_black_24dp));
        list.add(new ItemData("Jpy", R.drawable.ic_group_black_24dp));
        list.add(new ItemData("Aud", R.drawable.ic_group_black_24dp));
        Spinner sp = (Spinner) findViewById(R.id.spinner);


        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "You Select Position: " + position + " " + list.get(position).getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        SpinnerAdapter adapter = new SpinnerAdapter(this,
                R.layout.spinner_layout, R.id.txt, list);
        sp.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {

//        startActivity(new Intent(this, CaptureActivity.class));
        finish();


    }
}
