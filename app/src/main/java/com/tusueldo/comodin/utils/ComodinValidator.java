package com.tusueldo.comodin.utils;

import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.tusueldo.comodin.R;
import com.tusueldo.comodin.controller.RucRetrofitController;
import com.tusueldo.comodin.model.types.TypeFieldDate;
import com.tusueldo.comodin.model.types.TypeUserLogin;
import com.tusueldo.comodin.ui.ComodinAlertDialog;
import com.tusueldo.comodin.ui.adapters.EventPositiveRuc;

import java.util.regex.Pattern;


public class ComodinValidator {

    private static String campoDia = "";
    private static String campoMes = "";
    private static String campoAnio = "";
    public static String campoFechaCompleta;

    private static boolean fechaDiaValidada = false;
    private static boolean fechaMesValidada = false;
    private static boolean fechaAnioValidada = false;
    private static boolean camposFechaValidados = false;

    public static TextInputLayout ti_dia = null;
    public static TextInputLayout ti_mes = null;
    public static TextInputLayout ti_anio = null;
    public static TextInputLayout ti_telefono = null;

    public static boolean validacionCompleta = false;
    public static String ubigeo;
    public static String direccion;

    static boolean nombreValidado = false;
    static boolean apellidoValidado = false;
    public static boolean celularValidado = false;
    public static boolean correoValidado = false;
    private static boolean fechaNacimientoValidada = false;
    private static boolean passwordValidado = false;
    public static boolean rucvalidado = false;
    public static boolean distritoValidado = false;
    public static boolean genero_validado = false;
    public static boolean ruc_validate_server = true;
    private static boolean razon_social_validada = false;
    private static boolean direccion_validada = false;


    public static void validateRuc(TypeUserLogin typeUserLogin, final CharSequence campo_ruc, final TextInputLayout til_ruc, final TextInputLayout textInputLayout, final ImageView img_ruc,
                                   final ImageView img, final TextInputLayout til_direccion, final ImageView img_direccion, final TextInputLayout til_distrito, final ImageView img_distrito,
                                   LinearLayout layout_nombrecomercial, Button btn_registro) {
        int tamanioRuc = campo_ruc.length();
        boolean alertShowed = false;
        if (tamanioRuc == 0) {//Si el campo RUC está vacío se limpian los campos relacionados si tuviese datos.
            ComodinUtils.setFieldNormal(til_ruc, img_ruc);
            ComodinUtils.clearField(textInputLayout, img);
            ComodinUtils.clearField(til_direccion, img_direccion);
            ComodinUtils.clearField(til_distrito, img_distrito);
            layout_nombrecomercial.setVisibility(View.GONE);
            rucvalidado = false;
        } else if (tamanioRuc >= 1 && tamanioRuc < 11) {//Se habilita el contador a partir de 1

            ComodinUtils.setFieldInvalidateFull(til_ruc, img_ruc, R.string.espacio, 11);
            rucvalidado = false;
        } else if (tamanioRuc > 11) {
            rucvalidado = false;
            til_ruc.setCounterEnabled(false);
        } else if (tamanioRuc == 11) {
            switch (typeUserLogin) {
                case INDEPENDIENTE:
                    if (String.valueOf(campo_ruc.toString().charAt(0)).equals("2")) {
                        ComodinAlertDialog.showDialogRuc(til_ruc.getContext(), R.string.mensaje_ruc_alertdialog_independiente, new EventPositiveRuc(til_ruc.getContext(), typeUserLogin));
                        alertShowed = true;
                    }
                    break;
                case EMPRESA:
                    if (campo_ruc.toString().startsWith("1")) {
                        ComodinAlertDialog.showDialogRuc(til_ruc.getContext(), R.string.mensaje_ruc_alertdialog_empresa, new EventPositiveRuc(til_ruc.getContext(), typeUserLogin));
                        alertShowed = true;
                    }
                    break;
                default:
                    alertShowed = true;
                    break;
            }
            if (!alertShowed)
                RucRetrofitController.requestRUC(typeUserLogin, til_ruc, textInputLayout, campo_ruc, img_ruc, img, til_direccion, img_direccion, til_distrito, img_distrito, layout_nombrecomercial);
            if (rucvalidado) {
                Log.d("RUC HOY: ", "El ruc se ha validado");
            }
        } else {
            rucvalidado = false;
            ComodinUtils.setFieldInvalidateFull(til_ruc, img_ruc, R.string.espacio, 11);
        }
        checkValidation(btn_registro, typeUserLogin);

    }

    public static void validateNombre(TypeUserLogin typeUserLogin, CharSequence campo_nombre, TextInputLayout til_nombre, ImageView img_nombres, Button btn_registro) {

        String nombre = ComodinUtils.getTrim(campo_nombre).toLowerCase();
        boolean validarNombre = Pattern.matches(ComodinPatterns.NAMES, nombre);
        if (validarNombre) {
            if (nombre.length() >= 3) {
                nombreValidado = true;
                ComodinUtils.setFieldValidateWithMessageAndCheck(til_nombre, R.string.correcto_validacion, true);
                if (apellidoValidado)
                    ComodinUtils.setColorIconValidate(img_nombres);
            }
        } else if (nombre.isEmpty()) {
            ComodinUtils.setFieldNormal(til_nombre, img_nombres);
            nombreValidado = false;

        } else if (Pattern.matches(ComodinPatterns.HAS_SOME_NUMBER, nombre)) {
            ComodinUtils.setFieldInvalidateFull(til_nombre, img_nombres, R.string.no_numeros_validacion, 30);
            nombreValidado = false;
        } else {
            ComodinUtils.setFieldInvalidateFull(til_nombre, img_nombres, R.string.no_permitido_validacion, 30);
            nombreValidado = false;
        }
        checkValidation(btn_registro, typeUserLogin);
        Log.d("Nombres", String.valueOf(nombreValidado));
    }


    public static void validateApellido(TypeUserLogin typeUserLogin, CharSequence campo_apellido, TextInputLayout til_apellido, ImageView img_nombres, Button btn_registro) {
        String apellido = ComodinUtils.getTrim(campo_apellido).toLowerCase();
        boolean validarApellido = Pattern.matches(ComodinPatterns.NAMES, apellido);
        if (validarApellido) {
            if (apellido.length() >= 3) {
                apellidoValidado = true;
                ComodinUtils.setFieldValidateWithMessageAndCheck(til_apellido, R.string.correcto_validacion, true);
                if (nombreValidado)
                    ComodinUtils.setColorIconValidate(img_nombres);
            }

        } else if (apellido.isEmpty()) {
            ComodinUtils.setFieldNormal(til_apellido, img_nombres);
            apellidoValidado = false;

        } else if (Pattern.matches(ComodinPatterns.HAS_SOME_NUMBER, apellido)) {
            ComodinUtils.setFieldInvalidateFull(til_apellido, img_nombres, R.string.no_numeros_validacion, 30);
            apellidoValidado = false;
        } else {
            ComodinUtils.setFieldInvalidateFull(til_apellido, img_nombres, R.string.no_permitido_validacion, 30);
            apellidoValidado = false;
        }
        checkValidation(btn_registro, typeUserLogin);
        Log.d("Apellidos", String.valueOf(apellidoValidado));
    }

    public static void validateCorreo(TypeUserLogin typeUserLogin, CharSequence campo_email, TextInputLayout til_correo, ImageView img_correo, Button btn_registro) {
        String correo = ComodinUtils.getTrim(campo_email.toString()).toLowerCase();
        correoValidado = !TextUtils.isEmpty(campo_email) && Pattern.matches(ComodinPatterns.EMAIL, correo);
        if (correoValidado) {
            ComodinUtils.setFieldValidateFull(til_correo, R.string.correcto_validacion, img_correo);
        } else if (correo.isEmpty()) {
            ComodinUtils.setFieldNormal(til_correo, img_correo);
        } else {
            ComodinUtils.setFieldInvalidateFull(til_correo, img_correo, R.string.correo_no_valido_validacion, 40);
        }
        checkValidation(btn_registro, typeUserLogin);
    }


    public static void validateTelefono(TypeUserLogin typeUserLogin, CharSequence campo_telefono, TextInputLayout til_telefono, ImageView
            img_telefono, Button btn_registro) {
        String telefono = ComodinUtils.getTrim(campo_telefono);
        boolean esVacio = !TextUtils.isEmpty(telefono);
        celularValidado = esVacio && Pattern.matches(ComodinPatterns.MOBILE_PHONE, telefono);
        if (celularValidado) {
            ComodinUtils.setFieldValidateFull(til_telefono, R.string.correcto_validacion, img_telefono);
        } else if (telefono.isEmpty()) {
            ComodinUtils.setFieldNormal(til_telefono, img_telefono);
        } else {
            ComodinUtils.setFieldInvalidateFull(til_telefono, img_telefono, R.string.espacio, 9);
        }
        checkValidation(btn_registro, typeUserLogin);
    }

    public static void validateFechaNacimiento(TypeUserLogin typeUserLogin, TypeFieldDate fieldToValidate, TextInputLayout
            til_field_date, ImageView imageView, Button btn_registro) {
        String field_date;
        if (til_field_date.getEditText() != null) {
            field_date = ComodinUtils.getTrim(til_field_date.getEditText().getText());
            if (field_date.isEmpty()) {
                ComodinUtils.setFieldNormal(til_field_date, imageView);
            } else {

                boolean campoValidado = false;
                //Se evalua el tiempo de campo a validar
                switch (fieldToValidate.ordinal()) {
                    case 0://Se va a validar el dia
                        //Se valida el día según una expresión regular
                        fechaDiaValidada = !TextUtils.isEmpty(field_date) && Pattern.matches(ComodinPatterns.DATE_DAY, field_date);
                        campoValidado = fechaDiaValidada;
                        //Se guarda el dia introducido
                        campoDia = field_date;
                        //Cuando es correcto el día, pero aún faltan datos en la fecha o no es correcta, entonces se da el foco al mes.
                        if (campoValidado && field_date.length() == 2 && !fechaNacimientoValidada && ti_mes.getEditText() != null)
                            ti_mes.getEditText().requestFocus();

                        break;
                    case 1:
                        fechaMesValidada = !TextUtils.isEmpty(field_date) && Pattern.matches(ComodinPatterns.DATE_MONTH, field_date);
                        campoValidado = fechaMesValidada;
                        campoMes = field_date;
                        if (campoValidado && field_date.length() == 2 && !fechaNacimientoValidada && ti_anio.getEditText() != null)
                            ti_anio.getEditText().requestFocus();
                        break;
                    case 2:
                        fechaAnioValidada = !TextUtils.isEmpty(field_date) && Pattern.matches(ComodinPatterns.DATE_YEAR, field_date);
                        campoValidado = fechaAnioValidada;
                        campoAnio = field_date;
                        break;
                }
                if (campoValidado) {
                    camposFechaValidados = fechaDiaValidada && fechaMesValidada && fechaAnioValidada;
                    ComodinUtils.setFieldValidateSimple(til_field_date);
                    if (camposFechaValidados) {
                        if (campoDia.length() == 1) {
                            campoDia = "0".concat(campoDia);
                        }
                        if (campoMes.length() == 1) {
                            campoMes = "0".concat(campoMes);
                        }
                        if (campoAnio.length() == 2) {
                            campoAnio = "19".concat(campoAnio);
                        }
                        Log.d("Dia: ", campoDia);
                        Log.d("Mes: ", campoMes);
                        Log.d("Año: ", campoAnio);
                        campoFechaCompleta = campoDia.concat("/").concat(campoMes).concat("/").concat(campoAnio);
                        fechaNacimientoValidada = ComodinDateValidator.isThisDateValid(campoFechaCompleta);
                        Log.d("Fecha actual: ", campoFechaCompleta);
                        if (!fechaNacimientoValidada) {
                            ComodinUtils.setFieldInvalidateSimple(ti_dia);
                            ComodinUtils.setFieldInvalidateSimple(ti_mes);
                            ComodinUtils.setFieldInvalidateSimple(ti_anio);
                            ComodinUtils.setColorIconValidate(imageView);
                        } else {
                            ComodinUtils.setFieldValidateSimple(ti_dia);
                            ComodinUtils.setFieldValidateSimple(ti_mes);
                            ComodinUtils.setFieldValidateSimple(ti_anio);
                            ComodinUtils.setColorIconValidate(imageView);
                            Log.d("Fecha a persistir:", campoFechaCompleta);
                        }
                    }
                } else {
                    fechaNacimientoValidada = false;
                    ComodinUtils.setFieldInvalidateSimple(til_field_date);
                    ComodinUtils.setColorIconValidate(imageView, false);
                }
                til_field_date.setError(" ");
            }
        }
        checkValidation(btn_registro, typeUserLogin);
        Log.d("Fecha", String.valueOf(fechaNacimientoValidada));
        //Log.d("Fecha actual", campoFechaCompleta);
    }

    public static void validatePassword(TypeUserLogin typeUserLogin, CharSequence campo_password, TextInputLayout til_password, ImageView
            img_password, Button btn_registro) {

//        Log.d("RUC Accedido: ", String.valueOf(campoRucAccedido));
        int tamanioPassword = campo_password.length();

        if (ComodinUtils.getTrim(campo_password).isEmpty()) {
            ComodinUtils.setFieldNormal(til_password, img_password);
            passwordValidado = false;
            til_password.setPasswordVisibilityToggleTintList(ContextCompat.getColorStateList(til_password.getContext(), R.color.colorPrimaryDark));
        } else if (tamanioPassword < 8) {
            ComodinUtils.setFieldInvalidateFull(til_password, img_password, R.string.password_validacion, 15);
            passwordValidado = false;
            til_password.setPasswordVisibilityToggleTintList(ContextCompat.getColorStateList(til_password.getContext(), R.color.colorPrimaryDark));
        } else if (tamanioPassword >= 8 && tamanioPassword <= 15) {
            ComodinUtils.setFieldValidateFull(til_password, R.string.correcto_validacion, img_password);
            til_password.setPasswordVisibilityToggleTintList(ContextCompat.getColorStateList(til_password.getContext(), R.color.colorValidado));
            passwordValidado = true;
        } else if (tamanioPassword > 15) {
            ComodinUtils.setFieldInvalidateFull(til_password, img_password, R.string.no_permitido_validacion, 15);
            passwordValidado = false;
            til_password.setPasswordVisibilityToggleTintList(ContextCompat.getColorStateList(til_password.getContext(), R.color.colorPrimaryDark));
        }
        checkValidation(btn_registro, typeUserLogin);

    }


    public static void validateDistrito(TypeUserLogin typeUserLogin, CharSequence distrito, TextInputLayout til_distrito, ImageView img_distrito, Button btn_registro) {
        if (distrito.toString().isEmpty()) {
            ComodinUtils.setFieldNormal(til_distrito, img_distrito);
            til_distrito.setCounterEnabled(false);
            distritoValidado = false;
        }
        checkValidation(btn_registro, typeUserLogin);
    }

    public static void validateRazonSocial(TypeUserLogin typeUserLogin, CharSequence charSequence, TextInputLayout textInputLayout, ImageView imageView, Button btn_registro) {
        if (textInputLayout.isEnabled()) {
            if (charSequence.length() >= 8) {
                ComodinUtils.setFieldValidateFull(textInputLayout, R.string.correcto_validacion, imageView);
                razon_social_validada = true;

            } else if (charSequence.toString().isEmpty()) {
                ComodinUtils.setFieldNormal(textInputLayout, imageView);
                razon_social_validada = false;
            } else {
                ComodinUtils.setFieldInvalidateFull(textInputLayout, imageView, R.string.espacio, 50);
                razon_social_validada = false;
            }
        }
        checkValidation(btn_registro, typeUserLogin);
    }

    public static void validateDireccion(TypeUserLogin typeUserLogin, CharSequence charSequence, TextInputLayout textInputLayout, ImageView imageView, Button btn_registro) {
        if (textInputLayout.isEnabled()) {
            if (charSequence.length() >= 15) {
                ComodinUtils.setFieldValidateFull(textInputLayout, R.string.correcto_validacion, imageView);
                direccion_validada = true;
            } else if (charSequence.toString().isEmpty()) {
                ComodinUtils.setFieldNormal(textInputLayout, imageView);
                direccion_validada = false;
            } else {
                ComodinUtils.setFieldInvalidateFull(textInputLayout, imageView, R.string.espacio, 50);
                direccion_validada = false;
            }
        }
        checkValidation(btn_registro, typeUserLogin);
    }

    public static void validateNombreComercial(TypeUserLogin typeUserLogin, CharSequence charSequence, TextInputLayout textInputLayout, ImageView imageView) {
        if (textInputLayout.isEnabled()) {
            if (charSequence.length() >= 3) {
                ComodinUtils.setFieldValidateFull(textInputLayout, R.string.correcto_validacion, imageView);
            } else if (charSequence.toString().isEmpty()) {
                ComodinUtils.setFieldNormal(textInputLayout, imageView);
            } else {
                ComodinUtils.setFieldInvalidateFull(textInputLayout, imageView, R.string.espacio, 20);
            }
        }

    }

    public static void checkValidation(Button button, TypeUserLogin typeUserLogin) {

        boolean validacion = false;
        if (typeUserLogin == TypeUserLogin.INDEPENDIENTE) {
            validacion = nombreValidado && apellidoValidado && correoValidado && fechaNacimientoValidada
                    && distritoValidado && genero_validado && celularValidado && passwordValidado;
        } else if (typeUserLogin == TypeUserLogin.EMPRESA) {
            if (ruc_validate_server) {
                validacion = rucvalidado && correoValidado && celularValidado && passwordValidado;
            } else {
                validacion = rucvalidado && correoValidado && celularValidado && passwordValidado && distritoValidado
                        && direccion_validada && razon_social_validada;
            }
        }
        if (validacion) {
            button.setEnabled(true);
            button.setBackground(ContextCompat.getDrawable(button.getContext(), R.drawable.selector_button));
            validacionCompleta = true;
        } else {
            button.setEnabled(false);
            button.setBackgroundColor(ContextCompat.getColor(button.getContext(), android.R.color.darker_gray));
            validacionCompleta = false;
        }

    }
}
