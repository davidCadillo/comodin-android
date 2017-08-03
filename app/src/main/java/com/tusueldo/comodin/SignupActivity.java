package com.tusueldo.comodin;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import butterknife.*;
import com.tusueldo.comodin.utils.*;

public class SignupActivity extends AppCompatActivity {

    /*Cargando los imageView*/
    private boolean independiente_pressed = false;
    private boolean empresa_pressed = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.loadFragments, new IndependienteFragment());
        fragmentTransaction.commit();
        independiente_pressed = true;

    }


    @OnClick({R.id.btn_empresa, R.id.btn_independiente})
    public void click(View view) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        switch (view.getId()) {
            case R.id.btn_empresa:
                if (!empresa_pressed) {
                    fragmentTransaction.replace(R.id.loadFragments, new EmpresaFragment());
                    empresa_pressed = true;
                    independiente_pressed = false;
                }
                break;

            case R.id.btn_independiente:
            default:
                if (!independiente_pressed) {
                    fragmentTransaction.replace(R.id.loadFragments, new IndependienteFragment());
                    empresa_pressed = false;
                    independiente_pressed = true;
                    break;
                }
        }
        fragmentTransaction.commit();
    }


}
