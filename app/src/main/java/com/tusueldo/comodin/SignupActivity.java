package com.tusueldo.comodin;

import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import com.tusueldo.comodin.utils.ComodinValidator;

import java.util.List;

public class SignupActivity extends AppCompatActivity {


    @BindView(R.id.til_nombre) TextInputLayout til_nombre;
    @BindView(R.id.til_apellido) TextInputLayout til_apellido;
    @BindView(R.id.til_telefono) TextInputLayout til_telefono;
    @BindView(R.id.til_fecha_nac) TextInputLayout til_fecha_nac;
    @BindView(R.id.campo_telefono) EditText campo_telefono;
    /*Cargando los imageView*/
    @BindView(R.id.img_nombres) ImageView img_nombres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
    }

    //@OnTextChanged(value = R.id.campo_nombre, callback = OnTextChanged.Callback.TEXT_CHANGED)
    @OnTextChanged(R.id.campo_nombre)
    protected void onTextChangedNombre(CharSequence nombre) {
        boolean validado = ComodinValidator.validateNombre(this, nombre, til_nombre, img_nombres);
    }

    @OnTextChanged(R.id.campo_apellido)
    protected void onTextChangedApellido(CharSequence apellido) {
        boolean validado = ComodinValidator.validateApellido(this, apellido, til_apellido, img_nombres);
    }

}
