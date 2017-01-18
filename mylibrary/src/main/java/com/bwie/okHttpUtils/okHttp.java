package com.bwie.okHttpUtils;


import com.google.gson.Gson;
import java.io.IOException;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by lishaocong on 2016/11/3.
 */


public class okHttp {
    //单例模式
    private static OkHttpClient client;

    public static final MediaType JSON=MediaType.parse("application/json;charset=utf-8 ");
    //构造器私有化
    private okHttp(){}
     public static OkHttpClient getoKHttpclient(){
         if(client==null){
            client=new OkHttpClient();
         }else{
             return client;
         }
         return client;
     }


    //---------------------------------obj异步post-----------------------------
    /**
     * 异步post  参数为对象
     * @param url
     * @param obj
     * @param callback
     */
    public static void postAnsyObj(String url,Object obj,Callback callback){

        postAnsyObj(url,new Gson().toJson(obj),callback);
    }

    //-----------------------------------json异步post---------------------------
    /**
     * 异步post  参数为json
     * @param url
     * @param json
     * @param callback
     */
    public static void postAnsyjson(String url,String json,Callback callback){

        RequestBody body=RequestBody.create(JSON,json);
        Request request=new Request.Builder()
                .url(url)
                .post(body)
                .build();
        getoKHttpclient().newCall(request)
                .enqueue(callback);
    }

    //----------------------------------map异步Post---------------------------
    /**
     * 异步post 参数为Map
     * @param url
     * @param map
     * @param callback
     */
    public static void postAnsyMap(String url, Map<String,String > map,Callback callback){

        FormBody.Builder builder=new FormBody.Builder();
        for (String key:map.keySet()){
            builder.add(key,map.get(key));
        }
        RequestBody body=builder.build();
        Request request=new Request.Builder()
                .url(url)
                .post(body)
                .build();
        getoKHttpclient().newCall(request).enqueue(callback);
    }



    //-------------------------------------异步get----------------------------
    /**
     * 异步get
     * @param url
     * @return
     */
    public static void getAnsy(String url,Callback callback){
        Request request=new Request.Builder()
                .url(url)
                .build();
        getoKHttpclient().newCall(request)
                .enqueue(callback);

    }

    //------------------------------------同步get--------------------------------------
    /**
     * 同步get
     * @param url
     * @return
     */

    //Resquest是OkHttp中的请求，Builder是辅助类，response是OKHttp中的响应
    public static String get(String url){
//        OkHttpClient client=new OkHttpClient();
        Request request= new Request.Builder()
                .url(url)
                .build();

        try {
//            Response res=client.newCall(request)
//                    .execute();
            Call call=getoKHttpclient().newCall(request);
            Response res=call
                    .execute();
            if(res.isSuccessful()){
                return res.body().string();
            }else{
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
