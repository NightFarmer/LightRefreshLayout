package com.nightfarmer.lightrefreshlayout.sample;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nightfarmer.lightrefreshlayout.LightRefreshLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final LightRefreshLayout lightRefreshLayout = (LightRefreshLayout) findViewById(R.id.lightRefreshLayout);
//        lightRefreshLayout.setHeadViewProvider(new MyLightRefreshHead());

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);


        final Handler handler = new Handler();


        lightRefreshLayout.setOnRefreshListener(new LightRefreshLayout.OnRefreshListener() {
            @Override
            public void onCancel() {
                handler.removeCallbacksAndMessages(null);
            }

            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.dataListSize += 1;
                        adapter.notifyDataSetChanged();
                        lightRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }


}
