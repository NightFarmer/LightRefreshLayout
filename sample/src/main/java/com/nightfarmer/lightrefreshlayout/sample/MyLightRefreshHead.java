package com.nightfarmer.lightrefreshlayout.sample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nightfarmer.lightrefreshlayout.LightRefreshHeadProvider;

/**
 * Created by zhangfan on 2017/1/17 0017.
 */

public class MyLightRefreshHead implements LightRefreshHeadProvider<MyLightRefreshHead.HeadViewHolder> {


    @Override
    public MyLightRefreshHead.HeadViewHolder getHeadView(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_swiperefresh_head, parent, false);
        return new HeadViewHolder(view);
    }

    @Override
    public void onProgressChange(MyLightRefreshHead.HeadViewHolder headHolder, float progress, int state) {
        switch (state) {
            case 0: {
                headHolder.tv_label.setText("..." + progress);
                break;
            }
            case 1: {
                headHolder.tv_label.setText("○" + progress);
                break;
            }
            case 2: {
                headHolder.tv_label.setText("√" + progress);
            }
        }
        headHolder.headView.setAlpha(progress);
    }

    class HeadViewHolder extends LightRefreshHeadProvider.HeadViewHolder {
        TextView tv_label;

        HeadViewHolder(View headView) {
            super(headView);
            tv_label = (TextView) headView.findViewById(R.id.tv_label);
        }
    }
}
