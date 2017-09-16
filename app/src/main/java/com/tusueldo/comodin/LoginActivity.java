package com.tusueldo.comodin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
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
import com.tusueldo.comodin.ui.ComodinAlertDialog;
import com.tusueldo.comodin.ui.ComodinProgressDialog;
import com.tusueldo.comodin.utils.ComodinPatterns;
import com.tusueldo.comodin.utils.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.regex.Pattern;


public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.btn_facebook) LoginButton loginButton;
    @BindView(R.id.campo_usuario) EditText campoUsuario;
    @BindView(R.id.username) TextInputLayout username;
    @BindView(R.id.campo_password) EditText campoPassword;
    @BindView(R.id.password) TextInputLayout password;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        sessionManager = SessionManager.getInstance();
        sessionManager.setActivity(this);
        sessionManager.checkSession();
    }

    @OnClick({R.id.btn_registrarse, R.id.btn_login})
    public void click(Button button) {

        Intent intent;
        switch (button.getId()) {

            case R.id.btn_registrarse:
                intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                overridePendingTransition(R.animator.enter, R.animator.exit);
                break;

            case R.id.btn_login:
                ComodinLoginRequest request = validateLogin();
                if (request != null) {
                    IRetrofitServiceApi serviceApi = new RetrofitAdapter().getAdapater().create(IRetrofitServiceApi.class);
                    Call<ComodinLoginResponse> call = serviceApi.login(request);
                    final MaterialDialog materialDialog = ComodinProgressDialog.showProgressBar(this, R.string.iniciando_sesion, R.string.cargando);
                    call.enqueue(new Callback<ComodinLoginResponse>() {
                        @Override
                        public void onResponse(Call<ComodinLoginResponse> call, Response<ComodinLoginResponse> response) {
                            if (response.isSuccessful()) {
                                ComodinLoginResponse loginResponse = response.body();
                                if (loginResponse != null && loginResponse.getCode() == 200) {
                                    sessionManager.createSession(loginResponse.getMessage());
                                    Log.d("TOKEN: ", loginResponse.getMessage());
                                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(i);
                                    overridePendingTransition(R.animator.enter, R.animator.exit);

                                } else {
                                    ComodinLoginErrors.from(response.body());
                                    ComodinAlertDialog.showDialogMaterialInformative(LoginActivity.this, R.string.error_login, ComodinLoginErrors.showMessageError(), android.R.string.ok);
                                }

                            } else {
                                ComodinAlertDialog.showDialogMaterialInformative(LoginActivity.this, R.string.error_login, R.string.error_500_alert_dialog_login, android.R.string.ok);
                            }
                            ComodinProgressDialog.finish(materialDialog);
                        }

                        @Override
                        public void onFailure(Call<ComodinLoginResponse> call, Throwable t) {
                            ComodinAlertDialog.showDialogMaterialInformative(LoginActivity.this, R.string.error, R.string.error_500_alert_dialog_login, android.R.string.ok);
                            ComodinProgressDialog.finish(materialDialog);
                        }
                    });
                } else {
                    Log.d("LOGIN: ", "NULO");
                }
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
                request.setType_date_login(ComodinTypeDateLogin.EMAIL);
                request.setEmail(usuario);
                request.setPassword(password);
            } else if (Pattern.matches(ComodinPatterns.MOBILE_PHONE, usuario.trim())) {
                request.setType_date_login(ComodinTypeDateLogin.PHONE);
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
