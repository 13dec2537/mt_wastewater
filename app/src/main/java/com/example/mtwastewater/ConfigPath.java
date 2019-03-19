package com.example.mtwastewater;

import com.example.mtwastewater.Models.Viewer;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ConfigPath {
    @FormUrlEncoded
    @POST("/pdmis/include/sap/components/android/SYSTEM_TEST.php")
    Call<Model> CallMySql(@Field("name") JSONObject name);

    @FormUrlEncoded
    @POST("pdmis/include/sap/components/android/mtwastewater/Login.php")
    Call<Model> CallLogin(@Field("user") String user,
                          @Field("pass") String pass);

    @GET("pdmis/include/sap/components/android/mtwastewater/Viewer.php")
    Call<List<Viewer>> CallViewer();
}
