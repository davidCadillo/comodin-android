package com.tusueldo.comodin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SignupActivity extends AppCompatActivity {

    @BindView(R.id.loadFragments) FrameLayout frameLayout;

    private Fragment fragmentIndependiente;
    private Fragment fragmentEmpresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            fragmentIndependiente = new IndependienteFragment();
            fragmentEmpresa = new EmpresaFragment();
            fragmentTransaction.add(R.id.loadFragments, fragmentIndependiente);
            fragmentTransaction.add(R.id.loadFragments, fragmentEmpresa);
            fragmentTransaction.hide(fragmentEmpresa);
            fragmentTransaction.commit();
    }


    @OnClick({R.id.btn_empresa, R.id.btn_independiente})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_empresa:
                getSupportFragmentManager().beginTransaction().hide(fragmentIndependiente).commit();
                getSupportFragmentManager().beginTransaction().show(fragmentEmpresa).commit();
//                getSupportFragmentManager().beginTransaction().replace(R.id.loadFragments, fragmentEmpresa).commit();
                break;

            case R.id.btn_independiente:
            default:
                getSupportFragmentManager().beginTransaction().hide(fragmentEmpresa);
                getSupportFragmentManager().beginTransaction().show(fragmentIndependiente);
//                getSupportFragmentManager().beginTransaction().replace(R.id.loadFragments, fragmentIndependiente).commit();
                break;
        }
    }


}


