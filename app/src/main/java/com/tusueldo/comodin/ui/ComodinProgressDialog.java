package com.tusueldo.comodin.ui;

import android.content.Context;
import android.os.Build;
import android.support.annotation.StringRes;
import com.afollestad.materialdialogs.MaterialDialog;
import com.tusueldo.comodin.R;


public class ComodinProgressDialog {


    public static MaterialDialog showProgressBar(Context context, @StringRes int title, @StringRes int message, boolean cancelable) {

        MaterialDialog materialDialog;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            materialDialog = new MaterialDialog.Builder(context)
                    .title(title)
                    .content(message)
                    .progress(true, 0)
                    .cancelable(cancelable)
                    .icon(context.getResources().getDrawable(R.mipmap.ic_comodin, null))
                    .show();
        } else {
            materialDialog = new MaterialDialog.Builder(context)
                    .title(title)
                    .content(message)
                    .cancelable(cancelable)
                    .progress(true, 0)
                    .show();
        }
        return materialDialog;

    }

    public static MaterialDialog showProgressBar(Context context, @StringRes int title, @StringRes int message) {
        MaterialDialog materialDialog;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            materialDialog = new MaterialDialog.Builder(context)
                    .title(title)
                    .content(message)
                    .progress(true, 0)
                    .icon(context.getResources().getDrawable(R.mipmap.ic_comodin, null))
                    .show();
        } else {
            materialDialog = new MaterialDialog.Builder(context)
                    .title(title)
                    .content(message)
                    .progress(true, 0)
                    .show();
        }
        return materialDialog;

    }


    public static void finish(MaterialDialog materialDialog) {
        if (materialDialog.isShowing())
            materialDialog.dismiss();

    }


}
