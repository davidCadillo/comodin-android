package com.tusueldo.comodin.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import com.tusueldo.comodin.R;

import java.util.regex.Pattern;

/**
 * Created by USUARIO on 09/06/2017.
 */

public class ComodinValidator {

    private static boolean nombreValidado = false;
    private static boolean apellidoValidado = false;
    private static boolean telefonoValidado = false;
    private static boolean fechaValidada = false;
    private static boolean correoValidado = false;


    public static void validateNombre(Context context, CharSequence campo_nombre, TextInputLayout til_nombre, ImageView img_nombres) {
        String nombre = getTrim(campo_nombre).toLowerCase();
        boolean validarNombre = Pattern.matches(ComodinPattern.NOMBRES, nombre);
        if (Pattern.matches(ComodinPattern.TIENE_ALGUN_NUMERO, nombre)) {
            markEmpty(context, til_nombre, img_nombres, "No números");
        } else if (nombre.length() == 0) {
            markEmpty(context, til_nombre, img_nombres, "No puede quedar vacío.");
        } else if (!validarNombre) {
            markEmpty(context, til_nombre, img_nombres, "No permitido.");
        } else if (nombre.length() < 3) {
            markEmpty(context, til_nombre, img_nombres, "Mínimo 3 letras.");
        } else if (nombre.length() > til_nombre.getCounterMaxLength()) {
            markEmpty(context, til_nombre, img_nombres, "Exceso de caracteres.");
        } else if (validarNombre) {
            nombreValidado = true;
            markValidate(til_nombre);
            if (apellidoValidado) {
                addDrawableValidado(context, img_nombres);
            }
        }
    }

    public static void validateApellido(Context context, CharSequence campo_apellido, TextInputLayout til_apellido, ImageView img_nombres) {
        String apellido = getTrim(campo_apellido);
        boolean validarApellido = Pattern.matches(ComodinPattern.NOMBRES, apellido);
        if (Pattern.matches(ComodinPattern.TIENE_ALGUN_NUMERO, apellido)) {
            markEmpty(context, til_apellido, img_nombres, "No números");
        } else if (apellido.length() == 0) {
            markEmpty(context, til_apellido, img_nombres, "No puede quedar vacío.");
        } else if (!validarApellido) {
            markEmpty(context, til_apellido, img_nombres, "No permitido.");
        } else if (apellido.length() < 3) {
            markEmpty(context, til_apellido, img_nombres, "Mínimo 3 letras.");
        } else if (apellido.length() > til_apellido.getCounterMaxLength()) {
            markEmpty(context, til_apellido, img_nombres, "Exceso de caracteres.");
        } else if (validarApellido) {
            apellidoValidado = true;
            markValidate(til_apellido);
            if (nombreValidado) {
                addDrawableValidado(context, img_nombres);
            }
        }


    }

    public static void validateCorreo(Context context, CharSequence campo_email, TextInputLayout til_correo, ImageView img_correo) {
        String correo = getTrim(campo_email).toLowerCase();
        correoValidado = !TextUtils.isEmpty(campo_email) && Pattern.matches(ComodinPattern.EMAIL, correo);
        if (correoValidado) {
            markValidate(til_correo);
            addDrawableValidado(context, img_correo);
        } else {
            markEmpty(context, til_correo, img_correo, "No es un correo válido.");
            Log.d("TAG", ComodinPattern.EMAIL);
        }
    }

    public static void validateTelefono(Context context, CharSequence campo_telefono, TextInputLayout til_telefono, ImageView img_telefono) {
        String telefono = getTrim(campo_telefono);
        boolean esVacio = !TextUtils.isEmpty(telefono);
        telefonoValidado = esVacio && Pattern.matches(ComodinPattern.CELULAR, telefono);
        if (telefonoValidado) {
            markValidate(til_telefono);
            addDrawableValidado(context, img_telefono);
        } else {
            markEmpty(context, til_telefono, img_telefono, "Incorrecto");
        }
    }

    public static void validateFechaNac(Context context, CharSequence campo_fecha_nac, TextInputLayout til_fecha_nac, ImageView img_fecha_nac) {
        String fecha_nac = getTrim(campo_fecha_nac);
        fechaValidada = !TextUtils.isEmpty(fecha_nac) && Pattern.matches(ComodinPattern.FECHA_NACIMIENTO, fecha_nac) && ComodinDateValidator.isThisDateValid(fecha_nac);
        if (fechaValidada) {
            markValidate(til_fecha_nac);
            addDrawableValidado(context, img_fecha_nac);
        } else {
            markEmpty(context, til_fecha_nac, img_fecha_nac, "No es correcto");
        }
    }

    @NonNull
    private static String getTrim(CharSequence campo_fecha_nac) {
        return campo_fecha_nac.toString().trim();
    }


    private static void markEmpty(Context context, TextInputLayout textInputLayout, ImageView imageView, String message) {
        textInputLayout.setHintTextAppearance(R.style.Error);
        textInputLayout.setErrorTextAppearance(R.style.Error);
        textInputLayout.setError(message);
        textInputLayout.getEditText().setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        imageView.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimaryDark));
    }

    private static void markValidate(TextInputLayout campo) {
        campo.setHintTextAppearance(R.style.Hint);
        campo.setErrorTextAppearance(R.style.Validado);
        campo.setErrorEnabled(true);
        campo.setError(" ");
        campo.getEditText().setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check_circle_black_24px, 0);
    }

    private static void addDrawableValidado(Context context, ImageView image) {
        image.setColorFilter(ContextCompat.getColor(context, R.color.colorValidado));
    }


}
