package com.example.tonylee.retrofittest;

import com.google.gson.JsonObject;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by tony.lee on 2018-02-19.
 */

public interface RetrofitInterface {
    // GET/POST/DELETE/PUT 메소드들을 인터페이스에 구현하여 사용할 수 있다.
    // 예제 사이트 JSON 데이터 받아오는 곳.
    // http://samples.openweathermap.org/data/2.5/forecast?q=M%C3%BCnchen,DE&appid=b6907d289e10d714a6e88b30761fae22

    @GET("data/{owner}/{repo}")
    // JSON Array를 리턴하므로 List<>가 되었다
    Call<JsonObject> getData(// param 값으로 들어가는 것들이다
                             @Path("owner") String owner,
                             @Path("repo") String repo,
                             @Query("q") String q,
                             @Query("appid") String appid);
}
