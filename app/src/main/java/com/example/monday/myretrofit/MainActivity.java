package com.example.monday.myretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView tv_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         button=(Button)findViewById(R.id.bt_button);
         tv_text=(TextView)findViewById(R.id.tv_text);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //拦截器
                HttpLoggingInterceptor http=new HttpLoggingInterceptor();
                http.setLevel(HttpLoggingInterceptor.Level.BODY);

                OkHttpClient client = new OkHttpClient.Builder().addInterceptor(http).build();

                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl("http://www.tngou.net/")   //这里用的天狗云的公共api
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build();
                ApiService apiService = retrofit.create(ApiService.class);

                Call<TestModel> call=apiService.getModel(1);
                call.enqueue(new Callback<TestModel>() {
                    @Override
                    public void onResponse(Call<TestModel> call, Response<TestModel> response) {
                        Log.e("TAG","成功");
                        tv_text.setText(response.body().getTngou().get(0).getTitle());
                    }

                    @Override
                    public void onFailure(Call<TestModel> call, Throwable t) {

                    }
                });

            }
        });
    }

}
