package com.tusueldo.comodin.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import com.tusueldo.comodin.R;
import com.tusueldo.comodin.ui.adapters.EventDialogDismiss;

/**
 * Created by David Cadillo on 29/07/2017.
 */

public class ComodinAlertDialog {


    public static void showDialog(Context context, @StringRes int title, @StringRes int message,
                                  DialogInterface.OnClickListener onPositive, DialogInterface.OnClickListener onNegative) {

        AlertDialog.Builder alerBuilder = new AlertDialog.Builder(context);
        alerBuilder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, onPositive)
                .setNegativeButton(android.R.string.no, onNegative)
                .create().show();
    }


    public static void showDialogRuc(Context context, @StringRes int message,
                                     DialogInterface.OnClickListener onPositive) {

        AlertDialog.Builder alerBuilder = new AlertDialog.Builder(context);
        alerBuilder.setTitle(R.string.titulo_ruc_alertdialog)
                .setMessage(message)
                .setCancelable(true)
                .setPositiveButton(R.string.ir, onPositive)
                .setNegativeButton(android.R.string.ok, new EventDialogDismiss())
                .create().show();
    }


}