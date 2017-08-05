package com.tusueldo.comodin.model.connections;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.tusueldo.comodin.R;
import com.tusueldo.comodin.model.connections.ruc.IRetrofitServiceRUC;
import com.tusueldo.comodin.model.connections.ruc.InformationRUC;
import com.tusueldo.comodin.model.connections.ruc.RequestRUC;
import com.tusueldo.comodin.model.connections.ruc.RetrofitAdapter;
import com.tusueldo.comodin.model.types.ComodinValues;
import com.tusueldo.comodin.ui.ComodinProgressDialog;
import com.tusueldo.comodin.utils.ComodinValidator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RetrofitController {


    public static void requestRUC(final TextInputLayout til_ruc, final TextInputLayout til_razon_social, CharSequence campo_ruc, final ImageView img_ruc) {
        IRetrofitServiceRUC service = new RetrofitAdapter().getAdapater(ComodinValues.BASE_URL_RUC).create(IRetrofitServiceRUC.class);
        RequestRUC requestRUC = new RequestRUC(ComodinValues.API_TOKEN_RUC, campo_ruc.toString());
        Call<InformationRUC> call = service.loadInfoRuc(requestRUC);
        ComodinProgressDialog.showProgressBar(til_ruc.getContext(), R.string.titulo_ruc_progressdialog, R.string.mensaje_ruc_progressdialog);
        call.enqueue(new Callback<InformationRUC>() {
            @Override
            public void onResponse(@NonNull Call<InformationRUC> call, @NonNull Response<InformationRUC> response) {
                try {
                    InformationRUC informationRUC = response.body();
                    if (informationRUC != null) {
                        if (informationRUC.isSuccess()) {
                            InformationRUC.Entity entity = informationRUC.getEntity();
                            ComodinValidator.setFieldValidate(til_ruc, "Correcto", true);
                            ComodinValidator.setIconValidate(til_ruc.getContext(), img_ruc, true);
                            til_razon_social.setVisibility(View.VISIBLE);
                            EditText textInputEditText = til_razon_social.getEditText();
                            if (textInputEditText != null) {
                                textInputEditText.setText(entity.getNombreORazonSocial());
                            }
                        }
                    } else {
                        ComodinValidator.markEmpty(til_ruc.getContext(), til_ruc, null, "RUC no encontrado", 11);
                        til_razon_social.setVisibility(View.GONE);

                    }
                    ComodinProgressDialog.finish();

                } catch (NullPointerException e) {
                    ComodinValidator.markEmpty(til_ruc.getContext(), til_ruc, null, "RUC no encontrado", 11);
                    til_razon_social.setVisibility(View.GONE);
                    ComodinProgressDialog.finish();
                }
            }

            @Override
            public void onFailure(@NonNull Call<InformationRUC> call, @NonNull Throwable t) {
                Snackbar.make(til_razon_social.getRootView(), "Ha ocurrido un error", Snackbar.LENGTH_SHORT).show();
                ComodinProgressDialog.finish();
            }
        });
    }


}
