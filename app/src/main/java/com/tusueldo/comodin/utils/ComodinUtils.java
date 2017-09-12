package com.tusueldo.comodin.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import com.tusueldo.comodin.R;
import com.tusueldo.comodin.model.types.ComodinValues;

/**
 * @author David Cadillo Blas
 */

public class ComodinUtils {


    /**
     * Este método devuelve un TextInputLayout a la normalidad, incluido el contador cuando pierde el foco.
     *
     * @param hasFocus        evalúa si el TextInputLayout tiene el foco
     * @param textInputLayout elemento a operar
     */
    public static void setHintFocusField(boolean hasFocus, TextInputLayout textInputLayout, ImageView imageView, String campo) {
        EditText editText = textInputLayout.getEditText();

        if (!hasFocus) {//si el cursos no está en el campo
            if (editText != null) {//comprobación de existencia del campo
                String texto = editText.getText().toString();//Se obtiene el texto que hay en el campo
                if (TextUtils.isEmpty(texto)) {//Si este es vacío se desactiva el error y el contador
                    textInputLayout.setErrorEnabled(false);
                    textInputLayout.setCounterEnabled(false);
                } else {
                    switch (campo) {
                        case ComodinValues.NOMBRE:
                            String[] nombres = texto.split(" ");
                            if (nombres.length == 1) {
                                ComodinValidator.nombreValidado = false;
                                ComodinUtils.setFieldInvalidateFull(textInputLayout, imageView, R.string.falta_apellido_validacion, 50);
                            }
                            break;

                        case ComodinValues.CORREO:
                            break;
                        case ComodinValues.PASSWORD:
                            break;
                        case ComodinValues.TELEFONO:
                            break;
                    }
                }
            }
        } else {

            if (textInputLayout.getEditText() != null && textInputLayout.getEditText().getText().toString().isEmpty()) {
                textInputLayout.setCounterEnabled(true);
                switch (campo) {
                    case ComodinValues.NOMBRE:
                        textInputLayout.setCounterEnabled(false);
                        break;
                    case ComodinValues.CORREO:
                        textInputLayout.setCounterMaxLength(40);
                        break;
                    case ComodinValues.PASSWORD:
                        textInputLayout.setCounterMaxLength(15);
                        break;
                    case ComodinValues.TELEFONO:
                        break;
                }
            }
        }
    }


    /**
     * Este método evalúa un TextInputLayout a partir del foco. Si no posee el foco se muestra un
     * texto diferente a que si lo tuviera el foco.
     *
     * @param hasFocus         indica si el TextInputLayout tiene el foco
     * @param textInputLayout  el elemento al cual se aplicará
     * @param textWithFocus    señala el texto a pasar cuando tiene el foco
     * @param textWithoutFocus señala el texto a pasar cuando no tiene el foco
     */
    public static void setHintFocusField(boolean hasFocus, TextInputLayout textInputLayout, String textWithFocus, String textWithoutFocus) {
        /*Este método ha sido utilizado para ayudar al usuario con la fecha de su nacimiento ya que le muestra el formato adecuado
        * antes de que lo escriba.*/
        textInputLayout.setHint(textWithFocus);
        if (!hasFocus) {
            if (textInputLayout.getEditText() != null)
                if (TextUtils.isEmpty(textInputLayout.getEditText().getText())) {
                    textInputLayout.setErrorEnabled(false);
                    textInputLayout.setHint(textWithoutFocus);

                } else {
                    String campo = textInputLayout.getEditText().getText().toString();
                    switch (textWithFocus) {

                        case ComodinValues.DAY:
                        case ComodinValues.MONTH:
                            if (campo.length() == 1) {
                                campo = "0".concat(campo);
                                textInputLayout.getEditText().setText(campo);
                            }
                            break;
                        case ComodinValues.YEAR:
                            if (campo.length() == 2) {
                                campo = "19".concat(campo);
                                textInputLayout.getEditText().setText(campo);

                            }
                    }
                }
        }
    }

    /**
     * Esta función pinta el icono del color primary dark base.
     *
     * @param imageView icono a pintar
     */
    public static void setColorIconPrimary(ImageView imageView) {
        imageView.setColorFilter(ContextCompat.getColor(imageView.getContext(), R.color.colorPrimaryDark));
    }


    /**
     * Esta función marca en verde un icono.
     *
     * @param imageView icono a pintar
     */
    public static void setColorIconValidate(ImageView imageView) {
        imageView.setColorFilter(ContextCompat.getColor(imageView.getContext(), R.color.colorValidado));

    }

    /**
     * Esta función establece el icono de los formularios en rojo o verde, de acuerdo a si sus campos
     * correspondientes están validados o no.
     *
     * @param imageView imagen a pintar
     * @param validate  indica si la validación es buena o mala para ver el color del imageview a pintar
     */
    static void setColorIconValidate(ImageView imageView, boolean validate) {
        if (validate)
            imageView.setColorFilter(ContextCompat.getColor(imageView.getContext(), R.color.colorValidado));
        else {
            imageView.setColorFilter(ContextCompat.getColor(imageView.getContext(), R.color.colorNoValidado));
        }
    }

    /**
     * Esta función marca en verde un TextInputLayout sin agregarle iconos y sin texto
     *
     * @param campo cuadro de texto a marcar
     */
    static void setFieldValidateSimple(TextInputLayout campo) {
        campo.setHintTextAppearance(R.style.Hint);
        campo.setErrorTextAppearance(R.style.Validado);
        campo.setCounterEnabled(false);
        campo.setErrorEnabled(true);
        campo.setError(" ");
    }

    /**
     * Esta función pone en verde a un TextInput layout, le añade un texto y además añade un pequeño icono verde a la derecha del campo
     * si es que se especifica.
     *
     * @param textInputLayout cuadro de texto a marcar
     * @param message         una descripcion informativa
     * @param checkImage      un booleando que comprueba si se deberá marcar el campo
     */
    public static void setFieldValidateWithMessageAndCheck(TextInputLayout textInputLayout, @StringRes int message, boolean checkImage) {
        textInputLayout.setHintTextAppearance(R.style.Hint);
        textInputLayout.setErrorTextAppearance(R.style.Validado);
        textInputLayout.setErrorEnabled(true);
        textInputLayout.setError(textInputLayout.getContext().getString(message));
        if (checkImage && textInputLayout.getEditText() != null)
            textInputLayout.getEditText().setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check_circle_black_24px, 0);
    }


    public static void setFieldValidateFull(TextInputLayout textInputLayout, @StringRes int message, ImageView imageView) {
        textInputLayout.setHintTextAppearance(R.style.Hint);
        textInputLayout.setErrorTextAppearance(R.style.Validado);
        textInputLayout.setErrorEnabled(true);
        //textInputLayout.setErrorEnabled(false);
        textInputLayout.setCounterEnabled(false);
        //        tintWidget(textInputLayout, Color.BLUE);
        textInputLayout.setError(textInputLayout.getContext().getString(message));
        if (textInputLayout.getEditText() != null)
            textInputLayout.getEditText().setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check_circle_black_24px, 0);
        setColorIconValidate(imageView);
    }


    /**
     * Esta función marca en rojo(error) un textinputlayout, además le quita el icono verde si es que el campo ha estado validado,
     * también marca en rojo el icono a los campos a los que representa. Se puede especificar un mensaje y el tamaño del contador.
     *
     * @param textInputLayout  el cuadro de texto en el que se efectúa la función
     * @param imageView        un icono
     * @param message          texto descriptivo informativo
     * @param counterMaxLength cantidad máxima de caracteres permitidos del cuadro de texto.
     */
    public static void setFieldInvalidateFull(TextInputLayout textInputLayout, ImageView imageView, @StringRes int message, int counterMaxLength) {
        textInputLayout.setCounterEnabled(true);
        textInputLayout.setCounterMaxLength(counterMaxLength);
        textInputLayout.setHintTextAppearance(R.style.Error);
        textInputLayout.setErrorTextAppearance(R.style.Error);
        textInputLayout.setError(textInputLayout.getContext().getString(message));
        if (textInputLayout.getEditText() != null)
            textInputLayout.getEditText().setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        if (imageView != null)
            setColorIconValidate(imageView, false);
    }


    static void setFieldInvalidateSimple(TextInputLayout textInputLayout) {
        textInputLayout.setHintTextAppearance(R.style.Error);
        textInputLayout.setErrorTextAppearance(R.style.Error);
        //textInputLayout.setError(textInputLayout.getContext().getString(R.string.espacio));
    }


    public static void setFieldNormal(TextInputLayout textInputLayout, ImageView imageView) {
        textInputLayout.setCounterEnabled(false);
        textInputLayout.setErrorEnabled(false);
        textInputLayout.setEnabled(true);
        textInputLayout.setHintTextAppearance(R.style.Normal);
        imageView.setColorFilter(ContextCompat.getColor(imageView.getContext(), R.color.colorPrimaryDark));
        if (textInputLayout.getEditText() != null)
            textInputLayout.getEditText().setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
    }


    public static void clearField(TextInputLayout textInputLayout, ImageView imageView) {
        if (textInputLayout != null && imageView != null) {
            textInputLayout.setCounterEnabled(false);
            textInputLayout.setErrorEnabled(false);
            textInputLayout.setHintTextAppearance(R.style.Normal);
            textInputLayout.setEnabled(true);
            setColorIconPrimary(imageView);
            if (textInputLayout.getEditText() != null)
                textInputLayout.getEditText().setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            if (textInputLayout.getEditText() != null) {
                textInputLayout.getEditText().setText(null);
            }
        }
    }


    public static String formatDistrito(String distrito, String provincia) {
        distrito = distrito.substring(0, 1).toUpperCase() + distrito.substring(1).toLowerCase();
        return distrito + " (" + provincia + ")";

    }

    public static boolean isThereInternetConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }


    @NonNull
    static String getTrim(CharSequence campo_fecha_nac) {
        return campo_fecha_nac.toString();
    }
}
