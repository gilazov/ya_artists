package com.gilazovdeveloper.artists.model;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ruslan on 21.04.16.
 */
public class Api {

    public static final String BASE_URL = "http://cache-novosibrt03.cdn.yandex.net/download.cdn.yandex.net/mobilization-2016/";

    private static final OkHttpClient CLIENT = new OkHttpClient();

    public static ArtistService getArtistService(){
        return getRetrofit().create(ArtistService.class);
    }

    private static Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(CLIENT)
                .build();
    }


}
