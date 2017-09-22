package com.tusueldo.comodin.utils;


import android.app.Activity;

import java.util.List;


public class ComodinUsuariosGuardados {

    private  ComodinSharedPreferences sharedPreferences;
    private static ComodinUsuariosGuardados comodinUsuariosGuardados = null;

    private ComodinUsuariosGuardados(Activity activity) {
        sharedPreferences = new ComodinSharedPreferences(activity, "savedUsers");
        sharedPreferences.write("contador", 0);
    }

    public static ComodinUsuariosGuardados getInstance(Activity activity) {
        if (comodinUsuariosGuardados == null) {
            comodinUsuariosGuardados = new ComodinUsuariosGuardados(activity);
        }
        return comodinUsuariosGuardados;
    }


    public  List<String> getUsers() {
        return sharedPreferences.getAllValuesWhitoutRepeated();
    }


    public  void rememberEmail(String email) {
        int contador = sharedPreferences.read("contador", 0);
        contador++;
        sharedPreferences.write("user" + contador, email);
        sharedPreferences.write("contador", contador);
    }

}
