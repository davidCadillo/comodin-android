package com.tusueldo.comodin;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.stephentuso.welcome.WelcomeHelper;
import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class BienvenidoActivity extends AppCompatActivity {

    private KonfettiView konfettiView;
    WelcomeHelper welcomeScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);
        ButterKnife.bind(this);
        welcomeScreen = new WelcomeHelper(this, SlideActivity.class);
        welcomeScreen.forceShow();
/*        if (getIntent().getBooleanExtra("nuevoUsuario", false)) {
            welcomeScreen.forceShow();
        } else {
            welcomeScreen.show(savedInstanceState);
        }*/
        konfettiView = (KonfettiView) findViewById(R.id.viewKonfetti);
        konfettiView.build()
                .addColors(ContextCompat.getColor(this, R.color.colorPrimaryDark), Color.RED, Color.YELLOW, Color.GREEN)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 7f)
                .setFadeOutEnabled(true)
                .setTimeToLive(3000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(new Size(12, 5f), new Size(16, 6f), new Size(20, 8f))
                .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                .stream(500, 10000L);
    }

    @OnClick(R.id.btn_continuar_bienvenida)
    public void click() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        overridePendingTransition(R.animator.enter, R.animator.exit);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        welcomeScreen.onSaveInstanceState(outState);
    }
}
