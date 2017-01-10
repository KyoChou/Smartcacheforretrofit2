package com.lizubing.smartcache;


import okhttp3.Request;
import retrofit2.Response;

public interface CachingSystem {
    <T> void addInCache(Response<T> response, byte[] rawResponse);
    <T> Cache getFromCache(Request request);

    public class Cache {
        public Cache(byte[] data, boolean isOverdue) {
            this.data = data;
            this.isOverdue = isOverdue;
        }
        byte[] data;
        boolean isOverdue;
    }
}
