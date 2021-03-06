package com.thesis.pdm.hallergen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

public class NoOfflineDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAnimation();
        setContentView(R.layout.activity_no_offline_data);
    }

    public void retry_CLicked(View view) {

        if (UtilityNetworkConnectivity.checkNetworkConnection(getApplicationContext())) {
            Intent i = new Intent(this, LoginActivity.class);
            if (Build.VERSION.SDK_INT > 20) {
                ActivityOptions options =
                        ActivityOptions.makeSceneTransitionAnimation(this);
                startActivity(i, options.toBundle());
                finish();
            } else {
                startActivity(i);
                finish();
            }


        }


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

}
