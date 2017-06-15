package com.tusueldo.comodin.utils;

import android.util.Patterns;

/**
 * Created by USUARIO on 13/06/2017.
 */

public class ComodinPattern {


    /*Expresión regular para validar el formato de la fecha de nacimiento con el formato dd/mm/yyyy teniendo en cuenta
    * los dígitos adecuados para mes, año y día.
    * */
    public static final String FECHA_NACIMIENTO = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/(19|20)\\d\\d$";

    /*Expresión regular usado para validar un celular con el formato 9XXXXXXXXX*/
    public static final String CELULAR = "9\\d{8}";

    /*Expresión regular para validar un email. Se usa el que viene con Android*/
    public static final String EMAIL = Patterns.EMAIL_ADDRESS.pattern();

    /*Expresión regular para el nombre en el que se aceptan todas las letras del abecedarios, las tildes de las letras,
    los espacios y el guion.
    El nombre como mínimo ha de ser de 3 letras y máximo de 30. Se permiten nombres compuestos
    * */

    public static final String NOMBRES = "([a-zA-ZáéíóúñÑ]+['\\s-]?){1,5}";

    /*Expresión regular usada para detectar al menos un dígito en una cadena*/
    public static final String TIENE_ALGUN_NUMERO = ".*\\d+.*";

}
