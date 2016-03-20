package com.pinq.horizontalfabmenusample;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import javax.xml.datatype.Duration;

import com.pinq.horizontalfabmenu.HelperFab;
import com.pinq.horizontalfabmenu.HorizontalFabMenu;
import com.pinq.horizontalfabmenu.ViewPager;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HorizontalFabMenu menu = (HorizontalFabMenu)findViewById(R.id.menu);

        android.support.v4.view.ViewPager pager = (ViewPager)findViewById(R.id.pager);

        menu.setAdapter(new HorizontalFabMenu.HorizontalFabMenuAdapter() {
            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public int getBackgroundColor(int position) {
                return Color.parseColor("#C70000");
            }

            @Override
            public int getFabSelectedColor(int position) {
                return Color.RED;
            }

            @Override
            public int getFabRippleColor(int position) {
                return Color.LTGRAY;
            }

            @Override
            public Drawable getDrawable(int position) {
                return getResources().getDrawable(R.drawable.ic_public_white_24dp);
            }

            @Override
            public void onClick(int position) {
                Toast.makeText(MainActivity.this,"Page changed",Toast.LENGTH_SHORT).show();
            }
        });



        menu.attachToViewPager((ViewPager) pager);

        menu.setColorAnimation(true, new HorizontalFabMenu.ColorAnimation() {
            @Override
            public int getAnimationColor(int position) {
                return Color.BLACK;
            }
        });

        menu.addHelperFab(this,HelperFab.Location.RIGHT,new HelperFab() {
            @Override
            public int getBackgroundColor() {
                return Color.parseColor("#C70000");
            }

            @Override
            public int getFabSelectedColor() {
                return Color.RED;
            }

            @Override
            public int getFabRippleColor() {
                return Color.LTGRAY;
            }

            @Override
            public Drawable getDrawable() {
                return getResources().getDrawable(R.drawable.ic_public_white_24dp);
            }

            @Override
            public void onClick() {
                Toast.makeText(MainActivity.this,"Helper fab touched", Toast.LENGTH_SHORT).show();
            }
        });

        pager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                TextView view = new TextView(MainActivity.this);
                container.addView(view);
                view.setText("Hello!");

                return view;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return true;
            }
        });



        menu.setWorkingMode(HorizontalFabMenu.WorkingMode.LOLLIPOP);
        menu.setScaleAnimation(true);

        menu.build(this);


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
