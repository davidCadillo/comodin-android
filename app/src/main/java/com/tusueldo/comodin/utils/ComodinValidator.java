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
    public static boolean fechaNacimientoValidada = false;
    public static boolean passwordValidado = false;
    public static boolean rucvalidado = false;

    private static boolean fechaDiaValidada = false;
    private static boolean fechaMesValidada = false;
    private static boolean fechaAnioValidada = false;
    private static boolean camposFechaValidados = false;

    public static TextInputLayout ti_dia = null;
    public static TextInputLayout ti_mes = null;
    public static TextInputLayout ti_anio = null;
    public static TextInputLayout ti_telefono = null;


    public static void validateRuc(Context context, CharSequence campo_ruc, TextInputLayout til_ruc, ImageView img_ruc) {

        int tamanioRuc = campo_ruc.length();
        til_ruc.setCounterEnabled(true);
        til_ruc.setCounterMaxLength(11);
        if (tamanioRuc == 11) {
            rucvalidado = true;
        } else {
            rucvalidado = false;
        }
        setFieldValidate(til_ruc, "Correcto", rucvalidado);
        setIconValidate(context, img_ruc, rucvalidado);
    }

    public static void validatePassword(Context context, CharSequence campo_password, TextInputLayout til_password, ImageView img_password) {

        int tamanioPassword = campo_password.length();

        if (tamanioPassword < 8) {
            markEmpty(context, til_password, null, "Entre 8 y 15 caracteres", 15);
            passwordValidado = false;
        } else if (tamanioPassword >= 8 && tamanioPassword <= 15) {
            setFieldValidate(til_password, "Correcto", true);
            passwordValidado = true;
        } else if (tamanioPassword > 15) {
            markEmpty(context, til_password, null, "No permitido", 15);
            passwordValidado = false;
        }
        setIconValidate(context, img_password, passwordValidado);
//        Log.d("Password", String.valueOf(passwordValidado));

    }

    public static void validateNombre(Context context, CharSequence campo_nombre, TextInputLayout til_nombre, ImageView img_nombres) {
        String nombre = getTrim(campo_nombre).toLowerCase();
        boolean validarNombre = Pattern.matches(ComodinPattern.NOMBRES, nombre);

        //Validación para evitar numeros
        if (Pattern.matches(ComodinPattern.TIENE_ALGUN_NUMERO, nombre)) {
            markEmpty(context, til_nombre, img_nombres, "No números", 30);
            nombreValidado = false;
            //Validación para que no sea vacío
        } else if (nombre.length() == 0) {
            markEmpty(context, til_nombre, img_nombres, "Es requerido.", 30);
            nombreValidado = false;
            //Cuando no se ha validado
        } else if (!validarNombre) {
            markEmpty(context, til_nombre, img_nombres, "No permitido.", 30);
            nombreValidado = false;
            //Cuando se introduce menos de 3 caracteres
        } else if (nombre.length() < 3) {
            markEmpty(context, til_nombre, img_nombres, "Mínimo 3 letras.", 30);
            nombreValidado = false;
            //Cuando el nombre ya está validado
        } else if (validarNombre) {
            nombreValidado = true;
            setFieldValidate(til_nombre, "Correcto", true);
            if (apellidoValidado) {
                setIconValidate(context, img_nombres);
            }
        }
        Log.d("Nombres", String.valueOf(nombreValidado));
    }

    public static void validateApellido(Context context, CharSequence campo_apellido, TextInputLayout til_apellido, ImageView img_nombres) {
        String apellido = getTrim(campo_apellido);
        boolean validarApellido = Pattern.matches(ComodinPattern.NOMBRES, apellido);
        if (Pattern.matches(ComodinPattern.TIENE_ALGUN_NUMERO, apellido)) {
            markEmpty(context, til_apellido, img_nombres, "No números", 30);
            apellidoValidado = false;
        } else if (apellido.length() == 0) {
            markEmpty(context, til_apellido, img_nombres, "No puede quedar vacío.", 30);
            apellidoValidado = false;
        } else if (!validarApellido) {
            markEmpty(context, til_apellido, img_nombres, "No permitido.", 30);
            apellidoValidado = false;
        } else if (apellido.length() < 3) {
            markEmpty(context, til_apellido, img_nombres, "Mínimo 3 letras.", 30);
            apellidoValidado = false;
        } else if (validarApellido) {
            apellidoValidado = true;
            setFieldValidate(til_apellido, "Correcto", true);
            if (nombreValidado) {
                setIconValidate(context, img_nombres);
            }
        }
        Log.d("Apellido", String.valueOf(apellidoValidado));
    }

    public static void validateCorreo(Context context, CharSequence campo_email, TextInputLayout til_correo, ImageView img_correo) {
        String correo = getTrim(campo_email).toLowerCase();
        correoValidado = !TextUtils.isEmpty(campo_email) && Pattern.matches(ComodinPattern.EMAIL, correo);
        if (correoValidado) {
            setFieldValidate(til_correo, "Correcto", true);
            setIconValidate(context, img_correo);
        } else {
            markEmpty(context, til_correo, img_correo, "No es un correo válido.", 30);
        }
        Log.d("Correo", String.valueOf(correoValidado));
    }

    public static void validateTelefono(Context context, CharSequence campo_telefono, TextInputLayout til_telefono, ImageView img_telefono) {
        String telefono = getTrim(campo_telefono);
        boolean esVacio = !TextUtils.isEmpty(telefono);
        telefonoValidado = esVacio && Pattern.matches(ComodinPattern.CELULAR, telefono);
        if (telefonoValidado) {
            setFieldValidate(til_telefono, "Correcto", true);
            setIconValidate(context, img_telefono);
        } else {
            markEmpty(context, til_telefono, img_telefono, " ", 9);
        }
        Log.d("Telefono", String.valueOf(telefonoValidado));
    }

    public static void validateFieldDate(Context context, TypeFieldDate fieldToValidate, TextInputLayout til_field_date, ImageView imageView) {

        //Se obtiene el campo de la fecha(mes, dia año) y se limpia
        String field_date = getTrim(til_field_date.getEditText().getText());
        boolean campoValidado = false;
        //Se evalua el tiempo de campo a validar
        switch (fieldToValidate.ordinal()) {

            case 0://Se va a validar el dia
                //Se valida el día según una expresión regular
                fechaDiaValidada = !TextUtils.isEmpty(field_date) && Pattern.matches(ComodinPattern.FECHA_DIA, field_date);
                campoValidado = fechaDiaValidada;
                //Se guarda el dia introducido
                campoDia = field_date;
                //Cuando es correcto el día, pero aún faltan datos en la fecha o no es correcta, entonces se da el foco al mes.
                if (campoValidado && !fechaNacimientoValidada) {
                    ti_mes.getEditText().requestFocus();
                }
                break;
            case 1:
                fechaMesValidada = !TextUtils.isEmpty(field_date) && Pattern.matches(ComodinPattern.FECHA_MES, field_date);
                campoValidado = fechaMesValidada;
                campoMes = field_date;
                if (campoValidado && !fechaNacimientoValidada) {
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
                fechaNacimientoValidada = ComodinDateValidator.isThisDateValid(campoFechaCompleta);
                if (!fechaNacimientoValidada) {
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
                    setFieldValidate(ti_dia);
                    setFieldValidate(ti_mes);
                    setFieldValidate(ti_anio);
                    imageView.setColorFilter(ContextCompat.getColor(context, R.color.colorValidado));
                    ti_telefono.getEditText().requestFocus();
                }
            }
        } else {
            fechaNacimientoValidada = false;
            til_field_date.setHintTextAppearance(R.style.Error);
            til_field_date.setErrorTextAppearance(R.style.Error);
            setIconValidate(context, imageView, false);
        }
        til_field_date.setError(" ");
        Log.d("Fecha", String.valueOf(fechaNacimientoValidada));
    }

    @NonNull
    private static String getTrim(CharSequence campo_fecha_nac) {
        return campo_fecha_nac.toString().trim();
    }


    /**
     * Esta función marca en rojo(error) un textinputlayout, además le quita el icono verde si es que el campo ha estado validado,
     * también marca en rojo el icono a los campos a los que representa. Se puede especificar un mensaje y el tamaño del contador.
     *
     * @param context
     * @param textInputLayout
     * @param imageView
     * @param message
     * @param counterMaxLength
     */
    private static void markEmpty(Context context, TextInputLayout textInputLayout, ImageView imageView, String message, int counterMaxLength) {
        textInputLayout.setCounterEnabled(true);
        textInputLayout.setCounterMaxLength(counterMaxLength);
        textInputLayout.setHintTextAppearance(R.style.Error);
        textInputLayout.setErrorTextAppearance(R.style.Error);
        textInputLayout.setError(message);
        textInputLayout.getEditText().setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        if (imageView != null)
            setIconValidate(context, imageView, false);
    }


    /**
     * Esta función pone en verde a un TextInput layout, le añade un texto y además añade un pequeño icono verde a la derecha del campo
     * si es que se especifica.
     *
     * @param campo
     * @param message
     * @param checkImage
     */
    private static void setFieldValidate(TextInputLayout campo, String message, boolean checkImage) {
        campo.setHintTextAppearance(R.style.Hint);
        campo.setErrorTextAppearance(R.style.Validado);
        campo.setErrorEnabled(true);
        campo.setError(message);
        if (checkImage)
            campo.getEditText().setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check_circle_black_24px, 0);
    }

    /**
     * Esta función marca en verde un TextInputLayout sin agregarle iconos y sin texto
     *
     * @param campo
     */
    private static void setFieldValidate(TextInputLayout campo) {
        setFieldValidate(campo, " ", false);
    }

    /**
     * Esta función establece el icono de los formularios en rojo o verde, de acuerdo a si sus campos
     * correspondientes están validados o no.
     *
     * @param context
     * @param imageView
     * @param validate
     */
    public static void setIconValidate(Context context, ImageView imageView, boolean validate) {
        if (validate)
            imageView.setColorFilter(ContextCompat.getColor(context, R.color.colorValidado));
        else {
            imageView.setColorFilter(ContextCompat.getColor(context, R.color.colorNoValidado));
        }
    }

    /**
     * Esta función marca en verde un icono.
     *
     * @param context
     * @param imageView
     */
    public static void setIconValidate(Context context, ImageView imageView) {
        imageView.setColorFilter(ContextCompat.getColor(context, R.color.colorValidado));
    }
}
