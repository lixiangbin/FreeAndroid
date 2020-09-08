package com.lxb.freeAndroid.project.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;

public class GsonUtils {
      private static volatile Gson mGson;
  
      public static Gson getGson(){
          if(mGson==null){
              synchronized (GsonUtils.class){
                  if(mGson==null){
                      mGson = new Gson();
                  }
              }
          }
          return mGson;
      }
  
      public static <T> T fromJson(Object data,Class<T> clazz){
          T t = null;
          if(null==data) return null;
          try {
              if(data instanceof String){
                  t = GsonUtils.getGson().fromJson(data.toString(),clazz);
              }else if(data instanceof JsonElement){
                  t = GsonUtils.getGson().fromJson((JsonElement) data,clazz);
              }
          }catch (Exception e){
              e.printStackTrace();
          }
          return t;

      }
  
      public static <T> T fromJson(Object json, Type typeOfT){
         T t = GsonUtils.getGson().fromJson(json.toString(),typeOfT);
         return t;
      }
  
  }