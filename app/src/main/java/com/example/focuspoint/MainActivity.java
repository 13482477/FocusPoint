package com.example.focuspoint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private View focusPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.focusPoint = this.findViewById(R.id.focus_point);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (super.onTouchEvent(event)) {
            return true;
        } else if (MotionEvent.ACTION_DOWN == event.getAction()) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.focusPoint.getLayoutParams();
            int width = this.focusPoint.getWidth();
            int height = this.focusPoint.getHeight();

            layoutParams.leftMargin = ((int) event.getX()) - width / 2;
            layoutParams.topMargin = ((int) event.getY()) - width / 2;
            this.focusPoint.setLayoutParams(layoutParams);
            this.focusPoint.setVisibility(View.VISIBLE);

            ScaleAnimation animation = new ScaleAnimation(1f, 0.7f, 1f, 0.7f, ScaleAnimation.ABSOLUTE, (float) (width / 2), ScaleAnimation.ABSOLUTE, (float) (height / 2));
            animation.setAnimationListener(new FocusPointAnimationListener());
            animation.setDuration(1500);
            this.focusPoint.startAnimation(animation);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        this.focusPoint.setVisibility(View.INVISIBLE);
    }

    private class FocusPointAnimationListener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            MainActivity.this.focusPoint.clearAnimation();
            MainActivity.this.focusPoint.setVisibility(View.GONE);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            MainActivity.this.focusPoint.setVisibility(View.VISIBLE);
        }
    }
}
