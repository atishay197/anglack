package com.example.crynoz.temp;


import android.util.Log;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.mime.TypedFile;

/**
 * Created by CryNoz on 3/22/16.
 */
public class APIClient {




    private static OCRInterface ocrInterface;
    private static FaceInterface faceInterface;

    public static OCRInterface getOcrAPI() {
        if (ocrInterface == null) {
            RestAdapter adapter = new RestAdapter.Builder()
                    .setEndpoint("https://api.havenondemand.com")
                    .setLogLevel(RestAdapter.LogLevel.FULL).
                            setLog(new RestAdapter.Log() {
                                @Override
                                public void log(String msg) {
                                    Log.i("Retrofit", msg);
                                }
                            })
                    .build();
            ocrInterface = adapter.create(OCRInterface.class);
        }
        return ocrInterface;
    }

    public static FaceInterface getFaceAPI() {
        if (ocrInterface == null) {
            RestAdapter adapter = new RestAdapter.Builder()
                    .setEndpoint("https://api.havenondemand.com")
                    .setLogLevel(RestAdapter.LogLevel.FULL).
                            setLog(new RestAdapter.Log() {
                                @Override
                                public void log(String msg) {
                                    Log.i("Retrofit", msg);
                                }
                            })
                    .build();
            faceInterface = adapter.create(FaceInterface.class);
        }
        return faceInterface;
    }


    public interface OCRInterface{
        @Multipart
        @POST("/1/api/sync/ocrdocument/v1")
        void getOCR(@Part("file")TypedFile file, @Part("apikey")String apikey, Callback<OCR> ocrCallback );
    }

    public interface FaceInterface{
        @Multipart
        @POST("/1/api/sync/detectfaces/v1")
        void getFace(@Part("file")TypedFile file, @Part("apikey")String apikey, Callback<Face> ocrCallback );
    }
}
