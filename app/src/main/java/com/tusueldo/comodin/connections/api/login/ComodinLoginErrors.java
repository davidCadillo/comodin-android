package com.tusueldo.comodin.connections.api.login;


import com.tusueldo.comodin.R;

public class ComodinLoginErrors {

    private static ComodinLoginResponse response;


    private ComodinLoginErrors() {

    }

    public static void from(ComodinLoginResponse respuesta) {
        response = respuesta;
        showMessageError();
    }


    public static int showMessageError() {

        int mensajeError = 0;
        if (response.getCode() == 422) {

            switch (response.getMessage()) {
                case ComodinLoginValueErrors.ACCOOUNT_INCORRECT:
                    mensajeError = R.string.password_incorrecto;
                    break;
                case ComodinLoginValueErrors.ACCOUNT_DISABLED:
                    mensajeError = R.string.account_disabled;
                    break;
                case ComodinLoginValueErrors.ACCOUNT_NO_ACCOUNT:
                    mensajeError = R.string.account_no_account;
                    break;
                case ComodinLoginValueErrors.ACCOUNT_SUSPENDED:
                    mensajeError = R.string.account_suspendend;
                    break;
            }
        }

        return mensajeError;
    }


}
