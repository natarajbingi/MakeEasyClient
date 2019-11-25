package com.makein.client.ServerHit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroCall {

    //creating our api
    public static Api api;
    public static Retrofit retrofit;

    public static Api getClient() {
        if (retrofit == null && api == null) {
            //The gson builder
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            OkHttpClient clientWith60sTimeout = new OkHttpClient()
                    .newBuilder()
                    // .addInterceptor(interceptor)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(Api.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(clientWith60sTimeout)
                    .build();
            //creating our api
            api = retrofit.create(Api.class);
        }
        return api;
    }


    //creating retrofit object
   /* public static Retrofit retrofit1 = new Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();*/

}
