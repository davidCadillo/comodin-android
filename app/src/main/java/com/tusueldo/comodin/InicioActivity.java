package com.tusueldo.comodin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.facebook.*;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;


public class InicioActivity extends AppCompatActivity {

    CallbackManager callbackManager;
    @BindView(R.id.btn_facebook) LoginButton loginButton;
    @BindView(R.id.campo_usuario) EditText campoUsuario;
    @BindView(R.id.username) TextInputLayout username;
    @BindView(R.id.campo_password) EditText campoPassword;
    @BindView(R.id.password) TextInputLayout password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_inicio);
        ButterKnife.bind(this);
        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions(Arrays.asList("id", "email"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                Log.d("InicioActivity", "Correcto");
                final GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
//                                Log.v("LoginActivity", response.toString());
                                try {
                                    String email = object.getString("email");
                                    Log.d("Email: ", email);
                                } catch (JSONException e) {
                                    Log.d("Email: ", "nada");
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,email");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {
                Log.d("FacebookFragment", "Cancelado");
                showResult("Error eal conectar", "Usted ha cancelado el inicio de sesión.");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("InicioActivity", "Error");
            }

            private void showResult(String title, String alertMessage) {
                new AlertDialog.Builder(loginButton.getContext())
                        .setTitle(title)
                        .setMessage(alertMessage)
                        .setPositiveButton(android.R.string.ok, null)
                        .show();
            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.btn_registrarse)
    public void click(Button button) {

        Intent i;
        switch (button.getId()) {

            case R.id.btn_registrarse:
                i = new Intent(button.getContext(), SignupActivity.class);
                startActivity(i);
                overridePendingTransition(R.animator.enter, R.animator.exit);
                break;


        }
    }

    /*public void procesar(View v) {
        switch (v.getId()) {
            case R.id.btn_login:

               *//* if (username.getEditText().getText().toString().equals("admin"))
                    if (password.getEditText().getText().toString().equals("admin")) {
                        Intent i = new Intent(v.getContext(), UserActivity.class);
                        startActivity(i);

                    } else {
                        if (username.getEditText().getText().toString().isEmpty()) {
                            username.setError("No ha introducido su usario");
                            ComodinUtils.vibrate(this, username.getEditText(), R.anim.shake);
                        }

                        if (password.getEditText().getText().toString().isEmpty()) {
                            password.setError("No ha introducido la contraseña");
                            ComodinUtils.vibrate(this, password.getEditText(), R.anim.shake);
                        }
                    }
                else {
                    if (username.getEditText().getText().toString().isEmpty()) {
                        username.setError("No ha introducido su usario");
                        ComodinUtils.vibrate(this, username.getEditText(), R.anim.shake);
                    }

                    if (password.getEditText().getText().toString().isEmpty()) {
                        password.setError("No ha introducido la contraseña");
                        ComodinUtils.vibrate(this, password.getEditText(), R.anim.shake);
                    }
                }
*//*
                break;

            case R.id.btn_registrarse:
                Intent i = new Intent(v.getContext(), SignupActivity.class);
                startActivity(i);
                overridePendingTransition(R.animator.enter, R.animator.exit);
                break;

            case R.id.btn_recuperar:
                new MaterialDialog.Builder(this)
                        .title("Escoja método de recuperación: ")
                        .items(R.array.items)
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                return true;
                            }
                        })
                        .show();
                break;

        }

    }*/


}
