package com.tusueldo.comodin.controller;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.afollestad.materialdialogs.MaterialDialog;
import com.tusueldo.comodin.R;
import com.tusueldo.comodin.connections.ruc.IRetrofitServiceRUC;
import com.tusueldo.comodin.connections.ruc.InformationRUC;
import com.tusueldo.comodin.connections.ruc.RequestRUC;
import com.tusueldo.comodin.connections.ruc.RetrofitAdapter;
import com.tusueldo.comodin.model.exceptions.NoConnectivityInternetExeption;
import com.tusueldo.comodin.model.types.ComodinValues;
import com.tusueldo.comodin.model.types.TypeUserLogin;
import com.tusueldo.comodin.ui.ComodinAlertDialog;
import com.tusueldo.comodin.ui.ComodinProgressDialog;
import com.tusueldo.comodin.utils.ComodinUtils;
import com.tusueldo.comodin.utils.ComodinValidator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RucRetrofitController {

    public static void requestRUC(final TypeUserLogin typeUserLogin, final TextInputLayout til_ruc, final TextInputLayout textInputLayout, CharSequence campo_ruc, final ImageView img_ruc, final ImageView img,
                                  final TextInputLayout til_direccion, final ImageView img_direccion, final TextInputLayout til_distrito, final ImageView img_distrito,
                                  final LinearLayout layout_nombreComercial) {
        IRetrofitServiceRUC service = new RetrofitAdapter().getAdapater(ComodinValues.BASE_URL_RUC, til_ruc.getContext()).create(IRetrofitServiceRUC.class);
        RequestRUC requestRUC = new RequestRUC(ComodinValues.API_TOKEN_RUC, campo_ruc.toString());
        Call<InformationRUC> call = service.loadInfoRuc(requestRUC);
        final MaterialDialog materialDialog = ComodinProgressDialog.showProgressBar(til_ruc.getContext(), R.string.titulo_ruc_progressdialog, R.string.mensaje_ruc_progressdialog);
        call.enqueue(new Callback<InformationRUC>() {
            @Override
            public void onResponse(@NonNull Call<InformationRUC> call, @NonNull Response<InformationRUC> response) {
                try {
                    if (response.isSuccessful()) {
                        InformationRUC informationRUC = response.body();
                        if (informationRUC != null && informationRUC.isSuccess()) {
                            InformationRUC.Entity entity = informationRUC.getEntity();
                            ComodinUtils.setFieldValidateFull(til_ruc, R.string.correcto_validacion, img_ruc);
                            EditText textInputEditText = textInputLayout.getEditText();
                            ComodinValidator.rucvalidado = true;
                            if (textInputEditText != null) {
                                textInputEditText.setText(ComodinUtils.toUpperWord(entity.getNombreORazonSocial()));
                                textInputEditText.setEnabled(false);
                                ComodinUtils.setFieldValidateFull(textInputLayout, R.string.correcto_validacion, img);
                            }
                            if (til_direccion != null && til_direccion.getEditText() != null) {
                                til_direccion.getEditText().setText(entity.getDireccion());
                                til_direccion.getEditText().setEnabled(false);
                                ComodinUtils.setColorIconValidate(img_direccion);
                            }
                            if (til_distrito.getEditText() != null && img_distrito != null) {
                                til_distrito.getEditText().setText(ComodinUtils.formatDistrito(entity.getDistrito(), entity.getProvincia()));
                                til_distrito.setEnabled(false);
                                ComodinUtils.setFieldValidateFull(til_distrito, R.string.correcto_validacion, img_distrito);
                                ComodinValidator.distritoValidado = true;
                                ComodinValidator.ubigeo = entity.getUbigeo();
                                ComodinValidator.direccion = entity.getDireccion();

                            }
//                            ComodinValidator.nombreValidado = true;
                            if (layout_nombreComercial != null) {
                                layout_nombreComercial.setVisibility(View.VISIBLE);
                            }

                            ComodinValidator.ruc_validate_server = true;
                        }
                    } else {
                        ComodinUtils.setFieldInvalidateFull(til_ruc, img_ruc, R.string.ruc_no_encontrado_validacion, 11);
                        ComodinUtils.clearField(textInputLayout, img);
                        ComodinUtils.clearField(til_direccion, img_direccion);
                        ComodinUtils.clearField(til_distrito, img_distrito);
                        layout_nombreComercial.setVisibility(View.GONE);
                        ComodinProgressDialog.finish(materialDialog);
                        ComodinValidator.rucvalidado = false;

                    }
                    ComodinProgressDialog.finish(materialDialog);

                } catch (Exception e) {
                    ComodinUtils.setFieldInvalidateFull(til_ruc, null, R.string.ruc_exception_validacion, 11);
                    ComodinUtils.setFieldNormal(textInputLayout, img);
                    if (textInputLayout.getEditText() != null) {
                        textInputLayout.getEditText().setText(null);
                    }
                    ComodinProgressDialog.finish(materialDialog);
                    ComodinValidator.rucvalidado = false;
                }
            }

            @Override
            public void onFailure(@NonNull Call<InformationRUC> call, @NonNull Throwable t) {

                if (t instanceof NoConnectivityInternetExeption) {
                    Snackbar snackbar = Snackbar.make(textInputLayout.getRootView(), R.string.sin_internet, Snackbar.LENGTH_SHORT);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(ContextCompat.getColor(snackbarView.getContext(), R.color.colorPrimaryDark));
                    ComodinValidator.rucvalidado = false;
                    snackbar.show();
                } else {
                    ComodinAlertDialog.showDialogMaterialInformative(textInputLayout.getContext(), R.string.titulo_ruc_progressdialog, R.string.mensaje_ruc_servidor, android.R.string.ok);
                    ComodinUtils.clearField(textInputLayout, img);
                    ComodinUtils.clearField(til_direccion, img_direccion);
                    ComodinUtils.clearField(til_distrito, img_distrito);
                    ComodinValidator.ruc_validate_server = false;
                    ComodinValidator.rucvalidado = true;
                }

                ComodinProgressDialog.finish(materialDialog);


            }
        });
    }


}
