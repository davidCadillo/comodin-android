package com.tusueldo.comodin;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import com.tusueldo.comodin.utils.ComodinValidator;
import com.tusueldo.comodin.utils.TypeUser;


/**
 * @author David Cadillo Blas
 */


public class EmpresaFragment extends Fragment {

    @BindView(R.id.til_ruc) TextInputLayout til_ruc;
    @BindView(R.id.campo_ruc) TextInputEditText campo_ruc;
    @BindView(R.id.til_razon_social) TextInputLayout til_razon_social;
    @BindView(R.id.campo_razon_social) TextInputEditText campo_razon_social;
    @BindView(R.id.img_ruc) ImageView img_ruc;


    public EmpresaFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_empresa, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @OnTextChanged(R.id.campo_ruc)
    protected void onTextChangedRuc(CharSequence ruc) {
        ComodinValidator.validateRuc(TypeUser.EMPRESA, getActivity(), ruc, til_ruc, til_razon_social, img_ruc);
    }


}
