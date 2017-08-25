package com.tusueldo.comodin.ui.adapters;

import android.support.annotation.NonNull;
import com.github.javiersantos.bottomdialogs.BottomDialog;


public class EventDialogDismiss implements BottomDialog.ButtonCallback {


    @Override
    public void onClick(@NonNull BottomDialog dialog) {
        dialog.dismiss();
    }
}
