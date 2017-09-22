package com.tusueldo.comodin;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.*;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.tusueldo.comodin.connections.api.IRetrofitServiceApi;
import com.tusueldo.comodin.connections.api.RetrofitAdapter;
import com.tusueldo.comodin.connections.api.login.ComodinLoginErrors;
import com.tusueldo.comodin.connections.api.login.ComodinLoginRequest;
import com.tusueldo.comodin.connections.api.login.ComodinLoginResponse;
import com.tusueldo.comodin.connections.api.login.ComodinTypeDateLogin;
import com.tusueldo.comodin.ui.ComodinAlertDialog;
import com.tusueldo.comodin.ui.ComodinProgressDialog;
import com.tusueldo.comodin.utils.SessionManager;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFacebookFragment extends Fragment {

    @BindView(R.id.btn_facebook) LoginButton loginButton;
    CallbackManager callbackManager;

    public LoginFacebookFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_facebook, container, false);
        ButterKnife.bind(this, view);
        loginButton.setFragment(this);
        loginButton.setReadPermissions("email");
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                try {
                                    ComodinLoginRequest request = new ComodinLoginRequest();
                                    request.setTypeDateLogin(ComodinTypeDateLogin.FACEBOOK);
                                    request.setId(object.getString("id"));
                                    request.setEmail(object.getString("email"));
                                    final IRetrofitServiceApi serviceApi = RetrofitAdapter.getClient().create(IRetrofitServiceApi.class);
                                    ComodinProgressDialog.showProgressBar(getActivity(), R.string.iniciando_sesion, R.string.cargando);
                                    Call<ComodinLoginResponse> call = serviceApi.loginUser(request);
                                    call.enqueue(new Callback<ComodinLoginResponse>() {
                                        @Override
                                        public void onResponse(Call<ComodinLoginResponse> call, Response<ComodinLoginResponse> response) {
                                            ComodinLoginResponse loginResponse = response.body();
                                            if (loginResponse != null && loginResponse.getCode() == 200) {
                                                SessionManager sessionManager = SessionManager.getInstance();
                                                sessionManager.setActivity(getActivity());
                                                sessionManager.createSession(loginResponse.getMessage());
                                                Intent i = new Intent(getActivity(), MainActivity.class);
                                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                ComodinProgressDialog.finish();
                                                startActivity(i);
                                                getActivity().overridePendingTransition(R.animator.enter, R.animator.exit);
                                            } else {
                                                ComodinProgressDialog.finish();
                                                ComodinLoginErrors.from(response.body());
                                                ComodinAlertDialog.showDialogMaterialInformative(getActivity(), R.string.error_login, ComodinLoginErrors.showMessageError(), android.R.string.ok);
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<ComodinLoginResponse> call, Throwable t) {
                                            ComodinProgressDialog.finish();
                                            ComodinAlertDialog.showDialogMaterialInformative(getContext(), R.string.error, R.string.error_500_alert_dialog_login, android.R.string.ok);

                                        }
                                    });
                                    LoginManager.getInstance().logOut();

                                } catch (JSONException e) {
                                    Log.d("Email: ", "NADA");
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "email");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getActivity(), "Cancelado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getActivity(), "Error al conectarse", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        Log.d("EMAIL: ", String.valueOf(requestCode));
    }

}
