package com.tusueldo.comodin;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.tusueldo.comodin.model.UserJson;

public class CuentaFragment extends Fragment {

    @BindView(R.id.texto_nombre) TextView textoNombre;
    @BindView(R.id.texto_email) TextView textoEmail;
    @BindView(R.id.texto_celular) TextView textoCelular;
    @BindView(R.id.btn_connect_fb) TextView btnConnectFb;
    @BindView(R.id.btn_connect_google) TextView btnConnectGoogle;
    private UserJson usuario;

    public CuentaFragment() {

    }

    @SuppressLint("ValidFragment")
    public CuentaFragment(UserJson usuario) {
        super();
        this.usuario = usuario;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cuenta, container, false);
        ButterKnife.bind(this, view);
        loadUserInfo();
        return view;
    }

    public void loadUserInfo() {
        textoNombre.setText(usuario.getNombresyapellidos());
        textoEmail.setText(usuario.getEmail());
        textoCelular.setText(usuario.getCelular());
    }


    @OnClick({R.id.btn_connect_fb, R.id.btn_connect_google})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_connect_fb:
                break;
            case R.id.btn_connect_google:
                break;
        }
    }
}
