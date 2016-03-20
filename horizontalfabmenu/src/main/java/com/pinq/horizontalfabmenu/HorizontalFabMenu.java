package com.pinq.horizontalfabmenu;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.view.*;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;

public class HorizontalFabMenu extends LinearLayout {

    protected ArrayList<FloatingActionButton> fabList;
    protected ArrayList<FloatingActionButton> fabListBig;
    protected HorizontalFabMenuAdapter adapter;

    protected FloatingActionButton helperFabLeft;
    protected FloatingActionButton helperFabRight;

    protected boolean isScaleAnimation = false;
    protected boolean isColorAnimation = false;

    protected WorkingMode workingMode = WorkingMode.NORMAL;

    protected ColorAnimation colorAnimation;

    private Context context;

    protected com.pinq.horizontalfabmenu.ViewPager viewPager;

    private RelativeLayout.LayoutParams menuParams;

    public HorizontalFabMenu(Context context) {
        super(context);
        fabList = new ArrayList<>();
        this.context = context;
    }

    public HorizontalFabMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public void attachToViewPager(com.pinq.horizontalfabmenu.ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    public void setAdapter(HorizontalFabMenuAdapter adapter) {
        this.adapter = adapter;
    }

    public void setColorAnimation(boolean isColorAnimation,ColorAnimation colorAnimation) {
        this.isColorAnimation = isColorAnimation;
        this.colorAnimation = colorAnimation;
    }

    public boolean isColorAnimation() {
        return isColorAnimation;
    }

    public void setScaleAnimation(boolean isScaleAnimation) {
        this.isScaleAnimation = isScaleAnimation;
    }

    public boolean isScaleAnimation() {
        return isScaleAnimation;
    }


    public void setWorkingMode(WorkingMode workingMode) {
        this.workingMode = workingMode;
    }


    private void init(Context context, AttributeSet attributeSet) {
        menuParams = new RelativeLayout.LayoutParams(context,attributeSet);
    }

    public void setHelperFabBackgroundColor(HelperFab.Location location,int color){
        if(location == HelperFab.Location.RIGHT) {
            if(helperFabRight != null) {
                helperFabRight.setBackgroundColor(color);
                helperFabRight.setColorNormal(color);
            }
            else
                throw new HelperFabNotSetException("Right Helper Fab not set!");
        }
        else if(location == HelperFab.Location.LEFT) {
            if(helperFabLeft != null) {
                helperFabLeft.setBackgroundColor(color);
                helperFabRight.setColorNormal(color);
            }
            else
                throw new HelperFabNotSetException("Left Helper Fab not set!");
        }
    }

    public void setHelperFabDrawable(HelperFab.Location location,Drawable drawable){
        if(location == HelperFab.Location.RIGHT) {
            if(helperFabRight != null)
                helperFabRight.setImageDrawable(drawable);
            else
                throw new HelperFabNotSetException("Right Helper Fab not set!");
        }
        else if(location == HelperFab.Location.LEFT) {
            if(helperFabLeft != null)
                helperFabLeft.setImageDrawable(drawable);
            else
                throw new HelperFabNotSetException("Left Helper Fab not set!");
        }
    }

    public void setHelperFabSelectedColor(HelperFab.Location location,int color) {
        if(location == HelperFab.Location.RIGHT) {
            if(helperFabRight != null)
                helperFabRight.setColorPressed(color);
            else
                throw new HelperFabNotSetException("Right Helper Fab not set!");
        }
        else if(location == HelperFab.Location.LEFT) {
            if(helperFabLeft != null)
                helperFabLeft.setColorPressed(color);
            else
                throw new HelperFabNotSetException("Left Helper Fab not set!");
        }
    }

    public void setHelperFabRippleColor(HelperFab.Location location,int color) {
        if(location == HelperFab.Location.RIGHT) {
            if(helperFabRight != null)
                helperFabRight.setColorRipple(color);
            else
                throw new HelperFabNotSetException("Right Helper Fab not set!");
        }
        else if(location == HelperFab.Location.LEFT) {
            if(helperFabLeft != null)
                helperFabLeft.setColorRipple(color);
            else
                throw new HelperFabNotSetException("Left Helper Fab not set!");
        }
    }

    public void setHelperFabOnClick(HelperFab.Location location,OnClickListener listener){
        if(location == HelperFab.Location.RIGHT) {
            if(helperFabRight != null)
                helperFabRight.setOnClickListener(listener);
            else
                throw new HelperFabNotSetException("Right Helper Fab not set!");
        }
        else if(location == HelperFab.Location.LEFT) {
            if(helperFabLeft != null)
                helperFabLeft.setOnClickListener(listener);
            else
                throw new HelperFabNotSetException("Left Helper Fab not set!");
        }
    }


    public void addHelperFab(Context context ,HelperFab.Location location,final HelperFab fab) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        final FloatingActionButton newFab = (FloatingActionButton)inflater.inflate(R.layout.fab_layout_normal,null);
        newFab.setBackgroundColor(fab.getBackgroundColor());
        newFab.setColorNormal(fab.getBackgroundColor());
        newFab.setColorPressed(fab.getFabSelectedColor());
        newFab.setColorRipple(fab.getFabRippleColor());
        newFab.setImageDrawable(fab.getDrawable());
        newFab.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                fab.onClick();
            }
        });

        if(location == HelperFab.Location.RIGHT) {
            helperFabRight = newFab;
        }
        else if(location == HelperFab.Location.LEFT) {
            helperFabLeft = newFab;
        }
    }


    public void build(final Context context){
        this.context = context;


        if(adapter == null) {
            throw new AdapterNotSetException("Adapter hasn't set");
        }
        else if(viewPager == null) {
            throw new ViewPagerNotAttached("ViewPager not attached");
        }
        else {
            fabList = new ArrayList<>();

            if(workingMode == WorkingMode.LOLLIPOP)
                fabListBig = new ArrayList<>();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            this.setOrientation(HORIZONTAL);


            menuParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            this.setLayoutParams(menuParams);


            int count = adapter.getCount();

            Log.d("count",count + "");

            viewPager.setHorizontalFabMenu(this);

            viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            if(helperFabLeft != null)
                addView(helperFabLeft);

            for(int i = 0; i < count;i++) {
                final int where = i;
                final FloatingActionButton fab = (FloatingActionButton)inflater.inflate(R.layout.fab_layout,null);
                fab.setBackgroundColor(adapter.getBackgroundColor(where));
                fab.setColorNormal(adapter.getBackgroundColor(where));
                fab.setColorPressed(adapter.getFabSelectedColor(where));
                fab.setColorRipple(adapter.getFabRippleColor(where));
                fab.setImageDrawable(adapter.getDrawable(where));
                fab.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter.onClick(where);
                        viewPager.setCurrentItem(where);
                        runAnimation(context,where);
                    }
                });

                LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(10,2,10,2);
                params.gravity = Gravity.BOTTOM;

                fab.setLayoutParams(params);



                fabList.add(fab);

                addView(fab);

                if(workingMode == WorkingMode.LOLLIPOP) {
                    FloatingActionButton fab2 = (FloatingActionButton)inflater.inflate(R.layout.fab_layout_normal,null);
                    fab2.setBackgroundColor(adapter.getBackgroundColor(where));
                    fab2.setColorNormal(adapter.getBackgroundColor(where));
                    fab2.setColorPressed(adapter.getFabSelectedColor(where));
                    fab2.setColorRipple(adapter.getFabRippleColor(where));
                    fab2.setImageDrawable(adapter.getDrawable(where));
                    fab2.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            adapter.onClick(where);
                            viewPager.setCurrentItem(where);
                            runAnimation(context,where);
                        }
                    });
                    fab2.setVisibility(View.GONE);
                    params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.setMargins(10,2,10,2);
                    params.gravity = Gravity.BOTTOM;

                    fab2.setLayoutParams(params);

                    fabListBig.add(fab2);
                    addView(fab2);
                }
            }


            if(helperFabRight != null)
                addView(helperFabRight);

            runAnimation(context,0);


        }
    }

    protected void runAnimation(Context context,int position) {
        if(isScaleAnimation) {
            if(workingMode == WorkingMode.NORMAL) {
                Log.d("normal","n");
                for (int i = 0; i < fabList.size(); i++) {
                    if (i == position)
                        fabList.get(i).setType(FloatingActionButton.TYPE_NORMAL);
                    else
                        fabList.get(i).setType(FloatingActionButton.TYPE_MINI);
                }
            }
            else if(workingMode == WorkingMode.LOLLIPOP) {
                for (int i =0; i < fabList.size(); i++) {
                    if (i == position) {
                        fabList.get(i).setVisibility(View.GONE);
                        fabListBig.get(i).setVisibility(View.VISIBLE);
                    }
                    else {
                        fabList.get(i).setVisibility(View.VISIBLE);
                        fabListBig.get(i).setVisibility(View.GONE);
                    }
                }
            }
        }

        if(isColorAnimation) {
            for(int i = 0; i < fabList.size();i++) {
                if(i == position) {
                    if(workingMode == WorkingMode.LOLLIPOP)
                        fabListBig.get(i).setColorNormal(colorAnimation.getAnimationColor(i));
                    else
                        fabList.get(i).setColorNormal(colorAnimation.getAnimationColor(i));
                }
                else {
                    if(workingMode == WorkingMode.LOLLIPOP)
                        fabListBig.get(i).setColorNormal(adapter.getBackgroundColor(i));
                    else
                        fabList.get(i).setColorNormal(adapter.getBackgroundColor(i));
                }
            }
        }
    }

    public interface HorizontalFabMenuAdapter {
        public int getCount();

        public int getBackgroundColor(int position);

        public int getFabSelectedColor(int position);

        public int getFabRippleColor(int position);

        public Drawable getDrawable(int position);

        public void onClick(int position);
    }




    public interface ColorAnimation {
        public int getAnimationColor(int position);
    }

    public enum WorkingMode {
        NORMAL,
        LOLLIPOP
    }


    public class AdapterNotSetException extends RuntimeException {
        public AdapterNotSetException(String detailMessage) {
            super(detailMessage);
        }
    }
    public class ViewPagerNotAttached extends RuntimeException {
        public ViewPagerNotAttached(String detailMessage) {
            super(detailMessage);
        }
    }

    public class HelperFabNotSetException extends RuntimeException {
        public HelperFabNotSetException(String detailMessage) {
            super(detailMessage);
        }
    }
}
