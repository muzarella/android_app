package com.example.sample_project;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PostAPIinterface {

    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    // i no longer use post class for the responsebody i use the build in response body
    Call<Void> savePost(@Field("entry.1824927963") String email,
            @Field("entry.1877115667") String firstName,
                        @Field("entry.2006916086") String lastName,
                               @Field("entry.284483984") String github );

    Call<Post>createPost(@Body Post post) ;
}
