package com.tusueldo.comodin.utils;

import android.util.Patterns;


public class ComodinPatterns {

    /*Expresión regular usado para validar un celular con el formato 9XXXXXXXXX*/
    public static final String MOBILE_PHONE = "9\\d{8}";

    /*Expresión regular para validar un email. Se usa el que viene con Android*/
    public static final String EMAIL = Patterns.EMAIL_ADDRESS.pattern();

    /*Expresión regular para el nombre en el que se aceptan todas las letras del abecedarios, las tildes de las letras,
    los espacios y el guion.
    El nombre como mínimo ha de ser de 3 letras y máximo de 30. Se permiten nombres compuestos
    * */

    static final String NAMES = "([a-zA-ZáéíóúñÑ]+['\\s-]?){1,8}";

    /*Expresión regular usada para detectar al menos un dígito en una cadena*/
    static final String HAS_SOME_NUMBER = ".*\\d+.*";

    static final String DATE_DAY = "^0?[1-9]|[12][0-9]|3[01]$";
    static final String DATE_MONTH = "^0?[1-9]|1[012]$";
    static final String DATE_YEAR = "^(19)?[4-9]\\d|200[0-3]$";




}
