package com.tusueldo.comodin.connections;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.ImageView;
import com.tusueldo.comodin.connections.ruc.IRetrofitServiceRUC;
import com.tusueldo.comodin.connections.ruc.InformationRUC;
import com.tusueldo.comodin.connections.ruc.RequestRUC;
import com.tusueldo.comodin.connections.ruc.RetrofitAdapter;
import com.tusueldo.comodin.utils.ComodinValidator;
import com.tusueldo.comodin.utils.ComodinValues;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by USUARIO on 03/08/2017.
 */

public class RetrofitController {


    public static void requestRUC(final TextInputLayout til_ruc, final TextInputLayout til_razon_social, CharSequence campo_ruc, final ImageView img_ruc) {
        IRetrofitServiceRUC service = new RetrofitAdapter().getAdapater(ComodinValues.BASE_URL_RUC).create(IRetrofitServiceRUC.class);
        RequestRUC requestRUC = new RequestRUC(ComodinValues.API_TOKEN_RUC, campo_ruc.toString());
        Call<InformationRUC> call = service.loadInfoRuc(requestRUC);
        call.enqueue(new Callback<InformationRUC>() {
            @Override
            public void onResponse(Call<InformationRUC> call, Response<InformationRUC> response) {
                try {

                    InformationRUC informationRUC = response.body();
                    InformationRUC.Entity entity = informationRUC.getEntity();
                    if (informationRUC.isSuccess()) {
                        ComodinValidator.setFieldValidate(til_ruc, "Correcto", true);
                        ComodinValidator.setIconValidate(til_ruc.getContext(), img_ruc, true);
                        til_razon_social.setVisibility(View.VISIBLE);
                        til_razon_social.getEditText().setText(entity.getNombreORazonSocial());
                    }

                } catch (NullPointerException e) {
                    ComodinValidator.markEmpty(til_ruc.getContext(), til_ruc, null, "RUC no encontrado", 11);
                    til_razon_social.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<InformationRUC> call, Throwable t) {
                Snackbar.make(til_razon_social.getRootView(), "Ha ocurrido un error", Snackbar.LENGTH_SHORT).show();
                til_razon_social.setVisibility(View.GONE);
            }
        });
    }


}
