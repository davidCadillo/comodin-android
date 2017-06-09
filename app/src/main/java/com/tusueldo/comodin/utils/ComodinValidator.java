package com.tusueldo.comodin.utils;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.widget.ImageView;
import com.tusueldo.comodin.R;

import static com.tusueldo.comodin.R.id.img_nombres;

/**
 * Created by USUARIO on 09/06/2017.
 */

public class ComodinValidator {

    private static boolean nombreValidado;
    private static boolean apellidoValidado;


    public static boolean validateNombre(Context context, CharSequence campo_nombre, TextInputLayout til_nombre, ImageView img_nombres) {
        String nombre = campo_nombre.toString();
        nombreValidado = false;
        if (nombre.isEmpty() || nombre == null) {
            markEmpty(context, til_nombre, img_nombres, "Nombre no puede quedar vacío.");
            img_nombres.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        } else if (nombre.length() < 3) {
            markEmpty(context, til_nombre, img_nombres, "El nombre tiene al menos 3 letras.");
            img_nombres.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        } else if (nombre.length() > til_nombre.getCounterMaxLength()) {
            markEmpty(context, til_nombre, img_nombres, "Número de caracteres no permitidos");
            img_nombres.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        } else {
            til_nombre.setHintTextAppearance(R.style.Hint);
            til_nombre.setErrorTextAppearance(R.style.Validado);
            til_nombre.setError(" ");
            til_nombre.getEditText().setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check_circle_black_24dp, 0);
            //img_nombres.setColorFilter(ContextCompat.getColor(context, R.color.colorValidado));
            nombreValidado = true;
            if (apellidoValidado) {
                img_nombres.setColorFilter(ContextCompat.getColor(context, R.color.colorValidado));
            }
        }
        return nombreValidado;

    }

    public static boolean validateApellido(Context context, CharSequence campo_apellido, TextInputLayout til_apellido, ImageView img_nombres) {
        String apellido = campo_apellido.toString();
        apellidoValidado = false;
        if (apellido.isEmpty() || apellido == null) {
            markEmpty(context, til_apellido, img_nombres, "Apellidos no pueden quedar vacíos.");
            img_nombres.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        } else if (apellido.length() < 3) {
            markEmpty(context, til_apellido, img_nombres, "El apellido tiene al menos 3 letras.");
            img_nombres.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        } else if (apellido.length() > til_apellido.getCounterMaxLength()) {
            markEmpty(context, til_apellido, img_nombres, "Número de caracteres no permitidos");
            img_nombres.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        } else {
            til_apellido.setHintTextAppearance(R.style.Hint);
            til_apellido.setErrorTextAppearance(R.style.Validado);
            til_apellido.setError(" ");
            til_apellido.getEditText().setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check_circle_black_24dp, 0);
            apellidoValidado = true;
            if (nombreValidado) {
                img_nombres.setColorFilter(ContextCompat.getColor(context, R.color.colorValidado));
            }
        }
        return apellidoValidado;
    }

    private static void markEmpty(Context context, TextInputLayout textInputLayout, ImageView imageView, String message) {
        textInputLayout.setHintTextAppearance(R.style.Error);
        textInputLayout.setErrorTextAppearance(R.style.Error);
        textInputLayout.setError(message);
        textInputLayout.getEditText().setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        imageView.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimaryDark));

    }
}
