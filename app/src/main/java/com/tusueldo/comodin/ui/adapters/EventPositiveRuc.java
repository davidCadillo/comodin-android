package com.tusueldo.comodin.ui.adapters;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import com.tusueldo.comodin.EmpresaFragment;
import com.tusueldo.comodin.IndependienteFragment;
import com.tusueldo.comodin.R;
import com.tusueldo.comodin.model.types.TypeUserLogin;

/**
 * Created by USUARIO on 05/08/2017.
 */

public class EventPositiveRuc implements DialogInterface.OnClickListener {

    private Context context;
    private TypeUserLogin typeUserLogin;

    public EventPositiveRuc() {

    }

    public EventPositiveRuc(Context context, TypeUserLogin typeUserLogin) {
        this.context = context;
        this.typeUserLogin = typeUserLogin;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        final Activity activity = (Activity) context;
        FragmentTransaction fragmentTransaction = activity.getFragmentManager().beginTransaction();
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
