package com.example.mtwastewater.interfaces;

import com.example.mtwastewater.Models.Control;
import com.example.mtwastewater.Models.Login;
import com.example.mtwastewater.Models.Viewer;
import com.example.mtwastewater.Models.WasteWater;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Path {
    @FormUrlEncoded
    @POST("/pdmis/include/sap/components/android/SYSTEM_TEST.php")
    Call<Login> CallMySql(@Field("name") JSONObject name);

    @FormUrlEncoded
    @POST("pdmis/include/sap/components/android/mtwastewater/Login.php")
    Call<Login> CallLogin(@Field("user") String user,
                          @Field("pass") String pass);

    @GET("pdmis/include/sap/components/android/mtwastewater/Viewer.php")
    Call<List<Viewer>> CallViewer();

    @FormUrlEncoded
    @POST("pdmis/include/model-mtadmin/components/service/ajax_mtwastewater.php")
    Call<WasteWater> CallCreate(@Field("flag") String flag,@Field("uid") String uid);

    @FormUrlEncoded
    @POST("pdmis/include/sap/components/android/mtwastewater/service.php")
    Call<List<Control>> CallControl(@Field("flag") String flag);
}
