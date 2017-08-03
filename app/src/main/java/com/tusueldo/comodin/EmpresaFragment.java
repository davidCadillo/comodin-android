package com.tusueldo.comodin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author David Cadillo Blas
 */


public class EmpresaFragment extends Fragment {

    @BindView(R.id.til_ruc) TextInputLayout til_ruc;
    @BindView(R.id.campo_ruc) TextInputEditText campo_ruc;
    @BindView(R.id.til_razon_social) TextInputLayout til_razon_social;
    @BindView(R.id.campo_razon_social) TextInputEditText campo_razon_social;

    public EmpresaFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_empresa, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        //campo_razon_social.setHint(getString(R.string.razon_social));
    }
}
