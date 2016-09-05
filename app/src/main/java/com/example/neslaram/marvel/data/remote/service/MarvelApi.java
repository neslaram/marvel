package com.example.neslaram.marvel.data.remote.service;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by neslaram on 04/09/16.
 */
public class MarvelApi {

    public static final String API_URL = "http://gateway.marvel.com/v1/public/";
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static MarvelClient API_SERVICE;

    public static MarvelClient getApiService() {

        if (API_SERVICE == null) {
            httpClient.addInterceptor(new AuthInterceptor(
                    "7a5e6fc3bd1a467172e77b7b1d1d12bb",
                    "ec44de489a42e3e6aa23a35c59aa2934bf801ecb"));

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .client(new OkHttpClient())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create());

            Retrofit retrofit = builder.client(httpClient.build())
                    .build();

            API_SERVICE = retrofit.create(MarvelClient.class);
        }
        return API_SERVICE;
    }
}


