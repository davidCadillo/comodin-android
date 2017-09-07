package com.tusueldo.comodin.connections.ruc;

import android.content.Context;
import android.support.annotation.NonNull;
import com.tusueldo.comodin.model.exceptions.NoConnectivityInternetExeption;
import com.tusueldo.comodin.utils.ComodinUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;


class ConnectivityInterceptor implements Interceptor {
    private Context context;

    ConnectivityInterceptor(Context context) {
        this.context = context;
    }


    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        if (!ComodinUtils.isThereInternetConnection(context)) {
            throw new NoConnectivityInternetExeption();
        }
        Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }
}
