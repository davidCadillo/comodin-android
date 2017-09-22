package com.tusueldo.comodin;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.tusueldo.comodin.connections.api.IRetrofitServiceApi;
import com.tusueldo.comodin.connections.api.RetrofitAdapter;
import com.tusueldo.comodin.connections.api.login.ComodinLoginErrors;
import com.tusueldo.comodin.connections.api.login.ComodinLoginRequest;
import com.tusueldo.comodin.connections.api.login.ComodinLoginResponse;
import com.tusueldo.comodin.connections.api.login.ComodinTypeDateLogin;
import com.tusueldo.comodin.ui.ComodinAlertDialog;
import com.tusueldo.comodin.ui.ComodinProgressDialog;
import com.tusueldo.comodin.utils.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginGoogleFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    @BindView(R.id.btn_google) SignInButton btn_google;
    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;
    private ProgressDialog mProgressDialog;

    public LoginGoogleFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_google, container, false);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage(getActivity(), this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        ButterKnife.bind(this, view);
        btn_google.setSize(SignInButton.SIZE_WIDE);
        return view;
    }

   /* @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }*/

    @Override
    public void onResume() {
        super.onResume();
        hideProgressDialog();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
            ComodinLoginRequest request = new ComodinLoginRequest();
            request.setTypeDateLogin(ComodinTypeDateLogin.GOOGLE);
            request.setId(acct.getId());
            request.setEmail(acct.getEmail());
            IRetrofitServiceApi serviceApi = RetrofitAdapter.getClient().create(IRetrofitServiceApi.class);
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
                        sessionManager.setmGoogleApiClient(mGoogleApiClient);
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
                    signOut();
                }

                @Override
                public void onFailure(Call<ComodinLoginResponse> call, Throwable t) {
                    ComodinProgressDialog.finish();
                    ComodinAlertDialog.showDialogMaterialInformative(getContext(), R.string.error, R.string.error_500_alert_dialog_login, android.R.string.ok);

                }
            });
        } else {
            updateUI(false);
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut() {

        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        updateUI(false);
                        // [END_EXCLUDE]
                    }
                });
    }

    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        updateUI(false);
                        // [END_EXCLUDE]
                    }
                });
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    private void updateUI(boolean signedIn) {
        if (signedIn) {
            btn_google.setVisibility(View.GONE);
        } else {
            btn_google.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.btn_google)
    public void onClick(SignInButton v) {
        if (v.getId() == R.id.btn_google) {
            signIn();
        }
    }
}
