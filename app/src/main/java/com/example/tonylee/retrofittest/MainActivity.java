package com.example.tonylee.retrofittest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit mRetrofit;

    Button mReceiveBtn;
    TextView mTxt;
    RetrofitInterface retrofitInterface;

    private final static String BASE_URL = "http://samples.openweathermap.org/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        mReceiveBtn = (Button) findViewById(R.id.receive_btn);
        mTxt = (TextView) findViewById(R.id.txt);
        mReceiveBtn.setOnClickListener(onReceiveListener);

        retrofitInterface = mRetrofit.create(RetrofitInterface.class);
    }

    public void init() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
    }

    OnClickListener onReceiveListener = new OnClickListener() {
        @Override
        public void onClick(View v) {

            Call<JsonObject> call1 = retrofitInterface.getData("2.5", "weather", "London,uk", "b6907d289e10d714a6e88b30761fae22");

            call1.enqueue(new Callback<JsonObject>() {

                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    //날씨데이터를 받아옴
                    JsonObject object = response.body();
                    if (object != null) {
                        //데이터가 null 이 아니라면 날씨 데이터를 텍스트뷰로 보여주기
                        mTxt.setText(object.toString());
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {

                }
            });
        }
    };
}
