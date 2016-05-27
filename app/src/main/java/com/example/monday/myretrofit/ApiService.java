package com.example.monday.myretrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("tnfs/api/list")
    Call<TestModel> getModel(@Query("id") int id);

}
