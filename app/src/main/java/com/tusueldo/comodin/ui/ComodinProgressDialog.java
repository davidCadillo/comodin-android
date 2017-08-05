package com.tusueldo.comodin.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.StringRes;
import com.tusueldo.comodin.R;

/**
 * Created by USUARIO on 05/08/2017.
 */

public class ComodinProgressDialog {

    private static ProgressDialog progressDialog;


    public static void showProgressBar(Context context, @StringRes int title, @StringRes int message, boolean cancelable) {

        progressDialog = new ProgressDialog(context, R.style.CircularProgress);
        progressDialog.setTitle(title);
        progressDialog.setMessage(context.getString(message));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(cancelable);
        progressDialog.show();
    }

    public static void showProgressBar(Context context, @StringRes int title, @StringRes int message) {

        progressDialog = new ProgressDialog(context, R.style.CircularProgress);
        progressDialog.setTitle(title);
        progressDialog.setMessage(context.getString(message));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }


    public static void finish() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }


}
