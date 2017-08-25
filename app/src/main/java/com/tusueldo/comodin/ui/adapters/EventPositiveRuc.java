package com.tusueldo.comodin.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.github.javiersantos.bottomdialogs.BottomDialog;
import com.tusueldo.comodin.EmpresaFragment;
import com.tusueldo.comodin.IndependienteFragment;
import com.tusueldo.comodin.R;
import com.tusueldo.comodin.model.types.TypeUserLogin;


public class EventPositiveRuc implements BottomDialog.ButtonCallback {

    private Context context;
    private TypeUserLogin typeUserLogin;

    public EventPositiveRuc() {

    }

    public EventPositiveRuc(Context context, TypeUserLogin typeUserLogin) {
        this.context = context;
        this.typeUserLogin = typeUserLogin;
    }

    @Override
    public void onClick(@NonNull BottomDialog dialog) {
        final AppCompatActivity activity = (AppCompatActivity) context;
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        switch (typeUserLogin) {
            case INDEPENDIENTE:
                fragmentTransaction.replace(R.id.loadFragments, new EmpresaFragment());
                break;
            case EMPRESA:
                fragmentTransaction.replace(R.id.loadFragments, new IndependienteFragment());
                break;
            default:
                break;
        }

        fragmentTransaction.commit();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public TypeUserLogin getTypeUserLogin() {
        return typeUserLogin;
    }

    public void setTypeUserLogin(TypeUserLogin typeUserLogin) {
        this.typeUserLogin = typeUserLogin;
    }


}
