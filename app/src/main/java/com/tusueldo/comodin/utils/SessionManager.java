package com.tusueldo.comodin.utils;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;
import com.facebook.login.LoginManager;
import com.google.android.gms.common.api.GoogleApiClient;
import com.tusueldo.comodin.LoginActivity;
import com.tusueldo.comodin.MainActivity;
import com.tusueldo.comodin.R;
import com.tusueldo.comodin.model.UserJson;

public class SessionManager {

    private Activity activity;
    private ComodinSharedPreferences preferences;
    private GoogleApiClient mGoogleApiClient;
    private static SessionManager sesion;
    private static final String nameSession = "LOGIN";

    public static SessionManager getInstance() {
        if (sesion == null)
            sesion = new SessionManager();
        return sesion;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
        preferences = new ComodinSharedPreferences(activity, nameSession);
    }

    public void print(){
        Toast.makeText(activity, preferences.read("token", "nulo"), Toast.LENGTH_SHORT).show();
    }

    public void logout() {
        preferences.remove("token");
        LoginManager.getInstance().logOut();
        activity.startActivity(new Intent(activity, LoginActivity.class));
        activity.finish();
    }

    public void checkSession() {
        if (!preferences.read("token", "nulo").equals("nulo")) {
            Intent i = new Intent(activity, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(i);
            activity.overridePendingTransition(R.animator.enter, R.animator.exit);
        }
    }

    public UserJson getUser() {
        return new ComodinJWTUtils(preferences.read("token", "nulo")).getUser();
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
