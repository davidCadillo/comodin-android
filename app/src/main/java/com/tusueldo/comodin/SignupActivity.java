package com.tusueldo.comodin;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;

import com.tusueldo.comodin.utils.ComodinValidator;

public class SignupActivity extends AppCompatActivity {


    @BindView(R.id.til_nombre)
    TextInputLayout til_nombre;
    @BindView(R.id.til_apellido)
    TextInputLayout til_apellido;
    @BindView(R.id.til_correo)
    TextInputLayout til_correo;
    @BindView(R.id.til_telefono)
    TextInputLayout til_telefono;
    @BindView(R.id.til_fecha_nac)
    TextInputLayout til_fecha_nac;
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
        til_fecha_nac.setError("dd/mm/yyyy");
        til_fecha_nac.getEditText().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //You can identify which key pressed buy checking keyCode value with KeyEvent.KEYCODE_
                if (keyCode == KeyEvent.KEYCODE_DEL || keyCode == KeyEvent.KEYCODE_BACK)
                    pulsado = true;
                return false;
            }
        });
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

    @OnTextChanged(R.id.campo_fecha_nac)
    protected void onTextChangedFechaNac(CharSequence fecha_nac) {
        ComodinValidator.validateFechaNac(this, fecha_nac, til_fecha_nac, img_fecha_nac);
    }

    @OnTextChanged(value = {R.id.campo_fecha_nac}, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void inputFecha(Editable editable) {
        String fecha = editable.toString();
        if ((fecha.length() == 2 || fecha.length() == 5) && pulsado == false) {
            pulsado = false;
            editable.append("/");
        }

    }


    @OnFocusChange(R.id.campo_nombre)
    protected void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.campo_nombre:
                if (!v.hasFocus() && ComodinValidator.nombreValidado == false) {
                    til_nombre.setErrorEnabled(false);
                }
        }
    }

}
