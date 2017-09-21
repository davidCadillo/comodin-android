package com.tusueldo.comodin;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tusueldo.comodin.model.UserJson;

public class CuentaFragment extends Fragment {

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
        return inflater.inflate(R.layout.fragment_cuenta, container, false);
    }

}
