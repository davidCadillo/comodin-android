package com.tusueldo.comodin.utils;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.tusueldo.comodin.LoginActivity;
import com.tusueldo.comodin.MainActivity;
import com.tusueldo.comodin.R;

public class SessionManager {

    private Activity activity;
    private ComodinSharedPreferences preferences;
    private GoogleApiClient mGoogleApiClient;

    public SessionManager(Activity activity) {
        this.activity = activity;
        preferences = new ComodinSharedPreferences(activity);
    }


    public void logout() {
        preferences.remove("token");
        LoginManager.getInstance().logOut();
        activity.startActivity(new Intent(activity, LoginActivity.class));
        activity.finish();
    }

    public String print() {
        return preferences.read("token", "nulo");
    }

    public void checkSession() {
        if (!preferences.read("token", "nulo").equals("nulo")) {
            Intent i = new Intent(activity, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(i);
            activity.overridePendingTransition(R.animator.enter, R.animator.exit);
        }

    }

    public void createSession(String token) {
        preferences.write("token", token);
    }

    public GoogleApiClient getmGoogleApiClient() {
        return mGoogleApiClient;
    }

    public void setmGoogleApiClient(GoogleApiClient mGoogleApiClient) {
        this.mGoogleApiClient = mGoogleApiClient;
    }

    private void signOutGoogle() {
        if (mGoogleApiClient.isConnected()) {
            Toast.makeText(activity, "Conectado", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(activity, "No Conectado", Toast.LENGTH_LONG).show();
        }

            /*Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
                @Override
                public void onResult(@NonNull Status status) {
                    Toast.makeText(activity, "SALIENDO DE GOOGLE", Toast.LENGTH_LONG).show();
                }
            });*/
    }

}
