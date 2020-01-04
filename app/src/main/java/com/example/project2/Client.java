package com.example.project2;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    public static final String BASE_URL="http://api.themoviedb.org/3/";
    public static Retrofit retrofit = null;

    public static Retrofit getClient(){
        //Gson gson = new GsonBuilder().serializeNulls().create();
        if(retrofit==null)
        {
            //retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}
