package com.tusueldo.comodin;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class BienvenidoActivity extends AppCompatActivity {

    private KonfettiView konfettiView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);
        ButterKnife.bind(this);
        konfettiView = (KonfettiView) findViewById(R.id.viewKonfetti);
        konfettiView.build()
                .addColors(ContextCompat.getColor(this, R.color.colorPrimaryDark), Color.RED, Color.YELLOW, Color.GREEN, ContextCompat.getColor(this, R.color.colorPrimaryLight))
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 4f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(new Size(12, 5f), new Size(16, 6f), new Size(20, 8f))
                .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                .stream(500, 10000L);
    }

    @OnClick(R.id.btn_continuar_bienvenida)
    public void click() {
        Intent i = new Intent(this, PresentacionActivity.class);
        startActivity(i);
        overridePendingTransition(R.animator.enter, R.animator.exit);
    }


}
