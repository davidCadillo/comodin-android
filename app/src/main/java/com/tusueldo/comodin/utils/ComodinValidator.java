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

    public static String campoDia = "";
    public static String campoMes = "";
    public static String campoAnio = "";
    public static String campoFechaCompleta;
    public static boolean nombreValidado = false;
    public static boolean apellidoValidado = false;
    public static boolean telefonoValidado = false;
    public static boolean correoValidado = false;
    public static boolean fechaDiaValidada = false;
    public static boolean fechaMesValidada = false;
    public static boolean fechaAnioValidada = false;
    public static boolean camposFechaValidados = false;
    public static boolean fechaCorrecta;

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
            markEmpty(context, til_apellido, img_nombres, "No números", 30);
        } else if (apellido.length() == 0) {
            markEmpty(context, til_apellido, img_nombres, "No puede quedar vacío.", 30);
        } else if (!validarApellido) {
            markEmpty(context, til_apellido, img_nombres, "No permitido.", 30);
        } else if (apellido.length() < 3) {
            markEmpty(context, til_apellido, img_nombres, "Mínimo 3 letras.", 30);
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
            markEmpty(context, til_correo, img_correo, "No es un correo válido.", 30);
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
            markEmpty(context, til_telefono, img_telefono, " ", 9);
        }
    }


    public static void validateFieldDate(TypeFieldDate fieldToValidate, TextInputLayout til_field_date) {

        String field_date = getTrim(til_field_date.getEditText().getText());
        boolean campoValidado = false;
        switch (fieldToValidate.ordinal()) {
            case 0:
                fechaDiaValidada = !TextUtils.isEmpty(field_date) && Pattern.matches(ComodinPattern.FECHA_DIA, field_date);
                campoValidado = fechaDiaValidada;
                campoDia = field_date;
                Log.d("DIA", campoDia);

                break;
            case 1:
                fechaMesValidada = !TextUtils.isEmpty(field_date) && Pattern.matches(ComodinPattern.FECHA_MES, field_date);
                campoValidado = fechaMesValidada;
                campoMes = field_date;
                Log.d("MES", campoMes);
                break;
            case 2:
                fechaAnioValidada = !TextUtils.isEmpty(field_date) && Pattern.matches(ComodinPattern.FECHA_ANIO, field_date);
                campoValidado = fechaAnioValidada;
                campoAnio = field_date;
                Log.d("ANIO", campoAnio);
                break;
        }
        campoFechaCompleta = campoDia.concat("/").concat(campoMes).concat("/").concat(campoDia);
        Log.d("FECHA", campoFechaCompleta);
        camposFechaValidados = fechaDiaValidada && fechaMesValidada && fechaAnioValidada;
        if (campoValidado) {
            if (camposFechaValidados) {
                fechaCorrecta = ComodinDateValidator.isThisDateValid(campoFechaCompleta);
            }
            til_field_date.setHintTextAppearance(R.style.Hint);
            til_field_date.setErrorTextAppearance(R.style.Validado);
            til_field_date.setErrorEnabled(true);
        } else {
            til_field_date.setHintTextAppearance(R.style.Error);
            til_field_date.setErrorTextAppearance(R.style.Error);
            til_field_date.getEditText().setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
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
            imageView.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimaryDark));
    }


    private static void markValidate(TextInputLayout campo) {
        campo.setHintTextAppearance(R.style.Hint);
        campo.setErrorTextAppearance(R.style.Validado);
        campo.setErrorEnabled(true);
        campo.setError("Correcto");
        campo.getEditText().setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check_circle_black_24px, 0);
    }

    private static void addDrawableValidado(Context context, ImageView image) {
        image.setColorFilter(ContextCompat.getColor(context, R.color.colorValidado));
    }


}
