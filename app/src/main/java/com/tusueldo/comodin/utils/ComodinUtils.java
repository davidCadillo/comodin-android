package com.tusueldo.comodin.utils;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
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
     * Este método recibe un View y lo hace vibrar.
     *
     * @param activity
     * @param elemento
     * @param animacion
     */

    public static void vibrar(Context activity, View elemento, int animacion) {
    /*Este método está siendo utilzado tras un fallo en la validación*/
        Animation shake = AnimationUtils.loadAnimation(activity, animacion);
        if (elemento instanceof EditText) {
            ((EditText) elemento).startAnimation(shake);
            return;
        }
    }

    /**
     * Este método devuelve un TextInputLayout a la normalidad, incluido el contador cuando pierde el foco.
     *
     * @param hasFocus
     * @param textInputLayout
     */
    public static void setHintFocusField(boolean hasFocus, TextInputLayout textInputLayout) {

        if (!hasFocus) {
            if (TextUtils.isEmpty(textInputLayout.getEditText().getText())) {
                textInputLayout.setErrorEnabled(false);
                textInputLayout.setCounterEnabled(false);
            }
        }

    }

    /**
     * Este método evalúa un TextInputLayout a partir del foco. Si no posee el foco se muestra un
     * texto diferente a que si lo tuviera el foco.
     *
     * @param hasFocus
     * @param textInputLayout
     * @param textWithFocus
     * @param textWithoutFocus
     */
    public static void setHintFocusField(boolean hasFocus, TextInputLayout textInputLayout, String textWithFocus, String textWithoutFocus) {
        /*Este método ha sido utilizado para ayudar al usuario con la fecha de su nacimiento ya que le muestra el formato adecuado
        * antes de que lo escriba.*/
        textInputLayout.setHint(textWithFocus);
        if (!hasFocus) {
            if (TextUtils.isEmpty(textInputLayout.getEditText().getText())) {
                textInputLayout.setErrorEnabled(false);
                textInputLayout.setHint(textWithoutFocus);
            }
        }
    }


}
