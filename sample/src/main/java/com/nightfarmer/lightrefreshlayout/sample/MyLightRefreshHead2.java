package com.nightfarmer.lightrefreshlayout.sample;

import android.animation.ObjectAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.nightfarmer.lightrefreshlayout.LightRefreshHeadProvider;

/**
 * Created by zhangfan on 2017/1/17 0017.
 */

public class MyLightRefreshHead2 implements LightRefreshHeadProvider<MyLightRefreshHead2.HeadViewHolder> {


    @Override
    public MyLightRefreshHead2.HeadViewHolder getHeadView(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_swiperefresh_head2, parent, false);
        return new HeadViewHolder(view);
    }

    ObjectAnimator rotationAnim = null;

    @Override
    public void onProgressChange(MyLightRefreshHead2.HeadViewHolder headHolder, float progress, int state) {
        switch (state) {
            case 0: {
                if (rotationAnim != null) {
                    rotationAnim.cancel();
                    headHolder.imageView.setRotation(0);
//                    rotationAnim.
                }
                break;
            }
            case 1: {
                if (rotationAnim != null && rotationAnim.isRunning()) return;
                rotationAnim = ObjectAnimator.ofFloat(headHolder.imageView, "rotation", 360);
                rotationAnim.setInterpolator(new LinearInterpolator());
                rotationAnim.setRepeatCount(-1);
                rotationAnim.start();

                ObjectAnimator scaleX = ObjectAnimator.ofFloat(headHolder.imageView, "scaleX", 1);
                ObjectAnimator scaleY = ObjectAnimator.ofFloat(headHolder.imageView, "scaleY", 1);
                scaleX.setDuration(200);
                scaleX.setInterpolator(new LinearInterpolator());
                scaleY.setDuration(200);
                scaleY.setInterpolator(new LinearInterpolator());
                scaleX.start();
                scaleY.start();

                break;
            }
            case 2: {
                if (rotationAnim != null) {
                    rotationAnim.cancel();
                    headHolder.imageView.setRotation(0);
                }
            }
        }
        headHolder.headView.setAlpha(progress > 1 ? 1 : progress);
        headHolder.imageView.setScaleX(progress > 1 ? 1 : progress);
        headHolder.imageView.setScaleY(progress > 1 ? 1 : progress);
    }

    class HeadViewHolder extends LightRefreshHeadProvider.HeadViewHolder {
        ImageView imageView;

        HeadViewHolder(View headView) {
            super(headView);
            imageView = (ImageView) headView.findViewById(R.id.image_refresh);
        }
    }
}
