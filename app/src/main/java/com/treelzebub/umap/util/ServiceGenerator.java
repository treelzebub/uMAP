package com.treelzebub.umap.util;

import com.squareup.okhttp.OkHttpClient;
import com.treelzebub.umap.Constants;
import com.treelzebub.umap.api.AuthTools;
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


    // Construct valid request for request token
    public static <S> S createService(Class<S> serviceClass, String baseUrl, final String consumerKey,
                                      final String nonce, final String consumerSecret, final String signatureMethod,
                                      final long timestamp, final String callbackUrl) {


        final String contentHeader = Constants.CONTENT_FORM_URLENCODED;
        final String authHeader =
                "OAuth oauth_consumer_key=\"" + consumerKey + "\", " +
                        "oauth_nonce=\"" + nonce + "\" " +
                        "oauth_signature=\"" + consumerSecret + "&" + "\", " +
                        "oauth_signature_method=\"" + signatureMethod + "\", " +
                        "oauth_timestamp=\"" + Long.toString(timestamp) + "\", " +
                        "oauth_callback=\"" + callbackUrl + "\"";

        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(baseUrl)
                .setClient(new OkClient(new OkHttpClient()))
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("Content-Type", contentHeader);
                        request.addHeader("Authorization", authHeader);
                        request.addHeader("User-Agent", Constants.USER_AGENT);

                    }
                });
        RestAdapter adapter = builder.build();

        return adapter.create(serviceClass);
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
                    String encodedAuthStr = AuthTools.encodeBasicAuthBase64(credentials);
                    request.addHeader("Accept", "application/json");
                    request.addHeader("Authorization", "Discogs " + encodedAuthStr);
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

