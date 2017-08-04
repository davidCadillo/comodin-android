package com.tusueldo.comodin.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by David Cadillo on 29/07/2017.
 */

public class DialogHelper {


    public static void showDialogRuc(Context context) {

        AlertDialog.Builder alerBuilder = new AlertDialog.Builder(context);
        alerBuilder.setTitle("Hola")
                .setMessage("Maldito")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                }).create().show();
    }


}