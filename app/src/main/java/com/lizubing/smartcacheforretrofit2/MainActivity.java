package com.lizubing.smartcacheforretrofit2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.lizubing.smartcacheforretrofit2.retrofit.ListData;
import com.lizubing.smartcacheforretrofit2.retrofit.MainFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ImageListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        MainFactory.getInstance().getCategories().enqueue(new Callback<ListData>() {
            @Override
            public void onResponse(Call<ListData> call, Response<ListData> response) {
                Log.d("getCategories", response.raw().toString());
            }

            @Override
            public void onFailure(Call<ListData> call, Throwable t) {
                Log.d("getCategories", t.getMessage());
            }
        });
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.list);
        adapter = new ImageListAdapter();
        listView.setAdapter(adapter);
    }


}
