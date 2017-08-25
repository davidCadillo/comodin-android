package com.tusueldo.comodin.model.exceptions;

import java.io.IOException;


public class NoConnectivityInternetExeption extends IOException {

    @Override
    public String getMessage() {
        return "No connectivity exception";
    }
}
