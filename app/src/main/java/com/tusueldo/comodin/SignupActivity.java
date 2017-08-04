package com.tusueldo.comodin;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupActivity extends AppCompatActivity {

    private boolean independiente_pressed = false;
    private boolean empresa_pressed = false;
    @BindView(R.id.main_content) RelativeLayout main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.loadFragments, new IndependienteFragment());
        fragmentTransaction.commit();
        independiente_pressed = true;
        //Snackbar.make(main,"Ha ocurrido un error",Snackbar.LENGTH_INDEFINITE).show();

    }


    @OnClick({R.id.btn_empresa, R.id.btn_independiente})
    public void click(View view) {

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

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
