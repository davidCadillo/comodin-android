package com.tusueldo.comodin.utils;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ImageView;
import com.tusueldo.comodin.R;
import com.tusueldo.comodin.SignupActivity;

import java.util.regex.Pattern;

import static com.tusueldo.comodin.R.id.img_nombres;
import static com.tusueldo.comodin.R.id.img_telefono;
import static com.tusueldo.comodin.R.id.til_telefono;

/**
 * Created by USUARIO on 09/06/2017.
 */

public class ComodinValidator {

    private static boolean nombreValidado = false;
    private static boolean apellidoValidado = false;
    private static boolean telefonoValidado = false;
    private static boolean fechaValidada = false;
    private static boolean correoValidado;


    public static void validateNombre(Context context, CharSequence campo_nombre, TextInputLayout til_nombre, ImageView img_nombres) {
        String nombre = campo_nombre.toString().trim();

        if (nombre.isEmpty()) {
            markEmpty(context, til_nombre, img_nombres, "Nombre no puede quedar vacío.");
            img_nombres.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        } else if (nombre.length() < 3) {
//            markEmpty(context, til_nombre, img_nombres, "El nombre tiene al menos 3 letras.");
            markEmpty(context, til_nombre, img_nombres, "Mínimo 3 letras.");
            img_nombres.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        } else if (nombre.length() > til_nombre.getCounterMaxLength()) {
            markEmpty(context, til_nombre, img_nombres, "Número de caracteres no permitidos");
            img_nombres.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        } else {
            markValidate(til_nombre);
            //img_nombres.setColorFilter(ContextCompat.getColor(context, R.color.colorValidado));
            nombreValidado = true;
            if (apellidoValidado) {
                addDrawableValidado(context, img_nombres);
            }
        }

    }

    public static void validateApellido(Context context, CharSequence campo_apellido, TextInputLayout til_apellido, ImageView img_nombres) {
        String apellido = campo_apellido.toString().trim();
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
            markValidate(til_apellido);
            apellidoValidado = true;
            if (nombreValidado) {
                addDrawableValidado(context, img_nombres);
            }
        }
    }

    public static void validateCorreo(Context context, CharSequence campo_email, TextInputLayout til_correo, ImageView img_correo) {
        String correo = campo_email.toString().trim();
        correoValidado = !TextUtils.isEmpty(campo_email) && Patterns.EMAIL_ADDRESS.matcher(correo).matches();
        if (correoValidado) {
            markValidate(til_correo);
            addDrawableValidado(context, img_correo);
        } else {
            markEmpty(context, til_correo, img_correo, "No es un correo válido.");
        }

    }


    public static void validateTelefono(Context context, CharSequence campo_telefono, TextInputLayout til_telefono, ImageView img_telefono) {
        //String regexp = "\\d{1,2}/\\d{1,2}/\\d{4}";
        String patternRegexPhone = "9\\d{8}";
        String telefono = campo_telefono.toString().trim();
        boolean esVacio = !TextUtils.isEmpty(telefono);

        telefonoValidado = esVacio && Pattern.matches(patternRegexPhone, telefono);
        if (telefonoValidado) {
            markValidate(til_telefono);
            addDrawableValidado(context, img_telefono);
        } else {
            markEmpty(context, til_telefono, img_telefono, "No es correcto");
        }
    }

    public static void validateFechaNac(Context context, CharSequence campo_fecha_nac, TextInputLayout til_fecha_nac, ImageView img_fecha_nac) {
        String patternRegexFecha = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/(19|20)\\d\\d$";
        String fecha_nac = campo_fecha_nac.toString().trim();
        fechaValidada = !TextUtils.isEmpty(fecha_nac) && Pattern.matches(patternRegexFecha, fecha_nac) && ComodinDateValidator.isThisDateValid(fecha_nac);
        if (fechaValidada) {
            markValidate(til_fecha_nac);
            addDrawableValidado(context, img_fecha_nac);
        } else {
            markEmpty(context, til_fecha_nac, img_fecha_nac, "No es correcto");
        }
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
        campo.getEditText().setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check_circle_black_24dp, 0);
    }

    private static void addDrawableValidado(Context context, ImageView image) {
        image.setColorFilter(ContextCompat.getColor(context, R.color.colorValidado));
    }


}
