package com.amap.scrollview;

import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.SupportMapFragment;
import com.amap.api.maps.TextureSupportMapFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager mViewPager;
    List<String> mTitleList = new ArrayList<String>();
    List<Fragment> mFragments = new ArrayList<Fragment>();
    TextureSupportMapFragment mMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.view_page);

        mTitleList.add("测试1");
        mTitleList.add("地图");
        mTitleList.add("测试2");

        TestFragment testOne = new TestFragment();
        mFragments.add(testOne);

        try {
            MapsInitializer.initialize(this.getApplicationContext());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        mMapFragment = TextureSupportMapFragment.newInstance();
        mFragments.add(mMapFragment);
        AMap amap = (AMap) mMapFragment.getMap();
        amap.setOnMapTouchListener(new AMap.OnMapTouchListener() {
            @Override
            public void onTouch(MotionEvent motionEvent) {
                Log.d("LG","Map onTouch");
                if(motionEvent.getAction() == MotionEvent.ACTION_SCROLL){

                }

            }
        });

        TestTwoFragment testTwo = new TestTwoFragment();
        mFragments.add(testTwo);

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mTitleList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitleList.get(position);
            }
        };
        mViewPager.setAdapter(adapter);
    }
}
