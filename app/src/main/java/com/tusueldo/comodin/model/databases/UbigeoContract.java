package com.tusueldo.comodin.model.databases;

import android.provider.BaseColumns;


final class UbigeoContract {

    private UbigeoContract() {

    }

    static abstract class UbigeoEntry implements BaseColumns {

        static final String TABLE_NAME = "ubigeos";
        static final String _ID = "_id";
        static final String COD_UBIGEO = "cod_ubigeo";
        static final String DEPARTAMENTO = "departamento";
        static final String PROVINCIA = "provincia";
        static final String DISTRITO = "distrito";

    }
}
