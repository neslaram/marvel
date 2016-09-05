package com.example.neslaram.marvel.data.remote.service;

import com.example.neslaram.marvel.utils.HashUtil;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by neslaram on 04/09/16.
 */
public class AuthInterceptor implements Interceptor {

    private static final String TIMESTAMP_KEY = "ts";
    private static final String APIKEY_KEY = "apikey";
    private static final String HASH_KEY = "hash";

    private final String publicKey;
    private final String privateKey;
    private final long timestamp;


    public AuthInterceptor(String publicKey, String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        String hash = HashUtil.md5(timestamp + privateKey + publicKey);
        Request original = chain.request();

        HttpUrl originalHttpUrl = original.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter(TIMESTAMP_KEY, String.valueOf(this.timestamp))
                .addQueryParameter(APIKEY_KEY, this.publicKey)
                .addQueryParameter(HASH_KEY, hash)
                .build();

        Request.Builder requestBuilder = original.newBuilder()
                .url(url);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
