package com.nightfarmer.lightrefreshlayout.sample;

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
        LightRefreshLayout lightRefreshLayout = (LightRefreshLayout) findViewById(R.id.lightRefreshLayout);
        lightRefreshLayout.setHeadViewProvider(new MyLightRefreshHead());

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyRecyclerViewAdapter());
    }


}
