package com.tusueldo.comodin.ui;

import android.content.Context;
import android.os.Build;
import android.support.annotation.StringRes;
import com.afollestad.materialdialogs.MaterialDialog;
import com.tusueldo.comodin.R;


public class ComodinProgressDialog {


    private static MaterialDialog materialDialog = null;

    public static void showProgressBar(Context context, @StringRes int title, @StringRes int message, boolean cancelable) {

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

    }

    public static void showProgressBar(Context context, @StringRes int title, @StringRes int message) {
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

    }


    public static void finish() {
        if (materialDialog != null && materialDialog.isShowing()){
            materialDialog.dismiss();
            materialDialog = null;
        }


    }


}
