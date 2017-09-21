package com.tusueldo.comodin;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import com.tusueldo.comodin.model.UserEmpresa;
import com.tusueldo.comodin.model.types.TypeUserLogin;
import com.tusueldo.comodin.utils.ComodinValidator;


/**
 * @author David Cadillo Blas
 */


public class EmpresaFragment extends UserFragment {

    @BindView(R.id.til_razon_social) TextInputLayout til_razon_social;
    @BindView(R.id.til_direccion) TextInputLayout til_direccion;
    @BindView(R.id.til_nombreComercial) TextInputLayout til_nombreComercial;

    @BindView(R.id.campo_razon_social) TextInputEditText campo_razon_social;
    @BindView(R.id.campo_nombreComercial) TextInputEditText campo_nombreComercial;
    @BindView(R.id.campo_direccion) TextInputEditText campo_direccion;

    @BindView(R.id.layout_nombreComercial) LinearLayout layout_nombrecomercial;
    @BindView(R.id.img_razon_social) ImageView img_razon_social;
    @BindView(R.id.img_direccion) ImageView img_direccion;
    @BindView(R.id.img_nombre_comercial) ImageView img_nombreComercial;

    @BindView(R.id.btn_registro) Button button_registro;

    public EmpresaFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        til_razon_social.setEnabled(false);
        til_direccion.setEnabled(false);
        til_distrito.setEnabled(false);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_empresa;
    }

    @Override
    public TypeUserLogin getTypeUser() {
        return TypeUserLogin.EMPRESA;
    }

    @OnClick(R.id.btn_registro)
    public void click() {
        if (ComodinValidator.validacionCompleta) {
            UserEmpresa userEmpresa = new UserEmpresa();
            userEmpresa.setRuc(campo_ruc.getText().toString());
            userEmpresa.setRazon_social(campo_razon_social.getText().toString());
            userEmpresa.setDireccion(campo_direccion.getText().toString());
            userEmpresa.setValidate_ruc(ComodinValidator.ruc_validate_server);
            userEmpresa.setUbigeo_id(ComodinValidator.ubigeo);
            userEmpresa.setEmail(campo_correo.getText().toString());
            userEmpresa.setCelular(campo_celular.getText().toString());
            userEmpresa.setPassword(campo_password.getText().toString());
            userEmpresa.setNombre_comercial(campo_nombreComercial.getText().toString());
            userEmpresa.setTipo_usuario_id(2);
            userEmpresa.setNews(isNews());
            registerUser(userEmpresa);
        }
    }


    @OnTextChanged(R.id.campo_ruc)
    protected void onTextChangedRuc(CharSequence ruc) {
        ComodinValidator.validateRuc(TypeUserLogin.EMPRESA, ruc, til_ruc, til_razon_social, img_ruc, img_razon_social, til_direccion, img_direccion, til_distrito, img_distrito, layout_nombrecomercial, button_registro);
    }

    @OnTextChanged(R.id.campo_razon_social)
    protected void onTextChangedRazonSocial(CharSequence razon_social) {
        ComodinValidator.validateRazonSocial(TypeUserLogin.EMPRESA, razon_social, til_razon_social, img_razon_social, button_registro);
    }

    @OnTextChanged(R.id.campo_direccion)
    protected void onTextChangedDireccion(CharSequence direccion) {
        ComodinValidator.validateDireccion(TypeUserLogin.EMPRESA, direccion, til_direccion, img_direccion, button_registro);
    }


    @OnTextChanged(R.id.campo_nombreComercial)
    protected void onTextChangedNombreComercial(CharSequence nombreComercial) {
        ComodinValidator.validateNombreComercial(TypeUserLogin.EMPRESA, nombreComercial, til_nombreComercial, img_nombreComercial);
    }

}
