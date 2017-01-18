package com.nightfarmer.lightrefreshlayout.sample;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.nightfarmer.lightrefreshlayout.LightRefreshHeadProvider;

/**
 * Created by zhangfan on 2017/1/17 0017.
 */

public class MyLightRefreshHead3 implements LightRefreshHeadProvider<MyLightRefreshHead3.HeadViewHolder> {


    @Override
    public MyLightRefreshHead3.HeadViewHolder getHeadView(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_swiperefresh_head3, parent, false);
        return new HeadViewHolder(view);
    }

    ObjectAnimator animRotation = null;
    ObjectAnimator animTranslationX = null;
    ObjectAnimator animTranslationY = null;


    @Override
    public void onProgressChange(MyLightRefreshHead3.HeadViewHolder headHolder, float progress, int state) {
        switch (state) {
            case 0: {
                if (animRotation != null) {
                    animRotation.cancel();
                    headHolder.imageView.setRotation(0);
                    animTranslationX.cancel();
                    animTranslationY.cancel();
                }
                headHolder.imageView.setTranslationX(0);
                headHolder.imageView.setTranslationY(0);
                break;
            }
            case 1: {
                if (animRotation != null && animRotation.isRunning()) return;
                animRotation = ObjectAnimator.ofFloat(headHolder.imageView, "rotation", 360);
                animRotation.setInterpolator(new LinearInterpolator());
                animRotation.setRepeatCount(-1);
                animRotation.start();

                ObjectAnimator scaleX = ObjectAnimator.ofFloat(headHolder.imageView, "scaleX", 1);
                ObjectAnimator scaleY = ObjectAnimator.ofFloat(headHolder.imageView, "scaleY", 1);
                scaleX.setDuration(200);
                scaleX.setInterpolator(new LinearInterpolator());
                scaleY.setDuration(200);
                scaleY.setInterpolator(new LinearInterpolator());
                scaleX.start();
                scaleY.start();

                int measuredWidth = headHolder.headView.getMeasuredWidth();
                int transx = measuredWidth / 2 - headHolder.imageView.getMeasuredWidth() / 2;
                animTranslationX = ObjectAnimator.ofFloat(headHolder.imageView, "translationX", 0, transx, 0, -transx, 0);
                animTranslationX.setDuration(1400);
                animTranslationX.setRepeatCount(-1);
                animTranslationX.setInterpolator(new LinearInterpolator());
                animTranslationX.start();

                int measuredHeight = 150;
                int transy = measuredHeight / 2 - headHolder.imageView.getMeasuredHeight() / 2;
                animTranslationY = ObjectAnimator.ofFloat(headHolder.imageView, "translationY", 0, transy, 0, -transy, 0);
                animTranslationY.setDuration(400);
                animTranslationY.setRepeatCount(-1);
                animTranslationY.setInterpolator(new LinearInterpolator());
                animTranslationY.start();
//                anim = new AnimatorSet();
//                anim.playTogether(translateX, translateY, rotationAnim);
//                anim.start();
                break;
            }
            case 2: {
                if (animRotation != null) {
                    animRotation.cancel();
                    headHolder.imageView.setRotation(0);
                    animTranslationX.cancel();
                    animTranslationY.cancel();
                }
                runRestAnim(headHolder.imageView);
            }
        }
        headHolder.headView.setAlpha(progress > 1 ? 1 : progress);
        headHolder.imageView.setScaleX(progress > 1 ? 1 : progress);
        headHolder.imageView.setScaleY(progress > 1 ? 1 : progress);
    }

    public void runRestAnim(View view) {
        ObjectAnimator translateX = ObjectAnimator.ofFloat(view, "translationX", 0);
        translateX.setDuration(200);
        translateX.setInterpolator(new LinearInterpolator());
        ObjectAnimator translateY = ObjectAnimator.ofFloat(view, "translationY", 0);
        translateY.setDuration(200);
        translateY.setInterpolator(new LinearInterpolator());
        AnimatorSet anim = new AnimatorSet();
        anim.playTogether(translateX, translateY);
        anim.start();
    }

    class HeadViewHolder extends LightRefreshHeadProvider.HeadViewHolder {
        ImageView imageView;

        HeadViewHolder(View headView) {
            super(headView);
            imageView = (ImageView) headView.findViewById(R.id.image_refresh);
        }
    }
}
