package com.tusueldo.comodin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.afollestad.materialdialogs.MaterialDialog;
import com.facebook.login.widget.LoginButton;
import com.tusueldo.comodin.connections.api.*;
import com.tusueldo.comodin.connections.api.login.ComodinLoginErrors;
import com.tusueldo.comodin.connections.api.login.ComodinLoginRequest;
import com.tusueldo.comodin.connections.api.login.ComodinLoginResponse;
import com.tusueldo.comodin.connections.api.login.ComodinTypeDateLogin;
import com.tusueldo.comodin.model.Ubigeo;
import com.tusueldo.comodin.ui.ComodinAlertDialog;
import com.tusueldo.comodin.ui.ComodinProgressDialog;
import com.tusueldo.comodin.utils.ComodinPatterns;
import com.tusueldo.comodin.utils.ComodinUsuariosGuardados;
import com.tusueldo.comodin.utils.ComodinUtils;
import com.tusueldo.comodin.utils.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.btn_facebook) LoginButton loginButton;
    @BindView(R.id.campo_usuario) AutoCompleteTextView campoUsuario;
    @BindView(R.id.username) TextInputLayout username;
    @BindView(R.id.campo_password) EditText campoPassword;
    @BindView(R.id.password) TextInputLayout password;
    private SessionManager sessionManager;
    private ComodinUsuariosGuardados usuariosGuardados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        sessionManager = SessionManager.getInstance();
        sessionManager.setActivity(this);
        sessionManager.checkSession();
        usuariosGuardados = ComodinUsuariosGuardados.getInstance(this);
        List<String> elementos = usuariosGuardados.getUsers();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, elementos);
        campoUsuario.setThreshold(1);
        campoUsuario.setAdapter(adapter);


    }

    @OnClick({R.id.btn_registrarse, R.id.btn_login})
    public void click(Button button) {

        switch (button.getId()) {

            case R.id.btn_registrarse:
                startActivity(new Intent(this, RegisterActivity.class));
                overridePendingTransition(R.animator.enter, R.animator.exit);
                break;

            case R.id.btn_login:
                ComodinUtils.login(LoginActivity.this, validateLogin(), sessionManager, usuariosGuardados, true);
                break;
        }
    }


    private ComodinLoginRequest validateLogin() {
        ComodinLoginRequest request = null;
        String usuario = campoUsuario.getText().toString().trim();
        String password = campoPassword.getText().toString();
        if (!TextUtils.isEmpty(usuario) && !TextUtils.isEmpty(password)) {
            request = new ComodinLoginRequest();
            if (Pattern.matches(ComodinPatterns.EMAIL, usuario.trim())) {
                request.setTypeDateLogin(ComodinTypeDateLogin.EMAIL);
                request.setEmail(usuario);
                request.setPassword(password);
            } else if (Pattern.matches(ComodinPatterns.MOBILE_PHONE, usuario.trim())) {
                request.setTypeDateLogin(ComodinTypeDateLogin.PHONE);
                request.setCelular(usuario);
                request.setPassword(password);
            } else {
                Toast.makeText(this, R.string.usuario_no_valido, Toast.LENGTH_SHORT).show();
                request = null;
            }
        } else {
            Toast.makeText(this, R.string.campos_incompletos, Toast.LENGTH_SHORT).show();
        }

        return request;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Header:", "Me destruyo");
    }
}
