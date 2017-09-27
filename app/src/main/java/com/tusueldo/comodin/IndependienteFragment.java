package com.tusueldo.comodin;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import butterknife.*;
import com.tusueldo.comodin.model.User;
import com.tusueldo.comodin.model.types.ComodinValues;
import com.tusueldo.comodin.model.types.TypeFieldDate;
import com.tusueldo.comodin.model.types.TypeUserLogin;
import com.tusueldo.comodin.utils.ComodinUtils;
import com.tusueldo.comodin.utils.ComodinValidator;


public class IndependienteFragment extends UserFragment {

    private int contador = 0;
    /*Se cargan los TextInputEditText*/
    @BindView(R.id.campo_fecha_nac_mes) TextInputEditText campo_fecha_nac_mes;
    @BindView(R.id.campo_fecha_nac_anio) TextInputEditText campo_fecha_nac_anio;
    @BindView(R.id.campo_fecha_nac_dia) TextInputEditText campo_fecha_nac_dia;
    @BindView(R.id.campo_nombre) TextInputEditText campo_nombre;
    @BindView(R.id.campo_apellido) TextInputEditText campo_apellido;
    @BindView(R.id.campo_nombreComercial) TextInputEditText campo_nombreComercial;

    /*Se cargan los TextInputLayout*/
    @BindView(R.id.til_nombre) TextInputLayout til_nombre;
    @BindView(R.id.til_apellido) TextInputLayout til_apellido;
    @BindView(R.id.til_fecha_nac_dia) TextInputLayout til_fecha_nac_dia;
    @BindView(R.id.til_fecha_nac_mes) TextInputLayout til_fecha_nac_mes;
    @BindView(R.id.til_fecha_nac_anio) TextInputLayout til_fecha_nac_anio;
    @BindView(R.id.til_genero) TextInputLayout til_genero;
    @BindView(R.id.til_nombreComercial) TextInputLayout til_nombreComercial;

    /*Se cargan otros widgets*/
    @BindView(R.id.sw_ruc) SwitchCompat sw_ruc;
    @BindView(R.id.campo_genero) AppCompatSpinner campo_genero;
    @BindColor(R.color.colorAccent) ColorStateList colorAccent;
    @BindColor(R.color.colorValidado) ColorStateList colorValidado;


    /*Cargando los imageView*/
    @BindView(R.id.img_nombres) ImageView img_nombres;
    @BindView(R.id.img_fecha_nac) ImageView img_fecha_nac;
    @BindView(R.id.img_genero) ImageView img_genero;
    @BindView(R.id.img_nombre_comercial) ImageView img_nombreComercial;
    @BindView(R.id.layout_ruc) LinearLayout layout_ruc;
    @BindView(R.id.layout_nombreComercial) LinearLayout layout_nombrecomercial;
    @BindView(R.id.btn_registro) Button button_registro;

    private static boolean gender;

    public IndependienteFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ComodinValidator.ti_dia = til_fecha_nac_dia;
        ComodinValidator.ti_mes = til_fecha_nac_mes;
        ComodinValidator.ti_anio = til_fecha_nac_anio;
        ComodinValidator.ti_telefono = til_celular;
        layout_ruc.setVisibility(View.GONE);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.genero, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        campo_genero.setAdapter(adapter);
        campo_genero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (campo_genero.getSelectedItemPosition() > 0) {
                    gender = campo_genero.getSelectedItemPosition() == 1;
                    ComodinValidator.genero_validado = true;
                    ComodinUtils.setColorIconValidate(img_genero);
                    ComodinValidator.checkValidation(button_registro, getTypeUser());
                } else {
                    ComodinUtils.setColorIconPrimary(img_genero);
                    ComodinValidator.genero_validado = false;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_independiente;
    }

    @Override
    public TypeUserLogin getTypeUser() {
        return TypeUserLogin.INDEPENDIENTE;
    }

    @OnClick(R.id.btn_registro)
    public void click() {
        User user = new User();
        if (ComodinValidator.validacionCompleta) {
            if (ComodinValidator.rucvalidado) {
                user.setRuc(campo_ruc.getText().toString());
                user.setDireccion(ComodinValidator.direccion);
                user.setValidateRuc(ComodinValidator.ruc_validate_server);
                user.setNombreComercial(campo_nombreComercial.getText().toString());
            }
            user.setTipoUsuarioId(1);
            user.setNombresyapellidos(campo_apellido.getText().toString().concat("#").concat(campo_nombre.getText().toString()));
            user.setUbigeoId(ComodinValidator.ubigeo);
            user.setFechaNac(ComodinValidator.campoFechaCompleta);
            user.setEmail(campo_correo.getText().toString());
            user.setPassword(campo_password.getText().toString());
            user.setCelular(campo_celular.getText().toString());
            user.setGender(gender);
            user.setNews(isNews());
            registerUser(user);

        }
    }


    @OnTextChanged(R.id.campo_nombre)
    protected void onTextChangedNombre(CharSequence nombre) {
        ComodinValidator.validateNombre(TypeUserLogin.INDEPENDIENTE, nombre, til_nombre, img_nombres, button_registro);
    }

    @OnTextChanged(R.id.campo_apellido)
    protected void onTextChangedApellido(CharSequence apellido) {
        ComodinValidator.validateApellido(TypeUserLogin.INDEPENDIENTE, apellido, til_apellido, img_nombres, button_registro);
    }

    @OnTextChanged(R.id.campo_nombreComercial)
    protected void onTextChangedNombreComercial(CharSequence nombreComercial) {
        ComodinValidator.validateNombreComercial(TypeUserLogin.INDEPENDIENTE, nombreComercial, til_nombreComercial, img_nombreComercial);
    }

    @OnTextChanged(R.id.campo_fecha_nac_dia)
    protected void onTextChangedFechaDia() {
        ComodinValidator.validateFechaNacimiento(TypeUserLogin.INDEPENDIENTE, TypeFieldDate.DIA, til_fecha_nac_dia, img_fecha_nac, button_registro);
    }

    @OnTextChanged(R.id.campo_fecha_nac_mes)
    protected void onTextChangedFechaMes() {
        ComodinValidator.validateFechaNacimiento(TypeUserLogin.INDEPENDIENTE, TypeFieldDate.MES, til_fecha_nac_mes, img_fecha_nac, button_registro);
    }

    @OnTextChanged(R.id.campo_fecha_nac_anio)
    protected void onTextChangedFechaAnio() {
        ComodinValidator.validateFechaNacimiento(TypeUserLogin.INDEPENDIENTE, TypeFieldDate.ANIO, til_fecha_nac_anio, img_fecha_nac, button_registro);
    }

    @OnTextChanged(R.id.campo_ruc)
    protected void onTextChangedRuc(CharSequence ruc) {
        ComodinValidator.validateRuc(TypeUserLogin.INDEPENDIENTE, ruc, til_ruc, til_nombre, img_ruc, img_nombres, null, null, til_distrito, img_distrito, layout_nombrecomercial, button_registro);
    }

    @SuppressLint("SetTextI18n")
    @OnCheckedChanged(R.id.sw_ruc)
    protected void onCheckChangedRuc(boolean onSwitch) {
        if (onSwitch) {
            layout_ruc.setVisibility(View.VISIBLE);
            //sw_ruc.setTextColor(ContextCompat.getColor(getActivity(),R.color.colorValidado));
            sw_ruc.setTextColor(colorAccent);
            campo_ruc.requestFocus();
        } else {
            layout_ruc.setVisibility(View.GONE);
            sw_ruc.setTextColor(ContextCompat.getColor(getActivity(), android.R.color.darker_gray));
            ComodinUtils.clearField(til_nombre, img_nombres);
            ComodinUtils.clearField(til_ruc, img_ruc);
            ComodinUtils.clearField(til_distrito, img_distrito);

        }
        contador++;
        if (contador == 2) {
            campo_nombre.setText("Adalberto Gutíerrex");
            campo_correo.setText("hilda90@gmail.com");
            campo_password.setText("lodquesea");
            campo_distrito.setText("chim");
            campo_fecha_nac_anio.setText("1991");
            campo_fecha_nac_mes.setText("11");
            campo_fecha_nac_dia.setText("04");
            campo_celular.setText("987474122");
        }
    }


    @OnFocusChange({R.id.campo_fecha_nac_dia, R.id.campo_fecha_nac_mes, R.id.campo_fecha_nac_anio, R.id.campo_nombre,
            R.id.campo_correo, R.id.campo_telefono, R.id.campo_password})
    protected void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.campo_nombre:
                ComodinUtils.setHintFocusField(hasFocus, til_nombre, img_nombres, ComodinValues.NOMBRE);
                break;
            case R.id.campo_correo:
                ComodinUtils.setHintFocusField(hasFocus, til_correo, img_correo, ComodinValues.CORREO);
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
                ComodinUtils.setHintFocusField(hasFocus, til_password, img_password, ComodinValues.PASSWORD);
                break;
        }
    }


}
