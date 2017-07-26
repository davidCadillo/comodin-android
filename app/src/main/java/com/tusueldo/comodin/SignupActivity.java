package com.tusueldo.comodin;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import butterknife.*;
import com.tusueldo.comodin.utils.*;

public class SignupActivity extends AppCompatActivity {


    @BindView(R.id.til_nombre) TextInputLayout til_nombre;
    @BindView(R.id.til_apellido) TextInputLayout til_apellido;
    @BindView(R.id.til_correo) TextInputLayout til_correo;
    @BindView(R.id.til_telefono) TextInputLayout til_telefono;
    @BindView(R.id.til_fecha_nac_dia) TextInputLayout til_fecha_nac_dia;
    @BindView(R.id.campo_fecha_nac_mes) TextInputEditText campo_fecha_nac_mes;
    @BindView(R.id.til_fecha_nac_mes) TextInputLayout til_fecha_nac_mes;
    @BindView(R.id.campo_fecha_nac_anio) TextInputEditText campo_fecha_nac_anio;
    @BindView(R.id.til_fecha_nac_anio) TextInputLayout til_fecha_nac_anio;
    @BindView(R.id.til_password) TextInputLayout til_password;
    @BindView(R.id.campo_password) TextInputEditText campo_password;


    /*Cargando los imageView*/
    @BindView(R.id.img_nombres) ImageView img_nombres;
    @BindView(R.id.img_correo) ImageView img_correo;
    @BindView(R.id.img_telefono) ImageView img_telefono;
    @BindView(R.id.img_fecha_nac) ImageView img_fecha_nac;
    @BindView(R.id.img_genero) ImageView img_genero;
    @BindView(R.id.img_password) ImageView img_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        ComodinValidator.ti_dia = til_fecha_nac_dia;
        ComodinValidator.ti_mes = til_fecha_nac_mes;
        ComodinValidator.ti_anio = til_fecha_nac_anio;
        ComodinValidator.ti_telefono = til_telefono;
    }


    @OnClick({R.id.rb_masculino, R.id.rb_femenino})
    public void click(RadioButton boton) {
        boolean checked = boton.isChecked();
        if (checked) {
            ComodinValidator.addDrawableValidado(this, img_genero);
        }
    }

    @OnTextChanged(R.id.campo_nombre)
    protected void onTextChangedNombre(CharSequence nombre) {
        ComodinValidator.validateNombre(this, nombre, til_nombre, img_nombres);
    }

    @OnTextChanged(R.id.campo_apellido)
    protected void onTextChangedApellido(CharSequence apellido) {
        ComodinValidator.validateApellido(this, apellido, til_apellido, img_nombres);
    }

    @OnTextChanged(R.id.campo_correo)
    protected void onTextChangedEmail(CharSequence email) {
        ComodinValidator.validateCorreo(this, email, til_correo, img_correo);
    }

    @OnTextChanged(R.id.campo_telefono)
    protected void onTextChangedTelefono(CharSequence telefono) {
        ComodinValidator.validateTelefono(this, telefono, til_telefono, img_telefono);
    }

    @OnTextChanged(R.id.campo_fecha_nac_dia)
    protected void onTextChangedFechaDia() {
        ComodinValidator.validateFieldDate(this, TypeFieldDate.DIA, til_fecha_nac_dia, img_fecha_nac);
    }

    @OnTextChanged(R.id.campo_fecha_nac_mes)
    protected void onTextChangedFechaMes() {
        ComodinValidator.validateFieldDate(this, TypeFieldDate.MES, til_fecha_nac_mes, img_fecha_nac);
    }

    @OnTextChanged(R.id.campo_fecha_nac_anio)
    protected void onTextChangedFechaAnio() {
        ComodinValidator.validateFieldDate(this, TypeFieldDate.ANIO, til_fecha_nac_anio, img_fecha_nac);
    }

    @OnTextChanged(R.id.campo_password)
    protected void onTextChangedPassword(CharSequence password) {
        ComodinValidator.validatePassword(this, password, til_password, img_password);
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
