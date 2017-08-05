package com.tusueldo.comodin.ui.adapters;

import android.content.DialogInterface;

/**
 * Created by USUARIO on 05/08/2017.
 */

public class EventDialogDismiss implements DialogInterface.OnClickListener {
    @Override
    public void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
    }
}
