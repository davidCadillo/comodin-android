package com.tusueldo.comodin.ui;

import android.content.Context;
import android.support.annotation.StringRes;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.bottomdialogs.BottomDialog;
import com.tusueldo.comodin.R;
import com.tusueldo.comodin.ui.adapters.EventDialogDismiss;


public class ComodinAlertDialog {


    public static void showDialogMaterial(Context context, @StringRes int title, @StringRes int message,
                                          MaterialDialog.SingleButtonCallback onPositive, MaterialDialog.SingleButtonCallback onNegative) {

        new MaterialDialog.Builder(context)
                .title(title)
                .content(message)
                .onPositive(onPositive)
                .onNegative(onNegative)
                .show();

    }

    public static void showDialogMaterialInformative(Context context, @StringRes int title, @StringRes int message,
                                                     @StringRes int positiveText) {

        new MaterialDialog.Builder(context)
                .title(title)
                .content(message)
                .positiveText(positiveText)
                .show();

    }


    public static void showDialogRuc(Context context, @StringRes int message,
                                     BottomDialog.ButtonCallback onPositive) {


        new BottomDialog.Builder(context)
                .setTitle(R.string.titulo_ruc_alertdialog)
                .setContent(message)
                .setIcon(R.mipmap.ic_comodin)
                .setPositiveText(R.string.registrar)
                .onPositive(onPositive)
                .setNegativeText(android.R.string.ok)
                .onNegative(new EventDialogDismiss())
                .setCancelable(false)
                .show();

    }


}