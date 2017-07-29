package com.tusueldo.comodin.utils;

import android.util.Patterns;

/**
 * Created by USUARIO on 13/06/2017.
 */

public class ComodinPatterns {

    /*Expresión regular usado para validar un celular con el formato 9XXXXXXXXX*/
    public static final String MOBILE_PHONE = "9\\d{8}";

    /*Expresión regular para validar un email. Se usa el que viene con Android*/
    public static final String EMAIL = Patterns.EMAIL_ADDRESS.pattern();

    /*Expresión regular para el nombre en el que se aceptan todas las letras del abecedarios, las tildes de las letras,
    los espacios y el guion.
    El nombre como mínimo ha de ser de 3 letras y máximo de 30. Se permiten nombres compuestos
    * */

    public static final String NAMES = "([a-zA-ZáéíóúñÑ]+['\\s-]?){1,5}";

    /*Expresión regular usada para detectar al menos un dígito en una cadena*/
    public static final String HAS_SOME_NUMBER = ".*\\d+.*";

    public static final String DATE_DAY = "^0[1-9]|[12][0-9]|3[01]$";
    public static final String DATE_MONTH = "^0[1-9]|1[012]$";
    public static final String DATE_YEAR = "^(19[4-9]\\d|200[0-3])$";
    public static final String SINGLE_RUC = "10\\d{9}";
    public static final String BUSINESS_RUC = "20\\d{9}";

}
