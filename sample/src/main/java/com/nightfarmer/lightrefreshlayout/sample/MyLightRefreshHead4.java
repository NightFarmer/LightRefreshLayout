package com.nightfarmer.lightrefreshlayout.sample;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.nightfarmer.lightrefreshlayout.LightRefreshHeadProvider;

/**
 * Created by zhangfan on 2017/1/17 0017.
 */

public class MyLightRefreshHead4 implements LightRefreshHeadProvider<MyLightRefreshHead4.HeadViewHolder> {


    @Override
    public MyLightRefreshHead4.HeadViewHolder getHeadView(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_swiperefresh_head4, parent, false);
        return new HeadViewHolder(view);
    }

    ObjectAnimator animRotation = null;
    float preProgress = 0;

    @Override
    public void onProgressChange(MyLightRefreshHead4.HeadViewHolder headHolder, float progress, int state) {
        switch (state) {
            case 0: {
                headHolder.imageView.setImageResource(R.drawable.ptr_flip);
                if (animRotation != null) {
                    animRotation.cancel();
                }
                if (progress > 1 && preProgress < 1) {
                    ObjectAnimator rotation = ObjectAnimator.ofFloat(headHolder.imageView, "rotation", 180);
                    rotation.setInterpolator(new LinearInterpolator());
                    rotation.start();
                }
                if (progress < 1 && preProgress > 1) {
                    ObjectAnimator rotation = ObjectAnimator.ofFloat(headHolder.imageView, "rotation", 0);
                    rotation.setInterpolator(new LinearInterpolator());
                    rotation.start();
                }
                preProgress = progress;
                break;
            }
            case 1: {
                headHolder.imageView.setImageResource(R.drawable.ptr_refreshing);
                if (animRotation != null && animRotation.isRunning()) return;
                animRotation = ObjectAnimator.ofFloat(headHolder.imageView, "rotation", 0, 360);
                animRotation.setDuration(500);
                animRotation.setRepeatCount(-1);
                animRotation.setInterpolator(new LinearInterpolator());
                animRotation.start();
                break;
            }
            case 2: {
                headHolder.imageView.setImageResource(R.drawable.refresh_finished_circle);
                headHolder.imageView.setRotation(0);
                if (animRotation != null) {
                    animRotation.cancel();
                }
            }
        }

    }


    class HeadViewHolder extends LightRefreshHeadProvider.HeadViewHolder {
        ImageView imageView;

        HeadViewHolder(View headView) {
            super(headView);
            imageView = (ImageView) headView.findViewById(R.id.image_refresh);
        }
    }
}
