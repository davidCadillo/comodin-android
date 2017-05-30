package com.tusueldo.comodin.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import com.tusueldo.comodin.R;

/**
 * Created by USUARIO on 30/05/2017.
 */

public class ComodinUtils {


    /**
     *
     *
     * */

    public static void vibrar(Context activity, View elemento, int animacion) {

        Animation shake = AnimationUtils.loadAnimation(activity, animacion);
        if (elemento instanceof EditText) {
            ((EditText) elemento).startAnimation(shake);
            return;
        }


    }


}
