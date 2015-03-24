package com.treelzebub.umap.util;

import android.util.Base64;

import com.squareup.okhttp.OkHttpClient;
import com.treelzebub.umap.auth.AccessToken;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Tre Murillo on 3/23/15
 */
public class ServiceGenerator {

    private ServiceGenerator() {
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        return createService(serviceClass, baseUrl, null, null);
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl, String username, String password) {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(baseUrl)
                .setClient(new OkClient(new OkHttpClient()));

        if (username != null && password != null) {
            // concatenate username and password with colon for authentication
            final String credentials = username + ":" + password;

            builder.setRequestInterceptor(new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    // create Base64 encoded string
                    String string = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                    request.addHeader("Accept", "application/json");
                    request.addHeader("Authorization", "Discogs " + string);
                }
            });
        }
        RestAdapter adapter = builder.build();

        return adapter.create(serviceClass);
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl, final AccessToken accessToken) {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(baseUrl)
                .setClient(new OkClient(new OkHttpClient()));

        if (accessToken != null) {
            builder.setRequestInterceptor(new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    request.addHeader("Accept", "application/json");
                    request.addHeader("Authorization", accessToken.getTokenType() + " " + accessToken.getAccessToken());
                }
            });
        }
        RestAdapter adapter = builder.build();

        return adapter.create(serviceClass);
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl, final String token) {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(baseUrl)
                .setClient(new OkClient(new OkHttpClient()));

        if (token != null) {
            builder.setRequestInterceptor(new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    request.addHeader("Accept", "application/json");
                    request.addHeader("Authorization", token);
                }
            });
        }

        RestAdapter adapter = builder.build();
        return adapter.create(serviceClass);
    }

}

