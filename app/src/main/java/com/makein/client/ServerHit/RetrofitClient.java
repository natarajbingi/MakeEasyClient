package com.makein.client.ServerHit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {

            // HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            //  interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            //OkHttpClient okHttpClient = new OkHttpClient();
            OkHttpClient clientWith60sTimeout = new OkHttpClient()
                    .newBuilder()
                    // .addInterceptor(interceptor)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(clientWith60sTimeout)
                    .build();
        }
        return retrofit;
    }
}
