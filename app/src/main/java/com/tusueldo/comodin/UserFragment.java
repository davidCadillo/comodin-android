package com.tusueldo.comodin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import butterknife.*;
import com.tusueldo.comodin.connections.api.login.ComodinLoginRequest;
import com.tusueldo.comodin.connections.api.login.ComodinTypeDateLogin;
import com.tusueldo.comodin.model.*;
import com.tusueldo.comodin.connections.api.IRetrofitServiceApi;
import com.tusueldo.comodin.connections.api.RetrofitAdapter;
import com.tusueldo.comodin.model.databases.DatabaseUbigeosHelper;
import com.tusueldo.comodin.model.types.TypeUserLogin;
import com.tusueldo.comodin.ui.ComodinAlertDialog;
import com.tusueldo.comodin.ui.ComodinProgressDialog;
import com.tusueldo.comodin.connections.api.register.ComodinRegisterErrors;
import com.tusueldo.comodin.utils.ComodinUtils;
import com.tusueldo.comodin.utils.ComodinValidator;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;


public abstract class UserFragment extends Fragment {

    @BindView(R.id.til_ruc) TextInputLayout til_ruc;
    @BindView(R.id.til_correo) TextInputLayout til_correo;
    @BindView(R.id.til_telefono) TextInputLayout til_celular;
    @BindView(R.id.til_distrito) TextInputLayout til_distrito;
    @BindView(R.id.til_password) TextInputLayout til_password;

    @BindView(R.id.campo_ruc) TextInputEditText campo_ruc;
    @BindView(R.id.campo_password) TextInputEditText campo_password;
    @BindView(R.id.campo_telefono) TextInputEditText campo_celular;
    @BindView(R.id.campo_correo) TextInputEditText campo_correo;
    @BindView(R.id.campo_distrito) AutoCompleteTextView campo_distrito;

    @BindView(R.id.chk_acepta_terminos) AppCompatCheckBox chkAceptaTerminos;
    @BindView(R.id.chk_enviar_correo) AppCompatCheckBox chkEnviarCorreo;

    @BindView(R.id.img_correo) ImageView img_correo;
    @BindView(R.id.img_telefono) ImageView img_celular;
    @BindView(R.id.img_password) ImageView img_password;
    @BindView(R.id.img_ruc) ImageView img_ruc;
    @BindView(R.id.img_distrito) ImageView img_distrito;

    @BindView(R.id.btn_registro) Button button_registro;
    public static List<String> distritos;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadDatabase(getTypeUser());

    }

    public boolean isNews() {
        return chkEnviarCorreo.isChecked();
    }

    public abstract int getLayoutId();

    public abstract TypeUserLogin getTypeUser();

    public void loadDatabase(final TypeUserLogin typeUserLogin) {
        DatabaseUbigeosHelper ubigeosHelper = new DatabaseUbigeosHelper(getActivity());
        List<Ubigeo> ubigeoList = ubigeosHelper.getAll();
        distritos = ubigeosHelper.getDistritos();
        ArrayAdapter<Ubigeo> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, ubigeoList);
        campo_distrito.setThreshold(3);
        campo_distrito.setAdapter(adapter);
        campo_distrito.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Ubigeo distritoSeleccionado = (Ubigeo) adapterView.getAdapter().getItem(i);
                ComodinValidator.ubigeo = distritoSeleccionado.getCod_ubigeo();
                ComodinUtils.setFieldValidateFull(til_distrito, R.string.correcto_validacion, img_distrito);
                ComodinValidator.distritoValidado = true;
                ComodinValidator.checkValidation(button_registro, typeUserLogin);
            }
        });
    }


    @OnClick(R.id.chk_acepta_terminos)
    public void onClick() {
        Intent i = new Intent(getActivity(), CondicionesActivity.class);
        startActivity(i);
        chkAceptaTerminos.setChecked(true);
    }


    @OnTextChanged(R.id.campo_correo)
    protected void onTextChangedEmail(CharSequence email) {
        ComodinValidator.validateCorreo(getTypeUser(), email, til_correo, img_correo, button_registro);
    }

    @OnTextChanged(R.id.campo_telefono)
    protected void onTextChangedTelefono(CharSequence telefono) {
        ComodinValidator.validateTelefono(getTypeUser(), telefono, til_celular, img_celular, button_registro);
    }

    @OnTextChanged(R.id.campo_password)
    protected void onTextChangedPassword(CharSequence password) {
        ComodinValidator.validatePassword(getTypeUser(), password, til_password, img_password, button_registro);
    }

    @OnTextChanged(R.id.campo_distrito)
    protected void onTextChangedDistrito(CharSequence distrito) {
        ComodinValidator.validateDistrito(getTypeUser(), distrito, til_distrito, img_distrito, button_registro);
    }


    public void registerUser(final User user) {
        IRetrofitServiceApi serviceApi = RetrofitAdapter.getClient().create(IRetrofitServiceApi.class);
        Call<ResponseBody> call = serviceApi.registerUser(user);
         ComodinProgressDialog.showProgressBar(getActivity(), R.string.regitrando, R.string.esperar);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                try {
                    if (response.isSuccessful()) {
                        ComodinProgressDialog.finish();
                        Intent i = new Intent(getActivity(), BienvenidoActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("request", new ComodinLoginRequest(ComodinTypeDateLogin.EMAIL, user.getEmail(), user.getPassword()));
                        i.putExtras(bundle);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        getActivity().overridePendingTransition(R.animator.enter, R.animator.exit);
                    } else {
                        ComodinProgressDialog.finish();
                        fixErrors(response);
                    }
                } catch (Exception e) {
                    ComodinAlertDialog.showDialogMaterialInformative(getActivity(), R.string.error, R.string.error_500_alert_dialog_register, android.R.string.ok);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                ComodinProgressDialog.finish();
                ComodinAlertDialog.showDialogMaterialInformative(getActivity(), R.string.error, R.string.error_login, android.R.string.ok);
            }
        });
    }

    public void fixErrors(Response<ResponseBody> response) {
        if (response.code() == 500) {
            ComodinAlertDialog.showDialogMaterialInformative(getActivity(), R.string.error, R.string.error_500_alert_dialog_login, android.R.string.ok);
        } else if (response.code() == 422) {
            setValidateErrors(response);
        }

    }

    public void setValidateErrors(Response<ResponseBody> response) {
        ComodinRegisterErrors comodinRegisterErrors = new ComodinRegisterErrors(response);
        if (comodinRegisterErrors.isErrorCelular()) {
            ComodinUtils.setFieldInvalidateFull(til_celular, img_celular, R.string.celular_existe, 9);
            ComodinValidator.celularValidado = false;
        }

        if (comodinRegisterErrors.isErrorCorreo()) {
            ComodinUtils.setFieldInvalidateFull(til_correo, img_correo, R.string.correo_existe, 40);
            ComodinValidator.correoValidado = false;
        }

        if (comodinRegisterErrors.isErrorRuc()) {
            ComodinUtils.setFieldInvalidateFull(til_ruc, img_ruc, R.string.ruc_existe, 11);
            ComodinValidator.rucvalidado = false;
        }
        ComodinValidator.checkValidation(button_registro, getTypeUser());
    }


}
