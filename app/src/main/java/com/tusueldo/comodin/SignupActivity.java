package com.tusueldo.comodin;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;
import com.tusueldo.comodin.utils.ComodinUtils;
import com.tusueldo.comodin.utils.ComodinValidator;
import com.tusueldo.comodin.utils.ComodinValues;
import com.tusueldo.comodin.utils.TypeFieldDate;

public class SignupActivity extends AppCompatActivity {


    @BindView(R.id.til_nombre)
    TextInputLayout til_nombre;
    @BindView(R.id.til_apellido)
    TextInputLayout til_apellido;
    @BindView(R.id.til_correo)
    TextInputLayout til_correo;
    @BindView(R.id.til_telefono)
    TextInputLayout til_telefono;
    @BindView(R.id.til_fecha_nac_dia)
    TextInputLayout til_fecha_nac_dia;
    @BindView(R.id.campo_fecha_nac_mes)
    TextInputEditText campo_fecha_nac_mes;
    @BindView(R.id.til_fecha_nac_mes)
    TextInputLayout til_fecha_nac_mes;
    @BindView(R.id.campo_fecha_nac_anio)
    TextInputEditText campo_fecha_nac_anio;
    @BindView(R.id.til_fecha_nac_anio)
    TextInputLayout til_fecha_nac_anio;

    /*Cargando los imageView*/
    @BindView(R.id.img_nombres)
    ImageView img_nombres;
    @BindView(R.id.img_correo)
    ImageView img_correo;
    @BindView(R.id.img_telefono)
    ImageView img_telefono;
    @BindView(R.id.img_fecha_nac)
    ImageView img_fecha_nac;

    static boolean pulsado = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
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
        ComodinValidator.validateFieldDate(TypeFieldDate.DIA, til_fecha_nac_dia);
    }

    @OnTextChanged(R.id.campo_fecha_nac_mes)
    protected void onTextChangedFechaMes() {
        ComodinValidator.validateFieldDate(TypeFieldDate.MES, til_fecha_nac_mes);
    }

    @OnTextChanged(R.id.campo_fecha_nac_anio)
    protected void onTextChangedFechaAnio() {
        ComodinValidator.validateFieldDate(TypeFieldDate.ANIO, til_fecha_nac_anio);
    }

    @OnFocusChange(value = {R.id.campo_fecha_nac_dia, R.id.campo_fecha_nac_mes, R.id.campo_fecha_nac_anio,
            R.id.campo_nombre, R.id.campo_apellido, R.id.campo_correo, R.id.campo_telefono})
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
        }
    }

}
