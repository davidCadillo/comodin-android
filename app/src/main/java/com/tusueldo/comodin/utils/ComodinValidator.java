package com.tusueldo.comodin.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.tusueldo.comodin.R;

import java.util.regex.Pattern;

/**
 * Created by David Cadillo on 09/06/2017.
 */

public class ComodinValidator {

    private static String campoDia = "";
    private static String campoMes = "";
    private static String campoAnio = "";
    private static String campoFechaCompleta;
    private static boolean nombreValidado = false;
    private static boolean apellidoValidado = false;
    private static boolean telefonoValidado = false;
    private static boolean correoValidado = false;
    public static boolean fechaCorrecta = false;
    public static boolean passwordCorrecto = false;
    private static String password = null;

    private static boolean fechaDiaValidada = false;
    private static boolean fechaMesValidada = false;
    private static boolean fechaAnioValidada = false;
    private static boolean camposFechaValidados = false;

    public static TextInputLayout ti_dia = null;
    public static TextInputLayout ti_mes = null;
    public static TextInputLayout ti_anio = null;
    public static TextInputLayout ti_telefono = null;


    public static void validatePassword(Context context, CharSequence campo_password, TextInputLayout til_password, ImageView img_password) {

        int tamanioPassword = campo_password.length();

        if (tamanioPassword < 8) {
            markEmpty(context, til_password, null, "Entre 8 y 15 caracteres", 15);
            passwordCorrecto = false;
        } else if (tamanioPassword >= 8 && tamanioPassword <= 15) {
            markValidate(til_password, "Correcto", true);
            password = campo_password.toString();
            passwordCorrecto = true;
        } else if (tamanioPassword > 15) {
            markEmpty(context, til_password, null, "No permitido", 15);
            passwordCorrecto = false;
        }
        addDrawableValidado(context, img_password, passwordCorrecto);


    }


    public static void validateNombre(Context context, CharSequence campo_nombre, TextInputLayout til_nombre, ImageView img_nombres) {
        String nombre = getTrim(campo_nombre).toLowerCase();
        boolean validarNombre = Pattern.matches(ComodinPattern.NOMBRES, nombre);
        if (Pattern.matches(ComodinPattern.TIENE_ALGUN_NUMERO, nombre)) {
            markEmpty(context, til_nombre, img_nombres, "No números", 30);
        } else if (nombre.length() == 0) {
            markEmpty(context, til_nombre, img_nombres, "Es requerido.", 30);
        } else if (!validarNombre) {
            markEmpty(context, til_nombre, img_nombres, "No permitido.", 30);
        } else if (nombre.length() < 3) {
            markEmpty(context, til_nombre, img_nombres, "Mínimo 3 letras.", 30);
        } else if (validarNombre) {
            nombreValidado = true;
            markValidate(til_nombre, "Correcto", true);
            if (apellidoValidado) {
                addDrawableValidado(context, img_nombres);
            }
        }
    }

    public static void validateApellido(Context context, CharSequence campo_apellido, TextInputLayout til_apellido, ImageView img_nombres) {
        String apellido = getTrim(campo_apellido);
        boolean validarApellido = Pattern.matches(ComodinPattern.NOMBRES, apellido);
        if (Pattern.matches(ComodinPattern.TIENE_ALGUN_NUMERO, apellido)) {
            markEmpty(context, til_apellido, img_nombres, "No números", 30);
        } else if (apellido.length() == 0) {
            markEmpty(context, til_apellido, img_nombres, "No puede quedar vacío.", 30);
        } else if (!validarApellido) {
            markEmpty(context, til_apellido, img_nombres, "No permitido.", 30);
        } else if (apellido.length() < 3) {
            markEmpty(context, til_apellido, img_nombres, "Mínimo 3 letras.", 30);
        } else if (validarApellido) {
            markValidate(til_apellido, "Correcto", true);
            if (nombreValidado) {
                addDrawableValidado(context, img_nombres);
            }
        }


    }

    public static void validateCorreo(Context context, CharSequence campo_email, TextInputLayout til_correo, ImageView img_correo) {
        String correo = getTrim(campo_email).toLowerCase();
        correoValidado = !TextUtils.isEmpty(campo_email) && Pattern.matches(ComodinPattern.EMAIL, correo);
        if (correoValidado) {
            markValidate(til_correo, "Correcto", true);
            addDrawableValidado(context, img_correo);
        } else {
            markEmpty(context, til_correo, img_correo, "No es un correo válido.", 30);
            Log.d("TAG", ComodinPattern.EMAIL);
        }
    }

    public static void validateTelefono(Context context, CharSequence campo_telefono, TextInputLayout til_telefono, ImageView img_telefono) {
        String telefono = getTrim(campo_telefono);
        boolean esVacio = !TextUtils.isEmpty(telefono);
        telefonoValidado = esVacio && Pattern.matches(ComodinPattern.CELULAR, telefono);
        if (telefonoValidado) {
            markValidate(til_telefono, "Correcto", true);
            addDrawableValidado(context, img_telefono);
        } else {
            markEmpty(context, til_telefono, img_telefono, " ", 9);
        }
    }


    public static void validateFieldDate(Context context, TypeFieldDate fieldToValidate, TextInputLayout til_field_date, ImageView imageView) {

        String field_date = getTrim(til_field_date.getEditText().getText());
        boolean campoValidado = false;
        switch (fieldToValidate.ordinal()) {
            case 0:
                fechaDiaValidada = !TextUtils.isEmpty(field_date) && Pattern.matches(ComodinPattern.FECHA_DIA, field_date);
                campoValidado = fechaDiaValidada;
                campoDia = field_date;
                if (campoValidado && !fechaCorrecta) {
                    ti_mes.getEditText().requestFocus();
                }
                break;
            case 1:
                fechaMesValidada = !TextUtils.isEmpty(field_date) && Pattern.matches(ComodinPattern.FECHA_MES, field_date);
                campoValidado = fechaMesValidada;
                campoMes = field_date;
                if (campoValidado && !fechaCorrecta) {
                    ti_anio.getEditText().requestFocus();
                }
                break;
            case 2:
                fechaAnioValidada = !TextUtils.isEmpty(field_date) && Pattern.matches(ComodinPattern.FECHA_ANIO, field_date);
                campoValidado = fechaAnioValidada;
                campoAnio = field_date;
                break;
        }
        if (campoValidado) {
            camposFechaValidados = fechaDiaValidada && fechaMesValidada && fechaAnioValidada;
            til_field_date.setHintTextAppearance(R.style.Hint);
            til_field_date.setErrorTextAppearance(R.style.Validado);
            til_field_date.setErrorEnabled(true);
            if (camposFechaValidados) {
                campoFechaCompleta = campoDia.concat("/").concat(campoMes).concat("/").concat(campoAnio);
                fechaCorrecta = ComodinDateValidator.isThisDateValid(campoFechaCompleta);
                if (!fechaCorrecta) {
                    ComodinUtils.vibrar(context, imageView, R.anim.shake);
                    ti_dia.setHintTextAppearance(R.style.Error);
                    ti_dia.setErrorTextAppearance(R.style.Error);
                    ti_dia.setError(" ");
                    ti_mes.setHintTextAppearance(R.style.Error);
                    ti_mes.setErrorTextAppearance(R.style.Error);
                    ti_mes.setError(" ");
                    ti_anio.setHintTextAppearance(R.style.Error);
                    ti_anio.setErrorTextAppearance(R.style.Error);
                    ti_anio.setError(" ");
                    imageView.setColorFilter(ContextCompat.getColor(context, R.color.colorNoValidado));
                    return;

                } else {
                    markValidate(ti_dia);
                    markValidate(ti_mes);
                    markValidate(ti_anio);
                    imageView.setColorFilter(ContextCompat.getColor(context, R.color.colorValidado));
                    ti_telefono.getEditText().requestFocus();
                }
            }

        } else {
            til_field_date.setHintTextAppearance(R.style.Error);
            til_field_date.setErrorTextAppearance(R.style.Error);
            addDrawableValidado(context, imageView, false);
        }
        til_field_date.setError(" ");
    }

    @NonNull
    private static String getTrim(CharSequence campo_fecha_nac) {
        return campo_fecha_nac.toString().trim();
    }

    private static void markEmpty(Context context, TextInputLayout textInputLayout, ImageView imageView, String message, int counterMaxLength) {
        textInputLayout.setCounterEnabled(true);
        textInputLayout.setCounterMaxLength(counterMaxLength);
        textInputLayout.setHintTextAppearance(R.style.Error);
        textInputLayout.setErrorTextAppearance(R.style.Error);
        textInputLayout.setError(message);
        textInputLayout.getEditText().setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        if (imageView != null)
            addDrawableValidado(context, imageView, false);
    }

    private static void markEmpty(Context context, TextInputLayout textInputLayout, String message) {
        textInputLayout.setHintTextAppearance(R.style.Error);
        textInputLayout.setErrorTextAppearance(R.style.Error);
        textInputLayout.setError(message);
    }

    private static void markValidate(TextInputLayout campo, String message, boolean checkImage) {
        campo.setHintTextAppearance(R.style.Hint);
        campo.setErrorTextAppearance(R.style.Validado);
        campo.setErrorEnabled(true);
        campo.setError(message);
        if (checkImage)
            campo.getEditText().setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check_circle_black_24px, 0);
    }

    private static void markValidate(TextInputLayout campo) {
        markValidate(campo, " ", false);
    }

    private static void addDrawableValidado(Context context, ImageView imageView, boolean validate) {
        if (validate)
            imageView.setColorFilter(ContextCompat.getColor(context, R.color.colorValidado));
        else {
            imageView.setColorFilter(ContextCompat.getColor(context, R.color.colorNoValidado));
        }
    }

    public static void addDrawableValidado(Context context, ImageView imageView) {
        imageView.setColorFilter(ContextCompat.getColor(context, R.color.colorValidado));
    }
}
