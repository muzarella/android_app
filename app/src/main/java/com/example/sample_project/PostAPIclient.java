package com.example.sample_project;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostAPIclient {

    public static final String base_url =  "https://docs.google.com/forms/d/e/" ;
    //retrofit
    public  static Retrofit getClient() {

        HttpLoggingInterceptor httpLoggingInterceptor = new  HttpLoggingInterceptor() ;
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .retryOnConnectionFailure(true)
                .build() ;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit ;
    }

    public static PostAPIinterface postApIinterface (){
        return getClient().create(PostAPIinterface.class) ;
    }
}
