package com.pinq.horizontalfabmenu;

import android.content.Context;
import android.util.AttributeSet;

public class ViewPager extends android.support.v4.view.ViewPager {
    public ViewPager(Context context) {
        super(context);
    }

    public ViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    HorizontalFabMenu menu;

    protected void setHorizontalFabMenu(HorizontalFabMenu menu) {
        this.menu = menu;
    }

    private void init(Context context, AttributeSet attributeSet) {

    }

    @Override
    public void setOnPageChangeListener(final OnPageChangeListener listener) {
        super.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                listener.onPageScrolled(position,positionOffset,positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                if(menu != null) {
                    menu.runAnimation(getContext(),position);
                }
                listener.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                listener.onPageScrollStateChanged(state);
            }
        });
    }
}
