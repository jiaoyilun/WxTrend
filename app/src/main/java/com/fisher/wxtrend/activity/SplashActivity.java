package com.fisher.wxtrend.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.fisher.wxtrend.R;

public class SplashActivity extends Activity {
    private AlphaAnimation start_anima;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = View.inflate(this, R.layout.activity_splash, null);
        setContentView(view);
        initView();
        initData();
    }

    private void initData() {
        start_anima = new AlphaAnimation(0.3f, 1.0f);
        start_anima.setDuration(2000);
        view.startAnimation(start_anima);
        start_anima.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                redirectTo();
            }
        });
    }

    private void initView() {

    }

    private void redirectTo() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}
