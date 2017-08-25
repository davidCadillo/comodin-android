package com.tusueldo.comodin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.stephentuso.welcome.WelcomeHelper;


public class PresentacionActivity extends AppCompatActivity {
    WelcomeHelper welcomeScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentacion);
        welcomeScreen = new WelcomeHelper(this, SlideActivity.class);
//        welcomeScreen.show(savedInstanceState);
        welcomeScreen.forceShow();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        welcomeScreen.onSaveInstanceState(outState);
    }
}
