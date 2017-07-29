package com.tusueldo.comodin;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tusueldo.comodin.utils.ComodinUtils;

public class InicioActivity extends AppCompatActivity {

    private TextInputLayout username;
    private TextInputLayout password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        username = (TextInputLayout) findViewById(R.id.username);
        password = (TextInputLayout) findViewById(R.id.password);
    }


    public void procesar(View v) {
        switch (v.getId()) {
            case R.id.btn_login:

                if (username.getEditText().getText().toString().equals("admin") && password.getEditText().getText().toString().equals("admin")) {
                    Intent i = new Intent(v.getContext(), UserActivity.class);
                    startActivity(i);

                } else {
                    if (username.getEditText().getText().toString().isEmpty()) {
                        username.setError("No ha introducido su usario");
                        ComodinUtils.vibrar(this, username.getEditText(), R.anim.shake);
                    }

                    if (password.getEditText().getText().toString().isEmpty()) {
                        password.setError("No ha introducido la contraseña");
                        ComodinUtils.vibrar(this, password.getEditText(), R.anim.shake);
                    }
                }

                break;

            case R.id.btn_registrarse:
                Intent i = new Intent(v.getContext(), SignupActivity.class);
                startActivity(i);
                overridePendingTransition(R.animator.enter, R.animator.exit);

        }

    }


}
