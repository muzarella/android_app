package com.example.sample_project;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SkillsAPIclient {

    public static String base_url = "https://gadsapi.herokuapp.com/" ;

    //retrofit

    public  static Retrofit getClient() {

        HttpLoggingInterceptor httpLoggingInterceptor = new    HttpLoggingInterceptor() ;
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build() ;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit ;
    }

    public static SkillsAPIinterface apIinterface (){
        return getClient().create(SkillsAPIinterface.class) ;
    }

}
