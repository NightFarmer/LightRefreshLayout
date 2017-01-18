package com.nightfarmer.lightrefreshlayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by zhangfan on 2017/1/18 0018.
 */

class DefaultRefreshHeadProvider implements LightRefreshHeadProvider<DefaultRefreshHeadProvider.HeadViewHolder> {

    @Override
    public DefaultRefreshHeadProvider.HeadViewHolder getHeadView(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.light_refresh_default_head, parent, false);
        return new HeadViewHolder(view);
    }

    @Override
    public void onProgressChange(DefaultRefreshHeadProvider.HeadViewHolder headHolder, float progress, int state) {
        switch (state) {
            case 0: {
                if (progress > 1) {
                    headHolder.tv_label.setText("松开刷新");
                } else {
                    headHolder.tv_label.setText("下拉刷新");
                }
                break;
            }
            case 1: {
                headHolder.tv_label.setText("正在刷新...");
                break;
            }
            case 2: {
                headHolder.tv_label.setText("刷新完成");
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