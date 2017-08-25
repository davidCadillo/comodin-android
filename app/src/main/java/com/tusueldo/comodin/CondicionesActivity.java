package com.tusueldo.comodin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class CondicionesActivity extends AppCompatActivity {

    @BindView(R.id.tv_ver_mas) TextView tvVerMas;
    @BindView(R.id.btn_continuar_terminos) Button btn_continuar_teminos;
    @BindView(R.id.tv_title) TextView tvTitle;
    @BindView(R.id.chk_acepta_terminos) CheckBox chkAceptaTerminos;
    @BindView(R.id.chk_enviar_correo) CheckBox chkEnviarCorreo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condiciones);
        ButterKnife.bind(this);
    }


    @OnCheckedChanged({R.id.chk_acepta_terminos, R.id.chk_enviar_correo})
    public void cambiado() {
        if (chkAceptaTerminos.isChecked() && chkEnviarCorreo.isChecked()) {
            btn_continuar_teminos.setEnabled(true);
            btn_continuar_teminos.setBackground(ContextCompat.getDrawable(btn_continuar_teminos.getContext(), R.drawable.selector_button));
        } else {
            btn_continuar_teminos.setEnabled(false);
            btn_continuar_teminos.setBackgroundColor(ContextCompat.getColor(btn_continuar_teminos.getContext(), android.R.color.darker_gray));
        }

    }

    @OnClick(R.id.tv_ver_mas)
    public void clickVerMas() {
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/groups/949013481907214/"));
        startActivity(browser);
    }

    @OnClick(R.id.btn_continuar_terminos)
    public void clickContinuar() {
        Intent i = new Intent(this, BienvenidoActivity.class);
        startActivity(i);
        overridePendingTransition(R.animator.enter, R.animator.exit);
    }

    @Override
    public void onBackPressed() {
    }

}
