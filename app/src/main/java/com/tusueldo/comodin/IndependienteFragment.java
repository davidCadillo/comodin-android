package com.tusueldo.comodin;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import butterknife.*;
import com.tusueldo.comodin.model.types.ComodinValues;
import com.tusueldo.comodin.model.types.TypeFieldDate;
import com.tusueldo.comodin.model.types.TypeUserLogin;
import com.tusueldo.comodin.utils.ComodinUtils;
import com.tusueldo.comodin.utils.ComodinValidator;


public class IndependienteFragment extends Fragment {


    /*Se cargan los TextInputEditText*/
    @BindView(R.id.campo_razon_social) TextInputEditText campo_razon_social;
    @BindView(R.id.campo_ruc) TextInputEditText campo_ruc;
    @BindView(R.id.campo_fecha_nac_mes) TextInputEditText campo_fecha_nac_mes;
    @BindView(R.id.campo_password) TextInputEditText campo_password;
    @BindView(R.id.campo_fecha_nac_anio) TextInputEditText campo_fecha_nac_anio;

    /*Se cargan los TextInputLayout*/
    @BindView(R.id.til_ruc) TextInputLayout til_ruc;
    @BindView(R.id.til_razon_social) TextInputLayout til_razon_social;
    @BindView(R.id.til_nombre) TextInputLayout til_nombre;
    @BindView(R.id.til_apellido) TextInputLayout til_apellido;
    @BindView(R.id.til_correo) TextInputLayout til_correo;
    @BindView(R.id.til_telefono) TextInputLayout til_telefono;
    @BindView(R.id.til_fecha_nac_dia) TextInputLayout til_fecha_nac_dia;
    @BindView(R.id.til_fecha_nac_mes) TextInputLayout til_fecha_nac_mes;
    @BindView(R.id.til_fecha_nac_anio) TextInputLayout til_fecha_nac_anio;
    @BindView(R.id.til_password) TextInputLayout til_password;

    /*Se cargan otros widgets*/
    @BindView(R.id.sw_ruc) Switch sw_ruc;


    /*Cargando los imageView*/
    @BindView(R.id.img_nombres) ImageView img_nombres;
    @BindView(R.id.img_correo) ImageView img_correo;
    @BindView(R.id.img_telefono) ImageView img_telefono;
    @BindView(R.id.img_fecha_nac) ImageView img_fecha_nac;
    @BindView(R.id.img_genero) ImageView img_genero;
    @BindView(R.id.img_password) ImageView img_password;
    @BindView(R.id.img_ruc) ImageView img_ruc;

    public IndependienteFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_independiente, container, false);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ComodinValidator.ti_dia = til_fecha_nac_dia;
        ComodinValidator.ti_mes = til_fecha_nac_mes;
        ComodinValidator.ti_anio = til_fecha_nac_anio;
        ComodinValidator.ti_telefono = til_telefono;
        til_razon_social.setEnabled(false);
        til_razon_social.setVisibility(View.GONE);
        til_ruc.setVisibility(View.GONE);
    }

    @OnClick({R.id.rb_masculino, R.id.rb_femenino})
    public void click(RadioButton boton) {
        boolean checked = boton.isChecked();
        if (checked) {
            ComodinValidator.setIconValidate(getActivity().getBaseContext(), img_genero);
        }
    }


    @OnTextChanged(R.id.campo_nombre)
    protected void onTextChangedNombre(CharSequence nombre) {
        ComodinValidator.validateNombre(getActivity().getBaseContext(), nombre, til_nombre, img_nombres);
    }

    @OnTextChanged(R.id.campo_apellido)
    protected void onTextChangedApellido(CharSequence apellido) {
        ComodinValidator.validateApellido(getActivity().getBaseContext(), apellido, til_apellido, img_nombres);
    }

    @OnTextChanged(R.id.campo_correo)
    protected void onTextChangedEmail(CharSequence email) {
        ComodinValidator.validateCorreo(getActivity().getBaseContext(), email, til_correo, img_correo);
    }

    @OnTextChanged(R.id.campo_telefono)
    protected void onTextChangedTelefono(CharSequence telefono) {
        ComodinValidator.validateTelefono(getActivity().getBaseContext(), telefono, til_telefono, img_telefono);
    }

    @OnTextChanged(R.id.campo_fecha_nac_dia)
    protected void onTextChangedFechaDia() {
        ComodinValidator.validateFechaNacimiento(getActivity().getBaseContext(), TypeFieldDate.DIA, til_fecha_nac_dia, img_fecha_nac);
    }

    @OnTextChanged(R.id.campo_fecha_nac_mes)
    protected void onTextChangedFechaMes() {
        ComodinValidator.validateFechaNacimiento(getActivity().getBaseContext(), TypeFieldDate.MES, til_fecha_nac_mes, img_fecha_nac);
    }

    @OnTextChanged(R.id.campo_fecha_nac_anio)
    protected void onTextChangedFechaAnio() {
        ComodinValidator.validateFechaNacimiento(getActivity().getBaseContext(), TypeFieldDate.ANIO, til_fecha_nac_anio, img_fecha_nac);
    }

    @OnTextChanged(R.id.campo_password)
    protected void onTextChangedPassword(CharSequence password) {
        ComodinValidator.validatePassword(getActivity().getBaseContext(), password, til_password, img_password);
    }

    @OnTextChanged(R.id.campo_ruc)
    protected void onTextChangedRuc(CharSequence ruc) {
        ComodinValidator.validateRuc(TypeUserLogin.INDEPENDIENTE, ruc, til_ruc, til_razon_social, img_ruc);
    }

    @OnCheckedChanged(R.id.sw_ruc)
    protected void onCheckChangedRuc(boolean onSwitch) {
        if (onSwitch) {
            til_ruc.setVisibility(View.VISIBLE);
            campo_ruc.requestFocus();
            if (ComodinValidator.rucvalidado)
                til_razon_social.setVisibility(View.VISIBLE);
        } else {
            til_ruc.setVisibility(View.GONE);
            til_razon_social.setVisibility(View.GONE);
        }
    }

    @OnFocusChange({R.id.campo_fecha_nac_dia, R.id.campo_fecha_nac_mes, R.id.campo_fecha_nac_anio,
            R.id.campo_nombre, R.id.campo_apellido, R.id.campo_correo, R.id.campo_telefono, R.id.campo_password})
    protected void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.campo_nombre:
                ComodinUtils.setHintFocusField(hasFocus, til_nombre);
                break;
            case R.id.campo_apellido:
                ComodinUtils.setHintFocusField(hasFocus, til_apellido);
                break;
            case R.id.campo_correo:
                ComodinUtils.setHintFocusField(hasFocus, til_correo);
                break;
            case R.id.campo_telefono:
                ComodinUtils.setHintFocusField(hasFocus, til_telefono);
                break;
            case R.id.campo_fecha_nac_dia:
                ComodinUtils.setHintFocusField(hasFocus, til_fecha_nac_dia, ComodinValues.DAY, ComodinValues.DAY_SIMPLE);
                break;
            case R.id.campo_fecha_nac_mes:
                ComodinUtils.setHintFocusField(hasFocus, til_fecha_nac_mes, ComodinValues.MONTH, ComodinValues.MONTH_SIMPLE);
                break;

            case R.id.campo_fecha_nac_anio:
                ComodinUtils.setHintFocusField(hasFocus, til_fecha_nac_anio, ComodinValues.YEAR, ComodinValues.YEAR_SIMPLE);
                break;
            case R.id.campo_password:
                ComodinUtils.setHintFocusField(hasFocus, til_password);
                break;


        }
    }


}
