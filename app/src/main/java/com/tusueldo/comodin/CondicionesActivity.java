package com.tusueldo.comodin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.tusueldo.comodin.model.UserIndependiente;

public class CondicionesActivity extends AppCompatActivity {

    @BindView(R.id.tv_ver_mas) TextView tvVerMas;
    @BindView(R.id.btn_volver) Button btn_continuar_teminos;
    @BindView(R.id.tv_title) TextView tvTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condiciones);
        ButterKnife.bind(this);
      /*  UserIndependiente userIndependiente = getIntent().getParcelableExtra("user");
        if (userIndependiente != null)
            Toast.makeText(this, userIndependiente.toString(), Toast.LENGTH_LONG).show();*/
    }


    @OnClick(R.id.tv_ver_mas)
    public void clickVerMas() {
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/groups/949013481907214/"));
        startActivity(browser);
    }

    @OnClick(R.id.btn_volver)
    public void clickContinuar() {
        super.onBackPressed();
    }

/*
    @Override
    public void onBackPressed() {
    }

*/


}
